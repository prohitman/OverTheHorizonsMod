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
    }
}
