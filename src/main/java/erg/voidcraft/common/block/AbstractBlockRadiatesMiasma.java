package erg.voidcraft.common.block;

import erg.voidcraft.common.particle.ParticleDataMiasma;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.*;
import java.util.Random;

public abstract class AbstractBlockRadiatesMiasma extends Block {

    protected final int PARTICLE_RATE;
    protected final double PARTICLE_SPEED;
    protected final Direction[] FACINGS;
    protected final Block[] VALID_SPAWN_BLOCKS;
    protected final boolean IGNORE_VALID_SPAWN_BLOCKS;

    public AbstractBlockRadiatesMiasma(AbstractBlock.Properties properties, int particleRate, boolean ignoreValidParticleSpawnPositions) {
        super(properties);
        if(particleRate > 100) particleRate = 100;
        if(particleRate < 0) particleRate = 0;
        this.PARTICLE_RATE = particleRate;
        this.PARTICLE_SPEED = 0.01;
        this.FACINGS = Direction.values();
        this.IGNORE_VALID_SPAWN_BLOCKS = ignoreValidParticleSpawnPositions;
        this.VALID_SPAWN_BLOCKS = new Block[]{Blocks.AIR};
    }

    public AbstractBlockRadiatesMiasma(AbstractBlock.Properties properties, int particleRate, Block[] validSpawns) {
        super(properties);
        if(particleRate > 100) particleRate = 100;
        if(particleRate < 0) particleRate = 0;
        this.PARTICLE_RATE = particleRate;
        this.PARTICLE_SPEED = 0.01;
        this.FACINGS = Direction.values();
        this.IGNORE_VALID_SPAWN_BLOCKS = false;
        this.VALID_SPAWN_BLOCKS = validSpawns;
    }

    public AbstractBlockRadiatesMiasma(AbstractBlock.Properties properties, int particleRate, int particleSpeed, Direction[] facings, Block[] validSpawns) {
        super(properties);
        this.PARTICLE_RATE = particleRate;
        this.PARTICLE_SPEED = particleSpeed;
        this.FACINGS = facings;
        this.VALID_SPAWN_BLOCKS = validSpawns;
        IGNORE_VALID_SPAWN_BLOCKS = false;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {

        if(world.isClientSide) {

            for(Direction facing: FACINGS) {

                Vector3d[] posAndVel = getParticlePositionAndVelocityOnSide(facing, pos, rand);

                double xpos = posAndVel[0].x;
                double ypos = posAndVel[0].y;
                double zpos = posAndVel[0].z;

                double xvel = posAndVel[1].x;
                double yvel = posAndVel[1].y;
                double zvel = posAndVel[1].z;

                final boolean IGNORE_RANGE_CHECK = false;

                final boolean validLocation = IGNORE_VALID_SPAWN_BLOCKS || isValidSpawn(pos.relative(facing), world);

                final double PERCENT_CHANCE = validLocation ? PARTICLE_RATE : 0;

                if (rand.nextDouble() < PERCENT_CHANCE / 100d) {

                    Color tint = new Color(0.5f, 0.5f, 0.5f);
                    final double MIN_DIAMETER = 0.05;
                    final double MAX_DIAMETER = 0.40;
                    double diameter = MIN_DIAMETER + (MAX_DIAMETER - MIN_DIAMETER) * rand.nextDouble();

                    ParticleDataMiasma miasma = new ParticleDataMiasma(tint, diameter);

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
        double yvel = -PARTICLE_SPEED;
        double zvel = (rand.nextDouble() - 0.5) / 100;

        if(facing == Direction.UP) {
            y++;
            yvel = PARTICLE_SPEED;
        } else if (facing == Direction.EAST) { // +X
            x = pos.getX() + 1;
            y += rand.nextDouble();
            xvel = PARTICLE_SPEED;
            yvel = (rand.nextDouble() - 0.5) / 100;
        } else if (facing == Direction.WEST) { // -X
            x = pos.getX();
            y += rand.nextDouble();
            xvel = -PARTICLE_SPEED;
            yvel = (rand.nextDouble() - 0.5) / 100;
        } else if(facing == Direction.SOUTH) { // +Z
            z = pos.getZ() + 1;
            y += rand.nextDouble();
            zvel = PARTICLE_SPEED;
            yvel = (rand.nextDouble() - 0.5) / 100;
        } else if(facing == Direction.NORTH) { // -Z
            z = pos.getZ();
            y += rand.nextDouble();
            zvel = -PARTICLE_SPEED;
            yvel = (rand.nextDouble() - 0.5) / 100;
        }

        return new Vector3d[]{
                new Vector3d(x, y, z),
                new Vector3d(xvel, yvel, zvel)
        };
    }

    private boolean isValidSpawn(BlockPos inquiryPos, World world) {

        Block inquiryBlock = world.getBlockState(inquiryPos).getBlock();

        for(Block block: VALID_SPAWN_BLOCKS) {
            if(block.equals(inquiryBlock)) {
                return true;
            }
        }
        return false;
    }

}
