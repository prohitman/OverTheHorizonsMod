package com.prohitman.overthehorizons.core.init;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.common.entity.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, OverTheHorizonsMod.MOD_ID);

    public static final RegistryObject<EntityType<ModBoat>> MOD_BOAT = ENTITY_TYPES.register("boat",() -> EntityType.Builder
            .<ModBoat>of(ModBoat::new, MobCategory.MISC)
            .sized(1.375F, 0.5625F)
            .build(new ResourceLocation(OverTheHorizonsMod.MOD_ID, "boat").toString()));

    public static final RegistryObject<EntityType<ModChestBoat>> MOD_CHEST_BOAT = ENTITY_TYPES.register("chest_boat",() -> EntityType.Builder
            .<ModChestBoat>of(ModChestBoat::new, MobCategory.MISC)
            .sized(1.375F, 0.5625F)
            .clientTrackingRange(10)
            .build(new ResourceLocation(OverTheHorizonsMod.MOD_ID, "chest_boat").toString()));

    public static final RegistryObject<EntityType<Perch>> PERCH = ENTITY_TYPES.register("perch",() -> EntityType.Builder
            .<Perch>of(Perch::new, MobCategory.WATER_CREATURE)
            .sized(0.4F, 0.2F)
            .build(new ResourceLocation(OverTheHorizonsMod.MOD_ID, "perch").toString()));

    public static final RegistryObject<EntityType<CatFish>> CATFISH = ENTITY_TYPES.register("catfish",() -> EntityType.Builder
            .<CatFish>of(CatFish::new, MobCategory.WATER_CREATURE)
            .sized(0.75F, 0.5F)
            .build(new ResourceLocation(OverTheHorizonsMod.MOD_ID, "catfish").toString()));

    public static final RegistryObject<EntityType<FennecFox>> FENNEC_FOX = ENTITY_TYPES.register("fennec_fox",() -> EntityType.Builder
            .<FennecFox>of(FennecFox::new, MobCategory.CREATURE)
            .sized(0.5F, 0.6F)
            .build(new ResourceLocation(OverTheHorizonsMod.MOD_ID, "fennec_fox").toString()));
}
