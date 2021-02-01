package erg.basicportals.items;

import erg.basicportals.StartupCommon;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;

public class ItemVoidSword extends SwordItem {

    public ItemVoidSword() {
        super(StartupCommon.itemTierVoid, 4, -1.6F, new Item.Properties().group(ItemGroup.COMBAT).isImmuneToFire());
    }

}
