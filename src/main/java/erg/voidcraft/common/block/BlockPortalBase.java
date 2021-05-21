package erg.voidcraft.common.block;

import erg.voidcraft.common.inventory.InventoryPortalBaseContents;
import erg.voidcraft.common.item.ItemDestinationLodestar;
import erg.voidcraft.common.tile.TilePortalBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class BlockPortalBase extends ContainerBlock {

//    private static final Vector3d BASE_MIN_CORNER = new Vector3d(0.0, 0.0, 0.0);
//    private static final Vector3d BASE_MAX_CORNER = new Vector3d(16.0, 1.0, 16.0);
//
//    private static final VoxelShape SHAPE = Block.makeCuboidShape(BASE_MIN_CORNER.getX(), BASE_MIN_CORNER.getY(), BASE_MIN_CORNER.getZ(),
//            BASE_MAX_CORNER.getX(), BASE_MAX_CORNER.getY(), BASE_MAX_CORNER.getZ());
//
//
//    private static final VoxelShape EMPTY_SPACE = VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), SHAPE, IBooleanFunction.ONLY_FIRST);

    public BlockPortalBase() {
        super(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.5f, 3.5f).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool());
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
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return createNewTileEntity(world);
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
        BlockPos destination = null;

        if (tileEntity instanceof TilePortalBase) {
            tilePortalBase = (TilePortalBase) tileEntity;

            InventoryPortalBaseContents contents = tilePortalBase.getContents();

            ItemStack item = contents.getStackInSlot(0);

            if(item.getItem() instanceof ItemDestinationLodestar) {
                CompoundNBT tag = item.getTag();
                if(tag != null && tag.contains("destinationBlockPos")) {
                    CompoundNBT posTag = tag.getCompound("destinationBlockPos");
                    double x = posTag.getInt("x");
                    double y = posTag.getInt("y");
                    double z = posTag.getInt("z");

                    destination = new BlockPos(x, y, z);
                }
            }
        }

        if (destination != null) {
            double x = destination.getX();
            double y = destination.getY();
            double z = destination.getZ();

            if(worldIn.isRemote) worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.0f, 1.0f, false);
            else {
                entityIn.setPositionAndUpdate(x + 0.5, y + 1, z + 0.5);
            }
        }
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new TilePortalBase();
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
