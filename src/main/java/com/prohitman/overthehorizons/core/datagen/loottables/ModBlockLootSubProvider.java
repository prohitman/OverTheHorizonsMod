package com.prohitman.overthehorizons.core.datagen.loottables;

import com.prohitman.overthehorizons.core.init.ModBlocks;
import com.prohitman.overthehorizons.core.init.ModItems;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.DynamicLoot;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.minecraft.world.level.storage.loot.providers.number.BinomialDistributionGenerator;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class ModBlockLootSubProvider implements LootTableSubProvider {
    protected static final LootItemCondition.Builder HAS_SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))));
    protected static final LootItemCondition.Builder HAS_NO_SILK_TOUCH = HAS_SILK_TOUCH.invert();
    protected static final LootItemCondition.Builder HAS_SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));
    private static final LootItemCondition.Builder HAS_SHEARS_OR_SILK_TOUCH = HAS_SHEARS.or(HAS_SILK_TOUCH);
    private static final LootItemCondition.Builder HAS_NO_SHEARS_OR_SILK_TOUCH = HAS_SHEARS_OR_SILK_TOUCH.invert();
    private final Set<Item> explosionResistant;
    private final FeatureFlagSet enabledFeatures;
    private final Map<ResourceLocation, LootTable.Builder> map = new HashMap<>();
    protected static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
    private static final float[] NORMAL_LEAVES_STICK_CHANCES = new float[]{0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F};

    protected ModBlockLootSubProvider() {
        this.explosionResistant = Collections.emptySet();
        this.enabledFeatures = FeatureFlags.REGISTRY.allFlags();
    }

    protected <T extends FunctionUserBuilder<T>> T applyExplosionDecay(ItemLike pItem, FunctionUserBuilder<T> pFunctionBuilder) {
        return (T)(!this.explosionResistant.contains(pItem.asItem()) ? pFunctionBuilder.apply(ApplyExplosionDecay.explosionDecay()) : pFunctionBuilder.unwrap());
    }

    protected <T extends ConditionUserBuilder<T>> T applyExplosionCondition(ItemLike pItem, ConditionUserBuilder<T> pConditionBuilder) {
        return (T)(!this.explosionResistant.contains(pItem.asItem()) ? pConditionBuilder.when(ExplosionCondition.survivesExplosion()) : pConditionBuilder.unwrap());
    }

    public LootTable.Builder createSingleItemTable(ItemLike pItem) {
        return LootTable.lootTable().withPool(this.applyExplosionCondition(pItem, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(pItem))));
    }

    /**
     * If the condition from {@code conditionBuilder} succeeds, drops 1 {@code block}.
     * Otherwise, drops loot specified by {@code alternativeBuilder}.
     */
    protected static LootTable.Builder createSelfDropDispatchTable(Block pBlock, LootItemCondition.Builder pConditionBuilder, LootPoolEntryContainer.Builder<?> pAlternativeBuilder) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(pBlock).when(pConditionBuilder).otherwise(pAlternativeBuilder)));
    }

    /**
     * If the block is mined with Silk Touch, drops 1 {@code block}.
     * Otherwise, drops loot specified by {@code builder}.
     */
    protected static LootTable.Builder createSilkTouchDispatchTable(Block pBlock, LootPoolEntryContainer.Builder<?> pBuilder) {
        return createSelfDropDispatchTable(pBlock, HAS_SILK_TOUCH, pBuilder);
    }

    /**
     * If the block is mined with Shears, drops 1 {@code block}.
     * Otherwise, drops loot specified by {@code builder}.
     */
    protected static LootTable.Builder createShearsDispatchTable(Block pBlock, LootPoolEntryContainer.Builder<?> pBuilder) {
        return createSelfDropDispatchTable(pBlock, HAS_SHEARS, pBuilder);
    }

    /**
     * If the block is mined either with Silk Touch or Shears, drops 1 {@code block}.
     * Otherwise, drops loot specified by {@code builder}.
     */
    protected static LootTable.Builder createSilkTouchOrShearsDispatchTable(Block pBlock, LootPoolEntryContainer.Builder<?> pBuilder) {
        return createSelfDropDispatchTable(pBlock, HAS_SHEARS_OR_SILK_TOUCH, pBuilder);
    }

    protected static LootTable.Builder createSilkTouchOrShearsDispatchTableNoCondition(Block pBlock) {
        return createSelfDropDispatchTableNoCondition(pBlock, HAS_SHEARS_OR_SILK_TOUCH);
    }

    protected static LootTable.Builder createSelfDropDispatchTableNoCondition(Block pBlock, LootItemCondition.Builder pConditionBuilder) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(pBlock).when(pConditionBuilder)));
    }

    protected LootTable.Builder createSingleItemTableWithSilkTouch(Block pBlock, ItemLike pItem) {
        return createSilkTouchDispatchTable(pBlock, this.applyExplosionCondition(pBlock, LootItem.lootTableItem(pItem)));
    }

    protected LootTable.Builder createSingleItemTable(ItemLike pItem, NumberProvider pCount) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(this.applyExplosionDecay(pItem, LootItem.lootTableItem(pItem).apply(SetItemCountFunction.setCount(pCount)))));
    }

    protected LootTable.Builder createSingleItemTableWithSilkTouch(Block pBlock, ItemLike pItem, NumberProvider pCount) {
        return createSilkTouchDispatchTable(pBlock, this.applyExplosionDecay(pBlock, LootItem.lootTableItem(pItem).apply(SetItemCountFunction.setCount(pCount))));
    }

    protected static LootTable.Builder createSilkTouchOnlyTable(ItemLike pItem) {
        return LootTable.lootTable().withPool(LootPool.lootPool().when(HAS_SILK_TOUCH).setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(pItem)));
    }

    protected LootTable.Builder createPotFlowerItemTable(ItemLike pItem) {
        return LootTable.lootTable().withPool(this.applyExplosionCondition(Blocks.FLOWER_POT, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(Blocks.FLOWER_POT)))).withPool(this.applyExplosionCondition(pItem, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(pItem))));
    }

    protected LootTable.Builder createSlabItemTable(Block pBlock) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(this.applyExplosionDecay(pBlock, LootItem.lootTableItem(pBlock).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE)))))));
    }

    protected <T extends Comparable<T> & StringRepresentable> LootTable.Builder createSinglePropConditionTable(Block pBlock, Property<T> pProperty, T pValue) {
        return LootTable.lootTable().withPool(this.applyExplosionCondition(pBlock, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(pBlock).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(pProperty, pValue))))));
    }

    protected LootTable.Builder createNameableBlockEntityTable(Block pBlock) {
        return LootTable.lootTable().withPool(this.applyExplosionCondition(pBlock, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(pBlock).apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY)))));
    }

    protected LootTable.Builder createShulkerBoxDrop(Block pBlock) {
        return LootTable.lootTable().withPool(this.applyExplosionCondition(pBlock, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(pBlock).apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY)).apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY).copy("Lock", "BlockEntityTag.Lock").copy("LootTable", "BlockEntityTag.LootTable").copy("LootTableSeed", "BlockEntityTag.LootTableSeed")).apply(SetContainerContents.setContents(BlockEntityType.SHULKER_BOX).withEntry(DynamicLoot.dynamicEntry(ShulkerBoxBlock.CONTENTS))))));
    }

    protected LootTable.Builder createCopperOreDrops(Block pBlock) {
        return createSilkTouchDispatchTable(pBlock, this.applyExplosionDecay(pBlock, LootItem.lootTableItem(Items.RAW_COPPER).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F))).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    protected LootTable.Builder createLapisOreDrops(Block pBlock) {
        return createSilkTouchDispatchTable(pBlock, this.applyExplosionDecay(pBlock, LootItem.lootTableItem(Items.LAPIS_LAZULI).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 9.0F))).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    protected LootTable.Builder createRedstoneOreDrops(Block pBlock) {
        return createSilkTouchDispatchTable(pBlock, this.applyExplosionDecay(pBlock, LootItem.lootTableItem(Items.REDSTONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 5.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    protected LootTable.Builder createBannerDrop(Block pBlock) {
        return LootTable.lootTable().withPool(this.applyExplosionCondition(pBlock, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(pBlock).apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY)).apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY).copy("Patterns", "BlockEntityTag.Patterns")))));
    }

    protected static LootTable.Builder createBeeNestDrop(Block pBlock) {
        return LootTable.lootTable().withPool(LootPool.lootPool().when(HAS_SILK_TOUCH).setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(pBlock).apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY).copy("Bees", "BlockEntityTag.Bees")).apply(CopyBlockState.copyState(pBlock).copy(BeehiveBlock.HONEY_LEVEL))));
    }

    protected static LootTable.Builder createBeeHiveDrop(Block pBlock) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(pBlock).when(HAS_SILK_TOUCH).apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY).copy("Bees", "BlockEntityTag.Bees")).apply(CopyBlockState.copyState(pBlock).copy(BeehiveBlock.HONEY_LEVEL)).otherwise(LootItem.lootTableItem(pBlock))));
    }

    protected static LootTable.Builder createCaveVinesDrop(Block pBlock) {
        return LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(Items.GLOW_BERRIES)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CaveVines.BERRIES, true))));
    }

    protected LootTable.Builder createOreDrop(Block pBlock, Item pItem) {
        return createSilkTouchDispatchTable(pBlock, this.applyExplosionDecay(pBlock, LootItem.lootTableItem(pItem).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    protected LootTable.Builder createMushroomBlockDrop(Block pBlock, ItemLike pItem) {
        return createSilkTouchDispatchTable(pBlock, this.applyExplosionDecay(pBlock, LootItem.lootTableItem(pItem).apply(SetItemCountFunction.setCount(UniformGenerator.between(-6.0F, 2.0F))).apply(LimitCount.limitCount(IntRange.lowerBound(0)))));
    }

    protected LootTable.Builder createGrassDrops(Block pBlock) {
        return createShearsDispatchTable(pBlock, this.applyExplosionDecay(pBlock, LootItem.lootTableItem(Items.WHEAT_SEEDS).when(LootItemRandomChanceCondition.randomChance(0.125F)).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 2))));
    }

    /**
     * Creates a builder that drops the given IItemProvider in amounts between 0 and 3, based on the AGE property. Only
     * used in vanilla for pumpkin and melon stems.
     */
    public LootTable.Builder createStemDrops(Block pBlock, Item pItem) {
        return LootTable.lootTable().withPool(this.applyExplosionDecay(pBlock, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(pItem).apply(StemBlock.AGE.getPossibleValues(), (p_249795_) -> {
            return SetItemCountFunction.setCount(BinomialDistributionGenerator.binomial(3, (float)(p_249795_ + 1) / 15.0F)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StemBlock.AGE, p_249795_)));
        }))));
    }

    public LootTable.Builder createAttachedStemDrops(Block pBlock, Item pItem) {
        return LootTable.lootTable().withPool(this.applyExplosionDecay(pBlock, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(pItem).apply(SetItemCountFunction.setCount(BinomialDistributionGenerator.binomial(3, 0.53333336F))))));
    }

    protected static LootTable.Builder createShearsOnlyDrop(ItemLike pItem) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(HAS_SHEARS).add(LootItem.lootTableItem(pItem)));
    }

    protected LootTable.Builder createMultifaceBlockDrops(Block pBlock, LootItemCondition.Builder pBuilder) {
        return LootTable.lootTable().withPool(LootPool.lootPool().add(this.applyExplosionDecay(pBlock, LootItem.lootTableItem(pBlock).when(pBuilder).apply(Direction.values(), (p_251536_) -> {
            return SetItemCountFunction.setCount(ConstantValue.exactly(1.0F), true).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MultifaceBlock.getFaceProperty(p_251536_), true)));
        }).apply(SetItemCountFunction.setCount(ConstantValue.exactly(-1.0F), true)))));
    }

    /**
     * Used for all leaves, drops self with silk touch, otherwise drops the second Block param with the passed chances
     * for fortune levels, adding in sticks.
     */
    protected LootTable.Builder createLeavesDrops(Block pLeavesBlock, Block pSaplingBlock, float... pChances) {
        return createSilkTouchOrShearsDispatchTable(pLeavesBlock, this.applyExplosionCondition(pLeavesBlock, LootItem.lootTableItem(pSaplingBlock)).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, pChances))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(HAS_NO_SHEARS_OR_SILK_TOUCH).add(this.applyExplosionDecay(pLeavesBlock, LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, NORMAL_LEAVES_STICK_CHANCES))));
    }

    protected LootTable.Builder createDriedLeavesDrops(Block pLeavesBlock, float... pChances) {
        return createSilkTouchOrShearsDispatchTable(pLeavesBlock, this.applyExplosionCondition(pLeavesBlock, LootItem.lootTableItem(Items.STICK)).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, pChances))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(HAS_NO_SHEARS_OR_SILK_TOUCH).add(applyExplosionDecay(pLeavesBlock, LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 1.5F, 1.025F, 1.55F, 1.035F, 1.2F))));
    }

    /**
     * Used for oak and dark oak, same as droppingWithChancesAndSticks but adding in apples.
     */
    protected LootTable.Builder createOakLeavesDrops(Block pOakLeavesBlock, Block pSaplingBlock, float... pChances) {
        return this.createLeavesDrops(pOakLeavesBlock, pSaplingBlock, pChances).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(HAS_NO_SHEARS_OR_SILK_TOUCH).add(this.applyExplosionCondition(pOakLeavesBlock, LootItem.lootTableItem(Items.APPLE)).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F))));
    }

    protected LootTable.Builder createMangroveLeavesDrops(Block pBlock) {
        return createSilkTouchOrShearsDispatchTable(pBlock, this.applyExplosionDecay(Blocks.MANGROVE_LEAVES, LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, NORMAL_LEAVES_STICK_CHANCES)));
    }

    /**
     * If {@code dropGrownCropCondition} fails (i.e. crop is not ready), drops 1 {@code seedsItem}.
     * If {@code dropGrownCropCondition} succeeds (i.e. crop is ready), drops 1 {@code grownCropItem}, and 0-3 {@code
     * seedsItem} with fortune applied.
     */
    protected LootTable.Builder createCropDrops(Block pCropBlock, Item pGrownCropItem, Item pSeedsItem, LootItemCondition.Builder pDropGrownCropCondition) {
        return this.applyExplosionDecay(pCropBlock, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(pGrownCropItem).when(pDropGrownCropCondition).otherwise(LootItem.lootTableItem(pSeedsItem)))).withPool(LootPool.lootPool().when(pDropGrownCropCondition).add(LootItem.lootTableItem(pSeedsItem).apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3)))));
    }

    protected static LootTable.Builder createDoublePlantShearsDrop(Block pSheared) {
        return LootTable.lootTable().withPool(LootPool.lootPool().when(HAS_SHEARS).add(LootItem.lootTableItem(pSheared).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))));
    }

    protected LootTable.Builder createDoublePlantWithSeedDrops(Block pBlock, Block pSheared) {
        LootPoolEntryContainer.Builder<?> builder = LootItem.lootTableItem(pSheared).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))).when(HAS_SHEARS).otherwise(this.applyExplosionCondition(pBlock, LootItem.lootTableItem(Items.WHEAT_SEEDS)).when(LootItemRandomChanceCondition.randomChance(0.125F)));
        return LootTable.lootTable().withPool(LootPool.lootPool().add(builder).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER))).when(LocationCheck.checkLocation(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(pBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER).build()).build()), new BlockPos(0, 1, 0)))).withPool(LootPool.lootPool().add(builder).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER))).when(LocationCheck.checkLocation(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(pBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER).build()).build()), new BlockPos(0, -1, 0))));
    }

    protected LootTable.Builder createDoublePlantWithSeedItemDrops(Block block, Item item){
        LootPoolEntryContainer.Builder<?> builder = LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))).when(HAS_SHEARS).otherwise(this.applyExplosionCondition(block, LootItem.lootTableItem(item)).when(LootItemRandomChanceCondition.randomChance(0.125F)));
        return LootTable.lootTable().withPool(LootPool.lootPool().add(builder).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER))).when(LocationCheck.checkLocation(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER).build()).build()), new BlockPos(0, 1, 0)))).withPool(LootPool.lootPool().add(builder).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER))).when(LocationCheck.checkLocation(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER).build()).build()), new BlockPos(0, -1, 0))));
    }

    protected LootTable.Builder createCattailDrops(Block block){
        LootPoolEntryContainer.Builder<?> builder = LootItem.lootTableItem(block).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))).when(HAS_SHEARS).otherwise(this.applyExplosionCondition(block, LootItem.lootTableItem(ModItems.CATTAIL_SEEDS.get())).when(LootItemRandomChanceCondition.randomChance(0.125F)));
        return LootTable.lootTable().withPool(LootPool.lootPool().add(builder).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER))).when(LocationCheck.checkLocation(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER).build()).build()), new BlockPos(0, 1, 0)))).withPool(LootPool.lootPool().add(builder).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER))).when(LocationCheck.checkLocation(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER).build()).build()), new BlockPos(0, -1, 0))));
    }

    protected LootTable.Builder createCandleDrops(Block pCandleBlock) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(this.applyExplosionDecay(pCandleBlock, LootItem.lootTableItem(pCandleBlock).apply(List.of(2, 3, 4), (p_249985_) -> {
            return SetItemCountFunction.setCount(ConstantValue.exactly((float) p_249985_)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pCandleBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CandleBlock.CANDLES, p_249985_)));
        }))));
    }

    protected static LootTable.Builder createCandleCakeDrops(Block pCandleCakeBlock) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(pCandleCakeBlock)));
    }

    public static LootTable.Builder noDrop() {
        return LootTable.lootTable();
    }

    public void generate(){
        dropSelf(ModBlocks.ROSE.get());
        dropSelf(ModBlocks.HEDGEHOG_MUSHROOM.get());
        dropSelf(ModBlocks.HEDGEHOG_MUSHROOM_TALL.get());
        dropSelf(ModBlocks.PACKED_ROOTS.get());
        dropSelf(ModBlocks.ARID_SOIL.get());
        dropSelf(ModBlocks.STRIPPED_PINE_WOOD.get());
        dropSelf(ModBlocks.ADOBE.get());
        dropSelf(ModBlocks.FRAMED_ADOBE.get());
        dropSelf(ModBlocks.SMOOTH_ADOBE.get());
        this.add(ModBlocks.SMOOTH_ADOBE_SLAB.get(), this::createSlabItemTable);
        dropSelf(ModBlocks.SMOOTH_ADOBE_STAIRS.get());
        this.add(ModBlocks.ADOBE_SLAB.get(), this::createSlabItemTable);
        dropSelf(ModBlocks.ADOBE_STAIRS.get());
        dropSelf(ModBlocks.ADOBE_BRICKS.get());
        this.add(ModBlocks.ADOBE_BRICKS_SLAB.get(), this::createSlabItemTable);
        dropSelf(ModBlocks.ADOBE_BRICKS_STAIRS.get());
        this.add(ModBlocks.RED_LICHENSTONE.get(), (block) -> this.createSingleItemTableWithSilkTouch(block, Blocks.COBBLESTONE));
        this.add(ModBlocks.GREEN_LICHENSTONE.get(), (block) -> this.createSingleItemTableWithSilkTouch(block, Blocks.COBBLESTONE));
        dropSelf(ModBlocks.PEBBLES.get());
        dropSelf(ModBlocks.RIVER_ROCKS.get());
        this.add(ModBlocks.TREE_MOSS.get(), (block) -> ModBlockLootSubProvider.createShearsOnlyDrop(block.asItem()));
        dropSelf(ModBlocks.PINE_LOG.get());
        dropSelf(ModBlocks.PINE_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_PINE_LOG.get());
        dropSelf(ModBlocks.PINE_PLANKS.get());
        dropSelf(ModBlocks.PINE_STAIRS.get());
        this.add(ModBlocks.PINE_SLAB.get(), this::createSlabItemTable);
        dropSelf(ModBlocks.PINE_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.PINE_BUTTON.get());
        dropSelf(ModBlocks.PINE_TRAPDOOR.get());
        dropSelf(ModBlocks.PINE_CONE.get());
        dropSelf(ModBlocks.PINE_FENCE.get());
        dropSelf(ModBlocks.PINE_FENCE_GATE.get());
        dropSelf(ModBlocks.PINE_STANDING_SIGN.get());
        dropSelf(ModBlocks.PINE_WALL_SIGN.get());
        dropSelf(ModBlocks.DUCKWEED.get());
        this.add(ModBlocks.PINE_LEAVES.get(), (block) -> this.createLeavesDrops(block, Blocks.BIRCH_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES));
        this.add(ModBlocks.DRIED_BIRCH_LEAVES.get(), (block) -> this.createDriedLeavesDrops(block, NORMAL_LEAVES_SAPLING_CHANCES));
        this.add(ModBlocks.DRIED_OAK_LEAVES.get(), (block) -> this.createDriedLeavesDrops(block, NORMAL_LEAVES_SAPLING_CHANCES));
        this.add(ModBlocks.DRIED_DARK_OAK_LEAVES.get(), (block) -> this.createDriedLeavesDrops(block, NORMAL_LEAVES_SAPLING_CHANCES));
        this.add(ModBlocks.FALLEN_LEAVES.get(), ModBlockLootSubProvider::createSilkTouchOrShearsDispatchTableNoCondition);
        this.add(ModBlocks.RED_LICHEN_COVERAGE.get(), ModBlockLootSubProvider::createSilkTouchOrShearsDispatchTableNoCondition);
        this.add(ModBlocks.GREEN_LICHEN_COVERAGE.get(), ModBlockLootSubProvider::createSilkTouchOrShearsDispatchTableNoCondition);
        this.add(ModBlocks.DUNE_GRASS.get(), ModBlockLootSubProvider::createSilkTouchOrShearsDispatchTableNoCondition);
        this.add(ModBlocks.SPROUTS.get(), ModBlockLootSubProvider::createSilkTouchOrShearsDispatchTableNoCondition);
        this.add(ModBlocks.WILD_WHEAT.get(), ModBlockLootSubProvider::createSilkTouchOrShearsDispatchTableNoCondition);
        this.add(ModBlocks.TALL_WILD_WHEAT.get(), (block) -> this.createDoublePlantWithSeedDrops(block, ModBlocks.WILD_WHEAT.get()));
        this.add(ModBlocks.PINE_DOOR.get(), this::createDoorTable);
        dropSelf(ModBlocks.SLATE_BRICKS.get());
        dropSelf(ModBlocks.SLATE_TILES.get());
        dropSelf(ModBlocks.ERODED_SLATE.get());
        dropSelf(ModBlocks.SLATE.get());
        this.add(ModBlocks.TRAMPLED_GRASS.get(), ModBlockLootSubProvider::createSilkTouchOrShearsDispatchTableNoCondition);
        this.add(ModBlocks.LAND_REEDS.get(), this::createCattailDrops);
        this.add(ModBlocks.WATER_REEDS.get(), this::createCattailDrops);
    }

    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> builder) {
        this.generate();

        Set<ResourceLocation> set = new HashSet<>();
       for(Block block : ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).toList()) {
            if (block.isEnabled(this.enabledFeatures)) {
                ResourceLocation resourcelocation = block.getLootTable();
                if (resourcelocation != BuiltInLootTables.EMPTY && set.add(resourcelocation)) {
                    LootTable.Builder loottable$builder = this.map.remove(resourcelocation);
                    if (loottable$builder == null) {
                        throw new IllegalStateException(String.format(Locale.ROOT, "Missing loottable '%s' for '%s'", resourcelocation, BuiltInRegistries.BLOCK.getKey(block)));
                    }

                    builder.accept(resourcelocation, loottable$builder);
                }
            }
       }

        if (!this.map.isEmpty()) {
            throw new IllegalStateException("Created block loot tables for non-blocks: " + this.map.keySet());
        }
    }

    protected void addNetherVinesDropTable(Block pVines, Block pPlant) {
        LootTable.Builder loottable$builder = createSilkTouchOrShearsDispatchTable(pVines, LootItem.lootTableItem(pVines).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.33F, 0.55F, 0.77F, 1.0F)));
        this.add(pVines, loottable$builder);
        this.add(pPlant, loottable$builder);
    }

    protected LootTable.Builder createDoorTable(Block pDoorBlock) {
        return this.createSinglePropConditionTable(pDoorBlock, DoorBlock.HALF, DoubleBlockHalf.LOWER);
    }

    protected void dropPottedContents(Block pFlowerPot) {
        this.add(pFlowerPot, (p_250193_) -> {
            return this.createPotFlowerItemTable(((FlowerPotBlock)p_250193_).getContent());
        });
    }

    protected void otherWhenSilkTouch(Block pBlock, Block pOther) {
        this.add(pBlock, createSilkTouchOnlyTable(pOther));
    }

    protected void dropOther(Block pBlock, ItemLike pItem) {
        this.add(pBlock, this.createSingleItemTable(pItem));
    }

    protected void dropWhenSilkTouch(Block pBlock) {
        this.otherWhenSilkTouch(pBlock, pBlock);
    }

    protected void dropSelf(Block pBlock) {
        this.dropOther(pBlock, pBlock);
    }

    protected void add(Block pBlock, Function<Block, LootTable.Builder> pFactory) {
        this.add(pBlock, pFactory.apply(pBlock));
    }

    protected void add(Block pBlock, LootTable.Builder pBuilder) {
        this.map.put(pBlock.getLootTable(), pBuilder);
    }
}
