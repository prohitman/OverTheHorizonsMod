package com.prohitman.overthehorizons.core.datagen;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.core.init.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;

public class ModBlockStates extends BlockStateProvider {

    public ModBlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, OverTheHorizonsMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.RED_LICHENSTONE.get(), models().cubeBottomTop("red_lichenstone",modLoc("block/red_lichenstone_side"), mcLoc("block/stone"), modLoc("block/red_lichenstone_top")));
        simpleBlock(ModBlocks.GREEN_LICHENSTONE.get(), models().cubeBottomTop("green_lichenstone", modLoc("block/green_lichenstone_side"), mcLoc("block/stone"), modLoc("block/green_lichenstone_top")));
        simpleBlock(ModBlocks.RED_LICHEN_COVERAGE.get(), models().cross("red_lichen_coverage", modLoc("block/red_lichen_coverage")));
        simpleBlock(ModBlocks.GREEN_LICHEN_COVERAGE.get(), models().cross("green_lichen_coverage", modLoc("block/green_lichen_coverage")));
        simpleBlock(ModBlocks.PEBBLES.get(), models().cubeColumn("pebbles", modLoc("block/pebbles_side"), modLoc("block/pebbles_top")));

        simpleBlock(ModBlocks.PINE_PLANKS.get(), models().cubeAll("pine_planks", modLoc("block/pine_planks")));
        trapdoorBlock(ModBlocks.PINE_TRAPDOOR.get(), modLoc("block/pine_trapdoor"), true);
        doorBlock(ModBlocks.PINE_DOOR.get(), modLoc("block/pine_door_bottom"), modLoc("block/pine_door_top"));
        buttonBlock(ModBlocks.PINE_BUTTON.get(), modLoc("block/pine_planks"));
        slabBlock(ModBlocks.PINE_SLAB.get(), modLoc("block/pine_planks"), modLoc("block/pine_planks"));
        stairsBlock(ModBlocks.PINE_STAIRS.get(), modLoc("block/pine_planks"));
        pressurePlateBlock(ModBlocks.PINE_PRESSURE_PLATE.get(), modLoc("block/pine_planks"));
        logBlock(ModBlocks.PINE_LOG.get());
        logBlock(ModBlocks.STRIPPED_PINE_LOG.get());
        simpleBlock(ModBlocks.PINE_CONE.get(), models().cross("pine_cone", modLoc("block/pine_cone")));
        fenceBlock(ModBlocks.PINE_FENCE.get(), modLoc("block/pine_planks"));
        fenceGateBlock(ModBlocks.PINE_FENCE_GATE.get(), modLoc("block/pine_planks"));
    }
}
