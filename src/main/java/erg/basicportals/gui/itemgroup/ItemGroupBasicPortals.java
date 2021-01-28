package erg.basicportals.gui.itemgroup;

import erg.basicportals.StartupCommon;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.NonNullList;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.ParametersAreNonnullByDefault;

public class ItemGroupBasicPortals extends ItemGroup {

    public ItemGroupBasicPortals(String label) {
        super(label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(StartupCommon.itemVoidCrystal);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void fill(NonNullList<ItemStack> toFill) {
        for(Item item: ForgeRegistries.ITEMS) {
            if(item != null) {
                if(item.getRegistryName().getNamespace().equals("basicportals")) {
                    item.fillItemGroup(ItemGroup.SEARCH, toFill);
                }
            }
        }
    }

}
