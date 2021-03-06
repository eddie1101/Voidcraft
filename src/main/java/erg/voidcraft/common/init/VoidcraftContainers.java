package erg.voidcraft.common.init;

import erg.voidcraft.common.inventory.container.ContainerPortalBase;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VoidcraftContainers {

    private static final Logger LOGGER = LogManager.getLogger();

    public static ContainerType<ContainerPortalBase> PORTAL_BASE;

    @SubscribeEvent
    public static void registerContainers(final RegistryEvent.Register<ContainerType<?>> event) {
        LOGGER.debug("Registering Containers...");
        PORTAL_BASE =  IForgeContainerType.create((windowId, inv, data) -> {
            BlockPos pos = data.readBlockPos();
            World world = inv.player.level;
            return new ContainerPortalBase(windowId, inv, inv.player, world, pos);
        });
        PORTAL_BASE.setRegistryName("voidcraft", "container_portal_base");
        event.getRegistry().registerAll(
                PORTAL_BASE
        );
    }

}
