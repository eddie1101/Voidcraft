package erg.voidcraft.common.init;

import com.mojang.brigadier.CommandDispatcher;
import erg.voidcraft.common.command.CommandFindNearestOre;
import net.minecraft.command.CommandSource;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class VoidcraftCommands {


    @SubscribeEvent
    public static void onRegisterCommandEvent(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSource> commandDispatcher = event.getDispatcher();
        CommandFindNearestOre.register(commandDispatcher);
    }

}
