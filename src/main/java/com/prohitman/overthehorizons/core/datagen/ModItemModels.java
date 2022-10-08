package com.prohitman.overthehorizons.core.datagen;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.core.init.ModBlocks;
import com.prohitman.overthehorizons.core.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModels extends ItemModelProvider {
    public ModItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, OverTheHorizonsMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //Blocks
        withExistingParent(ModBlocks.RED_LICHENSTONE.get().getRegistryName().getPath(), modLoc("block/red_lichenstone"));
        withExistingParent(ModBlocks.GREEN_LICHENSTONE.get().getRegistryName().getPath(), modLoc("block/green_lichenstone"));
        withExistingParent(ModBlocks.PEBBLES.get().getRegistryName().getPath(), modLoc("block/pebbles"));
        withExistingParent(ModBlocks.RIVER_ROCKS.get().getRegistryName().getPath(), modLoc("block/river_rocks"));
        withExistingParent(ModBlocks.PINE_TRAPDOOR.get().getRegistryName().getPath(), modLoc("block/pine_trapdoor_bottom"));
        withExistingParent(ModBlocks.PINE_PLANKS.get().getRegistryName().getPath(), modLoc("block/pine_planks"));
        withExistingParent(ModBlocks.PINE_LOG.get().getRegistryName().getPath(), modLoc("block/pine_log"));
        withExistingParent(ModBlocks.PINE_WOOD.get().getRegistryName().getPath(), modLoc("block/pine_wood"));
        withExistingParent(ModBlocks.STRIPPED_PINE_WOOD.get().getRegistryName().getPath(), modLoc("block/stripped_pine_wood"));
        withExistingParent(ModBlocks.PINE_BUTTON.get().getRegistryName().getPath(), modLoc("block/pine_button_inventory"));
        withExistingParent(ModBlocks.PINE_PRESSURE_PLATE.get().getRegistryName().getPath(), modLoc("block/pine_pressure_plate"));
        withExistingParent(ModBlocks.PINE_SLAB.get().getRegistryName().getPath(), modLoc("block/pine_slab"));
        withExistingParent(ModBlocks.PINE_STAIRS.get().getRegistryName().getPath(), modLoc("block/pine_stairs"));
        withExistingParent(ModBlocks.STRIPPED_PINE_LOG.get().getRegistryName().getPath(), modLoc("block/stripped_pine_log"));
        withExistingParent(ModBlocks.PINE_LEAVES.get().getRegistryName().getPath(), modLoc("block/pine_leaves"));
        withExistingParent(ModBlocks.PINE_FENCE_GATE.get().getRegistryName().getPath(), modLoc("block/pine_fence_gate"));
        withExistingParent(ModBlocks.PINE_FENCE.get().getRegistryName().getPath(), modLoc("block/pine_fence_inventory"));
        withExistingParent(ModBlocks.ADOBE_SLAB.get().getRegistryName().getPath(), modLoc("block/adobe_slab"));
        withExistingParent(ModBlocks.ADOBE_STAIRS.get().getRegistryName().getPath(), modLoc("block/adobe_stairs"));
        withExistingParent(ModBlocks.SMOOTH_ADOBE_SLAB.get().getRegistryName().getPath(), modLoc("block/smooth_adobe_slab"));
        withExistingParent(ModBlocks.SMOOTH_ADOBE_STAIRS.get().getRegistryName().getPath(), modLoc("block/smooth_adobe_stairs"));
        withExistingParent(ModBlocks.ADOBE_BRICKS_SLAB.get().getRegistryName().getPath(), modLoc("block/adobe_bricks_slab"));
        withExistingParent(ModBlocks.ADOBE_BRICKS_STAIRS.get().getRegistryName().getPath(), modLoc("block/adobe_bricks_stairs"));
        withExistingParent(ModBlocks.ARID_SOIL.get().getRegistryName().getPath(), modLoc("block/arid_soil"));
        withExistingParent(ModBlocks.PACKED_ROOTS.get().getRegistryName().getPath(), modLoc("block/packed_roots"));

        singleTexture((ModBlocks.ROSE.get().getRegistryName().getPath()),
                mcLoc("item/generated"),
                "layer0", modLoc("block/rose"));
        singleTexture((ModBlocks.DUNE_GRASS.get().getRegistryName().getPath()),
                mcLoc("item/generated"),
                "layer0", modLoc("block/dune_grass"));
        singleTexture((ModBlocks.WILD_WHEAT.get().getRegistryName().getPath()),
                mcLoc("item/generated"),
                "layer0", modLoc("block/wild_wheat"));
        singleTexture((ModBlocks.TALL_WILD_WHEAT.get().getRegistryName().getPath()),
                mcLoc("item/generated"),
                "layer0", modLoc("block/tall_wild_wheat_top"));
        singleTexture((ModBlocks.GREEN_LICHEN_COVERAGE.get().getRegistryName().getPath()),
                mcLoc("item/generated"),
                "layer0", modLoc("item/green_lichen_coverage"));
        singleTexture((ModBlocks.RED_LICHEN_COVERAGE.get().getRegistryName().getPath()),
                mcLoc("item/generated"),
                "layer0", modLoc("item/red_lichen_coverage"));
        singleTexture((ModBlocks.TREE_MOSS.get().getRegistryName().getPath()),
                mcLoc("item/generated"),
                "layer0", modLoc("item/moss_cluster"));
        singleTexture((ModBlocks.PINE_CONE.get().getRegistryName().getPath()),
                mcLoc("item/generated"),
                "layer0", modLoc("item/pine_cone"));
        singleTexture((ModBlocks.PINE_DOOR.get().getRegistryName().getPath()),
                mcLoc("item/generated"),
                "layer0", modLoc("item/pine_door"));

        //Items
        singleTexture(ModItems.LICHEN_CLUSTER.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/lichen_cluster"));
        singleTexture(ModItems.COPPER_BULLET.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/copper_bullet"));
        singleTexture(ModItems.PINE_TEA.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/pine_tea"));
        singleTexture(ModItems.PINE_SIGN.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/pine_sign"));
        singleTexture(ModItems.PINE_BOAT.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/pine_boat"));
        singleTexture(ModItems.RAW_PERCH.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/raw_perch"));
        singleTexture(ModItems.PERCH_BUCKET.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/perch_bucket"));
        singleTexture(ModItems.COOKED_PERCH.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/cooked_perch"));
        singleTexture(ModItems.RAW_CATFISH.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/raw_catfish"));
        //Spawn Eggs
        withExistingParent(ModItems.PERCH_SPAWN_EGG.get().getRegistryName().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.CATFISH_SPAWN_EGG.get().getRegistryName().getPath(), mcLoc("item/template_spawn_egg"));
    }
}
