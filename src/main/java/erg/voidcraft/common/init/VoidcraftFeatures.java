package erg.voidcraft.common.init;

import erg.voidcraft.common.world.gen.VoidOreNetherRuleTest;
import erg.voidcraft.common.world.gen.VoidOreRuleTest;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VoidcraftFeatures {

    private static final Logger LOGGER = LogManager.getLogger();

    public static ConfiguredFeature<?, ?> OVERWORLD_VOID_ORE_CONFIG;
    public static ConfiguredFeature<?, ?> NETHER_VOID_ORE_CONFIG;
    public static ConfiguredFeature<?, ?> END_VOID_ORE_CONFIG;

    @SubscribeEvent
    public static void registerFeatures(FMLCommonSetupEvent event) {

        LOGGER.debug("Registering Features...");

        OVERWORLD_VOID_ORE_CONFIG = register("overworld_ore_void",
                Feature.ORE.configured(new OreFeatureConfig(
                        new VoidOreRuleTest(Blocks.BEDROCK),
                        VoidcraftBlocks.blockVoidOre.defaultBlockState(),
                        3))
                        .decorated(Placement.RANGE.configured(new TopSolidRangeConfig(1, 0, 8)))
                        .squared().count(3));

        NETHER_VOID_ORE_CONFIG = register("nether_ore_void",
                Feature.ORE.configured(new OreFeatureConfig(
                        new VoidOreNetherRuleTest(Blocks.BEDROCK),
                        VoidcraftBlocks.blockNetherVoidOre.defaultBlockState(),
                        4))
                        .decorated(Placement.RANGE.configured(new TopSolidRangeConfig(1, 0, 10)))
                        .squared().count(4));

        END_VOID_ORE_CONFIG = register("end_ore_void",
                Feature.ORE.configured(new OreFeatureConfig(
                        new BlockMatchRuleTest(Blocks.END_STONE),
                        VoidcraftBlocks.blockEndVoidOre.defaultBlockState(),
                        6))
                        .decorated(Placement.RANGE.configured(new TopSolidRangeConfig(1, 0, 128)))
                        .squared().count(8));

    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, feature);
    }

}
