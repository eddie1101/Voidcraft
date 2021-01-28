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
        World gen void ore
        Netherite -> Void
        Void Chisel
        Portal Base needs inventory
        Creative Tab
     */


}
