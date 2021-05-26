package erg.voidcraft.client.render.entity;

import erg.voidcraft.client.model.ModelVoidLurker;
import erg.voidcraft.common.entity.EntityVoidLurker;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderVoidLurker extends MobRenderer<EntityVoidLurker, ModelVoidLurker> {

    public RenderVoidLurker(EntityRendererManager rendererManager) {
        super(rendererManager, new ModelVoidLurker(), 0.0f);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityVoidLurker p_110775_1_) {
        return new ResourceLocation("voidcraft", "textures/model/model_void_lurker.png");
    }
}
