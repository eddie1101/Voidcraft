package erg.voidcraft.client.gui.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;

public class ButtonToggleRedstoneInput extends Button {

    public ButtonToggleRedstoneInput(int x, int y, int width, int height) {
        super(x, y, width, height, new StringTextComponent("Invert Incoming Redstone Signals"), Button -> {

        });
    }

}
