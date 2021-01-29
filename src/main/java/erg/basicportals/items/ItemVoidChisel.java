package erg.basicportals.items;

import com.sun.org.apache.xpath.internal.operations.String;
import erg.basicportals.exceptions.VoidChiselException;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.world.NoteBlockEvent;

import javax.annotation.Nullable;
import java.util.List;

import static net.minecraft.item.Items.AIR;
import static net.minecraft.util.Direction.*;

public class ItemVoidChisel extends Item {

    public ItemVoidChisel() {
        super((new Item.Properties().group(ItemGroup.TOOLS).maxStackSize(1).maxDamage(250)));
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext ctx) {
        World world = ctx.getWorld();
        BlockPos pos = ctx.getPos();
        Direction direction = ctx.getFace();
        PlayerEntity player = ctx.getPlayer();
        Hand hand = ctx.getHand();
        ItemStack chisel = ctx.getItem();

        BlockPos[] blocks = null;

        try {
            blocks = getBlocksIn(pos, 1, 3, direction);
        } catch(VoidChiselException e) {
            player.sendMessage(new StringTextComponent(e.getMessage()), player.getUniqueID());
        }

        if(blocks != null) {
            harvestBlocks(blocks, world, player, hand);
            chisel.damageItem(1, player, p -> {} );
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }

    private void harvestBlocks(BlockPos[] blocks, World world, PlayerEntity player, Hand hand) {

        for(int i = 0; i < blocks.length; i++) {
            harvestAndRemoveBlock(blocks[i], world, player, hand);
        }

    }

    private void harvestAndRemoveBlock(BlockPos pos, World world, PlayerEntity player, Hand hand) {
        BlockState blockState = world.getBlockState(pos);
        Block block = blockState.getBlock();
        ItemStack itemStack = player.getHeldItem(hand);
        TileEntity te = world.getTileEntity(pos);
        if(te != null) {
            te.remove();
            te = null;
        }
        block.harvestBlock(world, player, pos, blockState, te, itemStack);
        world.setBlockState(pos, Blocks.AIR.getDefaultState());
    }

    private BlockPos[] getBlocksIn(BlockPos origin, int radius, int depth, Direction facing) throws VoidChiselException {

        int diameter = 1 + (radius * 2);
        int facingSurfaceArea = (int)Math.pow(((radius * 2) + 1), 2);
        int numBlocks = facingSurfaceArea * depth;

        BlockPos[] blocks = new BlockPos[numBlocks];

        facing = facing.getOpposite(); //We want the direction the player is facing, not the facing of the block

        Direction horizontalAxis = (
                facing == EAST ? NORTH :
                facing == WEST ? SOUTH :
                facing == NORTH ? WEST : EAST); //EAST when facing south, and default to EAST when facing up or down

        Direction verticalAxis = facing == EAST || facing == WEST || facing == NORTH || facing == SOUTH ? DOWN : NORTH;

        Direction radialAxis = facing;


        BlockPos bottomLeftCorner = origin.offset(horizontalAxis, radius).offset(verticalAxis, radius);

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
                        index = index.offset(horizontalAxis.getOpposite());
                        debugIndex = index;
                    }
                    index = index.offset(horizontalAxis, diameter).offset(verticalAxis.getOpposite());
                }
                index = index.offset(verticalAxis, diameter).offset(radialAxis);
            }
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new VoidChiselException(debugD, debugI, debugN, debugCorner, debugIndex);
        }

        return blocks;

    }

    @Override
    public UseAction getUseAction(ItemStack itemStack) {
        return UseAction.BLOCK;
    }

    @Override
    public void addInformation(ItemStack itemStack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(new StringTextComponent("Right click to remove blocks in a 3x3x3 volume"));
    }

}
