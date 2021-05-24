package erg.voidcraft.common.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import erg.voidcraft.common.block.BlockOverworldVoidOre;
import net.minecraft.block.BlockState;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.Entity;
import net.minecraft.util.Util;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.Vector;
import java.util.stream.Stream;

public class CommandFindNearestOre {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> findNearestOreCommand =
                Commands.literal("findvoidore")
                .requires((commandSource) -> commandSource.hasPermissionLevel(1))
                .executes(commandContext -> sendMessage(commandContext, findOre(commandContext)));
        dispatcher.register(findNearestOreCommand);
    }

    static int sendMessage(CommandContext<CommandSource> commandContext, String message) throws CommandSyntaxException {
        TranslationTextComponent text = new TranslationTextComponent("chat.type.announcement",
                commandContext.getSource().getDisplayName(), new StringTextComponent(message));

        Entity source = commandContext.getSource().getEntity();

        if(source != null) {
            commandContext.getSource().getServer().getPlayerList().func_232641_a_(text, ChatType.CHAT, source.getUniqueID());
        } else {
            commandContext.getSource().getServer().getPlayerList().func_232641_a_(text, ChatType.SYSTEM, Util.DUMMY_UUID);
        }

        return 1;
    }

    static String findOre(CommandContext<CommandSource> ctx) {

        World sourceWorld = ctx.getSource().getEntity().getEntityWorld();
        Vector3d sourcePosition = ctx.getSource().getPos();

        BlockPos corner1 = new BlockPos(sourcePosition.x - 128, 1, sourcePosition.z - 128);
        int corner_y = 8;
        if(sourceWorld.getDimensionKey().getLocation().toString().equals("minecraft:the_nether")) {
            corner_y = 10;
        } else if (sourceWorld.getDimensionKey().getLocation().toString().equals("minecraft:the_end")) {
            corner_y = 70;
        }
        BlockPos corner2 = new BlockPos(sourcePosition.x + 128, corner_y, sourcePosition.z + 128);

        double minDistance = 10000;
        BlockPos closest = null;

        for(int x = corner1.getX(); x <= corner2.getX(); x++) {
            for(int y = corner1.getY(); y <= corner2.getY(); y++) {
                for(int z = corner1.getZ(); z <= corner2.getZ(); z++) {
                    BlockPos probe = new BlockPos(x, y, z);
                    if(sourceWorld.isAreaLoaded(probe, 0)) {
                        if(sourceWorld.getBlockState(probe).getBlock() instanceof BlockOverworldVoidOre) {
                            double distance = oreDistance(probe, sourcePosition);
                            if(distance < minDistance) {
                                minDistance = distance;
                                closest = new BlockPos(x, y, z);
                            }
                        }
                    }
                }
            }
        }

        String message;

        if(closest == null) {
            message = "No Void Ore found within 8 chunks";
        } else {
            message = "Closest Void Ore: " +
                    (closest.getX() + 1) + " " +
                    (closest.getY() + 1) + " " +
                    closest.getZ();
        }
        return message;
    }

    private static double oreDistance(BlockPos ore, Vector3d source) {
        double x = Math.pow(ore.getX() - source.getX(), 2);
        double y = Math.pow(ore.getY() - source.getY(), 2);
        double z = Math.pow(ore.getZ() - source.getZ(), 2);
        return Math.sqrt(x + y + z);
    }

}
