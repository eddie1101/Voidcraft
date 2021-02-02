package erg.voidcraft.common.item;

import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemVoidAxe extends AxeItem {

    public ItemVoidAxe(){
        super(VoidcraftItems.itemTierVoid, 6.0F, -3.0F, new Item.Properties().group(ItemGroup.TOOLS).isImmuneToFire());
    }

}
