package erg.voidcraft.common.block;

import erg.voidcraft.common.init.VoidcraftPacketHandler;
import erg.voidcraft.common.inventory.InventoryPortalBaseContents;
import erg.voidcraft.common.inventory.container.provider.PortalBaseContainerProvider;
import erg.voidcraft.common.item.AbstractLodestar;
import erg.voidcraft.common.item.ItemDestinationLodestar;
import erg.voidcraft.common.item.ItemDimensionalLodestar;
import erg.voidcraft.common.network.PacketSpawnTeleportParticles;
import erg.voidcraft.common.particle.ParticleDataMiasma;
import erg.voidcraft.common.tile.TilePortalBase;
import erg.voidcraft.common.tile.TilePortalBase2;
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
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.PacketDistributor;
import org.apache.logging.log4j.core.jmx.Server;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.Random;

public class BlockPortalBase extends ContainerBlock {

    public static final BooleanProperty POWERED = BooleanProperty.create("powered");
    public static final BooleanProperty INVERTED = BooleanProperty.create("inverted");
    private final VoidcraftTeleporter teleporter;

    public BlockPortalBase() {
        super(ContainerBlock.Properties.of(Material.STONE).strength(3.5f, 3.5f).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops());
        this.teleporter = new VoidcraftTeleporter();
    }

    @Override
    public BlockRenderType getRenderShape(BlockState iBlockState) {
        return BlockRenderType.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.block();
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(POWERED).add(INVERTED);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext ctx) {
        return this.defaultBlockState().setValue(POWERED, false).setValue(INVERTED, false);
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
        return newBlockEntity(world);
    }

    @Override
    public void neighborChanged(BlockState currentState, World world, BlockPos pos, Block block, BlockPos from, boolean moving) {
        BlockState newState = getPower(world, pos, currentState);
        if (newState != currentState) {
            final int FLAGS = SetBlockStateFlag.get(SetBlockStateFlag.BLOCK_UPDATE, SetBlockStateFlag.SEND_TO_CLIENTS);
            world.setBlock(pos, newState, FLAGS);
        }
    }

    @Override
    public void setPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(world, pos, state, placer, stack);
        BlockState newState = getPower(world, pos, state);
        final int FLAGS = SetBlockStateFlag.get(SetBlockStateFlag.BLOCK_UPDATE, SetBlockStateFlag.SEND_TO_CLIENTS);
        world.setBlock(pos, newState, FLAGS);
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult) {
        if(worldIn.isClientSide) return ActionResultType.SUCCESS;

        TileEntity te = worldIn.getBlockEntity(pos);

        if(te != null) {
            INamedContainerProvider containerProvider = new PortalBaseContainerProvider(worldIn, pos);
            NetworkHooks.openGui((ServerPlayerEntity) player, containerProvider, te.getBlockPos());
        }

//        INamedContainerProvider namedContainerProvider = this.getMenuProvider(state, worldIn, pos);
//        if(namedContainerProvider != null) {
//            if(!(player instanceof ServerPlayerEntity)) return ActionResultType.FAIL;
//            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)player;
//            NetworkHooks.openGui(serverPlayerEntity, namedContainerProvider, pos);
//        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public void onRemove(BlockState state, World world, BlockPos blockPos, BlockState newState, boolean isMoving) {
        if(state.getBlock() != newState.getBlock()) {
            TileEntity te = world.getBlockEntity(blockPos);
            if(te instanceof TilePortalBase) {
                TilePortalBase tilePortalBase = (TilePortalBase) te;
                tilePortalBase.dropAllContents(world, blockPos);
            }
            super.onRemove(state, world, blockPos, newState, isMoving);
        }
    }

    @Override
    public void stepOn(World worldIn, BlockPos pos, Entity entityIn) {

        TileEntity tileEntity = worldIn.getBlockEntity(pos);
        TilePortalBase tilePortalBase;

        BlockState state = worldIn.getBlockState(pos);

        boolean active = state.getValue(INVERTED) ? state.getValue(POWERED) : !state.getValue(POWERED);

        if (tileEntity instanceof TilePortalBase && active && !entityIn.isCrouching()) {
            tilePortalBase = (TilePortalBase) tileEntity;

            InventoryPortalBaseContents contents = tilePortalBase.getContents();

            ItemStack item = contents.getItem(0);

            if(item.getItem() instanceof AbstractLodestar) {
                CompoundNBT tag = item.getTag();
                if(tag != null && tag.contains("destinationBlockPos") && tag.contains("dimension")) {

                    String dimension = tag.getString("dimension").split(":")[1];
                    RegistryKey<World> dimensionKey = RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(dimension));

                    if(worldIn instanceof ServerWorld) {

                        ServerWorld destinationWorld = entityIn.getServer().getLevel(dimensionKey);

                        CompoundNBT posTag = tag.getCompound("destinationBlockPos");
                        double x = posTag.getInt("x");
                        double y = posTag.getInt("y");
                        double z = posTag.getInt("z");

                        BlockPos destination = new BlockPos(x, y, z);

                        if (item.getItem() instanceof ItemDimensionalLodestar) {
                            teleport(destinationWorld, worldIn, entityIn, pos, destination, true);
                        } else if (item.getItem() instanceof ItemDestinationLodestar && destinationWorld.dimension().equals(worldIn.dimension())) {
                            teleport(destinationWorld, worldIn, entityIn, pos, destination, false);
                        }
                    }
                }
            }
        }
    }

    protected void teleport(ServerWorld destinationWorld, World thisWorld, Entity entity, BlockPos thisBlock, BlockPos destination, boolean canChangeDimensions) {
        if(canChangeDimensions && !destinationWorld.dimension().location().toString().equals(thisWorld.dimension().location().toString())) {
            entity.changeDimension(destinationWorld, teleporter);
        }
        destinationWorld.getChunk(destination);
        entity.teleportTo(destination.getX() + 0.5f, destination.getY() + 1f, destination.getZ() + 0.5f);
        spawnParticles(destinationWorld, destination);
        if(entity instanceof ServerPlayerEntity) {
            spawnParticles(destinationWorld, destination, (ServerPlayerEntity) entity);
        }
        playSounds(thisWorld, destinationWorld, thisBlock, destination);
    }

    protected void playSounds(World thisWorld, World destWorld, BlockPos thisBlock, BlockPos destBlock) {
        thisWorld.playSound(null, thisBlock, SoundEvents.FIREWORK_ROCKET_BLAST, SoundCategory.PLAYERS, 1.0f, 1.0f);
        destWorld.playSound(null, destBlock, SoundEvents.FIREWORK_ROCKET_BLAST, SoundCategory.PLAYERS, 1.0f, 1.0f);
    }

    protected void spawnParticles(World world, BlockPos pos) {
        if(!world.isClientSide) {
            ResourceLocation worldRL = world.dimension().location();
            PacketSpawnTeleportParticles packet = new PacketSpawnTeleportParticles(worldRL, pos);
            VoidcraftPacketHandler.channel.send(PacketDistributor.DIMENSION.with(() -> world.dimension()), packet);
        }
    }

    protected void spawnParticles(World world, BlockPos pos, ServerPlayerEntity player) {
        if(!world.isClientSide) {
            ResourceLocation worldRL = world.dimension().location();
            PacketSpawnTeleportParticles packet = new PacketSpawnTeleportParticles(worldRL, pos);
            VoidcraftPacketHandler.channel.send(PacketDistributor.PLAYER.with(() -> player), packet);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {

        if(world.isClientSide && !state.getValue(POWERED)) {

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
    public TileEntity newBlockEntity(IBlockReader worldIn) {
        return new TilePortalBase2();
    }

    private BlockState getPower(World world, BlockPos pos, BlockState state) {
        boolean powered = world.hasNeighborSignal(pos);
        return state.setValue(POWERED, powered);
    }

}
