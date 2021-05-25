package erg.voidcraft.common.network;

import erg.voidcraft.common.item.ItemDestinationLodestar;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

@Deprecated
public class PacketSetLodestarDestination {

    private final BlockPos pos;
    private final ItemStack item;

    public PacketSetLodestarDestination(ItemStack item, BlockPos pos) {
        this.pos = pos;
        this.item = item;
    }

    public void encode(PacketBuffer buf) {
        buf.writeBlockPos(this.pos);
        buf.writeItem(this.item);
    }

    public static PacketSetLodestarDestination decode(PacketBuffer buf) {
        BlockPos pos = buf.readBlockPos();
        ItemStack item = buf.readItem();
        return new PacketSetLodestarDestination(item, pos);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        if(!(this.item.getItem() instanceof ItemDestinationLodestar)){
            ctx.get().setPacketHandled(true);
            return;
        }
        ctx.get().enqueueWork(() -> {
            CompoundNBT parent = item.getTag();

            if(parent == null) parent = new CompoundNBT();

            CompoundNBT child = new CompoundNBT();

            child.putInt("x", pos.getX());
            child.putInt("y", pos.getY());
            child.putInt("z", pos.getZ());

            parent.put("destinationBlockPos", child);

            item.setTag(parent);

            ctx.get().getSender().sendMessage(new StringTextComponent("Stored location:\nX=" + pos.getX() + "\nY=" + pos.getY() + "\nZ=" + pos.getZ() + "\nin LODESTAR"), ctx.get().getSender().getUUID());
        });
        ctx.get().setPacketHandled(true);
    }

}
