package erg.voidcraft.datagen;

import erg.voidcraft.common.init.VoidcraftBlocks;
import erg.voidcraft.common.init.VoidcraftItems;
import net.minecraft.data.DataGenerator;

public class LootTables extends BaseLootTableProvider {

    public LootTables(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void addTables() {

        lootTables.put(VoidcraftBlocks.PORTAL_BASE, createStandardTable("block_portal_base", VoidcraftBlocks.PORTAL_BASE));
        lootTables.put(VoidcraftBlocks.VOID_BLOCK, createStandardTable("block_void", VoidcraftBlocks.VOID_BLOCK));

        lootTables.put(VoidcraftBlocks.VOID_ORE, createOre("block_void_ore", VoidcraftItems.VOID_CRYSTAL, VoidcraftBlocks.VOID_ORE));
        lootTables.put(VoidcraftBlocks.NETHER_VOID_ORE, createOre("block_nether_void_ore", VoidcraftItems.VOID_CRYSTAL, VoidcraftBlocks.NETHER_VOID_ORE));
        lootTables.put(VoidcraftBlocks.END_VOID_ORE, createOre("block_end_void_ore", VoidcraftItems.VOID_CRYSTAL, VoidcraftBlocks.END_VOID_ORE));

    }

}
