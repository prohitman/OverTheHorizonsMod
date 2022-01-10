package com.prohitman.overthehorizons.core.datagen;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModels extends ItemModelProvider {
    public ModItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, OverTheHorizonsMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent("red_lichenstone", modLoc("block/red_lichenstone"));
        withExistingParent("green_lichenstone", modLoc("block/green_lichenstone"));
        withExistingParent("lichen_cluster", modLoc("block/lichen_cluster"));
    }
}
