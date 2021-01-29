package erg.basicportals.items;

import erg.basicportals.StartupCommon;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemVoidAxe extends AxeItem {

    public ItemVoidAxe(){
        super(StartupCommon.itemTierVoid, 6.0F, -3.0F, new Item.Properties().group(ItemGroup.TOOLS).isImmuneToFire());
    }

}
