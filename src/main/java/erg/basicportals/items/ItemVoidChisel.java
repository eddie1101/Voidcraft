package erg.basicportals.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemVoidChisel extends Item {

    public ItemVoidChisel() {
        super((new Item.Properties().group(ItemGroup.TOOLS).maxStackSize(1).maxDamage(250)));
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext ctx) {
        World world = ctx.getWorld();
        BlockPos pos = ctx.getPos();
        BlockState blockState = world.getBlockState(pos);
        world.setBlockState(pos, Blocks.AIR.getDefaultState());
        TileEntity te = world.getTileEntity(pos);
        if(te != null) {
            te.remove();
        }
        world.getBlockState(pos).getBlock().harvestBlock(world, ctx.getPlayer(), pos, blockState, null, ctx.getPlayer().getHeldItem(ctx.getHand()));
        return ActionResultType.SUCCESS;
    }

    @Override
    public UseAction getUseAction(ItemStack itemStack) {
        return UseAction.BLOCK;
    }
}
