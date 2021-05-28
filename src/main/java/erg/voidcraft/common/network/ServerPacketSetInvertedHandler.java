package erg.voidcraft.common.network;

import erg.voidcraft.common.block.BlockPortalBase;
import erg.voidcraft.common.particle.ParticleDataMiasma;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.LogicalSidedProvider;
import net.minecraftforge.fml.network.NetworkEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

public class ServerPacketSetInvertedHandler {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void onMessageReceived(final PacketSetInverted message, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context ctx = ctxSupplier.get();
        LogicalSide sideReceived = ctx.getDirection().getReceptionSide();
        ctx.setPacketHandled(true);

        if(ctx.getSender() == null) {
            LOGGER.warn("SetInverted message does not have a sender!");
            return;
        }

//        Optional<ClientWorld> clientWorld = LogicalSidedProvider.CLIENTWORLD.get(ctx.getDirection().getReceptionSide());
        ServerPlayerEntity player = ctx.getSender();
        ServerWorld world = player.getLevel();

        if (sideReceived != LogicalSide.SERVER) {
            LOGGER.warn("SetInverted message received on wrong side!" + ctx.getDirection().getReceptionSide());
            return;
        }

        ctx.enqueueWork(() -> processMessage(message, world));
    }

    /*
    This is extremely annoying, but when a player is teleported to another dimension they will
    not see the particles spawn. This is because at the time the packet is dispatched, the player
    has not yet entered the target dimension, so they will not receive it. Not sure how to fix,
    but it would be a huge amount of work and not worth the effort given how small of a bug this
    is IMO
     */
    public static void processMessage(PacketSetInverted message, ServerWorld world) {
        BlockPos pos = message.getPos();

        BlockState state = world.getBlockState(pos);

        if(!(state.getBlock() instanceof BlockPortalBase)) {
            return;
        }
        boolean inverted = state.getValue(BlockPortalBase.INVERTED);
        world.setBlockAndUpdate(pos, state.setValue(BlockPortalBase.INVERTED, !inverted));
    }

}
