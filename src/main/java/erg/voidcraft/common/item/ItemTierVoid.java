package erg.voidcraft.common.item;

import erg.voidcraft.common.init.VoidcraftItems;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public class ItemTierVoid implements IItemTier {

    private final int maxUses = 5070;
    private final float efficiency = 12.0f;
    private final float attackDamage = 4.0f;
    private final int harvestLevel = 4;
    private final int enchantability = 30;
    private Ingredient repairMaterial = Ingredient.of(VoidcraftItems.VOID_CRYSTAL);

    public ItemTierVoid() {

    }

    @Override
    public int getUses() {
        return maxUses;
    }

    @Override
    public float getSpeed() {
        return efficiency;
    }

    @Override
    public float getAttackDamageBonus() {
        return attackDamage;
    }

    @Override
    public int getLevel() {
        return harvestLevel;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairMaterial;
    }
}
