package com.prohitman.overthehorizons.core.init;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.common.entity.ModBoat;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, OverTheHorizonsMod.MOD_ID);

    public static final RegistryObject<EntityType<ModBoat>> MOD_BOAT = ENTITY_TYPES.register("boat",() -> EntityType.Builder
            .<ModBoat>of(ModBoat::new, MobCategory.MISC)
            .sized(1.375F, 0.5625F)
            .build(new ResourceLocation(OverTheHorizonsMod.MOD_ID, "boat").toString()));
    //public static final RegistryObject<EntityType<Boat>> PINE_BOAT = ENTITY_TYPES.register("pine_boat", () -> new Boat());
}
