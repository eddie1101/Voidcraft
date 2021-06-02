package erg.voidcraft.common.init;

import erg.voidcraft.common.tile.TilePortalBase;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static erg.voidcraft.common.init.VoidcraftBlocks.PORTAL_BASE;

public class VoidcraftTiles {

    public static TileEntityType<TilePortalBase> tileEntityPortalBaseType;

    private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    public static void registerTileEntities(final RegistryEvent.Register<TileEntityType<?>> event) {
        LOGGER.debug("Registering Block Entities...");
        tileEntityPortalBaseType = TileEntityType.Builder.of(TilePortalBase::new, PORTAL_BASE).build(null);
        tileEntityPortalBaseType.setRegistryName("voidcraft", "tile_entity_portal_base");

        event.getRegistry().registerAll(
                tileEntityPortalBaseType
        );
    }

}
