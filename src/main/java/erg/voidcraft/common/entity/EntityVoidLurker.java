package erg.voidcraft.common.entity;

import erg.voidcraft.common.init.VoidcraftEntities;
import erg.voidcraft.common.particle.ParticleDataMiasma;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.awt.*;

public class EntityVoidLurker extends MonsterEntity {

    private static final DataParameter<Integer> VOID_LURKER_TYPE = EntityDataManager.defineId(EntityVoidLurker.class, DataSerializers.INT);

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
        Entity sourceEntity = source.getDirectEntity();
        if (sourceEntity != null) {
            sourceEntity.hurt(DamageSource.GENERIC, amount / 2);
        }
        return super.hurt(source, amount);
    }

    @Override
    public void baseTick() {
        super.baseTick();

        if(level.isClientSide) {

            Color tint = new Color(0.5f, 0.5f, 0.5f);
            double diameter = 0.1f + (Math.random() * 0.25f);

            double posX = getX() + (Math.random() - 0.5) * 0.25;
            double posY = getY() + (Math.random() - 0.5) * 0.25;
            double posZ = getZ() + (Math.random() - 0.5) * 0.25;

            ParticleDataMiasma dataMiasma = new ParticleDataMiasma(tint, diameter);
            level.addParticle(dataMiasma, posX, posY, posZ, 0, -0.01, 0);
        }
    }

    @Override
    public void remove() {

        if(level != null && level.isClientSide) {
            Color tint = new Color(0.5f, 0.5f, 0.5f);

            for(int i = 0; i < 50; i++) {
                double diameter = 0.1f + (Math.random() * 0.25f);

                double posX = getX() + (Math.random() - 0.5) * 0.25;
                double posY = getY() + (Math.random() - 0.5) * 0.25;
                double posZ = getZ() + (Math.random() - 0.5) * 0.25;

                ParticleDataMiasma dataMiasma = new ParticleDataMiasma(tint, diameter);
                level.addParticle(dataMiasma, posX, posY, posZ, 0, -0.01, 0);
            }
        }
    }

    @Override
    public boolean canBeCollidedWith() {
        return isAlive();
    }

    @Override
    public boolean canBeLeashed(PlayerEntity player) {
        return false;
    }

}
