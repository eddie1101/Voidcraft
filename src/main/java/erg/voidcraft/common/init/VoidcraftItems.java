package erg.voidcraft.common.init;

import erg.voidcraft.common.core.ItemGroupVoidcraft;
import erg.voidcraft.common.item.*;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ObjectHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static erg.voidcraft.common.init.VoidcraftBlocks.*;
import static erg.voidcraft.common.util.SetRegistryName.setBlockItemName;
import static erg.voidcraft.common.util.SetRegistryName.setItemName;

public class VoidcraftItems {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final ItemGroupVoidcraft TAB_VOIDCRAFT = new ItemGroupVoidcraft("item_group_voidcraft");

    public static final ItemTierVoid itemTierVoid = new ItemTierVoid();
    public static final ArmorMaterialVoid armorMaterialVoid = new ArmorMaterialVoid();

    @ObjectHolder("voidcraft:block_void")
    public static BlockItem ITEM_BLOCK_VOID = null;
    @ObjectHolder("voidcraft:block_crystal_core")
    public static BlockItem ITEM_BLOCK_CRYSTAL_CORE = null;
    @ObjectHolder("voidcraft:block_portal_base")
    public static BlockItem ITEM_BLOCK_PORTAL_BASE = null;
    @ObjectHolder("voidcraft:block_void_ore")
    public static BlockItem ITEM_BLOCK_VOID_ORE = null;
    @ObjectHolder("voidcraft:block_nether_void_ore")
    public static BlockItem ITEM_BLOCK_NETHER_VOID_ORE = null;
    @ObjectHolder("voidcraft:block_end_void_ore")
    public static BlockItem ITEM_BLOCK_END_VOID_ORE = null;


    @ObjectHolder("voidcraft:item_dimensional_lodestar")
    public static final ItemDimensionalLodestar DIMENSIONAL_LODESTAR = null;
    @ObjectHolder("voidcraft:item_destination_lodestar")
    public static final ItemDestinationLodestar DESTINATION_LODESTAR = null;
    @ObjectHolder("voidcraft:item_void_crystal")
    public static final ItemVoidCrystal VOID_CRYSTAL = null;
    @ObjectHolder("voidcraft:item_crystal_catalyst")
    public static final ItemCrystalCatalyst CRYSTAL_CATALYST = null;
    @ObjectHolder("voidcraft:item_void_pearl")
    public static final Item VOID_PEARL = null;

    @ObjectHolder("voidcraft:item_void_chisel")
    public static final ItemVoidChisel VOID_CHISEL = null;
    @ObjectHolder("voidcraft:item_void_pickaxe")
    public static final ItemVoidPickaxe VOID_PICKAXE = null;
    @ObjectHolder("voidcraft:item_void_axe")
    public static final ItemVoidAxe VOID_AXE = null;
    @ObjectHolder("voidcraft:item_void_shovel")
    public static final ItemVoidShovel VOID_SHOVEL = null;
    @ObjectHolder("voidcraft:item_void_sword")
    public static final ItemVoidSword VOID_SWORD = null;
    @ObjectHolder("voidcraft:item_void_hoe")
    public static final ItemVoidHoe VOID_HOE = null;
    @ObjectHolder("voidcraft:item_void_helmet")
    public static final ArmorVoid VOID_HELMET = null;
    @ObjectHolder("voidcraft:item_void_chestplate")
    public static final ArmorVoid VOID_CHESTPLATE = null;
    @ObjectHolder("voidcraft:item_void_leggings")
    public static final ArmorVoid VOID_LEGGINGS = null;
    @ObjectHolder("voidcraft:item_void_boots")
    public static final ArmorVoid VOID_BOOTS = null;

    @ObjectHolder("voidcraft:item_particle_tester")
    public static final ItemParticleTester PARTICLE_TESTER = null;

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        LOGGER.debug("Registering Items...");
        event.getRegistry().registerAll(
                setItemName(new ItemDimensionalLodestar(), "item_dimensional_lodestar"),
                setItemName(new ItemDestinationLodestar(), "item_destination_lodestar"),
                setItemName(new ItemCrystalCatalyst(), "item_crystal_catalyst"),
                setItemName(new ItemVoidCrystal(), "item_void_crystal"),
                setItemName(new Item(new Item.Properties().stacksTo(16).tab(ItemGroup.TAB_MISC).rarity(Rarity.RARE)), "item_void_pearl"),
                setItemName(new ItemVoidChisel(), "item_void_chisel"),
                setItemName(new ItemVoidPickaxe(), "item_void_pickaxe"),
                setItemName(new ItemVoidAxe(), "item_void_axe"),
                setItemName(new ItemVoidShovel(), "item_void_shovel"),
                setItemName(new ItemVoidHoe(), "item_void_hoe"),
                setItemName(new ItemVoidSword(), "item_void_sword"),
                setItemName(new ArmorVoid(EquipmentSlotType.HEAD), "item_void_helmet"),
                setItemName(new ArmorVoid(EquipmentSlotType.CHEST), "item_void_chestplate"),
                setItemName(new ArmorVoid(EquipmentSlotType.LEGS), "item_void_leggings"),
                setItemName(new ArmorVoid(EquipmentSlotType.FEET), "item_void_boots"),

                setItemName(new ItemParticleTester(), "item_particle_tester")
        );
    }

    @SubscribeEvent
    public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {
        LOGGER.debug("Registering BlockItems...");
        Item.Properties defaultProperties = new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS);

        ITEM_BLOCK_VOID = new BlockItem(VOID_BLOCK, defaultProperties);
        ITEM_BLOCK_CRYSTAL_CORE = new BlockItem(CRYSTAL_CORE, defaultProperties);
        ITEM_BLOCK_PORTAL_BASE = new BlockItem(PORTAL_BASE, defaultProperties);
        ITEM_BLOCK_VOID_ORE = new BlockItem(VOID_ORE, defaultProperties);
        ITEM_BLOCK_NETHER_VOID_ORE = new BlockItem(NETHER_VOID_ORE, defaultProperties);
        ITEM_BLOCK_END_VOID_ORE = new BlockItem(END_VOID_ORE, defaultProperties);

        event.getRegistry().registerAll(
                setBlockItemName(ITEM_BLOCK_VOID, VOID_BLOCK.getRegistryName()),
                setBlockItemName(ITEM_BLOCK_CRYSTAL_CORE, CRYSTAL_CORE.getRegistryName()),
                setBlockItemName(ITEM_BLOCK_PORTAL_BASE, PORTAL_BASE.getRegistryName()),
                setBlockItemName(ITEM_BLOCK_VOID_ORE, VOID_ORE.getRegistryName()),
                setBlockItemName(ITEM_BLOCK_NETHER_VOID_ORE, NETHER_VOID_ORE.getRegistryName()),
                setBlockItemName(ITEM_BLOCK_END_VOID_ORE, END_VOID_ORE.getRegistryName())
        );

    }

}
