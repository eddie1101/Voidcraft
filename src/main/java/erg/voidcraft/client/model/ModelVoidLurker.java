
package erg.voidcraft.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import erg.voidcraft.common.entity.EntityVoidLurker;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;



public class ModelVoidLurker extends BipedModel<EntityVoidLurker> {

    private final ModelRenderer Arm1_r1;
    private final ModelRenderer Arm2_r1;

    public ModelVoidLurker() {

        super(1f, 0f, 128, 64);

        head = new ModelRenderer(this);
        head.setPos(0.0F, -11.0F, 0.0F);
        head.texOffs(72, 0).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, 0.0F, false);
        head.texOffs(72, 20).addBox(-3.0F, -3.0F, -6.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        head.texOffs(72, 20).addBox(1.0F, -3.0F, -6.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);

        rightArm = new ModelRenderer(this);
        rightArm.setPos(-7.5F, -6.0F, -0.5F);


        Arm1_r1 = new ModelRenderer(this);
        Arm1_r1.setPos(2.5F, 0.0F, 0.0F);
        rightArm.addChild(Arm1_r1);
        setRotationAngle(Arm1_r1, -1.5708F, 0.0F, 0.0F);
        Arm1_r1.texOffs(52, 0).addBox(-5.0F, 0.0F, -2.5F, 5.0F, 15.0F, 5.0F, 0.0F, false);

        leftArm = new ModelRenderer(this);
        leftArm.setPos(7.5F, -6.0F, -0.5F);


        Arm2_r1 = new ModelRenderer(this);
        Arm2_r1.setPos(-2.5F, 0.0F, 0.0F);
        leftArm.addChild(Arm2_r1);
        setRotationAngle(Arm2_r1, -1.5708F, 0.0F, 0.0F);
        Arm2_r1.texOffs(52, 20).addBox(0.0F, 0.0F, -2.5F, 5.0F, 15.0F, 5.0F, 0.0F, false);

        rightLeg = new ModelRenderer(this);
        rightLeg.setPos(-2.5F, 9.0F, -0.5F);
        rightLeg.texOffs(32, 0).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 15.0F, 5.0F, 0.0F, false);

        leftLeg = new ModelRenderer(this);
        leftLeg.setPos(2.5F, 9.0F, -0.5F);
        leftLeg.texOffs(32, 20).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 15.0F, 5.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setPos(0.0F, 1.5F, -0.5F);
        body.texOffs(0, 0).addBox(-5.0F, -7.5F, -2.5F, 10.0F, 15.0F, 5.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(EntityVoidLurker entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
        leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
        rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
        leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}