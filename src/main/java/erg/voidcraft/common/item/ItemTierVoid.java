package erg.voidcraft.common.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public class ItemTierVoid implements IItemTier {

    private final int maxUses = 5070;
    private final float efficiency = 11.0f;
    private final float attackDamage = 4.0f;
    private final int harvestLevel = 4;
    private final int enchantability = 30;
    private Ingredient repairMaterial = Ingredient.fromItems(VoidcraftItems.itemVoidCrystal);

    public ItemTierVoid() {

    }

    @Override
    public int getMaxUses() {
        return maxUses;
    }

    @Override
    public float getEfficiency() {
        return efficiency;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public int getHarvestLevel() {
        return harvestLevel;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return repairMaterial;
    }
}
