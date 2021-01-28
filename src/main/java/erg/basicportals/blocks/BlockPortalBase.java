package erg.basicportals.blocks;

import erg.basicportals.tileentities.TileEntityPortalBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class BlockPortalBase extends Block {

    private static final Vector3d BASE_MIN_CORNER = new Vector3d(0.0, 0.0, 0.0);
    private static final Vector3d BASE_MAX_CORNER = new Vector3d(16.0, 1.0, 16.0);

    private static final VoxelShape SHAPE = Block.makeCuboidShape(BASE_MIN_CORNER.getX(), BASE_MIN_CORNER.getY(), BASE_MIN_CORNER.getZ(),
            BASE_MAX_CORNER.getX(), BASE_MAX_CORNER.getY(), BASE_MAX_CORNER.getZ());


    private static final VoxelShape EMPTY_SPACE = VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), SHAPE, IBooleanFunction.ONLY_FIRST);

    public BlockPortalBase() {
        super(Block.Properties.create(Material.IRON).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3.5f, 3.5f).harvestLevel(1).setRequiresTool());
    }

    @Override
    public BlockRenderType getRenderType(BlockState blockState) {
        return BlockRenderType.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext ctx) {
        return SHAPE;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityPortalBase();
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {

        TileEntity tileEntity = worldIn.getTileEntity(pos);
        TileEntityPortalBase tileEntityPortalBase;
        BlockPos destination = null;

        if (tileEntity instanceof TileEntityPortalBase) {
            tileEntityPortalBase = (TileEntityPortalBase) tileEntity;

            destination = tileEntityPortalBase.getDestinationBlockPos();
        }

        if (destination != null) {
            double x = destination.getX();
            double y = destination.getY();
            double z = destination.getZ();

            if(worldIn.isRemote) worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.0f, 1.0f, false);
            else {
                entityIn.setPositionAndUpdate(x, y + 1, z);
            }
        }
    }

//    @Override
//    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
//
//        if (!(player.getHeldItem(handIn).getItem() instanceof ItemDestinationLodestar) || !player.isSneaking()) return ActionResultType.PASS;
//
//        if (worldIn.isRemote) {
//            BasicPortalsPacketHandler.channel.sendToServer(new PacketSetPortalDestination(player.getHeldItem(handIn), pos));
//            return ActionResultType.SUCCESS;
//        }
//        return ActionResultType.PASS;
//    }

}
