package erg.voidcraft.world.gen;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.template.IRuleTestType;
import net.minecraft.world.gen.feature.template.RuleTest;

import java.util.Random;

public class VoidOreNetherRuleTest extends RuleTest {

    public static final Codec<VoidOreNetherRuleTest> field_237075_a_ = Registry.BLOCK.fieldOf("block").xmap(VoidOreNetherRuleTest::new, (VoidOreNetherRuleTest) -> {
        return Blocks.BEDROCK;
    }).codec();

    public VoidOreNetherRuleTest(Block block){}

    @Override
    public boolean test(BlockState state, Random random) {
        return  state.isIn(Blocks.BEDROCK) || state.isIn(Blocks.NETHERRACK) || state.isIn(Blocks.BASALT) || state.isIn(Blocks.BLACKSTONE);
    }

    @Override
    protected IRuleTestType<?> getType() {
        return IRuleTestType.BLOCK_MATCH;
    }
}
