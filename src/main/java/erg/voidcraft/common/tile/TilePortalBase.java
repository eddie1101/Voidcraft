package erg.voidcraft.common.tile;

import erg.voidcraft.common.container.ContainerPortalBase;
import erg.voidcraft.common.inventory.InventoryPortalBaseContents;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class TilePortalBase extends TileEntity implements INamedContainerProvider {

//    private BlockPos destinationBlockPos;

    public static final int NUM_SLOTS = 1;
    public static final String PORTAL_CONTENTS_INV_TAG = "contents";

    private final InventoryPortalBaseContents portalBaseContents;

    public TilePortalBase() {
        super(VoidcraftTiles.tileEntityPortalBaseType);
        portalBaseContents = InventoryPortalBaseContents.createForTileEntity(NUM_SLOTS, this::canPlayerAccessInventory, this::markDirty);
    }

    public boolean canPlayerAccessInventory(PlayerEntity player) {
        if(this.world.getTileEntity(this.pos) != this) return false;
        final double X_CENTRE_OFFSET = 0.5;
        final double Y_CENTRE_OFFSET = 0.5;
        final double Z_CENTRE_OFFSET = 0.5;
        final double MAXIMUM_DISTANCE_SQ = 8.0 * 8.0;
        return player.getDistanceSq(pos.getX() + X_CENTRE_OFFSET, pos.getY() + Y_CENTRE_OFFSET, pos.getZ() + Z_CENTRE_OFFSET) < MAXIMUM_DISTANCE_SQ;
    }

    @Override
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbt = new CompoundNBT();
        write(nbt);
        int tileEntityType = 69;
        return new SUpdateTileEntityPacket(this.pos, tileEntityType, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager network, SUpdateTileEntityPacket packet) {
        BlockState blockState = world.getBlockState(pos);
        read(blockState, packet.getNbtCompound());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT nbtTagCompound = new CompoundNBT();
        write(nbtTagCompound);
        return nbtTagCompound;
    }

    @Override
    public void handleUpdateTag(BlockState blockState, CompoundNBT parentNBTTagCompound) {
        this.read(blockState, parentNBTTagCompound);
    }

    @Override
    public CompoundNBT write(CompoundNBT parentNBTTagCompound) {
        super.write(parentNBTTagCompound);

        CompoundNBT inventoryNBT = portalBaseContents.serializeNBT();
        parentNBTTagCompound.put(PORTAL_CONTENTS_INV_TAG, inventoryNBT);

//        if(this.destinationBlockPos != null) {
//            CompoundNBT blockPosNBT = new CompoundNBT();
//            blockPosNBT.putInt("x", this.destinationBlockPos.getX());
//            blockPosNBT.putInt("y", this.destinationBlockPos.getY());
//            blockPosNBT.putInt("z", this.destinationBlockPos.getZ());
//            parentNBTTagCompound.put("destinationBlockPos", blockPosNBT);
//        }

        return parentNBTTagCompound;
    }

    @Override
    public void read(BlockState blockState, CompoundNBT parentNBTTagCompound) {
        super.read(blockState, parentNBTTagCompound);

        CompoundNBT inventoryNBT = parentNBTTagCompound.getCompound(PORTAL_CONTENTS_INV_TAG);
        portalBaseContents.deserializeNBT(inventoryNBT);

        if(portalBaseContents.getSizeInventory() != NUM_SLOTS) {
            throw new IllegalArgumentException("Corrupted NBT: Unexpected number of inventory slots for Portal Base.");
        }

//        BlockPos pos;
//        CompoundNBT blockPosNBT = null;
//
//        if(parentNBTTagCompound.contains("destinationBlockPos")) {
//            blockPosNBT = parentNBTTagCompound.getCompound("destinationBlockPos");
//        }
//
//        if(blockPosNBT != null && blockPosNBT.contains("x") && blockPosNBT.contains("y") && blockPosNBT.contains("z")) {
//            pos = new BlockPos(blockPosNBT.getInt("x"), blockPosNBT.getInt("y"), blockPosNBT.getInt("z"));
//            this.destinationBlockPos = pos;
//        }

    }

    public void dropAllContents(World world, BlockPos blockPos) {
        InventoryHelper.dropInventoryItems(world, blockPos, portalBaseContents);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("container.voidcraft.container_portal_base");
    }

    @Nullable
    @Override
    public Container createMenu(int windowID, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return ContainerPortalBase.createContainerServerSide(windowID, playerInventory, portalBaseContents);
    }

    public InventoryPortalBaseContents getContents() {
        return this.portalBaseContents;
    }

}
