package erg.voidcraft.common.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class AbstractLodestar extends Item {

    protected AbstractLodestar(Item.Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        CompoundNBT tag = stack.getTag();
        if(tag == null) return false;
        return tag.contains("destinationBlockPos");
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext ctx) {
        PlayerEntity player = ctx.getPlayer();
        ItemStack item = ctx.getItem();
        BlockPos pos = ctx.getPos();
        World world = ctx.getWorld();


        if (!(item.getItem() instanceof AbstractLodestar)) {
            return ActionResultType.PASS;
        }

        if (!world.isRemote) {

            CompoundNBT parent = item.getTag();
            CompoundNBT child = new CompoundNBT();

            if (parent == null) parent = new CompoundNBT();

            child.putInt("x", pos.getX());
            child.putInt("y", pos.getY());
            child.putInt("z", pos.getZ());

            parent.put("destinationBlockPos", child);

            parent.putString("dimension", world.getDimensionKey().getLocation().toString());

            item.setTag(parent);
            return ActionResultType.SUCCESS;

        }

        return ActionResultType.PASS;
    }

    @Override
    public UseAction getUseAction(ItemStack itemStack) {
        return UseAction.BLOCK;
    }

    @Override
    public void addInformation(ItemStack itemStack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(new StringTextComponent("Right click on a block to store its position"));
        CompoundNBT tag = itemStack.getTag();
        if(tag != null && tag.contains("destinationBlockPos") && tag.contains("dimension")) {
            CompoundNBT blockPosTag = tag.getCompound("destinationBlockPos");
            tooltip.add(new StringTextComponent(""));
            tooltip.add(new StringTextComponent("Stored location:"));
            tooltip.add(new StringTextComponent("X=" + blockPosTag.getInt("x")));
            tooltip.add(new StringTextComponent("Y=" + blockPosTag.getInt("y")));
            tooltip.add(new StringTextComponent("Z=" + blockPosTag.getInt("z")));
        }
    }

}
