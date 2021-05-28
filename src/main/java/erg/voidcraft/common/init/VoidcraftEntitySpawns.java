package erg.voidcraft.common.init;

import erg.voidcraft.common.entity.EntityVoidLurker;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class VoidcraftEntitySpawns {

    public static void registerSpawnPlacement(FMLCommonSetupEvent event) {
        EntitySpawnPlacementRegistry.register(VoidcraftEntities.VOID_LURKER, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityVoidLurker::checkSpawnRules);
    }

    public static void onBiomeLoad(BiomeLoadingEvent event) {
        MobSpawnInfoBuilder spawns = event.getSpawns();

        int weight = 5;
        int minGroup = 1;
        int maxGroup = 2;
        if(event.getCategory().equals(Biome.Category.THEEND)) {
            weight = 100;
            minGroup = 3;
            maxGroup = 4;
        }

        spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(VoidcraftEntities.VOID_LURKER, weight, minGroup, maxGroup));
    }

}
