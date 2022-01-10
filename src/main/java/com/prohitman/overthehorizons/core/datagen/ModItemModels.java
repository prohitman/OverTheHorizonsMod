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
        withExistingParent(ModBlocks.RED_LICHEN_COVERAGE.get().getRegistryName().getPath(), modLoc("block/red_lichen_coverage"));
        withExistingParent(ModBlocks.GREEN_LICHEN_COVERAGE.get().getRegistryName().getPath(), modLoc("block/green_lichen_coverage"));

        //Items
        singleTexture(ModItems.LICHEN_CLUSTER.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/lichen_cluster"));
    }
}
