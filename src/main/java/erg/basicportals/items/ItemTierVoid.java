package erg.basicportals.items;

import erg.basicportals.StartupCommon;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public class ItemTierVoid implements IItemTier {

    private int maxUses = 5070;
    private float efficiency = 11.0f;
    private float attackDamage = 4.0f;
    private int harvestLevel = 4;
    private int enchantability = 30;
    private Ingredient repairMaterial = Ingredient.fromItems(StartupCommon.itemVoidCrystal);

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
