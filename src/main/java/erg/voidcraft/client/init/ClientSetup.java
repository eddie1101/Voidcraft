package erg.voidcraft.client.init;

import erg.voidcraft.client.gui.screen.ScreenPortalBase;
import erg.voidcraft.client.particle.ParticleMiasmaFactory;
import erg.voidcraft.client.render.entity.RenderVoidLurker;
import erg.voidcraft.common.init.VoidcraftBlocks;
import erg.voidcraft.common.init.VoidcraftContainers;
import erg.voidcraft.common.init.VoidcraftEntities;
import erg.voidcraft.common.init.VoidcraftParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientSetup {

    @SubscribeEvent
    public static void registerScreens(FMLClientSetupEvent event) {
        ScreenManager.register(VoidcraftContainers.PORTAL_BASE, ScreenPortalBase::new);
    }

    @SubscribeEvent
    public static void setRenderLayers(FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(VoidcraftBlocks.VOID_BLOCK, RenderType.solid());
        RenderTypeLookup.setRenderLayer(VoidcraftBlocks.PORTAL_BASE, RenderType.solid());
        RenderTypeLookup.setRenderLayer(VoidcraftBlocks.VOID_ORE, RenderType.solid());
        RenderTypeLookup.setRenderLayer(VoidcraftBlocks.NETHER_VOID_ORE, RenderType.solid());
        RenderTypeLookup.setRenderLayer(VoidcraftBlocks.END_VOID_ORE, RenderType.solid());
    }

    @SubscribeEvent
    public static void registerRenderers(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(VoidcraftEntities.VOID_LURKER, RenderVoidLurker::new);
    }

    @SubscribeEvent
    public static void registerParticleFactories(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(VoidcraftParticles.MIASMA, sprite -> new ParticleMiasmaFactory(sprite));
    }

}
