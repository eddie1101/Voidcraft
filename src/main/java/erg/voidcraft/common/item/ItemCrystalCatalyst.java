package erg.voidcraft.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemCrystalCatalyst extends Item {

    public ItemCrystalCatalyst() {
        super(new Item.Properties().tab(ItemGroup.TAB_MISC).stacksTo(64));
    }

}
