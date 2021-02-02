package erg.voidcraft.common.network;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.Optional;

public class VoidcraftPacketHandler {

    private static int ID = 0;
    public static int nextID() {
        return ID++;
    }

    private static final String PROTOCOL_VERSION = "1";
    public static SimpleChannel channel;

    public static void registerMessages() {
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
    }


}
