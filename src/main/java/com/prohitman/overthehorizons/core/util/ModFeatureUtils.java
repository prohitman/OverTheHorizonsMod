package com.prohitman.overthehorizons.core.util;

import com.prohitman.overthehorizons.core.init.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

public class ModFeatureUtils {
    //Vegetation patches
    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> GREEN_LICHEN_VEGETATION = FeatureUtils.register("green_lichen_vegetation", Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(ModBlocks.GREEN_LICHEN_COVERAGE.get().defaultBlockState(), 50))));
    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> RED_LICHEN_VEGETATION = FeatureUtils.register("red_lichen_vegetation", Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(ModBlocks.RED_LICHEN_COVERAGE.get().defaultBlockState(), 50))));

    //Bonemeal Vegetation
    public static final Holder<ConfiguredFeature<VegetationPatchConfiguration, ?>> GREEN_LICHENSTONE_PATCH_BONEMEAL = FeatureUtils.register("green_lichen_patch_bonemeal", Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.simple(ModBlocks.GREEN_LICHENSTONE.get()), PlacementUtils.inlinePlaced(GREEN_LICHEN_VEGETATION), CaveSurface.FLOOR, ConstantInt.of(1), 0.0F, 5, 0.6F, UniformInt.of(1, 2), 0.75F));
    public static final Holder<ConfiguredFeature<VegetationPatchConfiguration, ?>> RED_LICHENSTONE_PATCH_BONEMEAL = FeatureUtils.register("red_lichen_patch_bonemeal", Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.simple(ModBlocks.RED_LICHENSTONE.get()), PlacementUtils.inlinePlaced(RED_LICHEN_VEGETATION), CaveSurface.FLOOR, ConstantInt.of(1), 0.0F, 5, 0.6F, UniformInt.of(1, 2), 0.75F));

}
