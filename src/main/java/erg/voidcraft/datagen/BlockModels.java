package erg.voidcraft.datagen;

import erg.voidcraft.common.Voidcraft;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockModels extends BlockModelProvider {

    public BlockModels(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, Voidcraft.MODID, helper);
    }

    @Override
    protected void registerModels() {

        final ModelFile PARENT_CUBE = getExistingFile(mcLoc("block/cube"));

        builderWithParent(PARENT_CUBE, "block_void");
        builderWithParent(PARENT_CUBE, "block_crystal_core");
        builderWithParent(PARENT_CUBE, "block_void_ore");
        builderWithParent(PARENT_CUBE, "block_end_void_ore");
        builderWithParent(PARENT_CUBE, "block_nether_void_ore");
        builderWithParent(PARENT_CUBE, "block_portal_base");

    }

    private void builderWithParent(ModelFile parent, String name) {
        getBuilder(name).parent(parent)
                .texture("down", "block/" + name)
                .texture("up", "block/" + name)
                .texture("north", "block/" + name)
                .texture("east", "block/" + name)
                .texture("south", "block/" + name)
                .texture("west", "block/" + name)
                .texture("particle", "block/" + name);
    }
}
