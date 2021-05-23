package erg.voidcraft.common.network;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class PacketSpawnTeleportParticles {

    private final ResourceLocation dimension;
    private final BlockPos pos;

    public PacketSpawnTeleportParticles(ResourceLocation dimension, BlockPos pos) {
        this.dimension = dimension;
        this.pos = pos;
    }

    public ResourceLocation getDimension() {
        return this.dimension;
    }

    public BlockPos getPos() {
        return this.pos;
    }

    public void encode(PacketBuffer buf) {
        buf.writeResourceLocation(this.dimension);
        buf.writeBlockPos(this.pos);
    }

    public static PacketSpawnTeleportParticles decode(PacketBuffer buf) {
        ResourceLocation dimension = buf.readResourceLocation();
        BlockPos pos = buf.readBlockPos();
        return new PacketSpawnTeleportParticles(dimension, pos);
    }

}
