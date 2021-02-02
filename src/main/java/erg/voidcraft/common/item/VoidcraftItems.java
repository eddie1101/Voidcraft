package erg.voidcraft.common.item;

import erg.voidcraft.common.core.ItemGroupVoidcraft;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ObjectHolder;

import static erg.voidcraft.common.block.VoidcraftBlocks.*;
import static erg.voidcraft.common.util.Util.setBlockItemName;
import static erg.voidcraft.common.util.Util.setItemName;

public class VoidcraftItems {

    public static final ItemGroupVoidcraft group = new ItemGroupVoidcraft("item_group_basic_portals");

    public static final ItemTierVoid itemTierVoid = new ItemTierVoid();
    public static final ArmorMaterialVoid armorMaterialVoid = new ArmorMaterialVoid();

    @ObjectHolder("voidcraft:block_void")
    public static BlockItem itemBlockVoid = null;
    @ObjectHolder("voidcraft:block_portal_base")
    public static BlockItem itemBlockPortalBase = null;
    @ObjectHolder("voidcraft:block_void_ore")
    public static BlockItem itemBlockVoidOre = null;
    @ObjectHolder("voidcraft:block_nether_void_ore")
    public static BlockItem itemBlockNetherVoidOre = null;
    @ObjectHolder("voidcraft:block_end_void_ore")
    public static BlockItem itemBlockEndVoidOre = null;

    @ObjectHolder("voidcraft:item_destination_lodestar")
    public static final ItemDestinationLodestar itemDestinationLodestar = null;
    @ObjectHolder("voidcraft:item_void_crystal")
    public static final ItemVoidCrystal itemVoidCrystal = null;
    @ObjectHolder("voidcraft:item_void_chisel")
    public static final ItemVoidChisel itemVoidChisel = null;
    @ObjectHolder("voidcraft:item_void_pickaxe")
    public static final ItemVoidPickaxe itemVoidPickaxe = null;
    @ObjectHolder("voidcraft:item_void_axe")
    public static final ItemVoidPickaxe itemVoidAxe = null;
    @ObjectHolder("voidcraft:item_void_shovel")
    public static final ItemVoidPickaxe itemVoidShovel = null;
    @ObjectHolder("voidcraft:item_void_sword")
    public static final ItemVoidPickaxe itemVoidSword = null;
    @ObjectHolder("voidcraft:item_void_hoe")
    public static final ItemVoidPickaxe itemVoidHoe = null;
    @ObjectHolder("voidcraft:item_void_helmet")
    public static final ArmorVoid itemVoidHelmet = null;
    @ObjectHolder("voidcraft:item_void_chestplate")
    public static final ArmorVoid itemVoidChestplate = null;
    @ObjectHolder("voidcraft:item_void_leggings")
    public static final ArmorVoid itemVoidLeggings = null;
    @ObjectHolder("voidcraft:item_void_boots")
    public static final ArmorVoid itemVoidBoots = null;

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {

        event.getRegistry().registerAll(
                setItemName(new ItemDestinationLodestar(), "item_destination_lodestar"),
                setItemName(new ItemVoidCrystal(), "item_void_crystal"),
                setItemName(new ItemVoidChisel(), "item_void_chisel"),
                setItemName(new ItemVoidPickaxe(), "item_void_pickaxe"),
                setItemName(new ItemVoidAxe(), "item_void_axe"),
                setItemName(new ItemVoidShovel(), "item_void_shovel"),
                setItemName(new ItemVoidHoe(), "item_void_hoe"),
                setItemName(new ItemVoidSword(), "item_void_sword"),
                setItemName(new ArmorVoid(EquipmentSlotType.HEAD), "item_void_helmet"),
                setItemName(new ArmorVoid(EquipmentSlotType.CHEST), "item_void_chestplate"),
                setItemName(new ArmorVoid(EquipmentSlotType.LEGS), "item_void_leggings"),
                setItemName(new ArmorVoid(EquipmentSlotType.FEET), "item_void_boots")
        );
    }

    @SubscribeEvent
    public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {

        Item.Properties defaultProperties = new Item.Properties().group(ItemGroup.BUILDING_BLOCKS);

        itemBlockVoid = new BlockItem(blockVoid, defaultProperties);
        itemBlockPortalBase = new BlockItem(blockPortalBase, defaultProperties);
        itemBlockVoidOre = new BlockItem(blockVoidOre, defaultProperties);
        itemBlockNetherVoidOre = new BlockItem(blockNetherVoidOre, defaultProperties);
        itemBlockEndVoidOre = new BlockItem(blockEndVoidOre, defaultProperties);

        event.getRegistry().registerAll(
                setBlockItemName(itemBlockVoid, blockVoid.getRegistryName()),
                setBlockItemName(itemBlockPortalBase, blockPortalBase.getRegistryName()),
                setBlockItemName(itemBlockVoidOre, blockVoidOre.getRegistryName()),
                setBlockItemName(itemBlockNetherVoidOre, blockNetherVoidOre.getRegistryName()),
                setBlockItemName(itemBlockEndVoidOre, blockEndVoidOre.getRegistryName())
        );

    }

}
