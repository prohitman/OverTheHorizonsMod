package com.prohitman.overthehorizons.core.datagen.loottables;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.core.init.ModBlocks;
import com.prohitman.overthehorizons.core.init.ModItems;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.BlockPos;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;
import java.util.stream.Collectors;

public class ModBlockLoottables extends VanillaBlockLoot {
    protected static final LootItemCondition.Builder HAS_SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))));
    protected static final LootItemCondition.Builder HAS_NO_SILK_TOUCH = HAS_SILK_TOUCH.invert();
    protected static final LootItemCondition.Builder HAS_SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));
    private static final LootItemCondition.Builder HAS_SHEARS_OR_SILK_TOUCH = HAS_SHEARS.or(HAS_SILK_TOUCH);
    private static final LootItemCondition.Builder HAS_NO_SHEARS_OR_SILK_TOUCH = HAS_SHEARS_OR_SILK_TOUCH.invert();

    protected static LootTable.Builder createSilkTouchOrShearsDispatchTableNoCondition(Block pBlock) {
        return createSelfDropDispatchTableNoCondition(pBlock, HAS_SHEARS_OR_SILK_TOUCH);
    }

    protected static LootTable.Builder createSelfDropDispatchTableNoCondition(Block pBlock, LootItemCondition.Builder pConditionBuilder) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(pBlock).when(pConditionBuilder)));
    }

    protected LootTable.Builder createDriedLeavesDrops(Block pLeavesBlock, float... pChances) {
        return createSilkTouchOrShearsDispatchTable(pLeavesBlock, this.applyExplosionCondition(pLeavesBlock, LootItem.lootTableItem(Items.STICK)).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, pChances))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(HAS_NO_SHEARS_OR_SILK_TOUCH).add(applyExplosionDecay(pLeavesBlock, LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 1.5F, 1.025F, 1.55F, 1.035F, 1.2F))));
    }

    protected LootTable.Builder createCattailDrops(Block block){
        LootPoolEntryContainer.Builder<?> builder = LootItem.lootTableItem(block).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))).when(HAS_SHEARS).otherwise(this.applyExplosionCondition(block, LootItem.lootTableItem(ModItems.CATTAIL_SEEDS.get())).when(LootItemRandomChanceCondition.randomChance(0.125F)));
        return LootTable.lootTable().withPool(LootPool.lootPool().add(builder).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER))).when(LocationCheck.checkLocation(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER).build()).build()), new BlockPos(0, 1, 0)))).withPool(LootPool.lootPool().add(builder).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER))).when(LocationCheck.checkLocation(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER).build()).build()), new BlockPos(0, -1, 0))));
    }

    @Override
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
        this.add(ModBlocks.TREE_MOSS.get(), (block) -> ModBlockLoottables.createShearsOnlyDrop(block.asItem()));
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
        this.add(ModBlocks.FALLEN_LEAVES.get(), ModBlockLoottables::createSilkTouchOrShearsDispatchTableNoCondition);
        this.add(ModBlocks.RED_LICHEN_COVERAGE.get(), ModBlockLoottables::createSilkTouchOrShearsDispatchTableNoCondition);
        this.add(ModBlocks.GREEN_LICHEN_COVERAGE.get(), ModBlockLoottables::createSilkTouchOrShearsDispatchTableNoCondition);
        this.add(ModBlocks.DUNE_GRASS.get(), ModBlockLoottables::createSilkTouchOrShearsDispatchTableNoCondition);
        this.add(ModBlocks.SPROUTS.get(), ModBlockLoottables::createSilkTouchOrShearsDispatchTableNoCondition);
        this.add(ModBlocks.WILD_WHEAT.get(), ModBlockLoottables::createSilkTouchOrShearsDispatchTableNoCondition);
        this.add(ModBlocks.TALL_WILD_WHEAT.get(), (block) -> this.createDoublePlantWithSeedDrops(block, ModBlocks.WILD_WHEAT.get()));
        this.add(ModBlocks.PINE_DOOR.get(), this::createDoorTable);
        dropSelf(ModBlocks.SLATE_BRICKS.get());
        dropSelf(ModBlocks.SLATE_TILES.get());
        dropSelf(ModBlocks.ERODED_SLATE.get());
        dropSelf(ModBlocks.SLATE.get());
        this.add(ModBlocks.TRAMPLED_GRASS.get(), ModBlockLoottables::createSilkTouchOrShearsDispatchTableNoCondition);
        this.add(ModBlocks.LAND_REEDS.get(), this::createCattailDrops);
        this.add(ModBlocks.WATER_REEDS.get(), this::createCattailDrops);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ForgeRegistries.BLOCKS.getEntries().stream()
                .filter(e -> e.getKey().location().getNamespace().equals(OverTheHorizonsMod.MOD_ID))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
