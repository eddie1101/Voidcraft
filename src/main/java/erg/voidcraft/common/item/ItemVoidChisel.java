package erg.voidcraft.common.item;

import erg.voidcraft.exceptions.VoidChiselException;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ContainerBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.IContainerProvider;
import net.minecraft.item.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static net.minecraft.util.Direction.*;

public class ItemVoidChisel extends Item {

    public ItemVoidChisel() {
        super((new Item.Properties().tab(ItemGroup.TAB_TOOLS).stacksTo(1).durability(250)));
    }

    @Override
    public ActionResultType useOn(ItemUseContext ctx) {
        World world = ctx.getLevel();
        BlockPos pos = ctx.getClickedPos();
        Direction direction = ctx.getClickedFace();
        PlayerEntity player = ctx.getPlayer();
        Hand hand = ctx.getHand();
        ItemStack chisel = ctx.getItemInHand();

        BlockPos[] blocks = null;

        if(!world.isClientSide) {

            try {
                blocks = getBlocksIn(pos, 1, 3, direction);
            } catch (VoidChiselException e) {
                player.sendMessage(new StringTextComponent(e.getMessage()), player.getUUID());
            }

            if (blocks != null) {
                harvestAndRemoveBlocks(blocks, world, player, hand);
                chisel.hurtAndBreak(1, player, p -> {
                });
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.PASS;
    }

    private void harvestAndRemoveBlocks(BlockPos[] blocks, World world, PlayerEntity player, Hand hand) {
        for (int i = 0; i < blocks.length; i++) {
            BlockPos pos = blocks[i];
            BlockState blockState = world.getBlockState(pos);
            Block block = blockState.getBlock();
            ItemStack itemStack = player.getItemInHand(hand);
            TileEntity te = world.getBlockEntity(pos);

            world.destroyBlock(pos, true);

            if (te != null) {
                te.setRemoved();
            }
        }
    }

    private BlockPos[] getBlocksIn(BlockPos origin, int radius, int depth, Direction facing) throws VoidChiselException {

        int diameter = 1 + (radius * 2);
        int facingSurfaceArea = diameter * diameter;
        int numBlocks = facingSurfaceArea * depth;

        BlockPos[] blocks = new BlockPos[numBlocks];

        facing = facing.getOpposite(); //We want the direction the player is facing, not the facing of the block

        Direction horizontalAxis = (
                facing == EAST ? NORTH :
                facing == WEST ? SOUTH :
                facing == NORTH ? WEST : EAST); //EAST when facing south, and default to EAST when facing up or down

        Direction verticalAxis = facing == EAST || facing == WEST || facing == NORTH || facing == SOUTH ? DOWN : NORTH;

        Direction radialAxis = facing;

        BlockPos bottomLeftCorner = origin.relative(horizontalAxis, radius).relative(verticalAxis, radius);

        int debugD = 0;
        int debugI = 0;
        int debugN = 0;
        BlockPos debugCorner = bottomLeftCorner;
        BlockPos debugIndex = null;

        try {
            BlockPos index = new BlockPos(bottomLeftCorner);
            debugIndex = index;
            for (int d = 0; d < depth; d++) {
                debugD = d;
                for (int i = 0; i < diameter; i++) {
                    debugI = i;
                    for (int n = 0; n < diameter; n++) {
                        debugN = n;
                        blocks[d * facingSurfaceArea + ((i % facingSurfaceArea) * diameter) + n] = index;
                        index = index.relative(horizontalAxis.getOpposite());
                        debugIndex = index;
                    }
                    index = index.relative(horizontalAxis, diameter).relative(verticalAxis.getOpposite());
                }
                index = index.relative(verticalAxis, diameter).relative(radialAxis);
            }
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new VoidChiselException(debugD, debugI, debugN, debugCorner, debugIndex);
        }

        return blocks;

    }

    @Override
    public UseAction getUseAnimation(ItemStack itemStack) {
        return UseAction.BLOCK;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(new StringTextComponent("Right click to remove blocks in a 3x3x3 volume"));
    }

}
