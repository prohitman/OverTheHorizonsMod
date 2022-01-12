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
        singleTexture((ModBlocks.GREEN_LICHEN_COVERAGE.get().getRegistryName().getPath()),
                mcLoc("item/generated"),
                "layer0", modLoc("block/green_lichen_coverage"));
        singleTexture((ModBlocks.RED_LICHEN_COVERAGE.get().getRegistryName().getPath()),
                mcLoc("item/generated"),
                "layer0", modLoc("block/red_lichen_coverage"));
        singleTexture((ModBlocks.TREE_MOSS.get().getRegistryName().getPath()),
                mcLoc("item/generated"),
                "layer0", modLoc("item/moss_cluster"));

        //Items
        singleTexture(ModItems.LICHEN_CLUSTER.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/lichen_cluster"));
    }
}
