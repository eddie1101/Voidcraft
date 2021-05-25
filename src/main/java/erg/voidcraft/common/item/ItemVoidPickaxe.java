package erg.voidcraft.common.item;

import erg.voidcraft.common.init.VoidcraftItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;

public class ItemVoidPickaxe extends PickaxeItem {

    public ItemVoidPickaxe() {
        super(VoidcraftItems.itemTierVoid, 1, -2.8f, new Item.Properties().tab(ItemGroup.TAB_TOOLS).fireResistant());
    }

}
