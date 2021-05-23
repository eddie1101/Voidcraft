package erg.voidcraft.common.network;

import erg.voidcraft.common.tile.TilePortalBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

@Deprecated
public class PacketSetPortalDestination {

    private ItemStack item;
    private BlockPos pos;

    public PacketSetPortalDestination(ItemStack item, BlockPos pos) {
        this.item = item;
        this.pos = pos;
    }

    public void encode(PacketBuffer buf) {
        buf.writeBlockPos(pos);
        buf.writeItemStack(item);
    }

    public static PacketSetPortalDestination decode(PacketBuffer buf) {
        BlockPos pos = buf.readBlockPos();
        ItemStack item = buf.readItemStack();
        return new PacketSetPortalDestination(item, pos);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        if(!(ctx.get().getSender().getServerWorld().getTileEntity(pos) instanceof TilePortalBase)) {
            ctx.get().setPacketHandled(true);
            return;
        }

        ctx.get().enqueueWork(() -> {
            ServerWorld world = ctx.get().getSender().getServerWorld();

            TilePortalBase tilePortalBase = (TilePortalBase)world.getTileEntity(this.pos);

            CompoundNBT itemTag = item.getTag();

            if(!itemTag.contains("destinationBlockPos")) {
                return;
            }

            CompoundNBT blockPosTag = itemTag.getCompound("destinationBlockPos");

            int x = blockPosTag.getInt("x");
            int y = blockPosTag.getInt("y");
            int z = blockPosTag.getInt("z");


            BlockPos destination = new BlockPos(x, y, z);

        });
        ctx.get().setPacketHandled(true);
    }

}
