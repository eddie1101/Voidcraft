package erg.voidcraft.common.tile;

import erg.voidcraft.common.init.VoidcraftTiles;
import erg.voidcraft.common.item.AbstractLodestar;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TilePortalBase2 extends TileEntity {

    private ItemStackHandler itemStackHandler = this.createHandler();

    private LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemStackHandler);

    public static final int NUM_SLOTS = 1;
    public static final String PORTAL_CONTENTS_INV_TAG = "contents";


    public TilePortalBase2() {
        super(VoidcraftTiles.tileEntityPortalBaseType);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        handler.invalidate();
    }

    @Override
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbt = new CompoundNBT();
        save(nbt);
        int tileEntityType = 69;
        return new SUpdateTileEntityPacket(this.worldPosition, tileEntityType, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager network, SUpdateTileEntityPacket packet) {
        BlockState blockState = level.getBlockState(worldPosition);
        load(blockState, packet.getTag());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT nbtTagCompound = new CompoundNBT();
        save(nbtTagCompound);
        return nbtTagCompound;
    }

    @Override
    public void handleUpdateTag(BlockState blockState, CompoundNBT parentNBTTagCompound) {
        this.load(blockState, parentNBTTagCompound);
    }

    @Override
    public void load(BlockState state, CompoundNBT tag) {
        itemStackHandler.deserializeNBT(tag.getCompound(PORTAL_CONTENTS_INV_TAG));
        super.load(state, tag);
    }

    @Override
    public CompoundNBT save(CompoundNBT tag) {
        tag.put(PORTAL_CONTENTS_INV_TAG, itemStackHandler.serializeNBT());
        return super.save(tag);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(1) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, ItemStack item) {
                return item.getItem() instanceof AbstractLodestar;
            }

            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack item, boolean simulate) {
                if(!(item.getItem() instanceof AbstractLodestar)) {
                    return item;
                }
                return super.insertItem(slot, item, simulate);
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        return super.getCapability(cap, side);
    }

}
