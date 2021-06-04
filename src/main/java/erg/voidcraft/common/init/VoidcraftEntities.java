package erg.voidcraft.common.init;

import erg.voidcraft.common.Voidcraft;
import erg.voidcraft.common.entity.EntityVoidLurker;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ObjectHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VoidcraftEntities {

    private static final Logger LOGGER = LogManager.getLogger();

    @ObjectHolder("voidcraft:entity_void_lurker")
    public static final EntityType<EntityVoidLurker> VOID_LURKER = EntityType.Builder.<EntityVoidLurker>of(EntityVoidLurker::new, EntityClassification.MONSTER)
            .sized(0.75f, 2.5f)
            .setUpdateInterval(1)
            .setTrackingRange(10)
            .setShouldReceiveVelocityUpdates(true)
            .build(new ResourceLocation("voidcraft", "entity_void_lurker").toString());

    public static final Item VOID_LURKER_SPAWN_EGG = null;

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
        LOGGER.debug("Registering Entities...");
        event.getRegistry().registerAll(
            VOID_LURKER.setRegistryName("voidcraft:entity_void_lurker")
        );
    }

    @SubscribeEvent
    public static void registerSpawnEggs(RegistryEvent.Register<Item> event) {
        LOGGER.debug("Registering Spawn Eggs...");
        event.getRegistry().registerAll(
                new SpawnEggItem(VOID_LURKER, 0, 5507967, new Item.Properties().tab(ItemGroup.TAB_MISC)).setRegistryName(Voidcraft.MODID, "item_void_lurker_spawn_egg")
        );
    }

    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(VOID_LURKER, MobEntity.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.65)
                .add(Attributes.ATTACK_DAMAGE, 10)
                .add(Attributes.FOLLOW_RANGE, 50)
                .add(Attributes.ARMOR, 8)
                .add(Attributes.ARMOR_TOUGHNESS, 2)
                .add(Attributes.ATTACK_SPEED, 0.1)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.8)
                .add(Attributes.MAX_HEALTH, 30.0)
                .build());
    }

}
