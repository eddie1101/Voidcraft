package erg.voidcraft.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import erg.voidcraft.common.init.VoidcraftPacketHandler;
import erg.voidcraft.common.inventory.container.ContainerPortalBase;
import erg.voidcraft.common.network.PacketSetInverted;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraft.client.gui.screen.inventory.CraftingScreen;

import java.awt.*;


public class ScreenPortalBase extends ContainerScreen<ContainerPortalBase> {

    private Button toggleRedstoneInput;

    public ScreenPortalBase(ContainerPortalBase containerPortalBase, PlayerInventory playerInventory, ITextComponent title) {
        super(containerPortalBase, playerInventory, title);
        imageWidth = 176;
        imageHeight = 133;

        if(containerPortalBase.pos != null) {
            BlockPos statePos = containerPortalBase.pos;
            toggleRedstoneInput = new Button(100, 100, 25, 20,
                    new StringTextComponent("!"),
                    button -> VoidcraftPacketHandler.channel.send(PacketDistributor.SERVER.noArg(), new PacketSetInverted(statePos)));
        } else {
            toggleRedstoneInput = new Button(100, 100, 25, 20,
                    new StringTextComponent("!"),
                    button -> {});
        }
    }

    @Override
    protected void init() {
        super.init();
        addButton(toggleRedstoneInput);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        renderTooltip(matrixStack, mouseX, mouseY);
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
