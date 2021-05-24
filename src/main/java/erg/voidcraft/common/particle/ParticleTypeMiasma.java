package erg.voidcraft.common.particle;

import com.mojang.serialization.Codec;
import net.minecraft.particles.ParticleType;

public class MiasmaParticleType extends ParticleType<MiasmaParticleData> {

    private static boolean IGNORE_RANGE_CHECK = false;
    public MiasmaParticleType() {
        super(IGNORE_RANGE_CHECK, MiasmaParticleData.DESERIALIZER);
    }

    public Codec<MiasmaParticleData> func_230522_e_() {
        return MiasmaParticleData.CODEC;
    }

}
