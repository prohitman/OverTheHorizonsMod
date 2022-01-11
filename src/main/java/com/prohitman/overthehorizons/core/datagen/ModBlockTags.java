package com.prohitman.overthehorizons.core.datagen;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.core.init.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

public class ModBlockTags extends BlockTagsProvider {
    public ModBlockTags(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, OverTheHorizonsMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.GREEN_LICHENSTONE.get())
                .add(ModBlocks.RED_LICHENSTONE.get())
                .add(ModBlocks.ADOBE.get())
                .add(ModBlocks.ADOBE_BRICKS.get())
                .add(ModBlocks.SMOOTH_ADOBE.get())
                .add(ModBlocks.FRAMED_ADOBE.get())
                .add(ModBlocks.PEBBLES.get());
        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.GREEN_LICHENSTONE.get())
                .add(ModBlocks.RED_LICHENSTONE.get())
                .add(ModBlocks.ADOBE.get())
                .add(ModBlocks.ADOBE_BRICKS.get())
                .add(ModBlocks.SMOOTH_ADOBE.get())
                .add(ModBlocks.FRAMED_ADOBE.get())
                .add(ModBlocks.PEBBLES.get());
        tag(BlockTags.REPLACEABLE_PLANTS)
                .add(ModBlocks.GREEN_LICHEN_COVERAGE.get())
                .add(ModBlocks.RED_LICHEN_COVERAGE.get());
        tag(BlockTags.CLIMBABLE)
                .add(ModBlocks.TREE_MOSS.get());
    }
}
