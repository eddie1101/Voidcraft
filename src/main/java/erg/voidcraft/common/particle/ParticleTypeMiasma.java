package erg.voidcraft.common.particle;

import com.mojang.serialization.Codec;
import net.minecraft.particles.ParticleType;

public class ParticleTypeMiasma extends ParticleType<ParticleDataMiasma> {

    private static boolean IGNORE_RANGE_CHECK = false;
    public ParticleTypeMiasma() {
        super(IGNORE_RANGE_CHECK, ParticleDataMiasma.DESERIALIZER);
    }

    public Codec<ParticleDataMiasma> func_230522_e_() {
        return ParticleDataMiasma.CODEC;
    }

}
