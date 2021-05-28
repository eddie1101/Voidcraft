package erg.voidcraft.common.world;

import net.minecraft.world.GameRules;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class VoidcraftGameRules {

    public static GameRules.RuleKey<?> RULE_VOIDLURKERHEIGHTDAMAGE;

    public static void registerGameRules(FMLCommonSetupEvent event) {
        RULE_VOIDLURKERHEIGHTDAMAGE = GameRules.register("voidLurkersTakeHeightDamage", GameRules.Category.MOBS, GameRules.BooleanValue.create(true));
    }

}
