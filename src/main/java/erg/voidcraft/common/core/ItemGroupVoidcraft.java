package erg.voidcraft.common.core;

import erg.voidcraft.common.init.VoidcraftItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.ParametersAreNonnullByDefault;

public class ItemGroupVoidcraft extends ItemGroup {

    public ItemGroupVoidcraft(String label) {
        super(label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(VoidcraftItems.itemVoidCrystal);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void fillItemList(NonNullList<ItemStack> toFill) {
        for(Item item: ForgeRegistries.ITEMS) {
            if(item != null) {
                if(item.getRegistryName().getNamespace().equals("voidcraft")) {
                    item.fillItemCategory(ItemGroup.TAB_SEARCH, toFill);
                }
            }
        }
    }

}
