package com.prohitman.overthehorizons.core.datagen;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.core.init.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStates extends BlockStateProvider {

    public ModBlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, OverTheHorizonsMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        horizontalBlock(ModBlocks.RED_LICHENSTONE.get(), modLoc("textures/block/red_lichenstone_side"), mcLoc("textures/block/stone"), modLoc("textures/block/red_lichenstone_top"));
        horizontalBlock(ModBlocks.GREEN_LICHENSTONE.get(), modLoc("textures/block/green_lichenstone_side"), mcLoc("textures/block/stone"), modLoc("textures/block/green_lichenstone_top"));
    }
}
