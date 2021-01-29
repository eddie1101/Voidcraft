package erg.basicportals.items;

import erg.basicportals.StartupCommon;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ArmorVoid extends ArmorItem {

    public ArmorVoid(EquipmentSlotType equipmentSlot) {
        super(StartupCommon.armorMaterialVoid, equipmentSlot, new Item.Properties().group(ItemGroup.COMBAT));
    }

}
