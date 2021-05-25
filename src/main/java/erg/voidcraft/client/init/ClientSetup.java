package erg.voidcraft.client.init;

import erg.voidcraft.client.gui.screen.ScreenPortalBase;
import erg.voidcraft.client.model.ModelVoidLurker;
import erg.voidcraft.client.particle.ParticleMiasmaFactory;
import erg.voidcraft.client.render.entity.RenderVoidLurker;
import erg.voidcraft.common.entity.EntityVoidLurker;
import erg.voidcraft.common.init.VoidcraftBlocks;
import erg.voidcraft.common.init.VoidcraftContainers;
import erg.voidcraft.common.init.VoidcraftEntities;
import erg.voidcraft.common.init.VoidcraftParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientSetup {

    @SubscribeEvent
    public static void registerScreens(FMLClientSetupEvent event) {
        ScreenManager.register(VoidcraftContainers.containerTypePortalBase, ScreenPortalBase::new);
    }

    @SubscribeEvent
    public static void setRenderLayers(FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(VoidcraftBlocks.blockVoid, RenderType.solid());
        RenderTypeLookup.setRenderLayer(VoidcraftBlocks.blockPortalBase, RenderType.solid());
        RenderTypeLookup.setRenderLayer(VoidcraftBlocks.blockVoidOre, RenderType.solid());
        RenderTypeLookup.setRenderLayer(VoidcraftBlocks.blockNetherVoidOre, RenderType.solid());
        RenderTypeLookup.setRenderLayer(VoidcraftBlocks.blockEndVoidOre, RenderType.solid());
    }

    @SubscribeEvent
    public static void registerRenderers(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(VoidcraftEntities.VOID_LURKER, RenderVoidLurker::new);
    }

    @SubscribeEvent
    public static void registerParticleFactories(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(VoidcraftParticles.miasmaParticleType, sprite -> new ParticleMiasmaFactory(sprite));
    }

}
