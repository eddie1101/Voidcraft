package erg.basicportals;

import erg.basicportals.blocks.*;
import erg.basicportals.gui.itemgroup.ItemGroupBasicPortals;
import erg.basicportals.items.ItemDestinationLodestar;
import erg.basicportals.items.ItemVoidChisel;
import erg.basicportals.items.ItemVoidCrystal;
import erg.basicportals.tileentities.TileEntityPortalBase;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ObjectHolder;

import static erg.basicportals.util.Util.*;

public class StartupCommon {

    public static ItemGroupBasicPortals basicPortalsGroup;

    public static BlockVoid blockVoid;
    public static BlockPortalBase blockPortalBase;
    public static BlockVoidOre blockVoidOre;
    public static BlockNetherVoidOre blockNetherVoidOre;
    public static BlockEndVoidOre blockEndVoidOre;

    public static TileEntityType<TileEntityPortalBase> tileEntityPortalBaseType;

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
    public static ItemDestinationLodestar itemDestinationLodestar = null;
    @ObjectHolder("basicportals:item_void_crystal")
    public static ItemVoidCrystal itemVoidCrystal = null;
    @ObjectHolder("basicportals:item_void_chisel")
    public static ItemVoidChisel itemVoidChisel = null;

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

        basicPortalsGroup = new ItemGroupBasicPortals("item_group_basic_portals");

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
                setItemName(new ItemVoidChisel(), "item_void_chisel")
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
    public static void onCommonSetupEvent(FMLCommonSetupEvent event) {

    }

}
