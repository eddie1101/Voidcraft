package erg.basicportals.items;

import erg.basicportals.StartupCommon;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemVoidHoe extends HoeItem {

    public ItemVoidHoe() {
        super(StartupCommon.itemTierVoid, -4, 0.0F, new Item.Properties().group(ItemGroup.TOOLS).isImmuneToFire());
    }

}
