package erg.voidcraft.common.init;

import erg.voidcraft.common.tile.TilePortalBase;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static erg.voidcraft.common.init.VoidcraftBlocks.blockPortalBase;

public class VoidcraftTiles {

    public static TileEntityType<TilePortalBase> tileEntityPortalBaseType;

    @SubscribeEvent
    public static void registerTileEntities(final RegistryEvent.Register<TileEntityType<?>> event) {

        //
        tileEntityPortalBaseType = TileEntityType.Builder.of(TilePortalBase::new, blockPortalBase).build(null);
        tileEntityPortalBaseType.setRegistryName("voidcraft", "tile_entity_portal_base");

        event.getRegistry().registerAll(
                tileEntityPortalBaseType
        );
    }

}
