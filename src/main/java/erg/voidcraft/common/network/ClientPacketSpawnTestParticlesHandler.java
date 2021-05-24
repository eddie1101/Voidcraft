package erg.voidcraft.common.network;

import erg.voidcraft.common.particle.ParticleDataMiasma;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.LogicalSidedProvider;
import net.minecraftforge.fml.network.NetworkEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

public class ClientPacketSpawnTestParticlesHandler {

    private static Logger LOGGER = LogManager.getLogger();

    public static void onMessageReceived(PacketSpawnTestParticles message, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context ctx = ctxSupplier.get();
        LogicalSide sideReceived = ctx.getDirection().getReceptionSide();
        ctx.setPacketHandled(true);

        Optional<ClientWorld> clientWorld = LogicalSidedProvider.CLIENTWORLD.get(ctx.getDirection().getReceptionSide());

        if (sideReceived != LogicalSide.CLIENT) {
            LOGGER.warn("SpawnTestParticles message received on wrong side!" + ctx.getDirection().getReceptionSide());
            return;
        }

        ctx.enqueueWork(() -> processMessage(message, clientWorld.get()));
    }

    public static void processMessage(PacketSpawnTestParticles message, ClientWorld world) {

        BlockPos pos = message.getPos();
        Random rand = new Random();


        double xpos = pos.getX();
        double ypos = pos.getY() + 1;
        double zpos = pos.getZ();

        double xvel = 0;
        double yvel = 0;
        double zvel = 0;

        final double POSITION_WOBBLE = 0.01;

        for (int i = 0; i < 1000; i++) {

            Color tint = new Color(0.5f, 0.5f, 0.5f);
            final double MIN_DIAMETER = 0.05;
            final double MAX_DIAMETER = 0.40;
            double diameter = MIN_DIAMETER + (MAX_DIAMETER - MIN_DIAMETER) * rand.nextDouble();

            ParticleDataMiasma miasma = new ParticleDataMiasma(tint, diameter);

            zpos += POSITION_WOBBLE * (rand.nextDouble() - 0.5);
            xpos += POSITION_WOBBLE * (rand.nextDouble() - 0.5);

            xvel += rand.nextDouble() - 0.5;
            yvel += rand.nextDouble() - 0.5;
            zvel += rand.nextDouble() - 0.5;

            xvel /= 10;
            yvel /= 10;
            zvel /= 10;

            world.addParticle(miasma, false,
                    xpos, ypos, zpos, xvel, yvel, zvel);
        }
    }

}
