package erg.voidcraft.common.init;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class VoidcraftMobDrops {

    @SubscribeEvent
    public void dropPearls(LivingDropsEvent event) {
        Entity entity = event.getEntity();
        double x = event.getEntity().position().x;
        double y = event.getEntity().position().y;
        double z = event.getEntity().position().z;
        event.getDrops().add(new ItemEntity(event.getEntity().level, x, y, z, new ItemStack(VoidcraftItems.itemVoidPearl)));
    }

}
