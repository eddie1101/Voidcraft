package erg.voidcraft.common.init;

import erg.voidcraft.common.network.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class VoidcraftPacketHandler {

    private static final Logger LOGGER = LogManager.getLogger();

    private static int ID = 0;
    public static int nextID() {
        return ID++;
    }

    private static final String PROTOCOL_VERSION = "1.0";
    public static SimpleChannel channel;

    @SubscribeEvent
    public static void registerMessages(FMLCommonSetupEvent event) {
        LOGGER.debug("Registering Network Messages...");
        channel = NetworkRegistry.newSimpleChannel(
                new ResourceLocation("voidcraft", "main"),
                () -> PROTOCOL_VERSION,
                PROTOCOL_VERSION::equals,
                PROTOCOL_VERSION::equals
                );

        channel.registerMessage(
                nextID(),
                PacketSetLodestarDestination.class,
                PacketSetLodestarDestination::encode,
                PacketSetLodestarDestination::decode,
                PacketSetLodestarDestination::handle,
                Optional.of(NetworkDirection.PLAY_TO_SERVER));

        channel.registerMessage(
                nextID(),
                PacketSetPortalDestination.class,
                PacketSetPortalDestination::encode,
                PacketSetPortalDestination::decode,
                PacketSetPortalDestination::handle,
                Optional.of(NetworkDirection.PLAY_TO_SERVER));

        channel.registerMessage(
                nextID(),
                PacketSpawnTeleportParticles.class,
                PacketSpawnTeleportParticles::encode,
                PacketSpawnTeleportParticles::decode,
                ClientPacketSpawnTeleportParticlesHandler::onMessageReceived,
                Optional.of(NetworkDirection.PLAY_TO_CLIENT));

        channel.registerMessage(
                nextID(),
                PacketSpawnTestParticles.class,
                PacketSpawnTestParticles::encode,
                PacketSpawnTestParticles::decode,
                ClientPacketSpawnTestParticlesHandler::onMessageReceived,
                Optional.of(NetworkDirection.PLAY_TO_CLIENT));

        channel.registerMessage(
                nextID(),
                PacketSetInverted.class,
                PacketSetInverted::encode,
                PacketSetInverted::decode,
                ServerPacketSetInvertedHandler::onMessageReceived,
                Optional.of(NetworkDirection.PLAY_TO_SERVER));
    }


}
