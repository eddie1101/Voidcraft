package erg.basicportals.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockVoidOre extends Block {

    public BlockVoidOre() {
        super(Block.Properties.create(Material.ROCK).hardnessAndResistance(8, 6).harvestTool(ToolType.PICKAXE).harvestLevel(3));
    }

}
