package erg.basicportals.common.tileentities;

import erg.basicportals.StartupCommon;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public class TileEntityPortalBase extends TileEntity {

    private BlockPos destinationBlockPos = new BlockPos(0, 0, 0);

    public TileEntityPortalBase() {
        super(StartupCommon.tileEntityPortalBaseType);
    }

    @Override
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbt = new CompoundNBT();
        write(nbt);
        int tileEntityType = 69;
        return new SUpdateTileEntityPacket(this.destinationBlockPos, tileEntityType, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager network, SUpdateTileEntityPacket packet) {
        BlockState blockState = world.getBlockState(destinationBlockPos);
        read(blockState, packet.getNbtCompound());
    }

    @Override
    public void handleUpdateTag(BlockState blockState, CompoundNBT parentNBTTagCompound) {
        this.read(blockState, parentNBTTagCompound);
    }

    @Override
    public CompoundNBT write(CompoundNBT parentNBTTagCompound) {
        super.write(parentNBTTagCompound);

        if(this.destinationBlockPos != null) {
            CompoundNBT blockPosNBT = new CompoundNBT();
            blockPosNBT.putInt("x", this.destinationBlockPos.getX());
            blockPosNBT.putInt("y", this.destinationBlockPos.getY());
            blockPosNBT.putInt("z", this.destinationBlockPos.getZ());
            parentNBTTagCompound.put("destinationBlockPos", blockPosNBT);
        }

        return parentNBTTagCompound;
    }

    @Override
    public void read(BlockState blockState, CompoundNBT parentNBTTagCompound) {
        super.read(blockState, parentNBTTagCompound);

        BlockPos pos;

        CompoundNBT blockPosNBT = parentNBTTagCompound.getCompound("destinationBlockPos");

        if(blockPosNBT.contains("x") && blockPosNBT.contains("y") && blockPosNBT.contains("z")) {
            pos = new BlockPos(blockPosNBT.getInt("x"), blockPosNBT.getInt("y"), blockPosNBT.getInt("z"));
            this.destinationBlockPos = pos;
        }

    }

    public void setDestinationBlockPos(BlockPos destinationBlockPos) {
        this.destinationBlockPos = destinationBlockPos;
    }

    public BlockPos getDestinationBlockPos() {
        return this.destinationBlockPos;
    }

}
