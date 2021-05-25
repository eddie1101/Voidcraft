package erg.voidcraft.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import erg.voidcraft.common.inventory.container.ContainerPortalBase;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.awt.*;

public class ScreenPortalBase extends ContainerScreen<ContainerPortalBase> {

    public ScreenPortalBase(ContainerPortalBase containerPortalBase, PlayerInventory playerInventory, ITextComponent title) {
        super(containerPortalBase, playerInventory, title);
        imageWidth = 176;
        imageHeight = 133;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderToolTip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(MatrixStack matrixStack, int mouseX, int mouseY) {
        final float LABEL_XPOS = 5;
        final float FONT_Y_SPACING = 12;

        final float PORTAL_INV_LABEL_YPOS = ContainerPortalBase.TE_INV_YPOS - FONT_Y_SPACING;
        this.font.draw(matrixStack, this.title, LABEL_XPOS, PORTAL_INV_LABEL_YPOS, Color.darkGray.getRGB());

        final float PLAYER_INV_LABEL_YPOS = ContainerPortalBase.PLAYER_INVENTORY_YPOS - FONT_Y_SPACING;
        this.font.draw(matrixStack, this.inventory.getDisplayName(), LABEL_XPOS, PLAYER_INV_LABEL_YPOS, Color.darkGray.getRGB());
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.getTextureManager().bind(BACKGROUND_TEXTURE);

        int edgeSpacingX = (this.width - this.imageWidth) / 2;
        int edgeSpacingY = (this.height - this.imageHeight) / 2;
        this.blit(matrixStack, edgeSpacingX, edgeSpacingY, 0, 0, this.imageWidth, this.imageHeight);
    }

    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation("voidcraft", "textures/gui/portalbase_inventory.png");

}
