package erg.voidcraft.common.inventory.container.slot;

import erg.voidcraft.common.item.ItemDestinationLodestar;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class SlotPortalBase extends Slot {
    public SlotPortalBase(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() instanceof ItemDestinationLodestar;
    }
}
