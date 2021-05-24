package erg.voidcraft.client.init;

import erg.voidcraft.client.gui.screen.ScreenPortalBase;
import erg.voidcraft.client.particle.ParticleMiasmaFactory;
import erg.voidcraft.common.init.VoidcraftBlocks;
import erg.voidcraft.common.init.VoidcraftContainers;
import erg.voidcraft.common.init.VoidcraftParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {

    @SubscribeEvent
    public static void registerScreens(FMLClientSetupEvent event) {
        ScreenManager.registerFactory(VoidcraftContainers.containerTypePortalBase, ScreenPortalBase::new);
    }

    @SubscribeEvent
    public static void setRenderLayers(FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(VoidcraftBlocks.blockVoid, RenderType.getSolid());
        RenderTypeLookup.setRenderLayer(VoidcraftBlocks.blockPortalBase, RenderType.getSolid());
        RenderTypeLookup.setRenderLayer(VoidcraftBlocks.blockVoidOre, RenderType.getSolid());
        RenderTypeLookup.setRenderLayer(VoidcraftBlocks.blockNetherVoidOre, RenderType.getSolid());
        RenderTypeLookup.setRenderLayer(VoidcraftBlocks.blockEndVoidOre, RenderType.getSolid());
    }

    @SubscribeEvent
    public static void registerParticleFactories(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particles.registerFactory(VoidcraftParticles.miasmaParticleType, sprite -> new ParticleMiasmaFactory(sprite));
    }

}
