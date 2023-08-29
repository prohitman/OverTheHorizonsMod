package com.prohitman.overthehorizons.common.worldgen;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPlacerTypes {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER_TYPES = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, OverTheHorizonsMod.MOD_ID);

    //public static final RegistryObject<TrunkPlacerType<?>> PINE_TRUNK_PLACER = TRUNK_PLACER_TYPES.register("pine_trunk_placer", () -> new TrunkPlacerType<>(PineTrunkPlacer.CODEC));

    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPES = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, OverTheHorizonsMod.MOD_ID);

    //public static final RegistryObject<FoliagePlacerType<?>> PINE_FOLIAGE_PLACER = FOLIAGE_PLACER_TYPES.register("pine_foliage_placer", () -> new FoliagePlacerType<>(PineFoliagePlacer.CODEC));
}
