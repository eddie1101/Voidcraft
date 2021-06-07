package erg.voidcraft.datagen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.KilledByPlayer;
import net.minecraft.loot.conditions.MatchTool;
import net.minecraft.loot.functions.*;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ForgeLootTableProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseLootTableProvider extends ForgeLootTableProvider {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    // Filled by subclasses
    protected final Map<Block, LootTable.Builder> blockLootTable = new HashMap<>();
    protected final Map<EntityType<?>, LootTable.Builder> entityLootTable = new HashMap<>();

    private final DataGenerator generator;

    public BaseLootTableProvider(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
        this.generator = dataGeneratorIn;
    }

    // Subclasses can override this to fill the 'lootTables' map.
    protected abstract void addTables();

    // Subclasses can call this if they want a standard loot table. Modify this for your own needs
    protected LootTable.Builder createStandardTable(String name, Block block) {
        LootPool.Builder builder = LootPool.lootPool()
                .name(name)
                .setRolls(ConstantRange.exactly(1))
                .add(ItemLootEntry.lootTableItem(block)
                        .apply(CopyName.copyName(CopyName.Source.BLOCK_ENTITY))
                        .apply(CopyNbt.copyData(CopyNbt.Source.BLOCK_ENTITY)
                                .copy("inv", "BlockEntityTag.inv", CopyNbt.Action.REPLACE)
                                .copy("energy", "BlockEntityTag.energy", CopyNbt.Action.REPLACE))
                        .apply(SetContents.setContents()
                                .withEntry(DynamicLootEntry.dynamicEntry(new ResourceLocation("minecraft", "contents"))))
                );
        return LootTable.lootTable().withPool(builder);
    }

    protected LootTable.Builder createFortuneOreTable(String name, IItemProvider drop, Block block) {

        ILootCondition.IBuilder silkTouch = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));
        LootEntry.Builder normalDrops = ItemLootEntry.lootTableItem(drop).apply(ApplyBonus.addOreBonusCount(Enchantments.BLOCK_FORTUNE));
        StandaloneLootEntry.Builder silkDrops = ItemLootEntry.lootTableItem(block);

        LootPool.Builder builder = LootPool.lootPool()
                .name(name)
                .setRolls(ConstantRange.exactly(1))
                .add(silkDrops
                    .when(silkTouch)
                    .otherwise(normalDrops));

        return LootTable.lootTable().withPool(builder);
    }

    protected LootTable.Builder createEntityTable(String name, IItemProvider drop) {

        LootPool.Builder builder = LootPool.lootPool()
                .name(name)
                .setRolls(ConstantRange.exactly(1))
                .add(ItemLootEntry.lootTableItem(drop)
                    .apply(SetCount.setCount(RandomValueRange.between(0f, 1f)))
                    .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0f, 1f))))
                    .when(KilledByPlayer.killedByPlayer());
        return LootTable.lootTable().withPool(builder);
    }

    @Override
    // Entry point
    public void run(DirectoryCache cache) {
        addTables();

        Map<ResourceLocation, LootTable> tables = new HashMap<>();
        for (Map.Entry<Block, LootTable.Builder> entry : blockLootTable.entrySet()) {
            tables.put(entry.getKey().getLootTable(), entry.getValue().setParamSet(LootParameterSets.BLOCK).build());
        }
        for(Map.Entry<EntityType<?>, LootTable.Builder> entry: entityLootTable.entrySet()) {
            tables.put(entry.getKey().getDefaultLootTable(), entry.getValue().setParamSet(LootParameterSets.ENTITY).build());
        }
        writeTables(cache, tables);
    }

    // Actually write out the tables in the output folder
    private void writeTables(DirectoryCache cache, Map<ResourceLocation, LootTable> tables) {
        Path outputFolder = this.generator.getOutputFolder();
        tables.forEach((key, lootTable) -> {
            Path path = outputFolder.resolve("data/" + key.getNamespace() + "/loot_tables/" + key.getPath() + ".json");
            try {
                IDataProvider.save(GSON, cache, LootTableManager.serialize(lootTable), path);
            } catch (IOException e) {
                LOGGER.error("Couldn't write loot table {}", path, e);
            }
        });
    }

    @Override
    public String getName() {
        return "LootTables";
    }
}
