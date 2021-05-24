package erg.voidcraft.common.init;

import erg.voidcraft.common.particle.ParticleDataMiasma;
import erg.voidcraft.common.particle.ParticleTypeMiasma;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class VoidcraftParticles {

    public static ParticleType<ParticleDataMiasma> miasmaParticleType;

    @SubscribeEvent
    public static void onIParticleTypeRegistration(RegistryEvent.Register<ParticleType<?>> iParticleTypeRegisterEvent) {
        miasmaParticleType = new ParticleTypeMiasma();
        miasmaParticleType.setRegistryName("voidcraft:miasma_particle_type");
        iParticleTypeRegisterEvent.getRegistry().register(miasmaParticleType);
    }

}
