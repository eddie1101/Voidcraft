package erg.voidcraft.common.item;

import erg.voidcraft.common.init.VoidcraftParticles;
import erg.voidcraft.common.particle.MiasmaParticleData;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.awt.*;
import java.util.Random;

public class ItemParticleTester extends Item {

    private final Random rand;

    public ItemParticleTester() {
        super(new Item.Properties());
        this.rand = new Random();
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext ctx) {

        World world = ctx.getWorld();

        if(world.isRemote) {

            PlayerEntity player = ctx.getPlayer();

            final float PARTIAL_TICKS = 1.0f;
            Vector3d eyePos = player.getEyePosition(PARTIAL_TICKS);
            Vector3d look = player.getLookVec().normalize();

            double xpos = eyePos.x;
            double ypos = eyePos.y;
            double zpos = eyePos.z;

            double xvel = look.x;
            double yvel = look.y;
            double zvel = look.z;

            final double POSITION_WOBBLE = 0.01;

            for(int i = 0; i < 100; i++) {

                Color tint = new Color(0f, 0f, 0f);
                final double MIN_DIAMETER = 0.05;
                final double MAX_DIAMETER = 0.40;
                double diameter = MIN_DIAMETER + (MAX_DIAMETER - MIN_DIAMETER) * random.nextDouble();

                MiasmaParticleData miasma = new MiasmaParticleData(tint, diameter);

                zpos += POSITION_WOBBLE * (rand.nextDouble() - 0.5);
                xpos += POSITION_WOBBLE * (rand.nextDouble() - 0.5);
                world.addParticle(miasma, false,
                        xpos, ypos, zpos, xvel, yvel, zvel);
            }

            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }

}
