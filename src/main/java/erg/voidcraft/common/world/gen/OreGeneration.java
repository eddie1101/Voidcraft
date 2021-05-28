package erg.voidcraft.common.world.gen;

import erg.voidcraft.common.init.VoidcraftFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;


public class OreGeneration {

    public static void generateOres(final BiomeLoadingEvent event) {

        BiomeGenerationSettingsBuilder settings = event.getGeneration();

        if (!(event.getCategory().equals(Biome.Category.NETHER) || event.getCategory().equals(Biome.Category.THEEND))) {
            settings.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, VoidcraftFeatures.OVERWORLD_VOID_ORE_CONFIG);
        }else if(event.getCategory().equals(Biome.Category.NETHER)) {
            settings.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, VoidcraftFeatures.NETHER_VOID_ORE_CONFIG);
        }else if(event.getCategory().equals(Biome.Category.THEEND)) {
            settings.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, VoidcraftFeatures.END_VOID_ORE_CONFIG);
        }
    }
}