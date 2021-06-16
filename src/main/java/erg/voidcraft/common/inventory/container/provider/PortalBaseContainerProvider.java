package erg.voidcraft.common.inventory.container.provider;

import erg.voidcraft.common.inventory.container.ContainerPortalBase;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class PortalBaseContainerProvider implements INamedContainerProvider {

    World world;
    BlockPos pos;

    public PortalBaseContainerProvider(World world, BlockPos pos) {
        this.world = world;
        this.pos = pos;
    }


    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("screen.voidcraft.Portal");
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return new ContainerPortalBase(p_createMenu_1_, p_createMenu_2_, p_createMenu_3_, this.world, this.pos);
    }
}
