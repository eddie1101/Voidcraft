package erg.voidcraft.common.init;

import com.mojang.brigadier.CommandDispatcher;
import erg.voidcraft.common.command.CommandFindNearestOre;
import net.minecraft.command.CommandSource;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VoidcraftCommands {

    private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    public static void onRegisterCommandEvent(RegisterCommandsEvent event) {
        LOGGER.debug("Registering Commands...");
        CommandDispatcher<CommandSource> commandDispatcher = event.getDispatcher();
        CommandFindNearestOre.register(commandDispatcher);
    }

}
