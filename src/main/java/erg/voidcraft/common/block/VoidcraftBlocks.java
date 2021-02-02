package erg.voidcraft.common.block;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;

import static erg.voidcraft.common.util.Util.setBlockName;

public class VoidcraftBlocks {

    public static BlockVoid blockVoid;
    public static BlockPortalBase blockPortalBase;
    public static BlockVoidOre blockVoidOre;
    public static BlockNetherVoidOre blockNetherVoidOre;
    public static BlockEndVoidOre blockEndVoidOre;

    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
        blockVoid = (BlockVoid)setBlockName(new BlockVoid(), "block_void");
        blockPortalBase = (BlockPortalBase)setBlockName(new BlockPortalBase(), "block_portal_base");
        blockVoidOre = (BlockVoidOre)setBlockName(new BlockVoidOre(), "block_void_ore");
        blockNetherVoidOre = (BlockNetherVoidOre)setBlockName(new BlockNetherVoidOre(), "block_nether_void_ore");
        blockEndVoidOre = (BlockEndVoidOre)setBlockName(new BlockEndVoidOre(), "block_end_void_ore");

        event.getRegistry().registerAll(
                blockVoid,
                blockPortalBase,
                blockVoidOre,
                blockNetherVoidOre,
                blockEndVoidOre
        );
    }

}
