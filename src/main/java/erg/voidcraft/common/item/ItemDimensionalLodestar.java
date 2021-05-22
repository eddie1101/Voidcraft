package erg.voidcraft.common.item;

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

public class ItemDimensionalLodestar extends AbstractLodestar {

    public ItemDimensionalLodestar() {
        super(new Item.Properties().group(ItemGroup.MISC).maxStackSize(1));
    }

    @Override
    public void addInformation(ItemStack itemStack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {

        super.addInformation(itemStack, world, tooltip, flag);

        tooltip.set(1, new StringTextComponent("Right click on a block to store its position and dimension."));
        CompoundNBT tag = itemStack.getTag();
        if(tag != null && tag.contains("destinationBlockPos") && tag.contains("dimension")) {

            String dimension = tag.getString("dimension").split(":")[1];

            if(dimension.equals("the_end")) {
                dimension = "The End";
            }else if(dimension.equals("the_nether")) {
                dimension = "Hell";
            }else if(dimension.equals("overworld")) {
                dimension = "Overworld";
            }

            tooltip.add(new StringTextComponent("Dimension=" + dimension));
        }
    }

}
