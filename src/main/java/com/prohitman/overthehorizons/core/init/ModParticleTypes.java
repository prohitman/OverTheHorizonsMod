package com.prohitman.overthehorizons.core.init;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticleTypes {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, OverTheHorizonsMod.MOD_ID);

    public static final RegistryObject<SimpleParticleType> OAK_LEAF_PARTICLE = PARTICLES.register("oak_leaf_particle", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> DARK_OAK_LEAF_PARTICLE = PARTICLES.register( "dark_oak_leaf_particle", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> BIRCH_LEAF_PARTICLE = PARTICLES.register("birch_leaf_particle", () -> new SimpleParticleType(true));

}
