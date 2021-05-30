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


    @ObjectHolder("voidcraft:item_dimensional_lodestar")
    public static final ItemDimensionalLodestar itemDimensionalLodestar = null;
    @ObjectHolder("voidcraft:item_destination_lodestar")
    public static final ItemDestinationLodestar itemDestinationLodestar = null;
    @ObjectHolder("voidcraft:item_void_crystal")
    public static final ItemVoidCrystal itemVoidCrystal = null;
    @ObjectHolder("voidcraft:item_crystal_catalyst")
    public static final ItemCrystalCatalyst itemCrystalCatalyst = null;
    @ObjectHolder("voidcraft:item_void_pearl")
    public static final Item itemVoidPearl = null;

    @ObjectHolder("voidcraft:item_void_chisel")
    public static final ItemVoidChisel itemVoidChisel = null;
    @ObjectHolder("voidcraft:item_void_pickaxe")
    public static final ItemVoidPickaxe itemVoidPickaxe = null;
    @ObjectHolder("voidcraft:item_void_axe")
    public static final ItemVoidAxe itemVoidAxe = null;
    @ObjectHolder("voidcraft:item_void_shovel")
    public static final ItemVoidShovel itemVoidShovel = null;
    @ObjectHolder("voidcraft:item_void_sword")
    public static final ItemVoidSword itemVoidSword = null;
    @ObjectHolder("voidcraft:item_void_hoe")
    public static final ItemVoidHoe itemVoidHoe = null;
    @ObjectHolder("voidcraft:item_void_helmet")
    public static final ArmorVoid itemVoidHelmet = null;
    @ObjectHolder("voidcraft:item_void_chestplate")
    public static final ArmorVoid itemVoidChestplate = null;
    @ObjectHolder("voidcraft:item_void_leggings")
    public static final ArmorVoid itemVoidLeggings = null;
    @ObjectHolder("voidcraft:item_void_boots")
    public static final ArmorVoid itemVoidBoots = null;

    @ObjectHolder("voidcraft:item_particle_tester")
    public static final ItemParticleTester itemParticleTester = null;

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
