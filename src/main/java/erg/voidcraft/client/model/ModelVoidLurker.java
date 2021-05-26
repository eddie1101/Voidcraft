
package erg.voidcraft.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import erg.voidcraft.common.entity.EntityVoidLurker;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;



public class ModelVoidLurker extends EntityModel<EntityVoidLurker> {
    private final ModelRenderer CombinedHead;
    private final ModelRenderer Eye2_r1;
    private final ModelRenderer bb_main;
    private final ModelRenderer Arm2_r1;
    private final ModelRenderer Arm1_r1;
    private final ModelRenderer Leg2_r1;

    public ModelVoidLurker() {
        texWidth = 128;
        texHeight = 64;

        CombinedHead = new ModelRenderer(this);
        CombinedHead.setPos(0.0F, 24.0F, 0.0F);


        Eye2_r1 = new ModelRenderer(this);
        Eye2_r1.setPos(0.0F, 0.0F, 0.0F);
        CombinedHead.addChild(Eye2_r1);
        setRotationAngle(Eye2_r1, 0.0F, 3.1416F, 0.0F);
        Eye2_r1.texOffs(72, 20).addBox(-3.0F, -38.0F, 5.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        Eye2_r1.texOffs(72, 20).addBox(1.0F, -38.0F, 5.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        Eye2_r1.texOffs(72, 0).addBox(-5.0F, -40.0F, -5.0F, 10.0F, 10.0F, 10.0F, 0.0F, false);

        bb_main = new ModelRenderer(this);
        bb_main.setPos(0.0F, 24.0F, 0.0F);


        Arm2_r1 = new ModelRenderer(this);
        Arm2_r1.setPos(-7.5F, -30.0F, 0.5F);
        bb_main.addChild(Arm2_r1);
        setRotationAngle(Arm2_r1, 1.5708F, 3.1416F, 0.0F);
        Arm2_r1.texOffs(52, 20).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 15.0F, 5.0F, 0.0F, false);

        Arm1_r1 = new ModelRenderer(this);
        Arm1_r1.setPos(7.5F, -30.0F, 0.5F);
        bb_main.addChild(Arm1_r1);
        setRotationAngle(Arm1_r1, 1.5708F, 3.1416F, 0.0F);
        Arm1_r1.texOffs(52, 0).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 15.0F, 5.0F, 0.0F, false);

        Leg2_r1 = new ModelRenderer(this);
        Leg2_r1.setPos(0.0F, -15.0F, 0.0F);
        bb_main.addChild(Leg2_r1);
        setRotationAngle(Leg2_r1, 0.0F, 3.1416F, 0.0F);
        Leg2_r1.texOffs(32, 20).addBox(-5.0F, 0.0F, -2.0F, 5.0F, 15.0F, 5.0F, 0.0F, false);
        Leg2_r1.texOffs(32, 0).addBox(0.0F, 0.0F, -2.0F, 5.0F, 15.0F, 5.0F, 0.0F, false);
        Leg2_r1.texOffs(0, 0).addBox(-5.0F, -15.0F, -2.0F, 10.0F, 15.0F, 5.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(EntityVoidLurker entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        CombinedHead.render(matrixStack, buffer, packedLight, packedOverlay);
        bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}