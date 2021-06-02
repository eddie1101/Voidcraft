package erg.voidcraft.datagen;

import erg.voidcraft.common.Voidcraft;
import erg.voidcraft.common.init.VoidcraftBlocks;
import erg.voidcraft.common.init.VoidcraftItems;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

public class Recipes extends RecipeProvider {

    public Recipes(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {

        //Void Block Recipe
        ShapedRecipeBuilder.shaped(VoidcraftBlocks.VOID_BLOCK, 1)
                .pattern("VVV")
                .pattern("VVV")
                .pattern("VVV")
                .define('V', VoidcraftItems.VOID_CRYSTAL)
                .unlockedBy("has_item", has(VoidcraftItems.VOID_CRYSTAL))
                .save(consumer);


        //Portal Base Crafting Recipe
        ShapedRecipeBuilder.shaped(VoidcraftBlocks.PORTAL_BASE, 1)
                .pattern("OVO")
                .pattern("VBV")
                .pattern("OVO")
                .define('V', VoidcraftItems.VOID_CRYSTAL)
                .define('O', Blocks.OBSIDIAN)
                .define('B', VoidcraftBlocks.VOID_BLOCK)
                .unlockedBy("has_item", has(VoidcraftItems.VOID_CRYSTAL))
                .save(consumer);

        //Crystal Catalyst Recipe
        ShapedRecipeBuilder.shaped(VoidcraftItems.CRYSTAL_CATALYST, 1)
                .pattern("DDD")
                .pattern("DVD")
                .pattern("DDD")
                .define('V', VoidcraftBlocks.VOID_BLOCK)
                .define('D', Items.DIAMOND)
                .unlockedBy("has_item", has(VoidcraftItems.VOID_CRYSTAL))
                .save(consumer);

        //Destination Lodestar Crafting Recipe
        ShapedRecipeBuilder.shaped(VoidcraftItems.DESTINATION_LODESTAR, 1)
                .pattern("DSD")
                .pattern("SVS")
                .pattern("DSD")
                .define('V', VoidcraftItems.VOID_CRYSTAL)
                .define('D', Items.DIAMOND)
                .define('S', Blocks.STONE)
                .unlockedBy("has_item", has(VoidcraftBlocks.PORTAL_BASE))
                .save(consumer);

        //Dimensional Lodestar Crafting Recipe
        ShapedRecipeBuilder.shaped(VoidcraftItems.DIMENSIONAL_LODESTAR, 1)
                .pattern("NRV")
                .pattern("RCE")
                .pattern("NEV")
                .define('V', VoidcraftBlocks.END_VOID_ORE)
                .define('N', VoidcraftBlocks.NETHER_VOID_ORE)
                .define('R', Blocks.NETHERRACK)
                .define('E', Blocks.END_STONE)
                .define('C', VoidcraftItems.CRYSTAL_CATALYST)
                .unlockedBy("has_item", has(VoidcraftItems.VOID_PEARL))
                .save(consumer);

        ShapedRecipeBuilder.shaped(VoidcraftItems.VOID_CHISEL, 1)
                .pattern("  S")
                .pattern(" S ")
                .pattern("V  ")
                .define('S', Items.STICK)
                .define('V', VoidcraftItems.VOID_CRYSTAL)
                .unlockedBy("has_item", has(VoidcraftItems.VOID_PEARL))
                .save(consumer);

//        Void Equipment recipes
        SmithingRecipeBuilder.smithing(
                Ingredient.of(Items.NETHERITE_SWORD),
                Ingredient.of(VoidcraftItems.VOID_CRYSTAL),
                VoidcraftItems.VOID_SWORD)
                .unlocks("has_item", has(VoidcraftItems.VOID_CRYSTAL))
                .save(consumer, ForgeRegistries.ITEMS.getKey(VoidcraftItems.VOID_SWORD));

        SmithingRecipeBuilder.smithing(
                Ingredient.of(Items.NETHERITE_AXE),
                Ingredient.of(VoidcraftItems.VOID_CRYSTAL),
                VoidcraftItems.VOID_AXE)
                .unlocks("has_item", has(VoidcraftItems.VOID_CRYSTAL))
                .save(consumer, ForgeRegistries.ITEMS.getKey(VoidcraftItems.VOID_AXE));

        SmithingRecipeBuilder.smithing(
                Ingredient.of(Items.NETHERITE_HOE),
                Ingredient.of(VoidcraftItems.VOID_CRYSTAL),
                VoidcraftItems.VOID_HOE)
                .unlocks("has_item", has(VoidcraftItems.VOID_CRYSTAL))
                .save(consumer, ForgeRegistries.ITEMS.getKey(VoidcraftItems.VOID_HOE));

        SmithingRecipeBuilder.smithing(
                Ingredient.of(Items.NETHERITE_PICKAXE),
                Ingredient.of(VoidcraftItems.VOID_CRYSTAL),
                VoidcraftItems.VOID_PICKAXE)
                .unlocks("has_item", has(VoidcraftItems.VOID_CRYSTAL))
                .save(consumer, ForgeRegistries.ITEMS.getKey(VoidcraftItems.VOID_PICKAXE));

        SmithingRecipeBuilder.smithing(
                Ingredient.of(Items.NETHERITE_SHOVEL),
                Ingredient.of(VoidcraftItems.VOID_CRYSTAL),
                VoidcraftItems.VOID_SHOVEL)
                .unlocks("has_item", has(VoidcraftItems.VOID_CRYSTAL))
                .save(consumer, ForgeRegistries.ITEMS.getKey(VoidcraftItems.VOID_SHOVEL));

        SmithingRecipeBuilder.smithing(
                Ingredient.of(Items.NETHERITE_CHESTPLATE),
                Ingredient.of(VoidcraftItems.VOID_CRYSTAL),
                VoidcraftItems.VOID_CHESTPLATE)
                .unlocks("has_item", has(VoidcraftItems.VOID_CRYSTAL))
                .save(consumer, ForgeRegistries.ITEMS.getKey(VoidcraftItems.VOID_CHESTPLATE));

        SmithingRecipeBuilder.smithing(
                Ingredient.of(Items.NETHERITE_LEGGINGS),
                Ingredient.of(VoidcraftItems.VOID_CRYSTAL),
                VoidcraftItems.VOID_LEGGINGS)
                .unlocks("has_item", has(VoidcraftItems.VOID_CRYSTAL))
                .save(consumer, ForgeRegistries.ITEMS.getKey(VoidcraftItems.VOID_LEGGINGS));

        SmithingRecipeBuilder.smithing(
                Ingredient.of(Items.NETHERITE_HELMET),
                Ingredient.of(VoidcraftItems.VOID_CRYSTAL),
                VoidcraftItems.VOID_HELMET)
                .unlocks("has_item", has(VoidcraftItems.VOID_CRYSTAL))
                .save(consumer, ForgeRegistries.ITEMS.getKey(VoidcraftItems.VOID_HELMET));

        SmithingRecipeBuilder.smithing(
                Ingredient.of(Items.NETHERITE_BOOTS),
                Ingredient.of(VoidcraftItems.VOID_CRYSTAL),
                VoidcraftItems.VOID_BOOTS)
                .unlocks("has_item", has(VoidcraftItems.VOID_CRYSTAL))
                .save(consumer, ForgeRegistries.ITEMS.getKey(VoidcraftItems.VOID_BOOTS));

    }

}