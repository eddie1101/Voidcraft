package erg.voidcraft.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ShovelItem;

public class ItemVoidShovel extends ShovelItem {

    public ItemVoidShovel() {
        super(VoidcraftItems.itemTierVoid, 1F, -3.0F, new Item.Properties().group(ItemGroup.TOOLS).isImmuneToFire());
    }

}
