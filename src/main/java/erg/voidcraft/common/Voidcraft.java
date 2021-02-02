package erg.voidcraft.common;

import erg.voidcraft.client.gui.screen.ScreenPortalBase;
import erg.voidcraft.common.block.VoidcraftBlocks;
import erg.voidcraft.common.container.VoidcraftContainers;
import erg.voidcraft.common.item.VoidcraftItems;
import erg.voidcraft.common.tile.VoidcraftTiles;
import erg.voidcraft.common.world.gen.OreGeneration;
import net.minecraft.block.Block;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Voidcraft.MODID)
public class Voidcraft {
    public static final String MODID = "voidcraft";
    public static IEventBus MOD_EVENT_BUS;
    public static IEventBus FORGE_EVENT_BUS;

    public Voidcraft() {
        MOD_EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();
        FORGE_EVENT_BUS = MinecraftForge.EVENT_BUS;

        registerCommonEvents();
        DistExecutor.runWhenOn(Dist.CLIENT, () -> Voidcraft::registerClientOnlyEvents);
    }

    public static void registerCommonEvents() {
//        MOD_EVENT_BUS.register(VoidcraftBlocks.class);
//        MOD_EVENT_BUS.register(VoidcraftTiles.class);
//        MOD_EVENT_BUS.register(VoidcraftContainers.class);
//        MOD_EVENT_BUS.register(VoidcraftItems.class);
//        MOD_EVENT_BUS.register(OreGeneration.class);

        FORGE_EVENT_BUS.addListener(EventPriority.HIGH, OreGeneration::generateOres);
        FORGE_EVENT_BUS.addGenericListener(Item.class, VoidcraftItems::registerItems);
        FORGE_EVENT_BUS.addGenericListener(Item.class, VoidcraftItems::registerItemBlocks);
        FORGE_EVENT_BUS.addGenericListener(Block.class, VoidcraftBlocks::registerBlocks);
        FORGE_EVENT_BUS.addGenericListener(TileEntityType.class, VoidcraftTiles::registerTileEntities);
        FORGE_EVENT_BUS.addGenericListener(ContainerType.class, VoidcraftContainers::registerContainers);

        MOD_EVENT_BUS.register(OreGeneration.class);

    }

    public static void registerClientOnlyEvents() {
//        RenderTypeLookup.setRenderLayer(VoidcraftBlocks.blockVoid, RenderType.getSolid());
//        RenderTypeLookup.setRenderLayer(VoidcraftBlocks.blockPortalBase, RenderType.getSolid());
//        RenderTypeLookup.setRenderLayer(VoidcraftBlocks.blockVoidOre, RenderType.getSolid());
//
        ScreenManager.registerFactory(VoidcraftContainers.containerTypePortalBase, ScreenPortalBase::new);
    }

    /*TODO:
        -----------------------
        Soon
        -----------------------
        armor models
        Dimensional Lodestar
        -----------------------
        Not so soon and ideas
        -----------------------
        Voidlurker mob? (maybe some cool drops needed for crafting cool shit?)
        maybe some magic shit idk
        definitely some black-hole stuff
        make portals cooler (multiblock?)
        Some sort of power source for portals? other things that might require that power source?
        Make tileentities save nbt data when broken, that would be cool
        a way to access the void
        collect power from the void
        some way to infuse void crystals
        Soul Slay Bow (& void crystal arrows)
        void miasma when void is accessed
        particles for miasma
        big breaker thingy that breaks lots of blocks
     */


}
