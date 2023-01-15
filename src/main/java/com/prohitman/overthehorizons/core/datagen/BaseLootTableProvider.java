package com.prohitman.overthehorizons.core.datagen;

import com.google.common.collect.ImmutableSet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.*;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.packs.VanillaLootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.DynamicLoot;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public abstract class BaseLootTableProvider extends LootTableProvider {

    protected static final Logger LOGGER = LogManager.getLogger();
    protected static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    protected static final LootItemCondition.Builder HAS_SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))));
    protected static final LootItemCondition.Builder HAS_NO_SILK_TOUCH = HAS_SILK_TOUCH.invert();
    protected static final LootItemCondition.Builder HAS_SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));
    protected static final LootItemCondition.Builder HAS_SHEARS_OR_SILK_TOUCH = HAS_SHEARS.or(HAS_SILK_TOUCH);
    protected static final LootItemCondition.Builder HAS_NO_SHEARS_OR_SILK_TOUCH = HAS_SHEARS_OR_SILK_TOUCH.invert();
    protected static final Set<Item> EXPLOSION_RESISTANT = Stream.of(Blocks.DRAGON_EGG, Blocks.BEACON, Blocks.CONDUIT, Blocks.SKELETON_SKULL, Blocks.WITHER_SKELETON_SKULL, Blocks.PLAYER_HEAD, Blocks.ZOMBIE_HEAD, Blocks.CREEPER_HEAD, Blocks.DRAGON_HEAD, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX).map(ItemLike::asItem).collect(ImmutableSet.toImmutableSet());
    protected static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};

    protected final Map<Block, LootTable.Builder> lootTables = new HashMap<>();

    protected PackOutput output;

    protected static <T> T applyExplosionDecay(ItemLike pItem, FunctionUserBuilder<T> pFunction) {
        return (T)(!EXPLOSION_RESISTANT.contains(pItem.asItem()) ? pFunction.apply(ApplyExplosionDecay.explosionDecay()) : pFunction.unwrap());
    }

    public BaseLootTableProvider(PackOutput output) {
        super(output, Set.of(), VanillaLootTableProvider.create(output).getTables());
        this.output = output;
    }

    protected abstract void addTables();

    public static LootTable.Builder createDoorTable(Block p_124138_) {
        return createSinglePropConditionTable(p_124138_, DoorBlock.HALF, DoubleBlockHalf.LOWER);
    }

    protected static <T extends Comparable<T> & StringRepresentable> LootTable.Builder createSinglePropConditionTable(Block pBlock, Property<T> pProperty, T pValue) {
        return LootTable.lootTable().withPool(applyExplosionCondition(pBlock, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(pBlock).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(pProperty, pValue))))));
    }

    protected LootTable.Builder createStandardTable(String name, Block block, BlockEntityType<?> type) {
        LootPool.Builder builder = LootPool.lootPool()
                .name(name)
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(block)
                        .apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY))
                        .apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY)
                                .copy("inv", "BlockEntityTag.inv", CopyNbtFunction.MergeStrategy.REPLACE)
                                .copy("energy", "BlockEntityTag.energy", CopyNbtFunction.MergeStrategy.REPLACE))
                        .apply(SetContainerContents.setContents(type)
                                .withEntry(DynamicLoot.dynamicEntry(new ResourceLocation("minecraft", "contents"))))
                );
        return LootTable.lootTable().withPool(builder);
    }

    protected LootTable.Builder createSimpleTable(String name, Block block) {
        LootPool.Builder builder = LootPool.lootPool()
                .name(name)
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(block));
        return LootTable.lootTable().withPool(builder);
    }

    protected LootTable.Builder createSilkTouchTable(String name, Block block, Item lootItem, float min, float max) {
        LootPool.Builder builder = LootPool.lootPool()
                .name(name)
                .setRolls(ConstantValue.exactly(1))
                .add(AlternativesEntry.alternatives(
                                LootItem.lootTableItem(block)
                                        .when(MatchTool.toolMatches(ItemPredicate.Builder.item()
                                                .hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))))),
                                LootItem.lootTableItem(lootItem)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))
                                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 1))
                                        .apply(ApplyExplosionDecay.explosionDecay())
                        )
                );
        return LootTable.lootTable().withPool(builder);
    }

    protected LootTable.Builder createEntityLootTable(String name, int rolls, Item lootItem, float min, float max){
        LootPool.Builder builder = LootPool.lootPool()
                .name(name)
                .setRolls(ConstantValue.exactly(rolls))
                .add(AlternativesEntry.alternatives(
                        LootItem.lootTableItem(lootItem)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))));

        return LootTable.lootTable().withPool(builder);
    }

    protected static LootTable.Builder createLeavesDrops(Block pLeavesBlock, Block pSaplingBlock, float... pChances) {
        return createSilkTouchOrShearsDispatchTable(pLeavesBlock, applyExplosionCondition(pLeavesBlock, LootItem.lootTableItem(pSaplingBlock)).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, pChances))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(HAS_NO_SHEARS_OR_SILK_TOUCH).add(applyExplosionDecay(pLeavesBlock, LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F))));
    }

    protected static LootTable.Builder createDriedLeavesDrops(Block pLeavesBlock, float... pChances) {
        return createSilkTouchOrShearsDispatchTable(pLeavesBlock, applyExplosionCondition(pLeavesBlock, LootItem.lootTableItem(Items.STICK)).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, pChances))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(HAS_NO_SHEARS_OR_SILK_TOUCH).add(applyExplosionDecay(pLeavesBlock, LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 1.5F, 1.025F, 1.55F, 1.035F, 1.2F))));
    }

    protected static <T> T applyExplosionCondition(ItemLike pItem, ConditionUserBuilder<T> pCondition) {
        return (T)(!EXPLOSION_RESISTANT.contains(pItem.asItem()) ? pCondition.when(ExplosionCondition.survivesExplosion()) : pCondition.unwrap());
    }

    protected static LootTable.Builder createSilkTouchOrShearsDispatchTable(Block pBlock, LootPoolEntryContainer.Builder<?> pAlternativeEntryBuilder) {
        return createSelfDropDispatchTable(pBlock, HAS_SHEARS_OR_SILK_TOUCH, pAlternativeEntryBuilder);
    }

    protected static LootTable.Builder createSilkTouchOrShearsDispatchTableNoCondition(Block pBlock) {
        return createSelfDropDispatchTableNoCondition(pBlock, HAS_SHEARS_OR_SILK_TOUCH);
    }

    protected static LootTable.Builder createSelfDropDispatchTable(Block pBlock, LootItemCondition.Builder pConditionBuilder, LootPoolEntryContainer.Builder<?> pAlternativeEntryBuilder) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(pBlock).when(pConditionBuilder).otherwise(pAlternativeEntryBuilder)));
    }

    protected static LootTable.Builder createSelfDropDispatchTableNoCondition(Block pBlock, LootItemCondition.Builder pConditionBuilder) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(pBlock).when(pConditionBuilder)));
    }

    @Override
    public CompletableFuture<?> run(CachedOutput pOutput) {
        addTables();

        Map<ResourceLocation, LootTable> tables = new HashMap<>();
        for (Map.Entry<Block, LootTable.Builder> entry : lootTables.entrySet()) {
            tables.put(entry.getKey().getLootTable(), entry.getValue().setParamSet(LootContextParamSets.BLOCK).build());
        }
        writeTables(pOutput, tables);
        return super.run(pOutput);
    }

    private void writeTables(CachedOutput cache, Map<ResourceLocation, LootTable> tables) {
        Path outputFolder = output.getOutputFolder();
        tables.forEach((key, lootTable) -> {
            Path path = outputFolder.resolve("data/" + key.getNamespace() + "/loot_tables/" + key.getPath() + ".json");
            try {
                DataProvider.saveStable(cache, GSON.toJson(), path);
            } catch (IOException e) {
                LOGGER.error("Couldn't write loot table {}", path, e);
            }
        });
    }



    @Override
    public String getName() {
        return "Dragons&Dungeons LootTables";
    }
}
