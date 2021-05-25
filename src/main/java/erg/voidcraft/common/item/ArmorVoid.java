package erg.voidcraft.common.item;


import erg.voidcraft.common.init.VoidcraftItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ArmorVoid extends ArmorItem {

    public ArmorVoid(EquipmentSlotType equipmentSlot) {
        super(VoidcraftItems.armorMaterialVoid, equipmentSlot, new Item.Properties().tab(ItemGroup.TAB_COMBAT));
    }

}
