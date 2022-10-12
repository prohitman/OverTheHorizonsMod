package com.prohitman.overthehorizons.core.datagen;

import com.prohitman.overthehorizons.core.init.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.fml.common.Mod;

public class ModLootTables extends BaseLootTableProvider{
    public ModLootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {
        lootTables.put(ModBlocks.ROSE.get(), createSimpleTable("rose", ModBlocks.ROSE.get()));
        lootTables.put(ModBlocks.PACKED_ROOTS.get(), createSimpleTable("packed_roots", ModBlocks.PACKED_ROOTS.get()));
        lootTables.put(ModBlocks.ARID_SOIL.get(), createSimpleTable("arid_soil", ModBlocks.ARID_SOIL.get()));
        lootTables.put(ModBlocks.STRIPPED_PINE_WOOD.get(), createSimpleTable("stripped_pine_wood", ModBlocks.STRIPPED_PINE_WOOD.get()));
        lootTables.put(ModBlocks.ADOBE.get(), createSimpleTable("adobe", ModBlocks.ADOBE.get()));
        lootTables.put(ModBlocks.FRAMED_ADOBE.get(), createSimpleTable("framed_adobe", ModBlocks.FRAMED_ADOBE.get()));
        lootTables.put(ModBlocks.SMOOTH_ADOBE.get(), createSimpleTable("smooth_adobe", ModBlocks.SMOOTH_ADOBE.get()));
        lootTables.put(ModBlocks.SMOOTH_ADOBE_SLAB.get(), createSimpleTable("smooth_adobe_slab", ModBlocks.SMOOTH_ADOBE_SLAB.get()));
        lootTables.put(ModBlocks.SMOOTH_ADOBE_STAIRS.get(), createSimpleTable("smooth_adobe_stairs", ModBlocks.SMOOTH_ADOBE_STAIRS.get()));
        lootTables.put(ModBlocks.ADOBE_SLAB.get(), createSimpleTable("adobe_slab", ModBlocks.ADOBE_SLAB.get()));
        lootTables.put(ModBlocks.ADOBE_STAIRS.get(), createSimpleTable("adobe_stairs", ModBlocks.ADOBE_STAIRS.get()));
        lootTables.put(ModBlocks.ADOBE_BRICKS_SLAB.get(), createSimpleTable("adobe_bricks_slab", ModBlocks.ADOBE_BRICKS_SLAB.get()));
        lootTables.put(ModBlocks.ADOBE_BRICKS_STAIRS.get(), createSimpleTable("adobe_bricks_stairs", ModBlocks.ADOBE_BRICKS_STAIRS.get()));
        lootTables.put(ModBlocks.ADOBE_BRICKS.get(), createSimpleTable("adobe_bricks", ModBlocks.ADOBE_BRICKS.get()));
        lootTables.put(ModBlocks.RED_LICHENSTONE.get(), createSimpleTable("red_lichenstone", Blocks.COBBLESTONE));
        lootTables.put(ModBlocks.GREEN_LICHENSTONE.get(), createSimpleTable("green_lichenstone", Blocks.COBBLESTONE));
        lootTables.put(ModBlocks.RED_LICHENSTONE.get(), createSilkTouchTable("red_lichenstone", ModBlocks.RED_LICHENSTONE.get(), ModBlocks.RED_LICHENSTONE.get().asItem(), 1, 1));
        lootTables.put(ModBlocks.GREEN_LICHENSTONE.get(), createSilkTouchTable("green_lichenstone", ModBlocks.GREEN_LICHENSTONE.get(), ModBlocks.GREEN_LICHENSTONE.get().asItem(), 1, 1));
        lootTables.put(ModBlocks.PEBBLES.get(), createSimpleTable("pebbles", ModBlocks.PEBBLES.get()));
        lootTables.put(ModBlocks.RIVER_ROCKS.get(), createSimpleTable("river_rocks", ModBlocks.RIVER_ROCKS.get()));
        lootTables.put(ModBlocks.TREE_MOSS.get(), createSimpleTable("tree_moss", ModBlocks.TREE_MOSS.get()));
        lootTables.put(ModBlocks.PINE_LOG.get(), createSimpleTable("pine_log", ModBlocks.PINE_LOG.get()));
        lootTables.put(ModBlocks.PINE_WOOD.get(), createSimpleTable("pine_wood", ModBlocks.PINE_WOOD.get()));
        lootTables.put(ModBlocks.STRIPPED_PINE_LOG.get(), createSimpleTable("stripped_pine_log", ModBlocks.STRIPPED_PINE_LOG.get()));
        lootTables.put(ModBlocks.PINE_PLANKS.get(), createSimpleTable("pine_planks", ModBlocks.PINE_PLANKS.get()));
        lootTables.put(ModBlocks.PINE_STAIRS.get(), createSimpleTable("pine_stairs", ModBlocks.PINE_STAIRS.get()));
        lootTables.put(ModBlocks.PINE_SLAB.get(), createSimpleTable("pine_slab", ModBlocks.PINE_SLAB.get()));
        lootTables.put(ModBlocks.PINE_PRESSURE_PLATE.get(), createSimpleTable("pine_pressure_plate", ModBlocks.PINE_PRESSURE_PLATE.get()));
        lootTables.put(ModBlocks.PINE_BUTTON.get(), createSimpleTable("pine_button", ModBlocks.PINE_BUTTON.get()));
        lootTables.put(ModBlocks.PINE_DOOR.get(), createSimpleTable("pine_door", ModBlocks.PINE_DOOR.get()));
        lootTables.put(ModBlocks.PINE_TRAPDOOR.get(), createSimpleTable("pine_trapdoor", ModBlocks.PINE_TRAPDOOR.get()));
        lootTables.put(ModBlocks.PINE_CONE.get(), createSimpleTable("pine_cone", ModBlocks.PINE_CONE.get()));
        lootTables.put(ModBlocks.PINE_FENCE.get(), createSimpleTable("pine_fence", ModBlocks.PINE_FENCE.get()));
        lootTables.put(ModBlocks.PINE_FENCE_GATE.get(), createSimpleTable("pine_fence_gate", ModBlocks.PINE_FENCE_GATE.get()));
        lootTables.put(ModBlocks.PINE_STANDING_SIGN.get(), createSimpleTable("pine_standing_sign", ModBlocks.PINE_STANDING_SIGN.get()));
        lootTables.put(ModBlocks.PINE_WALL_SIGN.get(), createSimpleTable("pine_wall_sign", ModBlocks.PINE_WALL_SIGN.get()));
        lootTables.put(ModBlocks.DUCKWEED.get(), createSimpleTable("duckweed", ModBlocks.DUCKWEED.get()));
        lootTables.put(ModBlocks.PINE_LEAVES.get(), createLeavesDrops(ModBlocks.PINE_LEAVES.get(), Blocks.BIRCH_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES));
        lootTables.put(ModBlocks.DRIED_BIRCH_LEAVES.get(),createDriedLeavesDrops(ModBlocks.DRIED_BIRCH_LEAVES.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        lootTables.put(ModBlocks.DRIED_OAK_LEAVES.get(), createDriedLeavesDrops(ModBlocks.DRIED_OAK_LEAVES.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        lootTables.put(ModBlocks.DRIED_DARK_OAK_LEAVES.get(), createDriedLeavesDrops(ModBlocks.DRIED_DARK_OAK_LEAVES.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        lootTables.put(ModBlocks.FALLEN_LEAVES.get(), createSilkTouchOrShearsDispatchTableNoCondition(ModBlocks.FALLEN_LEAVES.get()));
        lootTables.put(ModBlocks.RED_LICHEN_COVERAGE.get(), createSilkTouchOrShearsDispatchTableNoCondition(ModBlocks.RED_LICHEN_COVERAGE.get()));
        lootTables.put(ModBlocks.GREEN_LICHEN_COVERAGE.get(), createSilkTouchOrShearsDispatchTableNoCondition(ModBlocks.GREEN_LICHEN_COVERAGE.get()));
        lootTables.put(ModBlocks.DUNE_GRASS.get(), createSilkTouchOrShearsDispatchTableNoCondition(ModBlocks.DUNE_GRASS.get()));
        lootTables.put(ModBlocks.SPROUTS.get(), createSilkTouchOrShearsDispatchTableNoCondition(ModBlocks.SPROUTS.get()));
        lootTables.put(ModBlocks.WILD_WHEAT.get(), createSilkTouchOrShearsDispatchTableNoCondition(ModBlocks.WILD_WHEAT.get()));
        lootTables.put(ModBlocks.TALL_WILD_WHEAT.get(), createSilkTouchOrShearsDispatchTableNoCondition(ModBlocks.TALL_WILD_WHEAT.get()));
        lootTables.put(ModBlocks.PINE_DOOR.get(), createDoorTable(ModBlocks.PINE_DOOR.get()));

    }
}
