package erg.voidcraft.common;

import erg.voidcraft.client.init.ClientSetup;
import erg.voidcraft.common.init.*;
import erg.voidcraft.common.world.gen.OreGeneration;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Voidcraft.MODID)
public class Voidcraft {
    public static final String MODID = "voidcraft";
    public static IEventBus MOD_EVENT_BUS;
    public static IEventBus FORGE_EVENT_BUS;

    public Voidcraft() {
        MOD_EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();
        FORGE_EVENT_BUS = MinecraftForge.EVENT_BUS;

        registerCommonEvents();

        FORGE_EVENT_BUS.addListener(EventPriority.HIGH, OreGeneration::generateOres);

        FORGE_EVENT_BUS.register(VoidcraftCommands.class);

        DistExecutor.runWhenOn(Dist.CLIENT, () -> this::registerClientEvents);
    }

    private void registerCommonEvents() {
        MOD_EVENT_BUS.register(VoidcraftBlocks.class);
        MOD_EVENT_BUS.register(VoidcraftTiles.class);
        MOD_EVENT_BUS.register(VoidcraftContainers.class);
        MOD_EVENT_BUS.register(VoidcraftItems.class);
        MOD_EVENT_BUS.register(VoidcraftParticles.class);
        MOD_EVENT_BUS.register(VoidcraftPacketHandler.class);
    }

   public void registerClientEvents() {
       MOD_EVENT_BUS.register(ClientSetup.class);
    }

    /*TODO:
        -----------------------
        Soon
        -----------------------
        armor models
        Some sort of TE to refine void crystals
        Voidlurker mob? (maybe some cool drops needed for crafting cool shit?)
        -----------------------
        Not so soon and ideas
        -----------------------
        maybe some magic shit idk
        definitely some black-hole stuff
        make portals cooler (multiblock?)
        Some sort of power source for portals? other things that might require that power source?
        a way to access the void
        collect power from the void
        some way to infuse void crystals
        Soul Slay Bow (& void crystal arrows)
        void miasma when void is accessed
     */


}
