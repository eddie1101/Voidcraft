package erg.voidcraft.common.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.items.ItemStackHandler;

import java.util.function.Predicate;

public class InventoryPortalBaseContents implements IInventory {

    private Predicate<PlayerEntity> canPlayerAccessInventoryLambda = (in) -> true;
    private Notify markDirtyNotificationLambda = () -> {};
    private Notify openInventoryNotificationLambda = () -> {};
    private Notify closeInventoryNotificationLambda = () -> {};

    private final ItemStackHandler portalContents;

    @FunctionalInterface
    public interface Notify {
        void invoke();
    }

    public static InventoryPortalBaseContents createForTileEntity(int size, Predicate<PlayerEntity> canPlayerAccessInventoryLambda, Notify markDirtyNotificationLambda) {
        return new InventoryPortalBaseContents(size, canPlayerAccessInventoryLambda, markDirtyNotificationLambda);
    }

    public static InventoryPortalBaseContents createForClientSideContainer(int size) {
        return new InventoryPortalBaseContents(size);
    }

    public CompoundNBT serializeNBT() {
        return portalContents.serializeNBT();
    }

    public void deserializeNBT(CompoundNBT nbt) {
        portalContents.deserializeNBT(nbt);
    }

    public void setCanPlayerAccessInventoryLambda(Predicate<PlayerEntity> canPlayerAccessInventoryLambda) {
        this.canPlayerAccessInventoryLambda = canPlayerAccessInventoryLambda;
    }

    public void setMarkDirtyNotificationLambda(Notify markDirtyNotificationLambda) {
        this.markDirtyNotificationLambda = markDirtyNotificationLambda;
    }

    public void setOpenInventoryNotificationLambda(Notify openInventoryNotificationLambda) {
        this.openInventoryNotificationLambda = openInventoryNotificationLambda;
    }

    public void setCloseInventoryNotificationLambda(Notify closeInventoryNotificationLambda) {
        this.closeInventoryNotificationLambda = closeInventoryNotificationLambda;
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return canPlayerAccessInventoryLambda.test(player);
    }

    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        return portalContents.isItemValid(index, stack);
    }

    @Override
    public void setChanged() {
        markDirtyNotificationLambda.invoke();
    }

    @Override
    public void startOpen(PlayerEntity player) {
        openInventoryNotificationLambda.invoke();
    }

    @Override
    public void stopOpen(PlayerEntity player) {
        closeInventoryNotificationLambda.invoke();
    }

    @Override
    public int getContainerSize() {
        return portalContents.getSlots();
    }

    @Override
    public boolean isEmpty() {
        for(int i = 0; i < portalContents.getSlots(); i++) {
            if(!portalContents.getStackInSlot(i).isEmpty()) return false;
        }
        return true;
    }

    @Override
    public ItemStack getItem(int index) {
        return portalContents.getStackInSlot(index);
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        return portalContents.extractItem(index, count, false);
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        int maxPossibleItemStackSize = portalContents.getSlotLimit(index);
        return portalContents.extractItem(index, maxPossibleItemStackSize, false);
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        portalContents.setStackInSlot(index, stack);
    }

    @Override
    public void clearContent() {
        for(int i = 0; i < portalContents.getSlots(); ++i) {
            portalContents.setStackInSlot(i, ItemStack.EMPTY);
        }
    }

    private InventoryPortalBaseContents(int size) {
        this.portalContents = new ItemStackHandler(size);
    }

    private InventoryPortalBaseContents(int size, Predicate<PlayerEntity> canPlayerAccessInventoryLambda, Notify markDirtyNotificationLambda) {
        this.portalContents = new ItemStackHandler(size);
        this.canPlayerAccessInventoryLambda = canPlayerAccessInventoryLambda;
        this.markDirtyNotificationLambda = markDirtyNotificationLambda;
    }

}
