package erg.basicportals;

import erg.basicportals.gui.screen.ScreenPortalBase;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class StartupClientOnly {

    @SubscribeEvent
    public static void onClientSetupEvent(FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(StartupCommon.blockVoid, RenderType.getSolid());
        RenderTypeLookup.setRenderLayer(StartupCommon.blockPortalBase, RenderType.getSolid());
        RenderTypeLookup.setRenderLayer(StartupCommon.blockVoidOre, RenderType.getSolid());

        ScreenManager.registerFactory(StartupCommon.containerTypePortalBase, ScreenPortalBase::new);

    }

}
