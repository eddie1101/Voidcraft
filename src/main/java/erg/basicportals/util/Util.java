package erg.basicportals.util;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class Util {

    public static final String MODID = "basicportals";

    public static Block setBlockName(Block block, String blockName) {
        block.setRegistryName(MODID, blockName);
        return block;
    }

    public static Item setItemName(Item item, String itemName) {
        item.setRegistryName(MODID, itemName);
        return item;
    }

    public static BlockItem setBlockItemName(BlockItem item, ResourceLocation itemName) {
        item.setRegistryName(itemName);
        return item;
    }

}
