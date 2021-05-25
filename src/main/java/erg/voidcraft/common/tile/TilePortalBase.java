package erg.voidcraft.common.tile;

import erg.voidcraft.common.inventory.container.ContainerPortalBase;
import erg.voidcraft.common.init.VoidcraftTiles;
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
        portalBaseContents = InventoryPortalBaseContents.createForTileEntity(NUM_SLOTS, this::canPlayerAccessInventory, this::setChanged);
    }

    public boolean canPlayerAccessInventory(PlayerEntity player) {
        if(this.level.getBlockEntity(worldPosition) != this) return false;
        final double X_CENTRE_OFFSET = 0.5;
        final double Y_CENTRE_OFFSET = 0.5;
        final double Z_CENTRE_OFFSET = 0.5;
        final double MAXIMUM_DISTANCE_SQ = 8.0 * 8.0;
        return player.distanceToSqr(worldPosition.getX() + X_CENTRE_OFFSET, worldPosition.getY() + Y_CENTRE_OFFSET, worldPosition.getZ() + Z_CENTRE_OFFSET) < MAXIMUM_DISTANCE_SQ;
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
    public CompoundNBT save(CompoundNBT parentNBTTagCompound) {
        super.save(parentNBTTagCompound);

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
    public void load(BlockState blockState, CompoundNBT parentNBTTagCompound) {
        super.load(blockState, parentNBTTagCompound);

        CompoundNBT inventoryNBT = parentNBTTagCompound.getCompound(PORTAL_CONTENTS_INV_TAG);
        portalBaseContents.deserializeNBT(inventoryNBT);

        if(portalBaseContents.getContainerSize() != NUM_SLOTS) {
            throw new IllegalArgumentException("Corrupted NBT: Unexpected number of inventory slots for Portal Base.");
        }

    }

    public void dropAllContents(World world, BlockPos blockPos) {
        InventoryHelper.dropContents(world, blockPos, portalBaseContents);
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
