package erg.basicportals.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.material.Material;

import static net.minecraft.state.properties.BlockStateProperties.*;

public class BlockArrow extends Block implements IWaterLoggable {

    public BlockArrow() {
        super(Block.Properties.create(Material.CARPET).harvestLevel(0).hardnessAndResistance(0.5f).doesNotBlockMovement());

        BlockState defaultState = this.getStateContainer().getBaseState()
                .with(UP, false).with(DOWN, false)
                .with(EAST, false).with(WEST, false)
                .with(NORTH, false).with(SOUTH, false)
                .with(WATERLOGGED, false);
        this.setDefaultState(defaultState);
    }
//
//    @Override
//    public BlockState

}
