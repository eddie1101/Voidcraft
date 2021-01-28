package erg.basicportals.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;

public class BlockVoidOre extends Block {

    public BlockVoidOre() {
        super(Block.Properties.create(Material.ROCK).hardnessAndResistance(18, 8).harvestTool(ToolType.PICKAXE).harvestLevel(3).setRequiresTool());
    }

    @Override
    public int getExpDrop(BlockState state, IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
        return silktouch == 0 ? MathHelper.nextInt(RANDOM, 16, 19) : 0;
    }

}
