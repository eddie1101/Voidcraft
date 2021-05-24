package erg.voidcraft.common.network;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class PacketSpawnTestParticles {

    private final BlockPos pos;

    public PacketSpawnTestParticles(BlockPos pos) {
        this.pos = pos;
    }

    public BlockPos getPos() {
        return this.pos;
    }

    public void encode(PacketBuffer buf) {
        buf.writeBlockPos(this.pos);
    }

    public static PacketSpawnTestParticles decode(PacketBuffer buf) {
        BlockPos pos = buf.readBlockPos();
        return new PacketSpawnTestParticles(pos);
    }

}
