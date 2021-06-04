package erg.voidcraft.datagen;

import erg.voidcraft.common.Voidcraft;
import erg.voidcraft.common.init.VoidcraftBlocks;
import net.minecraft.data.DataGenerator;

public class LootTables extends BaseLootTableProvider {

    public LootTables(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void addTables() {
        lootTables.put(VoidcraftBlocks.PORTAL_BASE, createStandardTable("block_portal_base", VoidcraftBlocks.PORTAL_BASE));
    }

}
