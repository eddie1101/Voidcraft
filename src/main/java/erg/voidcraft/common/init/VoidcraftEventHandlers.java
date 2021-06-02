package erg.voidcraft.common.init;

import erg.voidcraft.common.entity.EntityVoidLurker;
import erg.voidcraft.common.particle.ParticleDataMiasma;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.awt.*;

public class VoidcraftEventHandlers {

//    @SubscribeEvent
//    public void dropPearls(LivingDropsEvent event) {
//        LivingEntity entity = event.getEntityLiving();
//        if(entity != null && entity instanceof EntityVoidLurker) {
//            double x = event.getEntity().position().x;
//            double y = event.getEntity().position().y;
//            double z = event.getEntity().position().z;
//            event.getDrops().add(new ItemEntity(event.getEntity().level, x, y, z, new ItemStack(VoidcraftItems.itemVoidPearl)));
//        }
//    }

    @SubscribeEvent
    public void spawnDeathParticles(LivingDeathEvent event) {
        LivingEntity entity = event.getEntityLiving();
        if(entity != null && entity instanceof EntityVoidLurker) {
            Color tint = new Color(0.5f, 0.5f, 0.5f);

            for(int i = 0; i < 200; i++) {
                double diameter = 0.1f + (Math.random() * 0.25f);

                double posX = entity.getX() + (Math.random() - 0.5) * 1.25;
                double posY = entity.getY() + ((2 * Math.random()) - 0.5) + 1;
                double posZ = entity.getZ() + (Math.random() - 0.5) * 1.25;

                double velX = (Math.random() * 0.1) - 0.05;
                double velY = (Math.random() * 0.1) - 0.05;
                double velZ = (Math.random() * 0.1) - 0.05;

                ParticleDataMiasma dataMiasma = new ParticleDataMiasma(tint, diameter);
                entity.level.addParticle(dataMiasma, posX, posY, posZ, velX, velY, velZ);
            }
        }
    }

}
