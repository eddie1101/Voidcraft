package erg.voidcraft.common.block;

import erg.voidcraft.common.init.VoidcraftPacketHandler;
import erg.voidcraft.common.inventory.InventoryPortalBaseContents;
import erg.voidcraft.common.item.AbstractLodestar;
import erg.voidcraft.common.item.ItemDestinationLodestar;
import erg.voidcraft.common.item.ItemDimensionalLodestar;
import erg.voidcraft.common.network.PacketSpawnTeleportParticles;
import erg.voidcraft.common.particle.ParticleDataMiasma;
import erg.voidcraft.common.tile.TilePortalBase;
import erg.voidcraft.common.util.SetBlockStateFlag;
import erg.voidcraft.common.world.teleporter.VoidcraftTeleporter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.PacketDistributor;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.Random;

public class BlockPortalBase extends ContainerBlock {

    private static final BooleanProperty POWERED = BooleanProperty.create("powered");
    private final VoidcraftTeleporter teleporter;

    public BlockPortalBase() {
        super(ContainerBlock.Properties.create(Material.ROCK).hardnessAndResistance(3.5f, 3.5f).harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool());
        this.teleporter = new VoidcraftTeleporter();
    }

    @Override
    public BlockRenderType getRenderType(BlockState iBlockState) {
        return BlockRenderType.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.fullCube();
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext ctx) {
        return this.getDefaultState().with(POWERED, false);
    }

    @Override
    public boolean canConnectRedstone(BlockState state, IBlockReader world, BlockPos pos, @Nullable Direction direction) {
        return false;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return createNewTileEntity(world);
    }

    @Override
    public void neighborChanged(BlockState currentState, World world, BlockPos pos, Block block, BlockPos from, boolean moving) {
        BlockState newState = getPower(world, pos, currentState);
        if (newState != currentState) {
            final int FLAGS = SetBlockStateFlag.get(SetBlockStateFlag.BLOCK_UPDATE, SetBlockStateFlag.SEND_TO_CLIENTS);
            world.setBlockState(pos, newState, FLAGS);
        }
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(world, pos, state, placer, stack);
        BlockState newState = getPower(world, pos, state);
        final int FLAGS = SetBlockStateFlag.get(SetBlockStateFlag.BLOCK_UPDATE, SetBlockStateFlag.SEND_TO_CLIENTS);
        world.setBlockState(pos, newState, FLAGS);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult) {
        if(worldIn.isRemote) return ActionResultType.SUCCESS;

        INamedContainerProvider namedContainerProvider = this.getContainer(state, worldIn, pos);
        if(namedContainerProvider != null) {
            if(!(player instanceof ServerPlayerEntity)) return ActionResultType.FAIL;
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)player;
            NetworkHooks.openGui(serverPlayerEntity, namedContainerProvider, packetBuffer -> {});
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public void onReplaced(BlockState state, World world, BlockPos blockPos, BlockState newState, boolean isMoving) {
        if(state.getBlock() != newState.getBlock()) {
            TileEntity te = world.getTileEntity(blockPos);
            if(te instanceof TilePortalBase) {
                TilePortalBase tilePortalBase = (TilePortalBase) te;
                tilePortalBase.dropAllContents(world, blockPos);
            }
            super.onReplaced(state, world, blockPos, newState, isMoving);
        }
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        TilePortalBase tilePortalBase;

        BlockState state = worldIn.getBlockState(pos);

        if (tileEntity instanceof TilePortalBase && !state.get(POWERED) && !entityIn.isCrouching()) {
            tilePortalBase = (TilePortalBase) tileEntity;

            InventoryPortalBaseContents contents = tilePortalBase.getContents();

            ItemStack item = contents.getStackInSlot(0);

            if(item.getItem() instanceof AbstractLodestar) {
                CompoundNBT tag = item.getTag();
                if(tag != null && tag.contains("destinationBlockPos") && tag.contains("dimension")) {

                    String dimension = tag.getString("dimension").split(":")[1];
                    RegistryKey<World> dimensionKey = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation(dimension));

                    if(worldIn instanceof ServerWorld) {

                        ServerWorld destinationWorld = entityIn.getServer().getWorld(dimensionKey);

                        CompoundNBT posTag = tag.getCompound("destinationBlockPos");
                        double x = posTag.getInt("x");
                        double y = posTag.getInt("y");
                        double z = posTag.getInt("z");

//                      this logic could probably be condensed but im too tired right now
                        if (item.getItem() instanceof ItemDimensionalLodestar) {
                            if(!destinationWorld.getDimensionKey().getLocation().toString().equals(worldIn.getDimensionKey().getLocation().toString())) {
                                entityIn.changeDimension(destinationWorld, teleporter);
                            }
                            destinationWorld.getChunk(new BlockPos(x, y, z));
                            entityIn.setPositionAndUpdate(x + 0.5, y + 1, z + 0.5);
                            spawnParticles(destinationWorld, new BlockPos(x, y, z));
                            worldIn.playSound(null, pos, SoundEvents.ENTITY_FIREWORK_ROCKET_BLAST, SoundCategory.PLAYERS, 1.0f, 1.0f);
                            worldIn.playSound(null, new BlockPos(x, y, z), SoundEvents.ENTITY_FIREWORK_ROCKET_BLAST, SoundCategory.PLAYERS, 1.0f, 1.0f);
                        } else if (destinationWorld.getDimensionKey().getLocation().toString().equals(worldIn.getDimensionKey().getLocation().toString()) && item.getItem() instanceof ItemDestinationLodestar) {
                            destinationWorld.getChunk(new BlockPos(x, y, z));
                            entityIn.setPositionAndUpdate(x + 0.5, y + 1, z + 0.5);
                            spawnParticles(destinationWorld, new BlockPos(x, y, z));
                            worldIn.playSound(null, pos, SoundEvents.ENTITY_FIREWORK_ROCKET_BLAST, SoundCategory.PLAYERS, 1.0f, 1.0f);
                            worldIn.playSound(null, new BlockPos(x, y, z), SoundEvents.ENTITY_FIREWORK_ROCKET_BLAST, SoundCategory.PLAYERS, 1.0f, 1.0f);
                        }
                    }
                }
            }
        }
    }

    private void spawnParticles(World world, BlockPos pos) {
        if(!world.isRemote) {
            ResourceLocation worldRL = world.getDimensionKey().getLocation();

            PacketSpawnTeleportParticles packet = new PacketSpawnTeleportParticles(worldRL, pos);
            VoidcraftPacketHandler.channel.send(PacketDistributor.DIMENSION.with(() -> world.getDimensionKey()), packet);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {

        if(world.isRemote && !state.get(POWERED)) {

            double xpos = pos.getX() + rand.nextDouble();
            double ypos = pos.getY() + 1;
            double zpos = pos.getZ() + rand.nextDouble();

            double xvel = (rand.nextDouble() - 0.5) / 50d;
            double yvel = 0.025d;
            double zvel = (rand.nextDouble() - 0.5) / 50d;

            final boolean IGNORE_RANGE_CHECK = false;
            final double PERCENT_CHANCE = 50;

            if(rand.nextDouble() <= PERCENT_CHANCE / 100d) {

                Color tint = new Color(0.5f, 0.5f, 0.5f);
                final double MIN_DIAMETER = 0.05;
                final double MAX_DIAMETER = 0.40;
                double diameter = MIN_DIAMETER + (MAX_DIAMETER - MIN_DIAMETER) * rand.nextDouble();

                ParticleDataMiasma miasma = new ParticleDataMiasma(tint, diameter);

                world.addParticle(miasma, IGNORE_RANGE_CHECK,
                        xpos, ypos, zpos, xvel, yvel, zvel);
            }

        }

    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new TilePortalBase();
    }

    private BlockState getPower(World world, BlockPos pos, BlockState state) {
        boolean powered = world.isBlockPowered(pos);
        return state.with(POWERED, powered);
    }

//    @Override
//    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
//
//        if (!(player.getHeldItem(handIn).getItem() instanceof ItemDestinationLodestar) || !player.isSneaking()) return ActionResultType.PASS;
//
//        if (worldIn.isRemote) {
//            voidcraftPacketHandler.channel.sendToServer(new PacketSetPortalDestination(player.getHeldItem(handIn), pos));
//            return ActionResultType.SUCCESS;
//        }
//        return ActionResultType.PASS;
//    }

}
