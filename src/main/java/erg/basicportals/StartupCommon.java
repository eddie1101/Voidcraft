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

public class StartupCommon {
    public static BlockVoid blockVoid;
    public static BlockItem itemBlockVoid;

    public static BlockPortalBase blockPortalBase;
    public static BlockItem itemBlockPortalBase;

    public static BlockVoidOre blockVoidOre;
    public static BlockItem itemBlockVoidOre;

    public static TileEntityType<TileEntityPortalBase> tileEntityPortalBaseType;

    public static ItemDestinationLodestar itemDestinationLodestar;
    public static ItemVoidCrystal itemVoidCrystal;

    @SubscribeEvent
    public static void onBlocksRegistration(final RegistryEvent.Register<Block> event) {
        blockVoid = (BlockVoid)(new BlockVoid().setRegistryName("basicportals", "block_void"));
        blockPortalBase = (BlockPortalBase)(new BlockPortalBase().setRegistryName("basicportals", "block_portal_base"));
        blockVoidOre = (BlockVoidOre)(new BlockVoidOre().setRegistryName("basicportals", "block_void_ore"));

        event.getRegistry().registerAll(
                blockVoid,
                blockPortalBase,
                blockVoidOre
        );
    }

    @SubscribeEvent
    public static void onItemsRegistration(final RegistryEvent.Register<Item> event) {

        //---------------------------BlockItems-------------------------------------------------------
        Item.Properties itemBlockVoidProperties = new Item.Properties().group(ItemGroup.BUILDING_BLOCKS);
        itemBlockVoid = new BlockItem(blockVoid, itemBlockVoidProperties);
        itemBlockVoid.setRegistryName(blockVoid.getRegistryName());

        Item.Properties itemBlockPortalBaseProperties = new Item.Properties().group(ItemGroup.BUILDING_BLOCKS).maxStackSize(1);
        itemBlockPortalBase = new BlockItem(blockPortalBase, itemBlockPortalBaseProperties);
        itemBlockPortalBase.setRegistryName(blockPortalBase.getRegistryName());

        Item.Properties itemBlockVoidOreProperties = new Item.Properties().group(ItemGroup.BUILDING_BLOCKS);
        itemBlockVoidOre = new BlockItem(blockVoidOre, itemBlockVoidOreProperties);
        itemBlockVoidOre.setRegistryName(blockVoidOre.getRegistryName());


        //-----------------------------Items------------------------------------------------------
        itemDestinationLodestar = new ItemDestinationLodestar();
        itemDestinationLodestar.setRegistryName("basicportals","item_destination_lodestar");

        itemVoidCrystal = new ItemVoidCrystal();
        itemVoidCrystal.setRegistryName("basicportals", "item_void_crystal");



        event.getRegistry().registerAll(
                //BlockItems
                itemBlockVoid,
                itemBlockPortalBase,
                itemBlockVoidOre,
                //Items
                itemDestinationLodestar,
                itemVoidCrystal
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
