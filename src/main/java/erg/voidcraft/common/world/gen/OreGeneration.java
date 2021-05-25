package erg.voidcraft.common.world.gen;

import erg.voidcraft.common.init.VoidcraftBlocks;
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
            generateOre(event.getGeneration(), new erg.voidcraft.common.world.gen.VoidOreRuleTest(Blocks.BEDROCK),
                    VoidcraftBlocks.blockVoidOre.defaultBlockState(), 3, 1, 8, 3);
        }else if(event.getCategory().equals(Biome.Category.NETHER)) {
            generateOre(event.getGeneration(), new erg.voidcraft.common.world.gen.VoidOreNetherRuleTest(Blocks.BEDROCK),
                    VoidcraftBlocks.blockNetherVoidOre.defaultBlockState(), 4, 1, 10, 4);
        }else if(event.getCategory().equals(Biome.Category.THEEND)) {
            generateOre(event.getGeneration(), new BlockMatchRuleTest(Blocks.END_STONE),
                    VoidcraftBlocks.blockEndVoidOre.defaultBlockState(), 6, 1, 128, 8);
        }
    }

    private static void generateOre(BiomeGenerationSettingsBuilder settings, RuleTest fillerType, BlockState state,
                                    int veinSize, int minHeight, int maxHeight, int amount) {
        settings.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                Feature.ORE.configured(new OreFeatureConfig(fillerType, state, veinSize))
                        .decorated(Placement.RANGE.configured(new TopSolidRangeConfig(minHeight, 0, maxHeight)))
                        .squared().count(amount));
    }
}