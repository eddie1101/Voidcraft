package erg.voidcraft.datagen;

import erg.voidcraft.common.Voidcraft;
import erg.voidcraft.common.init.VoidcraftBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStates extends BlockStateProvider {

    public BlockStates(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, Voidcraft.MODID, helper);
    }

    @Override
    protected void registerStatesAndModels() {

        simpleBlock(VoidcraftBlocks.VOID_BLOCK);
        simpleBlock(VoidcraftBlocks.VOID_ORE);
        simpleBlock(VoidcraftBlocks.END_VOID_ORE);
        simpleBlock(VoidcraftBlocks.NETHER_VOID_ORE);
        simpleBlock(VoidcraftBlocks.PORTAL_BASE);

    }

}
