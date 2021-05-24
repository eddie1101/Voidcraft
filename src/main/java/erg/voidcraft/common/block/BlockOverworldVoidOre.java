package erg.voidcraft.common.block;

import erg.voidcraft.common.particle.ParticleDataMiasma;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

import java.awt.*;
import java.util.Random;

public class BlockOverworldVoidOre extends AbstractBlockRadiatesMiasma {

    public BlockOverworldVoidOre() {
        super(Block.Properties.create(Material.ROCK).hardnessAndResistance(22, 18).harvestTool(ToolType.PICKAXE).harvestLevel(3).setRequiresTool(), 50, false);
    }

    @Override
    public int getExpDrop(BlockState state, IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
        return silktouch == 0 ? MathHelper.nextInt(RANDOM, 16, 19) : 0;
    }

}
