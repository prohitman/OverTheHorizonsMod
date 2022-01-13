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
        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.PINE_LOG.get())
                .add(ModBlocks.PINE_BUTTON.get())
                .add(ModBlocks.PINE_DOOR.get())
                .add(ModBlocks.PINE_PLANKS.get())
                .add(ModBlocks.PINE_PRESSURE_PLATE.get())
                .add(ModBlocks.PINE_SLAB.get())
                .add(ModBlocks.STRIPPED_PINE_LOG.get())
                .add(ModBlocks.PINE_STAIRS.get())
                .add(ModBlocks.PINE_FENCE.get())
                .add(ModBlocks.PINE_FENCE_GATE.get())
                .add(ModBlocks.PINE_TRAPDOOR.get());
        tag(BlockTags.MINEABLE_WITH_HOE).add(ModBlocks.PINE_LEAVES.get())
                .add(ModBlocks.DRIED_BIRCH_LEAVES.get())
                .add(ModBlocks.DRIED_DARK_OAK_LEAVES.get())
                .add(ModBlocks.DRIED_OAK_LEAVES.get());
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
        tag(BlockTags.LOGS)
                .add(ModBlocks.STRIPPED_PINE_LOG.get())
                .add(ModBlocks.PINE_LOG.get());
        tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.STRIPPED_PINE_LOG.get())
                .add(ModBlocks.PINE_LOG.get());
        tag(BlockTags.LEAVES)
                .add(ModBlocks.PINE_LEAVES.get())
                .add(ModBlocks.FALLEN_LEAVES.get())
                .add(ModBlocks.DRIED_BIRCH_LEAVES.get())
                .add(ModBlocks.DRIED_DARK_OAK_LEAVES.get())
                .add(ModBlocks.DRIED_OAK_LEAVES.get());
        tag(BlockTags.PLANKS)
                .add(ModBlocks.PINE_PLANKS.get());
        tag(BlockTags.WOODEN_BUTTONS)
                .add(ModBlocks.PINE_BUTTON.get());
        tag(BlockTags.WOODEN_DOORS)
                .add(ModBlocks.PINE_DOOR.get());
        tag(BlockTags.WOODEN_SLABS)
                .add(ModBlocks.PINE_SLAB.get());
        tag(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.PINE_PRESSURE_PLATE.get());
        tag(BlockTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.PINE_TRAPDOOR.get());
        tag(BlockTags.STAIRS)
                .add(ModBlocks.PINE_STAIRS.get());
        tag(BlockTags.FENCES)
                .add(ModBlocks.PINE_FENCE.get());
        tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.PINE_FENCE_GATE.get());
    }
}
