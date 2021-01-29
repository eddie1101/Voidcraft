package erg.basicportals.world.gen;

import erg.basicportals.BasicPortals;
import erg.basicportals.StartupCommon;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;


public class OreGeneration {

    public static void generateOres(final BiomeLoadingEvent event) {
        if (!(event.getCategory().equals(Biome.Category.NETHER) || event.getCategory().equals(Biome.Category.THEEND))) {
            generateOre(event.getGeneration(), new VoidOreRuleTest(Blocks.BEDROCK),
                    StartupCommon.blockVoidOre.getDefaultState(), 3, 1, 5, 2);
        }else if(event.getCategory().equals(Biome.Category.NETHER)) {
            generateOre(event.getGeneration(), new VoidOreNetherRuleTest(Blocks.BEDROCK),
                    StartupCommon.blockNetherVoidOre.getDefaultState(), 4, 1, 5, 3);
        }else if(event.getCategory().equals(Biome.Category.THEEND)) {
            generateOre(event.getGeneration(), new BlockMatchRuleTest(Blocks.END_STONE),
                    StartupCommon.blockEndVoidOre.getDefaultState(), 6, 1, 128, 8);
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