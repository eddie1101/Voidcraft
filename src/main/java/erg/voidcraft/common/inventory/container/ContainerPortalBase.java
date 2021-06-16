package erg.voidcraft.common.inventory.container;

import erg.voidcraft.common.init.VoidcraftContainers;
import erg.voidcraft.common.inventory.InventoryPortalBaseContents;
import erg.voidcraft.common.inventory.container.slot.SlotPortalBase;
import erg.voidcraft.common.tile.TilePortalBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.items.wrapper.PlayerInvWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ContainerPortalBase extends Container {

    private static final Logger LOGGER = LogManager.getLogger();

    //There's only one slot so most of this is extraneous but im keeping it for reference in case
    //I make an inventory that actually has more than one slot
    private static final int NUM_HOTBAR_SLOTS = 9;
    private static final int NUM_INV_ROWS = 3;
    private static final int NUM_INV_COLS = 9;
    private static final int NUM_INV_SLOTS = NUM_INV_COLS * NUM_INV_ROWS;
    private static final int NUM_TOTAL_SLOTS = NUM_INV_SLOTS + NUM_HOTBAR_SLOTS;

    private static final int FIRST_INV_SLOT_INDEX = 0;
    private static final int TE_SLOT_INDEX = FIRST_INV_SLOT_INDEX + NUM_TOTAL_SLOTS;
    private static final int NUM_TE_SLOTS = TilePortalBase.NUM_SLOTS;

    private static final int PLAYER_INVENTORY_XPOS = 8;

    public static final int TE_INV_YPOS = 20;
    public static final int PLAYER_INVENTORY_YPOS = 51;

    final int SLOT_X_SPACING = 18;
    final int SLOT_Y_SPACING = 18;
    final int HOTBAR_XPOS = 8;
    final int HOTBAR_Y_POS = 109;

    final int TE_INV_XPOS = 8;

    private InventoryPortalBaseContents portalContents;

    private TileEntity te;
    private IItemHandler playerInv;
    private PlayerEntity player;
    public BlockPos pos = null;

    public static ContainerPortalBase createContainerServerSide(int windowID, PlayerInventory playerInventory, InventoryPortalBaseContents portalContents) {
        return new ContainerPortalBase(windowID, playerInventory, portalContents);
    }

    public static ContainerPortalBase createContainerClientSide(int windowID, PlayerInventory playerInventory, PacketBuffer data) {

        InventoryPortalBaseContents portalContents = InventoryPortalBaseContents.createForClientSideContainer(TilePortalBase.NUM_SLOTS);

        return new ContainerPortalBase(windowID, playerInventory, portalContents, data.readBlockPos());
    }

    public ContainerPortalBase(int windowID, PlayerInventory playerInv, PlayerEntity player, World world, BlockPos pos) {
        super(VoidcraftContainers.PORTAL_BASE, windowID);
        te = world.getBlockEntity(pos);
        this.player = player;
        this.playerInv = new InvWrapper(playerInv);
        this.pos = pos;

        layoutPlayerInventorySlots(playerInv);

        addSlot(new SlotPortalBase(portalContents, 0, TE_INV_XPOS + SLOT_X_SPACING * 4, TE_INV_YPOS));

    }

    private ContainerPortalBase(int WindowID, PlayerInventory playerInventory, InventoryPortalBaseContents portalContents, BlockPos pos) {
        this(WindowID, playerInventory, portalContents);
        this.pos = pos;
    }

    private ContainerPortalBase(int windowID, PlayerInventory playerInventory, InventoryPortalBaseContents portalContents) {
        super(VoidcraftContainers.PORTAL_BASE, windowID);
        if(VoidcraftContainers.PORTAL_BASE == null) {
            throw new IllegalStateException("Must initialize containerTypeBasic before constructing ContainerPortalBase");
        }

        PlayerInvWrapper playerInventoryForge = new PlayerInvWrapper(playerInventory);

        layoutPlayerInventorySlots(playerInventory);

        this.portalContents = portalContents;
        this.pos = pos;

        if(NUM_TE_SLOTS != portalContents.getContainerSize()) {
            LOGGER.warn("Portal Base inventory size is inconsistent");
        }

        addSlot(new SlotPortalBase(portalContents, 0, TE_INV_XPOS + SLOT_X_SPACING * 4, TE_INV_YPOS));
    }

    @Override
    public boolean stillValid(PlayerEntity playerIn) {
        return portalContents.stillValid(playerIn);
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity playerEntity, int sourceSlotIndex) {


        //ALL THIS SHIT IS UNNECESSARY AND SHOULD GET REDONE
        //THERE IS ONLY ONE SLOT MOST OF THIS LOGIC IS UNNECESSARY


        Slot sourceSlot = slots.get(sourceSlotIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (sourceSlotIndex >= FIRST_INV_SLOT_INDEX && sourceSlotIndex < FIRST_INV_SLOT_INDEX + NUM_TOTAL_SLOTS) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_SLOT_INDEX, TE_SLOT_INDEX + NUM_TE_SLOTS, false)){
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (sourceSlotIndex >= TE_SLOT_INDEX && sourceSlotIndex < TE_SLOT_INDEX + NUM_TE_SLOTS) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, FIRST_INV_SLOT_INDEX, FIRST_INV_SLOT_INDEX + NUM_TOTAL_SLOTS, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            return ItemStack.EMPTY;
        }

        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }

        sourceSlot.onTake(playerEntity, sourceStack);
        return copyOfSourceStack;
    }

    private void layoutPlayerInventorySlots(PlayerInventory playerInventory) {


        PlayerInvWrapper playerInventoryForge = new PlayerInvWrapper(playerInventory);

        for(int i = 0; i < NUM_HOTBAR_SLOTS; i++) {
            addSlot(new SlotItemHandler(playerInventoryForge, i, HOTBAR_XPOS + SLOT_X_SPACING * i, HOTBAR_Y_POS));
        }

        for(int i = 0; i < NUM_INV_ROWS; i++) {
            for(int n = 0; n < NUM_INV_COLS; n++) {
                int slotNumber = NUM_HOTBAR_SLOTS + (i * NUM_INV_COLS) + n;
                int xpos = PLAYER_INVENTORY_XPOS + n * SLOT_X_SPACING;
                int ypos = PLAYER_INVENTORY_YPOS + i * SLOT_Y_SPACING;
                addSlot(new SlotItemHandler(playerInventoryForge, slotNumber,  xpos, ypos));
            }
        }
    }

}
