package erg.basicportals.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;


public class BlockVoid extends Block {

    public BlockVoid() {
        super(Block.Properties.create(Material.ROCK).hardnessAndResistance(10, 10).harvestLevel(3).harvestTool(ToolType.PICKAXE));
    }


    @Override
    public BlockRenderType getRenderType(BlockState blockState) {
        return BlockRenderType.MODEL;
    }
}
