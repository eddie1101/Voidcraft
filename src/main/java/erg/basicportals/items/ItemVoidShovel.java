package erg.basicportals.items;

import erg.basicportals.StartupCommon;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemTier;
import net.minecraft.item.ShovelItem;

public class ItemVoidShovel extends ShovelItem {

    public ItemVoidShovel() {
        super(StartupCommon.itemTierVoid, 1F, -3.0F, new Item.Properties().group(ItemGroup.TOOLS).isImmuneToFire());
    }

}
