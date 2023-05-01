package com.prohitman.overthehorizons.common.worldgen;

import com.prohitman.overthehorizons.core.init.ModBlocks;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

public class ConfiguredFeaures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> LICHEN_PATCH = FeatureUtils.createKey("lichen_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LICHEN_VEGETATION = FeatureUtils.createKey("lichen_vegetation");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> feature) {
        HolderGetter<ConfiguredFeature<?, ?>> holdergetter = feature.lookup(Registries.CONFIGURED_FEATURE);

        FeatureUtils.register(feature, LICHEN_VEGETATION, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(ModBlocks.GREEN_LICHEN_COVERAGE.get().defaultBlockState(), 25).add(Blocks.GRASS.defaultBlockState(), 50).add(Blocks.TALL_GRASS.defaultBlockState(), 10))));
        FeatureUtils.register(feature, LICHEN_PATCH, Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.simple(ModBlocks.GREEN_LICHENSTONE.get()), PlacementUtils.inlinePlaced(holdergetter.getOrThrow(LICHEN_VEGETATION)), CaveSurface.FLOOR, ConstantInt.of(1), 0.0F, 5, 0.6F, UniformInt.of(1, 2), 0.75F));
    }
}
