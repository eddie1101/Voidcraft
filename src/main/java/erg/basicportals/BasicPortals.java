package erg.basicportals;

import erg.basicportals.network.BasicPortalsPacketHandler;
import erg.basicportals.world.gen.OreGeneration;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BasicPortals.MODID)
public class BasicPortals {
    public static final String MODID = "basicportals";
    public static IEventBus MOD_EVENT_BUS;

    public BasicPortals() {
        MOD_EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();

        registerCommonEvents();

        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, OreGeneration::generateOres);

        BasicPortalsPacketHandler.registerMessages();
        DistExecutor.runWhenOn(Dist.CLIENT, () -> BasicPortals::registerClientOnlyEvents);
    }

    public static void registerCommonEvents() {
        MOD_EVENT_BUS.register(StartupCommon.class);
        MOD_EVENT_BUS.register(OreGeneration.class);
    }

    public static void registerClientOnlyEvents() {
        MOD_EVENT_BUS.register(StartupClientOnly.class);
    }

    /*TODO:
        -----------------------
        Soon
        -----------------------
        Void tools/armor/weapon
        Netherite -> Void
        Void Chisel (WIP) break bedrock y > 0 ?
        Portal Base needs inventory/gui
        Dimensional Lodestar
        Clean up this fucking project, I mean, there's shit everywhere (Reorganize registering)
        Change Recipes to use mod items
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
