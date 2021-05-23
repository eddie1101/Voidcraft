package erg.voidcraft.common.init;

import erg.voidcraft.common.particle.MiasmaParticleData;
import erg.voidcraft.common.particle.MiasmaParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class VoidcraftParticles {

    public static ParticleType<MiasmaParticleData> miasmaParticleType;

    @SubscribeEvent
    public static void onIParticleTypeRegistration(RegistryEvent.Register<ParticleType<?>> iParticleTypeRegisterEvent) {
        miasmaParticleType = new MiasmaParticleType();
        miasmaParticleType.setRegistryName("voidcraft:miasma_particle_type");
        iParticleTypeRegisterEvent.getRegistry().register(miasmaParticleType);
    }

}
