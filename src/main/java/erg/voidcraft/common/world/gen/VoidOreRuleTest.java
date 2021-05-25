package erg.voidcraft.common.world.gen;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.template.IRuleTestType;
import net.minecraft.world.gen.feature.template.RuleTest;

import java.util.Random;

public class VoidOreRuleTest extends RuleTest {

    public static final Codec<VoidOreRuleTest> CODEC = Registry.BLOCK.fieldOf("block").xmap(VoidOreRuleTest::new, (VoidOreRuleTest) -> {
        return Blocks.BEDROCK;
    }).codec();

    public VoidOreRuleTest(Block block){}

    @Override
    public boolean test(BlockState state, Random random) {
        return  state.is(Blocks.BEDROCK) || state.is(Blocks.STONE) || state.is(Blocks.ANDESITE) || state.is(Blocks.GRANITE) || state.is(Blocks.DIORITE);
    }

    @Override
    protected IRuleTestType<?> getType() {
        return IRuleTestType.BLOCK_TEST;
    }
}
