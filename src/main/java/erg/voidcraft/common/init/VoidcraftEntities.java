package erg.voidcraft.common.init;

import erg.voidcraft.common.entity.EntityVoidLurker;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ObjectHolder;

public class VoidcraftEntities {

    public static final EntityType<EntityVoidLurker> VOID_LURKER = EntityType.Builder.<EntityVoidLurker>of(EntityVoidLurker::new, EntityClassification.MONSTER)
            .sized(1, 1)
            .setUpdateInterval(6)
            .setTrackingRange(10)
            .setShouldReceiveVelocityUpdates(true)
            .build(new ResourceLocation("voidcraft", "entity_void_lurker").toString());

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
        event.getRegistry().registerAll(
            VOID_LURKER.setRegistryName("voidcraft:entity_void_lurker")
        );
    }

    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(VOID_LURKER, MobEntity.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.75)
                .add(Attributes.ATTACK_DAMAGE, 6)
                .add(Attributes.FOLLOW_RANGE, 50)
                .add(Attributes.ARMOR, 8)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
                .add(Attributes.MAX_HEALTH, 30.0)
                .build());
    }

}
