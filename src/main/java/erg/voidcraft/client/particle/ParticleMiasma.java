package erg.voidcraft.client.particle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.world.ClientWorld;

import java.awt.*;
import java.util.Random;

public class ParticleMiasma extends SpriteTexturedParticle {

    private final IAnimatedSprite sprites;
    private final int shrinkOffset;

    public ParticleMiasma(ClientWorld world, double x, double y, double z,
                          double velocityX, double velocityY, double velocityZ,
                          Color tint, double diameter,
                          IAnimatedSprite sprites) {

        super(world, x, y, z, velocityX, velocityY, velocityZ);
        this.sprites = sprites;
        Random random = new Random();
        shrinkOffset = random.nextInt(10);

        setColor(tint.getRed()/255.0F, tint.getGreen()/255.0F, tint.getBlue()/255.0F);
        setSize((float)diameter, (float)diameter);

        quadSize = 0.5f * (float)diameter;

        lifetime = 50;

        final float ALPHA_VALUE = 1.0F;
        this.alpha = ALPHA_VALUE;

        xd = velocityX;
        yd = velocityY;
        zd = velocityZ;

        this.hasPhysics = true;

    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {

        xo = x;
        yo = y;
        zo = z;

        move(xd, yd, zd);

        if (onGround) {
            this.remove();
        }

        if (yo == y && yd > 0) {
            this.remove();
        }

        float timeToDeath = this.lifetime - this.age;
        if(timeToDeath - shrinkOffset <= 10) {
            this.scale((timeToDeath) / (10 + shrinkOffset));
        }

        if (this.age++ >= this.lifetime) {
            this.remove();
        }

    }
}
