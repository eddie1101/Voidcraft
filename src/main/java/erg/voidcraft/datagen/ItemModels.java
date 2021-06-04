package erg.voidcraft.datagen;

import erg.voidcraft.common.Voidcraft;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModels extends ItemModelProvider {

    public ItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Voidcraft.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        final ModelFile ITEM_GENERATED  = getExistingFile(mcLoc("item/generated"));
        final ModelFile ITEM_TEMPLATE_SPAWN_EGG = getExistingFile(mcLoc("item/template_spawn_egg"));
        final ModelFile ITEM_HANDHELD = getExistingFile(mcLoc("item/handheld"));

        withExistingParent("block_void", modLoc("block/block_void"));
        withExistingParent("block_void_ore", modLoc("block/block_void_ore"));
        withExistingParent("block_nether_void_ore", modLoc("block/block_nether_void_ore"));
        withExistingParent("block_end_void_ore", modLoc("block/block_end_void_ore"));
        withExistingParent("block_portal_base", modLoc("block/block_portal_base"));

        builderWithParent(ITEM_GENERATED, "item_void_crystal");
        builderWithParent(ITEM_GENERATED, "item_crystal_catalyst");
        builderWithParent(ITEM_GENERATED, "item_void_pearl");
        builderWithParent(ITEM_GENERATED, "item_destination_lodestar");
        builderWithParent(ITEM_GENERATED, "item_dimensional_lodestar");
        builderWithParent(ITEM_GENERATED, "item_void_helmet");
        builderWithParent(ITEM_GENERATED, "item_void_chestplate");
        builderWithParent(ITEM_GENERATED, "item_void_leggings");
        builderWithParent(ITEM_GENERATED, "item_void_boots");

        builderWithParent(ITEM_HANDHELD, "item_void_chisel");
        builderWithParent(ITEM_HANDHELD, "item_void_pickaxe");
        builderWithParent(ITEM_HANDHELD, "item_void_shovel");
        builderWithParent(ITEM_HANDHELD, "item_void_sword");
        builderWithParent(ITEM_HANDHELD, "item_void_axe");
        builderWithParent(ITEM_HANDHELD, "item_void_hoe");

        getBuilder("item_void_lurker_spawn_egg").parent(ITEM_TEMPLATE_SPAWN_EGG);
    }

    private void builderWithParent(ModelFile parent, String name) {
        getBuilder(name).parent(parent).texture("layer0", "item/" + name);
    }

}
