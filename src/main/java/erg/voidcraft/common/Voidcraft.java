package erg.voidcraft.common;

import erg.voidcraft.client.init.ClientSetup;
import erg.voidcraft.common.init.*;
import erg.voidcraft.common.world.VoidcraftGameRules;
import erg.voidcraft.common.world.gen.OreGeneration;
import net.minecraft.world.GameRules;
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

        FORGE_EVENT_BUS.addListener(OreGeneration::generateOres);
        FORGE_EVENT_BUS.addListener(VoidcraftEntitySpawns::onBiomeLoad);
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
        MOD_EVENT_BUS.register(VoidcraftEntities.class);

        MOD_EVENT_BUS.addListener(VoidcraftFeatures::registerFeatures);
        MOD_EVENT_BUS.addListener(VoidcraftEntitySpawns::registerSpawnPlacement);
        MOD_EVENT_BUS.addListener(VoidcraftEntities::registerAttributes);
        MOD_EVENT_BUS.addListener(VoidcraftGameRules::registerGameRules);
    }

    public void registerClientEvents() {
        MOD_EVENT_BUS.register(ClientSetup.class);
    }

}

    /*TODO:
        -----------------------
        Soon
        -----------------------
        make the portal block animated based on whether it's powered or inverted
        voidlurker spawn egg
        give voidlurkers some cool drops
        Some sort of TE to refine void crystals
        Pouch and bottomless pouch
        IM GONNA MAKE A FUCKING GRENADE
        armor models
        rewrite voidlurker model in a sensible way FOR THE LOVE OF GOD PLEASE
        -----------------------
        Pending fixes
        -----------------------
        Portal Base totally glitching out on dedicated server (can't reproduce)
        Portal Base block can't be harvested
        -----------------------
        Not so soon and ideas
        -----------------------
        definitely some black-hole stuff
        make portals cooler (multiblock?)
        void dimension
        Soul Slay Bow (& void crystal arrows)


        Crystal Core, a block of crystal catalyst and void block, which is an incredibly hard and resistant block,
        and is also used to make a crusher, crush void crystals into (void dust?)
        use void dust to craft void capacitor
        place void capacitor into a ritual block at y < 10 in an open area, will continually spawn
        voidlurkers. Each slain voidlurker fills the ritual block with essence. once it is
        completely filled, the capacitor becomes charged, and can be used to craft things like lodestars,
        the void chisel, the extra dimensional pouch, and even more powerful void armor (which will also grant abilities)
        voidlurkers rarely drop (anomalies), which are used to craft dimensional lodestars
        with the void chisel, the player can destroy bedrock, and jump down into the void dimension, where you can do
        even cooler shit that I'll figure out later.

        (maybe have cooler armor, tools, and weapons configurable with runes that do various shit)
      */


