package erg.voidcraft.client.particle;

import erg.voidcraft.common.particle.ParticleDataMiasma;
import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;

import javax.annotation.Nullable;

//https://github.com/TheGreyGhost/MinecraftByExample/blob/master/src/main/java/minecraftbyexample/mbe50_particle/FlameParticleFactory.java
public class ParticleMiasmaFactory implements IParticleFactory<ParticleDataMiasma> {

    private final IAnimatedSprite sprites;

    @Nullable
    @Override
    public Particle createParticle(ParticleDataMiasma flameParticleData, ClientWorld world, double xPos, double yPos, double zPos, double xVelocity, double yVelocity, double zVelocity) {
        ParticleMiasma newParticle = new ParticleMiasma(world, xPos, yPos, zPos, xVelocity, yVelocity, zVelocity,
                flameParticleData.getTint(), flameParticleData.getDiameter(),
                sprites);
        newParticle.pickSprite(sprites);
        return newParticle;
    }

    public ParticleMiasmaFactory(IAnimatedSprite sprite) {
        this.sprites = sprite;
    }

    private ParticleMiasmaFactory() {
        throw new UnsupportedOperationException("Use the MiasmaParticleFactory(IAnimatedSprite sprite) constructor");
    }

}
