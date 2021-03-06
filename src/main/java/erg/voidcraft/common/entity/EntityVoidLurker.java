package erg.voidcraft.common.entity;

import erg.voidcraft.common.Voidcraft;
import erg.voidcraft.common.init.VoidcraftEntities;
import erg.voidcraft.common.particle.ParticleDataMiasma;
import erg.voidcraft.common.world.VoidcraftGameRules;
import net.minecraft.command.arguments.EntityAnchorArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.Random;

public class EntityVoidLurker extends MonsterEntity {

    private static final DataParameter<Integer> VOID_LURKER_TYPE = EntityDataManager.defineId(EntityVoidLurker.class, DataSerializers.INT);

    private static final ResourceLocation lootTable = new ResourceLocation(Voidcraft.MODID, "entities/entity_void_lurker");

    public EntityVoidLurker(EntityType<EntityVoidLurker> type, World world) {
        super(type, world);
    }

    public EntityVoidLurker(World world) {
        this(VoidcraftEntities.VOID_LURKER, world);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(VOID_LURKER_TYPE, 0);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }

    @Override
    public boolean hurt(@Nonnull DamageSource source, float amount) {
        Entity sourceEntity = source.getEntity();
        float reflectionFraction = 0.0f;
        if(level.getDifficulty().equals(Difficulty.EASY)) {
            reflectionFraction = 0.25f;
        } else if(level.getDifficulty().equals(Difficulty.NORMAL)) {
            reflectionFraction = 0.5f;
        } else if(level.getDifficulty().equals(Difficulty.HARD)) {
            reflectionFraction = 0.75f;
        }

        float takenFraction = 1f - reflectionFraction;
        if (sourceEntity != null) {
            sourceEntity.hurt(DamageSource.GENERIC, amount * reflectionFraction);
        }
        return super.hurt(source, amount * takenFraction);
    }

    @Override
    public void baseTick() {
        super.baseTick();

        if(!level.isClientSide) {
            Vector3d pos = this.position();
            PlayerEntity nearestPlayer = level.getNearestPlayer(pos.x, pos.y, pos.z, 50, true);
            if(nearestPlayer != null) {
                this.lookAt(EntityAnchorArgument.Type.EYES, nearestPlayer.position());
            }
        } else {

            Color tint = new Color(0.5f, 0.5f, 0.5f);
            double diameter = 0.1f + (Math.random() * 0.25f);

            double posX = getX() + (Math.random() - 0.5);
            double posY = getY() + ((2 * Math.random()) - 0.5) + 1;
            double posZ = getZ() + (Math.random() - 0.5);

            ParticleDataMiasma dataMiasma = new ParticleDataMiasma(tint, diameter);
            level.addParticle(dataMiasma, posX, posY, posZ, 0, -0.01, 0);
        }

        if(this.position().y() > 15 && !this.level.dimension().equals(World.END) && this.level.getGameRules().getBoolean(VoidcraftGameRules.RULE_VOIDLURKERHEIGHTDAMAGE)) {
            this.hurt(DamageSource.MAGIC, 1);
        }
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
    }

    @Override
    public void remove() {

        if(level != null && level.isClientSide) {
            Color tint = new Color(0.5f, 0.5f, 0.5f);

            for(int i = 0; i < 200; i++) {
                double diameter = 0.1f + (Math.random() * 0.25f);

                double posX = getX() + (Math.random() - 0.5) * 1.25;
                double posY = getY() + ((2 * Math.random()) - 0.5) + 1;
                double posZ = getZ() + (Math.random() - 0.5) * 1.25;

                double velX = (Math.random() * 0.1) - 0.05;
                double velY = (Math.random() * 0.1) - 0.05;
                double velZ = (Math.random() * 0.1) - 0.05;

                ParticleDataMiasma dataMiasma = new ParticleDataMiasma(tint, diameter);
                level.addParticle(dataMiasma, posX, posY, posZ, velX, velY, velZ);
            }
        }
        super.remove();
    }

    @Override
    public boolean shouldDespawnInPeaceful() {
        return true;
    }

    @Override
    public boolean canBeCollidedWith() {
        return isAlive();
    }

    @Override
    public boolean canBeLeashed(PlayerEntity player) {
        return false;
    }

    public static boolean checkSpawnRules(EntityType<? extends MonsterEntity> entity, IServerWorld world, SpawnReason reason, BlockPos pos, Random rand) {
        return pos.getY() < 15 && MonsterEntity.checkMonsterSpawnRules(entity, world, reason, pos, rand);
    }

}
