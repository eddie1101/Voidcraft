package erg.voidcraft.common.init;

import erg.voidcraft.common.entity.EntityVoidLurker;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ObjectHolder;

public class VoidcraftEntities {

    public static final EntityType<EntityVoidLurker> VOID_LURKER = EntityType.Builder.<EntityVoidLurker>of(EntityVoidLurker::new, EntityClassification.MONSTER)
            .sized(0.75f, 2.5f)
            .setUpdateInterval(1)
            .setTrackingRange(10)
            .setShouldReceiveVelocityUpdates(true)
            .build(new ResourceLocation("voidcraft", "entity_void_lurker").toString());

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
        event.getRegistry().registerAll(
            VOID_LURKER.setRegistryName("voidcraft:entity_void_lurker")
        );
    }

    public static void registerWorldSpawn(BiomeLoadingEvent event) {
        event.getSpawns().addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(VOID_LURKER, 2, 1, 3));
    }

    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(VOID_LURKER, MobEntity.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 6)
                .add(Attributes.FOLLOW_RANGE, 50)
                .add(Attributes.ARMOR, 8)
                .add(Attributes.ARMOR_TOUGHNESS, 2)
                .add(Attributes.ATTACK_SPEED, 0.5)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.8)
                .add(Attributes.MAX_HEALTH, 30.0)
                .build());
    }

}
