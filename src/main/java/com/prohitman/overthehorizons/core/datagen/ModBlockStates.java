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
        simpleBlock(ModBlocks.ARID_SOIL.get(), models().cubeAll("arid_soil", modLoc("block/arid_soil")));
        simpleBlock(ModBlocks.PACKED_ROOTS.get(), models().cubeAll("packed_roots", modLoc("block/packed_roots")));
        simpleBlock(ModBlocks.ROSE.get(), models().cross("rose", modLoc("block/rose")));
        simpleBlock(ModBlocks.SLATE_BRICKS.get(), models().cubeAll("slate_bricks", modLoc("block/slate_bricks")));
        simpleBlock(ModBlocks.SLATE_TILES.get(), models().cubeAll("slate_tiles", modLoc("block/slate_tiles")));
        simpleBlock(ModBlocks.SLATE.get(), models().cubeAll("slate", modLoc("block/slate")));
        simpleBlock(ModBlocks.ERODED_SLATE.get(), models().cubeAll("eroded_slate", modLoc("block/eroded_slate")));
        simpleBlock(ModBlocks.HEDGEHOG_MUSHROOM_TALL.get(), models().cross("hedgehog_mushroom_tall", modLoc("block/hedgehog_mushroom_tall")));
        simpleBlock(ModBlocks.HEDGEHOG_MUSHROOM.get(), models().cross("hedgehog_mushroom", modLoc("block/hedgehog_mushroom")));

        simpleBlock(ModBlocks.DUNE_GRASS.get(), models().cross("dune_grass", modLoc("block/dune_grass")));
        simpleBlock(ModBlocks.WILD_WHEAT.get(), models().cross("wild_wheat", modLoc("block/wild_wheat")));
        simpleBlock(ModBlocks.SPROUTS.get(), models().cross("sprouts", modLoc("block/sprouts")));
        simpleBlock(ModBlocks.TRAMPLED_GRASS.get(), models().singleTexture("trampled_grass", mcLoc("block/tinted_cross"), "cross", modLoc("block/trampled_grass")));

        slabBlock(ModBlocks.ADOBE_SLAB.get(), modLoc("block/adobe"), modLoc("block/adobe"));
        stairsBlock(ModBlocks.ADOBE_STAIRS.get(), modLoc("block/adobe"));
        slabBlock(ModBlocks.ADOBE_BRICKS_SLAB.get(), modLoc("block/adobe_bricks"), modLoc("block/adobe_bricks"));
        stairsBlock(ModBlocks.ADOBE_BRICKS_STAIRS.get(), modLoc("block/adobe_bricks"));
        slabBlock(ModBlocks.SMOOTH_ADOBE_SLAB.get(), modLoc("block/smooth_adobe"), modLoc("block/smooth_adobe"));
        stairsBlock(ModBlocks.SMOOTH_ADOBE_STAIRS.get(), modLoc("block/smooth_adobe"));

        simpleBlock(ModBlocks.PINE_PLANKS.get(), models().cubeAll("pine_planks", modLoc("block/pine_planks")));
        trapdoorBlock(ModBlocks.PINE_TRAPDOOR.get(), modLoc("block/pine_trapdoor"), true);
        doorBlock(ModBlocks.PINE_DOOR.get(), modLoc("block/pine_door_bottom"), modLoc("block/pine_door_top"));
        buttonBlock(ModBlocks.PINE_BUTTON.get(), modLoc("block/pine_planks"));
        slabBlock(ModBlocks.PINE_SLAB.get(), modLoc("block/pine_planks"), modLoc("block/pine_planks"));
        stairsBlock(ModBlocks.PINE_STAIRS.get(), modLoc("block/pine_planks"));
        pressurePlateBlock(ModBlocks.PINE_PRESSURE_PLATE.get(), modLoc("block/pine_planks"));
        logBlock(ModBlocks.PINE_LOG.get());
        logBlock(ModBlocks.STRIPPED_PINE_LOG.get());
        simpleBlock(ModBlocks.PINE_WOOD.get(), models().cubeAll("pine_wood", modLoc("block/pine_log")));
        simpleBlock(ModBlocks.STRIPPED_PINE_WOOD.get(), models().cubeAll("stripped_pine_wood", modLoc("block/stripped_pine_log")));
        simpleBlock(ModBlocks.PINE_CONE.get(), models().cross("pine_cone", modLoc("block/pine_cone")));
        fenceBlock(ModBlocks.PINE_FENCE.get(), modLoc("block/pine_planks"));
        fenceGateBlock(ModBlocks.PINE_FENCE_GATE.get(), modLoc("block/pine_planks"));
        signBlock(ModBlocks.PINE_STANDING_SIGN.get(), ModBlocks.PINE_WALL_SIGN.get(), modLoc("entity/signs/pine"));
    }
}
