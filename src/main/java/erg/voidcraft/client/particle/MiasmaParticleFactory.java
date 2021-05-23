package erg.voidcraft.client.particle;

import erg.voidcraft.common.particle.MiasmaParticleData;
import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;

import javax.annotation.Nullable;

//https://github.com/TheGreyGhost/MinecraftByExample/blob/master/src/main/java/minecraftbyexample/mbe50_particle/FlameParticleFactory.java
public class MiasmaParticleFactory implements IParticleFactory<MiasmaParticleData> {

    private final IAnimatedSprite sprites;

    @Nullable
    @Override
    public Particle makeParticle(MiasmaParticleData flameParticleData, ClientWorld world, double xPos, double yPos, double zPos, double xVelocity, double yVelocity, double zVelocity) {
        MiasmaParticle newParticle = new MiasmaParticle(world, xPos, yPos, zPos, xVelocity, yVelocity, zVelocity,
                flameParticleData.getTint(), flameParticleData.getDiameter(),
                sprites);
        newParticle.selectSpriteRandomly(sprites);
        return newParticle;
    }

    public MiasmaParticleFactory(IAnimatedSprite sprite) {
        this.sprites = sprite;
    }

    private MiasmaParticleFactory() {
        throw new UnsupportedOperationException("Use the MiasmaParticleFactory(IAnimatedSprite sprite) constructor");
    }

}
