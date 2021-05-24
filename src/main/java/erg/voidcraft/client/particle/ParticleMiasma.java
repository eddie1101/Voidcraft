package erg.voidcraft.client.particle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.world.ClientWorld;

import java.awt.*;

public class MiasmaParticle extends SpriteTexturedParticle {

    private final IAnimatedSprite sprites;

    public MiasmaParticle(ClientWorld world, double x, double y, double z,
                          double velocityX, double velocityY, double velocityZ,
                          Color tint, double diameter,
                          IAnimatedSprite sprites) {

        super(world, x, y, z, velocityX, velocityY, velocityZ);
        this.sprites = sprites;

        setColor(tint.getRed()/255.0F, tint.getGreen()/255.0F, tint.getBlue()/255.0F);
        setSize((float)diameter, (float)diameter);

        particleScale = 0.5f * (float)diameter;

        maxAge = 50;

        final float ALPHA_VALUE = 1.0F;
        this.particleAlpha = ALPHA_VALUE;

        motionX = velocityX;
        motionY = velocityY;
        motionZ = velocityZ;

        this.canCollide = true;

    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {

        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;

        move(motionX, motionY, motionZ);

        if (onGround) {
            this.setExpired();
        }

        if (prevPosY == posY && motionY > 0) {
            this.setExpired();
        }

        if (this.age++ >= this.maxAge) {
            this.setExpired();
        }
    }
}
