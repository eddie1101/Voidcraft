package erg.basicportals;

import erg.basicportals.common.blocks.BlockVoid;
import erg.basicportals.common.blocks.BlockPortalBase;
import erg.basicportals.common.blocks.BlockVoidOre;
import erg.basicportals.common.items.ItemDestinationLodestar;
import erg.basicportals.common.items.ItemVoidCrystal;
import erg.basicportals.common.tileentities.TileEntityPortalBase;
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

    public static BlockVoid blockVoid;
    public static BlockPortalBase blockPortalBase;
    public static BlockVoidOre blockVoidOre;

    public static TileEntityType<TileEntityPortalBase> tileEntityPortalBaseType;

    @ObjectHolder("basicportals:block_void")
    public static BlockItem itemBlockVoid = null;
    @ObjectHolder("basicportals:block_portal_base")
    public static BlockItem itemBlockPortalBase = null;
    @ObjectHolder("basicportals:block_void_ore")
    public static BlockItem itemBlockVoidOre = null;

    @ObjectHolder("basicportals:item_destination_lodestar")
    public static ItemDestinationLodestar itemDestinationLodestar = null;
    @ObjectHolder("basicportals:item_void_crystal")
    public static ItemVoidCrystal itemVoidCrystal = null;

    @SubscribeEvent
    public static void onBlocksRegistration(final RegistryEvent.Register<Block> event) {
        blockVoid = (BlockVoid)setBlockName(new BlockVoid(), "block_void");
        blockPortalBase = (BlockPortalBase)setBlockName(new BlockPortalBase(), "block_portal_base");
        blockVoidOre = (BlockVoidOre)setBlockName(new BlockVoidOre(), "block_void_ore");

        event.getRegistry().registerAll(
                blockVoid,
                blockPortalBase,
                blockVoidOre
        );
    }

    @SubscribeEvent
    public static void onItemsRegistration(final RegistryEvent.Register<Item> event) {

        Item.Properties itemBlockVoidProperties = new Item.Properties().group(ItemGroup.BUILDING_BLOCKS);
        itemBlockVoid = new BlockItem(blockVoid, itemBlockVoidProperties);

        Item.Properties itemBlockPortalBaseProperties = new Item.Properties().group(ItemGroup.BUILDING_BLOCKS).maxStackSize(1);
        itemBlockPortalBase = new BlockItem(blockPortalBase, itemBlockPortalBaseProperties);


        Item.Properties itemBlockVoidOreProperties = new Item.Properties().group(ItemGroup.BUILDING_BLOCKS);
        itemBlockVoidOre = new BlockItem(blockVoidOre, itemBlockVoidOreProperties);

        event.getRegistry().registerAll(
                //BlockItems
                setBlockItemName(itemBlockVoid, blockVoid.getRegistryName()),
                setBlockItemName(itemBlockPortalBase, blockPortalBase.getRegistryName()),
                setBlockItemName(itemBlockVoidOre, blockVoidOre.getRegistryName()),
                //Items
                setItemName(new ItemDestinationLodestar(), "item_destination_lodestar"),
                setItemName(new ItemVoidCrystal(), "item_void_crystal")
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
