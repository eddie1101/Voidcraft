package erg.voidcraft.common.init;

import erg.voidcraft.common.particle.ParticleDataMiasma;
import erg.voidcraft.common.particle.ParticleTypeMiasma;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.particle.ParticleManager;

public class VoidcraftParticles {

    private static final Logger LOGGER = LogManager.getLogger();

    public static ParticleType<ParticleDataMiasma> MIASMA;

    @SubscribeEvent
    public static void onIParticleTypeRegistration(RegistryEvent.Register<ParticleType<?>> iParticleTypeRegisterEvent) {
        LOGGER.debug("Registering Particles...");
        MIASMA = new ParticleTypeMiasma();
        MIASMA.setRegistryName("voidcraft:miasma");
        iParticleTypeRegisterEvent.getRegistry().register(MIASMA);
    }

}
