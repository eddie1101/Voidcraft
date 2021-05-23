package erg.voidcraft.common.block;

import erg.voidcraft.common.particle.MiasmaParticleData;
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

public class BlockOverworldVoidOre extends Block {

    public BlockOverworldVoidOre() {
        super(Block.Properties.create(Material.ROCK).hardnessAndResistance(22, 18).harvestTool(ToolType.PICKAXE).harvestLevel(3).setRequiresTool());
    }

    @Override
    public int getExpDrop(BlockState state, IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
        return silktouch == 0 ? MathHelper.nextInt(RANDOM, 16, 19) : 0;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {

        if(world.isRemote) {

            for(Direction facing: Direction.values()) {

                Vector3d[] posAndVel = getParticlePositionAndVelocityOnSide(facing, pos, rand);



                double xpos = posAndVel[0].x;
                double ypos = posAndVel[0].y;
                double zpos = posAndVel[0].z;

                double xvel = posAndVel[1].x;
                double yvel = posAndVel[1].y;
                double zvel = posAndVel[1].z;

                final boolean IGNORE_RANGE_CHECK = false;

                final boolean validLocation = world.getBlockState(pos.offset(facing)).getBlock().equals(Blocks.AIR);

                final double PERCENT_CHANCE = validLocation ? 50 : 0;

                if (rand.nextDouble() < PERCENT_CHANCE / 100d) {

                    Color tint = new Color(0.5f, 0.5f, 0.5f);
                    final double MIN_DIAMETER = 0.05;
                    final double MAX_DIAMETER = 0.40;
                    double diameter = MIN_DIAMETER + (MAX_DIAMETER - MIN_DIAMETER) * rand.nextDouble();

                    MiasmaParticleData miasma = new MiasmaParticleData(tint, diameter);

                    world.addParticle(miasma, IGNORE_RANGE_CHECK,
                            xpos, ypos, zpos, xvel, yvel, zvel);
                }
            }
        }
    }

    // I hate this
    // array[0] is pos and array[1] is vel
    private Vector3d[] getParticlePositionAndVelocityOnSide(Direction facing, BlockPos pos, Random rand) {

        //default: down
        double x = pos.getX() + rand.nextDouble();
        double y = pos.getY();
        double z = pos.getZ() + rand.nextDouble();

        double xvel = (rand.nextDouble() - 0.5) / 100;
        double yvel = -0.01;
        double zvel = (rand.nextDouble() - 0.5) / 100;

        if(facing == Direction.UP) {
            y++;
            yvel = 0.01;
        } else if (facing == Direction.EAST) { // +X
            x = pos.getX() + 1;
            y += rand.nextDouble();
            xvel = 0.01;
            yvel = (rand.nextDouble() - 0.5) / 100;
        } else if (facing == Direction.WEST) { // -X
            x = pos.getX();
            y += rand.nextDouble();
            xvel = -0.01;
            yvel = (rand.nextDouble() - 0.5) / 100;
        } else if(facing == Direction.SOUTH) { // +Z
            z = pos.getZ() + 1;
            y += rand.nextDouble();
            zvel = 0.01;
            yvel = (rand.nextDouble() - 0.5) / 100;
        } else if(facing == Direction.NORTH) { // -Z
            z = pos.getZ();
            y += rand.nextDouble();
            zvel = -0.01;
            yvel = (rand.nextDouble() - 0.5) / 100;
        }

        return new Vector3d[]{
                new Vector3d(x, y, z),
                new Vector3d(xvel, yvel, zvel)
        };
    }

}
