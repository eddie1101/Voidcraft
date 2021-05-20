package erg.voidcraft.common.item;

import erg.voidcraft.common.network.PacketSetLodestarDestination;
import erg.voidcraft.common.network.VoidcraftPacketHandler;
import erg.voidcraft.common.network.PacketSetPortalDestination;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemDestinationLodestar extends Item {

    public ItemDestinationLodestar() {
        super(new Item.Properties().group(ItemGroup.MISC).maxStackSize(1));
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


        if (!(item.getItem() instanceof ItemDestinationLodestar)) {
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
        if(tag != null && tag.contains("destinationBlockPos")) {
            CompoundNBT blockPosTag = tag.getCompound("destinationBlockPos");
            tooltip.add(new StringTextComponent("\n"));
            tooltip.add(new StringTextComponent("Stored location:"));
            tooltip.add(new StringTextComponent("X=" + blockPosTag.getInt("x")));
            tooltip.add(new StringTextComponent("Y=" + blockPosTag.getInt("y")));
            tooltip.add(new StringTextComponent("Z=" + blockPosTag.getInt("z")));
        }
    }

}
