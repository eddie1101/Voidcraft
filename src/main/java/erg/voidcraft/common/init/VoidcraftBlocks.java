package erg.voidcraft.common.init;

import erg.voidcraft.common.block.*;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ObjectHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static erg.voidcraft.common.util.SetRegistryName.setBlockName;

public class VoidcraftBlocks {

    private static final Logger LOGGER = LogManager.getLogger();

    @ObjectHolder("voidcraft:block_void")
    public static final BlockVoid blockVoid = null;
    @ObjectHolder("voidcraft:block_portal_base")
    public static final BlockPortalBase blockPortalBase = null;
    @ObjectHolder("voidcraft:block_void_ore")
    public static final BlockOverworldVoidOre blockVoidOre = null;
    @ObjectHolder("voidcraft:block_nether_void_ore")
    public static final BlockNetherVoidOre blockNetherVoidOre = null;
    @ObjectHolder("voidcraft:block_end_void_ore")
    public static final BlockEndVoidOre blockEndVoidOre = null;

    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
        LOGGER.debug("Registering Blocks...");
        event.getRegistry().registerAll(
                setBlockName(new BlockVoid(), "block_void"),
                setBlockName(new BlockPortalBase(), "block_portal_base"),
                setBlockName(new BlockOverworldVoidOre(), "block_void_ore"),
                setBlockName(new BlockNetherVoidOre(), "block_nether_void_ore"),
                setBlockName(new BlockEndVoidOre(), "block_end_void_ore")
        );
    }

}
