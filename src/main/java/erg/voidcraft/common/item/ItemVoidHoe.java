package erg.voidcraft.common.item;

import erg.voidcraft.common.init.VoidcraftItems;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemVoidHoe extends HoeItem {

    public ItemVoidHoe() {
        super(VoidcraftItems.itemTierVoid, -4, 0.0F, new Item.Properties().tab(ItemGroup.TAB_TOOLS).fireResistant());
    }

}
