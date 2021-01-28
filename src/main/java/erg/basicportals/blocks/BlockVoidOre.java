package erg.basicportals.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockVoidOre extends Block {

    public BlockVoidOre() {
        super(Block.Properties.create(Material.ROCK).hardnessAndResistance(18, 8).harvestTool(ToolType.PICKAXE).harvestLevel(3).setRequiresTool());
    }

}
