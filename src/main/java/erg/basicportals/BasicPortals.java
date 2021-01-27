package erg.basicportals;

import erg.basicportals.network.BasicPortalsPacketHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BasicPortals.MODID)
public class BasicPortals {
    public static final String MODID = "basicportals";
    public static IEventBus MOD_EVENT_BUS;

    public BasicPortals() {
        MOD_EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();

        registerCommonEvents();
        BasicPortalsPacketHandler.registerMessages();
        DistExecutor.runWhenOn(Dist.CLIENT, () -> BasicPortals::registerClientOnlyEvents);
    }

    public static void registerCommonEvents() {
        MOD_EVENT_BUS.register(StartupCommon.class);
    }

    public static void registerClientOnlyEvents() {
        MOD_EVENT_BUS.register(StartupClientOnly.class);
    }


}
