package erg.voidcraft.common.network;

import erg.voidcraft.common.particle.MiasmaParticleData;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.LogicalSidedProvider;
import net.minecraftforge.fml.network.NetworkEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

public class ServerPacketSpawnTeleportParticlesHandler {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void onMessageReceived(final PacketSpawnTeleportParticles message, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context ctx = ctxSupplier.get();
        LogicalSide sideReceived = ctx.getDirection().getReceptionSide();
        ctx.setPacketHandled(true);

        Optional<ClientWorld> clientWorld = LogicalSidedProvider.CLIENTWORLD.get(ctx.getDirection().getReceptionSide());

        if (sideReceived != LogicalSide.CLIENT) {
            LOGGER.warn("AirstrikeMessageToServer received on wrong side:" + ctx.getDirection().getReceptionSide());
            return;
        }

        ctx.enqueueWork(() -> processMessage(message, clientWorld.get()));
    }

    /*
    This is extremely annoying, but when a player is teleported to another dimension they will
    not see the particles spawn. This is because at the time the packet is dispatched, the player
    has not yet entered the target dimension, so they will not receive it. Not sure how to fix,
    but it would be a huge amount of work and not worth the effort given how small of a bug this
    is IMO
     */
    public static void processMessage(PacketSpawnTeleportParticles message, ClientWorld client) {

        BlockPos pos = message.getPos();

        final Random rand = new Random();

        for (int i = 0; i < 100; i++) {

            Color tint = new Color(0.5f, 0.5f, 0.5f);
            final double MIN_DIAMETER = 0.05;
            final double MAX_DIAMETER = 0.40;
            double diameter = MIN_DIAMETER + (MAX_DIAMETER - MIN_DIAMETER) * rand.nextDouble();

            MiasmaParticleData miasma = new MiasmaParticleData(tint, diameter);

            double xvel = (rand.nextDouble() - 0.5) / 10;
            double yvel = (rand.nextDouble() - 0.5) / 10;
            double zvel = (rand.nextDouble() - 0.5) / 10;

            client.addParticle(miasma, false,
                    pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, xvel, yvel, zvel);
        }
    }

}
