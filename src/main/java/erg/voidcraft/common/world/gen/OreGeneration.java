package erg.voidcraft.common.world.gen;

import erg.voidcraft.common.block.VoidcraftBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;


public class OreGeneration {

    public static void generateOres(final BiomeLoadingEvent event) {
        if (!(event.getCategory().equals(Biome.Category.NETHER) || event.getCategory().equals(Biome.Category.THEEND))) {
            generateOre(event.getGeneration(), new erg.voidcraft.world.gen.VoidOreRuleTest(Blocks.BEDROCK),
                    VoidcraftBlocks.blockVoidOre.getDefaultState(), 3, 1, 5, 2);
        }else if(event.getCategory().equals(Biome.Category.NETHER)) {
            generateOre(event.getGeneration(), new erg.voidcraft.world.gen.VoidOreNetherRuleTest(Blocks.BEDROCK),
                    VoidcraftBlocks.blockNetherVoidOre.getDefaultState(), 4, 1, 5, 3);
        }else if(event.getCategory().equals(Biome.Category.THEEND)) {
            generateOre(event.getGeneration(), new BlockMatchRuleTest(Blocks.END_STONE),
                    VoidcraftBlocks.blockEndVoidOre.getDefaultState(), 6, 1, 128, 8);
        }
    }

    private static void generateOre(BiomeGenerationSettingsBuilder settings, RuleTest fillerType, BlockState state,
                                    int veinSize, int minHeight, int maxHeight, int amount) {
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                Feature.ORE.withConfiguration(new OreFeatureConfig(fillerType, state, veinSize))
                        .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(minHeight, 0, maxHeight)))
                        .square().func_242731_b(amount));
    }
}