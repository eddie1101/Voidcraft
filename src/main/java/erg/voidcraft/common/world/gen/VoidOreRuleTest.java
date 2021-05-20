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

    public static final Codec<VoidOreRuleTest> field_237075_a_ = Registry.BLOCK.fieldOf("block").xmap(VoidOreRuleTest::new, (VoidOreRuleTest) -> {
        return Blocks.BEDROCK;
    }).codec();

    public VoidOreRuleTest(Block block){}

    @Override
    public boolean test(BlockState state, Random random) {
        return  state.isIn(Blocks.BEDROCK) || state.isIn(Blocks.STONE) || state.isIn(Blocks.ANDESITE) || state.isIn(Blocks.GRANITE) || state.isIn(Blocks.DIORITE);
    }

    @Override
    protected IRuleTestType<?> getType() {
        return IRuleTestType.BLOCK_MATCH;
    }
}
