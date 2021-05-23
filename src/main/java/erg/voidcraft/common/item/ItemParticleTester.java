package erg.voidcraft.common.item;

import erg.voidcraft.common.init.VoidcraftParticles;
import erg.voidcraft.common.particle.MiasmaParticleData;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
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
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {

        if (world.isRemote) {

            final float PARTIAL_TICKS = 1.0f;
            Vector3d eyePos = player.getEyePosition(PARTIAL_TICKS);
            Vector3d look = player.getLookVec();

            double xpos = eyePos.x;
            double ypos = eyePos.y;
            double zpos = eyePos.z;

            double xvel = look.x;
            double yvel = look.y;
            double zvel = look.z;

            final double POSITION_WOBBLE = 0.01;

            for (int i = 0; i < 1000; i++) {

                Color tint = new Color(0.5f, 0.5f, 0.5f);
                final double MIN_DIAMETER = 0.05;
                final double MAX_DIAMETER = 0.40;
                double diameter = MIN_DIAMETER + (MAX_DIAMETER - MIN_DIAMETER) * random.nextDouble();

                MiasmaParticleData miasma = new MiasmaParticleData(tint, diameter);

                zpos += POSITION_WOBBLE * (rand.nextDouble() - 0.5);
                xpos += POSITION_WOBBLE * (rand.nextDouble() - 0.5);

                xvel += rand.nextDouble() - 0.5;
                yvel += rand.nextDouble() - 0.5;
                zvel += rand.nextDouble() - 0.5;

                xvel /= 10;
                yvel /= 10;
                zvel /= 10;

                world.addParticle(miasma, false,
                        xpos, ypos, zpos, xvel, yvel, zvel);
            }
        }
        return super.onItemRightClick(world, player, hand);
    }

}
