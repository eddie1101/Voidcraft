package erg.voidcraft.datagen;

import erg.voidcraft.common.init.VoidcraftBlocks;
import erg.voidcraft.common.init.VoidcraftEntities;
import erg.voidcraft.common.init.VoidcraftItems;
import net.minecraft.data.DataGenerator;

public class LootTables extends BaseLootTableProvider {

    public LootTables(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void addTables() {

        blockLootTable.put(VoidcraftBlocks.PORTAL_BASE, createStandardTable("block_portal_base", VoidcraftBlocks.PORTAL_BASE));
        blockLootTable.put(VoidcraftBlocks.VOID_BLOCK, createStandardTable("block_void", VoidcraftBlocks.VOID_BLOCK));
        blockLootTable.put(VoidcraftBlocks.CRYSTAL_CORE, createStandardTable("block_crystal_core", VoidcraftBlocks.CRYSTAL_CORE));

        blockLootTable.put(VoidcraftBlocks.VOID_ORE, createFortuneOreTable("block_void_ore", VoidcraftItems.VOID_CRYSTAL, VoidcraftBlocks.VOID_ORE));
        blockLootTable.put(VoidcraftBlocks.NETHER_VOID_ORE, createFortuneOreTable("block_nether_void_ore", VoidcraftItems.VOID_CRYSTAL, VoidcraftBlocks.NETHER_VOID_ORE));
        blockLootTable.put(VoidcraftBlocks.END_VOID_ORE, createFortuneOreTable("block_end_void_ore", VoidcraftItems.VOID_CRYSTAL, VoidcraftBlocks.END_VOID_ORE));

        entityLootTable.put(VoidcraftEntities.VOID_LURKER, createEntityTable("entity_void_lurker", VoidcraftItems.VOID_PEARL));

    }

}
