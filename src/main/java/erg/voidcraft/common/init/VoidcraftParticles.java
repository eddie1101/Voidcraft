package erg.voidcraft.common.init;

import erg.voidcraft.common.particle.ParticleDataMiasma;
import erg.voidcraft.common.particle.ParticleTypeMiasma;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VoidcraftParticles {

    private static final Logger LOGGER = LogManager.getLogger();

    public static ParticleType<ParticleDataMiasma> miasmaParticleType;

    @SubscribeEvent
    public static void onIParticleTypeRegistration(RegistryEvent.Register<ParticleType<?>> iParticleTypeRegisterEvent) {
        LOGGER.debug("Registering Particles...");
        miasmaParticleType = new ParticleTypeMiasma();
        miasmaParticleType.setRegistryName("voidcraft:miasma_particle_type");
        iParticleTypeRegisterEvent.getRegistry().register(miasmaParticleType);
    }

}
