package com.prohitman.overthehorizons.core.datagen;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.core.init.ModBlocks;
import com.prohitman.overthehorizons.core.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModels extends ItemModelProvider {
    public ModItemModels(PackOutput generator, ExistingFileHelper existingFileHelper) {
        super(generator, OverTheHorizonsMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //Blocks
        withExistingParent(ModBlocks.RED_LICHENSTONE.getId().getPath(), modLoc("block/red_lichenstone"));
        withExistingParent(ModBlocks.GREEN_LICHENSTONE.getId().getPath(), modLoc("block/green_lichenstone"));
        withExistingParent(ModBlocks.PEBBLES.getId().getPath(), modLoc("block/pebbles"));
        withExistingParent(ModBlocks.RIVER_ROCKS.getId().getPath(), modLoc("block/river_rocks"));
        withExistingParent(ModBlocks.PINE_TRAPDOOR.getId().getPath(), modLoc("block/pine_trapdoor_bottom"));
        withExistingParent(ModBlocks.PINE_PLANKS.getId().getPath(), modLoc("block/pine_planks"));
        withExistingParent(ModBlocks.PINE_LOG.getId().getPath(), modLoc("block/pine_log"));
        withExistingParent(ModBlocks.PINE_WOOD.getId().getPath(), modLoc("block/pine_wood"));
        withExistingParent(ModBlocks.STRIPPED_PINE_WOOD.getId().getPath(), modLoc("block/stripped_pine_wood"));
        withExistingParent(ModBlocks.PINE_BUTTON.getId().getPath(), modLoc("block/pine_button_inventory"));
        withExistingParent(ModBlocks.PINE_PRESSURE_PLATE.getId().getPath(), modLoc("block/pine_pressure_plate"));
        withExistingParent(ModBlocks.PINE_SLAB.getId().getPath(), modLoc("block/pine_slab"));
        withExistingParent(ModBlocks.PINE_STAIRS.getId().getPath(), modLoc("block/pine_stairs"));
        withExistingParent(ModBlocks.STRIPPED_PINE_LOG.getId().getPath(), modLoc("block/stripped_pine_log"));
        withExistingParent(ModBlocks.PINE_LEAVES.getId().getPath(), modLoc("block/pine_leaves"));
        withExistingParent(ModBlocks.PINE_FENCE_GATE.getId().getPath(), modLoc("block/pine_fence_gate"));
        withExistingParent(ModBlocks.PINE_FENCE.getId().getPath(), modLoc("block/pine_fence_inventory"));
        withExistingParent(ModBlocks.ADOBE_SLAB.getId().getPath(), modLoc("block/adobe_slab"));
        withExistingParent(ModBlocks.ADOBE_STAIRS.getId().getPath(), modLoc("block/adobe_stairs"));
        withExistingParent(ModBlocks.SMOOTH_ADOBE_SLAB.getId().getPath(), modLoc("block/smooth_adobe_slab"));
        withExistingParent(ModBlocks.SMOOTH_ADOBE_STAIRS.getId().getPath(), modLoc("block/smooth_adobe_stairs"));
        withExistingParent(ModBlocks.ADOBE_BRICKS_SLAB.getId().getPath(), modLoc("block/adobe_bricks_slab"));
        withExistingParent(ModBlocks.ADOBE_BRICKS_STAIRS.getId().getPath(), modLoc("block/adobe_bricks_stairs"));
        withExistingParent(ModBlocks.ARID_SOIL.getId().getPath(), modLoc("block/arid_soil"));
        withExistingParent(ModBlocks.PACKED_ROOTS.getId().getPath(), modLoc("block/packed_roots"));
        withExistingParent(ModBlocks.SLATE_BRICKS.getId().getPath(), modLoc("block/slate_bricks"));
        withExistingParent(ModBlocks.SLATE_TILES.getId().getPath(), modLoc("block/slate_tiles"));
        withExistingParent(ModBlocks.ERODED_SLATE.getId().getPath(), modLoc("block/eroded_slate"));
        withExistingParent(ModBlocks.SLATE.getId().getPath(), modLoc("block/slate"));

        singleTexture((ModBlocks.ROSE.getId().getPath()),
                mcLoc("item/generated"),
                "layer0", modLoc("block/rose"));
        singleTexture((ModBlocks.DUNE_GRASS.getId().getPath()),
                mcLoc("item/generated"),
                "layer0", modLoc("block/dune_grass"));
        singleTexture((ModBlocks.WILD_WHEAT.getId().getPath()),
                mcLoc("item/generated"),
                "layer0", modLoc("block/wild_wheat"));
        singleTexture((ModBlocks.TALL_WILD_WHEAT.getId().getPath()),
                mcLoc("item/generated"),
                "layer0", modLoc("block/tall_wild_wheat_top"));
        singleTexture((ModBlocks.GREEN_LICHEN_COVERAGE.getId().getPath()),
                mcLoc("item/generated"),
                "layer0", modLoc("item/green_lichen_coverage"));
        singleTexture((ModBlocks.RED_LICHEN_COVERAGE.getId().getPath()),
                mcLoc("item/generated"),
                "layer0", modLoc("item/red_lichen_coverage"));
        singleTexture((ModBlocks.TREE_MOSS.getId().getPath()),
                mcLoc("item/generated"),
                "layer0", modLoc("item/moss_cluster"));
        singleTexture((ModBlocks.PINE_CONE.getId().getPath()),
                mcLoc("item/generated"),
                "layer0", modLoc("item/pine_cone"));
        singleTexture((ModBlocks.PINE_DOOR.getId().getPath()),
                mcLoc("item/generated"),
                "layer0", modLoc("item/pine_door"));
        singleTexture((ModBlocks.SPROUTS.getId().getPath()),
                mcLoc("item/generated"),
                "layer0", modLoc("block/sprouts"));
        singleTexture((ModBlocks.HEDGEHOG_MUSHROOM.getId().getPath()),
                mcLoc("item/generated"),
                "layer0", modLoc("block/hedgehog_mushroom"));
        singleTexture((ModBlocks.HEDGEHOG_MUSHROOM_TALL.getId().getPath()),
                mcLoc("item/generated"),
                "layer0", modLoc("block/hedgehog_mushroom_tall"));
        singleTexture((ModBlocks.TRAMPLED_GRASS.getId().getPath()),
                mcLoc("item/generated"),
                "layer0", modLoc("block/trampled_grass"));
        singleTexture((ModBlocks.LAND_REEDS.getId().getPath()),
                mcLoc("item/generated"),
                "layer0", modLoc("block/land_reeds_top"));
        singleTexture((ModBlocks.WATER_REEDS.getId().getPath()),
                mcLoc("item/generated"),
                "layer0", modLoc("block/water_reeds_top"));
        singleTexture((ModBlocks.PINE_SAPLING.getId().getPath()),
                mcLoc("item/generated"),
                "layer0", modLoc("block/pine_sapling"));
        //Items
        singleTexture(ModItems.LICHEN_CLUSTER.getId().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/lichen_cluster"));
        singleTexture(ModItems.COPPER_BULLET.getId().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/copper_bullet"));
        singleTexture(ModItems.PINE_TEA.getId().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/pine_tea"));
        singleTexture(ModItems.PINE_SIGN.getId().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/pine_sign"));
        singleTexture(ModItems.PINE_HANGING_SIGN.getId().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/pine_hanging_sign"));
        singleTexture(ModItems.PINE_BOAT.getId().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/pine_boat"));
        singleTexture(ModItems.PINE_CHEST_BOAT.getId().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/pine_chest_boat"));
        singleTexture(ModItems.RAW_PERCH.getId().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/raw_perch"));
        singleTexture(ModItems.PERCH_BUCKET.getId().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/perch_bucket"));
        singleTexture(ModItems.COOKED_PERCH.getId().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/cooked_perch"));
        singleTexture(ModItems.RAW_CATFISH.getId().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/raw_catfish"));
        singleTexture(ModItems.COOKED_CATFISH.getId().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/cooked_catfish"));
        singleTexture(ModItems.FISH_BONES.getId().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/fish_bones"));
        singleTexture(ModItems.CATTAIL_SEEDS.getId().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/cattail_seeds"));

        //Spawn Eggs
        withExistingParent(ModItems.PERCH_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.CATFISH_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.FENNEC_FOX_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }
}
