package erg.voidcraft.common.item;

import erg.voidcraft.common.init.VoidcraftItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;

public class ItemVoidSword extends SwordItem {

    public ItemVoidSword() {
        super(VoidcraftItems.itemTierVoid, 4, -1.6F, new Item.Properties().tab(ItemGroup.TAB_COMBAT).fireResistant());
    }

}
