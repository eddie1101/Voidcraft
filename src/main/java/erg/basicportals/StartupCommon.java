package erg.basicportals;

import erg.basicportals.blocks.*;
import erg.basicportals.container.ContainerPortalBase;
import erg.basicportals.gui.itemgroup.ItemGroupBasicPortals;
import erg.basicportals.items.*;
import erg.basicportals.tileentities.TileEntityPortalBase;
import net.minecraft.block.Block;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ObjectHolder;

import static erg.basicportals.util.Util.*;

public class StartupCommon {

    public static final ItemGroupBasicPortals basicPortalsGroup = new ItemGroupBasicPortals("item_group_basic_portals");

    public static final ItemTierVoid itemTierVoid = new ItemTierVoid();
    public static final ArmorMaterialVoid armorMaterialVoid = new ArmorMaterialVoid();

    public static BlockVoid blockVoid;
    public static BlockPortalBase blockPortalBase;
    public static BlockVoidOre blockVoidOre;
    public static BlockNetherVoidOre blockNetherVoidOre;
    public static BlockEndVoidOre blockEndVoidOre;

    public static TileEntityType<TileEntityPortalBase> tileEntityPortalBaseType;
    public static ContainerType<ContainerPortalBase> containerTypePortalBase;

    @ObjectHolder("basicportals:block_void")
    public static BlockItem itemBlockVoid = null;
    @ObjectHolder("basicportals:block_portal_base")
    public static BlockItem itemBlockPortalBase = null;
    @ObjectHolder("basicportals:block_void_ore")
    public static BlockItem itemBlockVoidOre = null;
    @ObjectHolder("basicportals:block_nether_void_ore")
    public static BlockItem itemBlockNetherVoidOre = null;
    @ObjectHolder("basicportals:block_end_void_ore")
    public static BlockItem itemBlockEndVoidOre = null;

    @ObjectHolder("basicportals:item_destination_lodestar")
    public static final ItemDestinationLodestar itemDestinationLodestar = null;
    @ObjectHolder("basicportals:item_void_crystal")
    public static final ItemVoidCrystal itemVoidCrystal = null;
    @ObjectHolder("basicportals:item_void_chisel")
    public static final ItemVoidChisel itemVoidChisel = null;
    @ObjectHolder("basicportals:item_void_pickaxe")
    public static final ItemVoidPickaxe itemVoidPickaxe = null;
    @ObjectHolder("basicportals:item_void_axe")
    public static final ItemVoidPickaxe itemVoidAxe = null;
    @ObjectHolder("basicportals:item_void_shovel")
    public static final ItemVoidPickaxe itemVoidShovel = null;
    @ObjectHolder("basicportals:item_void_sword")
    public static final ItemVoidPickaxe itemVoidSword = null;
    @ObjectHolder("basicportals:item_void_hoe")
    public static final ItemVoidPickaxe itemVoidHoe = null;
    @ObjectHolder("basicportals:item_void_helmet")
    public static final ArmorVoid itemVoidHelmet = null;
    @ObjectHolder("basicportals:item_void_chestplate")
    public static final ArmorVoid itemVoidChestplate = null;
    @ObjectHolder("basicportals:item_void_leggings")
    public static final ArmorVoid itemVoidLeggings = null;
    @ObjectHolder("basicportals:item_void_boots")
    public static final ArmorVoid itemVoidBoots = null;

    @SubscribeEvent
    public static void onBlocksRegistration(final RegistryEvent.Register<Block> event) {
        blockVoid = (BlockVoid)setBlockName(new BlockVoid(), "block_void");
        blockPortalBase = (BlockPortalBase)setBlockName(new BlockPortalBase(), "block_portal_base");
        blockVoidOre = (BlockVoidOre)setBlockName(new BlockVoidOre(), "block_void_ore");
        blockNetherVoidOre = (BlockNetherVoidOre)setBlockName(new BlockNetherVoidOre(), "block_nether_void_ore");
        blockEndVoidOre = (BlockEndVoidOre)setBlockName(new BlockEndVoidOre(), "block_end_void_ore");

        event.getRegistry().registerAll(
                blockVoid,
                blockPortalBase,
                blockVoidOre,
                blockNetherVoidOre,
                blockEndVoidOre
        );
    }

    @SubscribeEvent
    public static void onItemsRegistration(final RegistryEvent.Register<Item> event) {

        Item.Properties itemBlockPropertiesDefault = new Item.Properties().group(ItemGroup.BUILDING_BLOCKS);

        itemBlockVoid = new BlockItem(blockVoid, itemBlockPropertiesDefault);
        itemBlockPortalBase = new BlockItem(blockPortalBase, itemBlockPropertiesDefault);
        itemBlockVoidOre = new BlockItem(blockVoidOre, itemBlockPropertiesDefault);
        itemBlockNetherVoidOre = new BlockItem(blockNetherVoidOre, itemBlockPropertiesDefault);
        itemBlockEndVoidOre = new BlockItem(blockEndVoidOre, itemBlockPropertiesDefault);

        event.getRegistry().registerAll(
                //BlockItems
                setBlockItemName(itemBlockVoid, blockVoid.getRegistryName()),
                setBlockItemName(itemBlockPortalBase, blockPortalBase.getRegistryName()),
                setBlockItemName(itemBlockVoidOre, blockVoidOre.getRegistryName()),
                setBlockItemName(itemBlockNetherVoidOre, blockNetherVoidOre.getRegistryName()),
                setBlockItemName(itemBlockEndVoidOre, blockEndVoidOre.getRegistryName()),
                //Items
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
    public static void onTileEntityTypeRegistration(final RegistryEvent.Register<TileEntityType<?>> event) {

        tileEntityPortalBaseType = TileEntityType.Builder.create(TileEntityPortalBase::new, blockPortalBase).build(null);
        tileEntityPortalBaseType.setRegistryName("basicportals", "tile_entity_portal_base");

        event.getRegistry().registerAll(
                tileEntityPortalBaseType
        );

    }

    @SubscribeEvent
    public static void onContainerTypeRegistration(final RegistryEvent.Register<ContainerType<?>> event) {
        containerTypePortalBase = IForgeContainerType.create(ContainerPortalBase::createContainerClientSide);
        containerTypePortalBase.setRegistryName("basicportals", "container_portal_base");
        event.getRegistry().registerAll(
                containerTypePortalBase
        );
    }

    @SubscribeEvent
    public static void onCommonSetupEvent(FMLCommonSetupEvent event) {

    }

}
