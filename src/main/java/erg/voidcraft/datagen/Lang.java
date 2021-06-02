package erg.voidcraft.datagen;

import erg.voidcraft.common.Voidcraft;
import erg.voidcraft.common.init.VoidcraftBlocks;
import erg.voidcraft.common.init.VoidcraftEntities;
import erg.voidcraft.common.init.VoidcraftItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.common.data.LanguageProvider;

import static erg.voidcraft.common.init.VoidcraftEntities.VOID_LURKER;

public class Lang extends LanguageProvider {

    public Lang(DataGenerator generator) {
        super(generator, Voidcraft.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(VoidcraftBlocks.VOID_BLOCK, "Void Block");
        add(VoidcraftBlocks.VOID_ORE, "Void Ore");
        add(VoidcraftBlocks.NETHER_VOID_ORE, "Void Ore");
        add(VoidcraftBlocks.END_VOID_ORE, "Void Ore");
        add(VoidcraftBlocks.PORTAL_BASE, "Portal");

        add(VoidcraftItems.DESTINATION_LODESTAR, "Destination Lodestar");
        add(VoidcraftItems.DIMENSIONAL_LODESTAR, "Dimensional Lodestar");
        add(VoidcraftItems.CRYSTAL_CATALYST, "Crystal Catalyst");
        add(VoidcraftItems.VOID_CRYSTAL, "Void Crystal");
        add(VoidcraftItems.VOID_PEARL, "Void Pearl");

        add(VoidcraftItems.VOID_CHISEL, "Void Chisel");

        add(VoidcraftItems.VOID_SWORD, "Void Sword");
        add(VoidcraftItems.VOID_SHOVEL, "Void Shovel");
        add(VoidcraftItems.VOID_AXE, "Void Axe");
        add(VoidcraftItems.VOID_HOE, "Void Hoe");
        add(VoidcraftItems.VOID_PICKAXE, "Void Pickaxe");

        add(VoidcraftItems.VOID_HELMET, "Void Helmet");
        add(VoidcraftItems.VOID_CHESTPLATE, "Void Chestplate");
        add(VoidcraftItems.VOID_LEGGINGS, "Void Leggings");
        add(VoidcraftItems.VOID_BOOTS, "Void Boots");

        add(VOID_LURKER, "VoidLurker");

        add("item.voidcraft.item_void_lurker_spawn_egg", "VoidLurker Spawn Egg");

        add("itemGroup.item_group_voidcraft", "Voidcraft");

        add("container.voidcraft.container_portal_base", "Portal");
    }
}
