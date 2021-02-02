package erg.voidcraft.common.init;

import erg.voidcraft.common.block.*;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ObjectHolder;

import static erg.voidcraft.common.util.Util.setBlockName;

public class VoidcraftBlocks {

    @ObjectHolder("voidcraft:block_void")
    public static final BlockVoid blockVoid = null;
    @ObjectHolder("voidcraft:block_portal_base")
    public static final BlockPortalBase blockPortalBase = null;
    @ObjectHolder("voidcraft:block_void_ore")
    public static final BlockVoidOre blockVoidOre = null;
    @ObjectHolder("voidcraft:block_nether_void_ore")
    public static final BlockNetherVoidOre blockNetherVoidOre = null;
    @ObjectHolder("voidcraft:block_end_void_ore")
    public static final BlockEndVoidOre blockEndVoidOre = null;

    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(
                setBlockName(new BlockVoid(), "block_void"),
                setBlockName(new BlockPortalBase(), "block_portal_base"),
                setBlockName(new BlockVoidOre(), "block_void_ore"),
                setBlockName(new BlockNetherVoidOre(), "block_nether_void_ore"),
                setBlockName(new BlockEndVoidOre(), "block_end_void_ore")
        );
    }

}
