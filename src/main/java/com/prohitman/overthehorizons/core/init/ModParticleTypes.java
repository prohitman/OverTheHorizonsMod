package com.prohitman.overthehorizons.core.init;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticleTypes {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, OverTheHorizonsMod.MOD_ID);

    public static final RegistryObject<SimpleParticleType> ORANGE_LEAF_PARTICLE = PARTICLES.register("orange_leaf_particle", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> BROWN_LEAF_PARTICLE = PARTICLES.register( "brown_leaf_particle", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> YELLOW_LEAF_PARTICLE = PARTICLES.register("yellow_leaf_particle", () -> new SimpleParticleType(true));

}
