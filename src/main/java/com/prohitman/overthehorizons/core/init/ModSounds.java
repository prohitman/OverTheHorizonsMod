package com.prohitman.overthehorizons.core.init;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_TYPES = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, OverTheHorizonsMod.MOD_ID);

    public static final RegistryObject<SoundEvent> RIFLE_RELOAD = SOUND_TYPES.register("rifle_reload",
            () -> new SoundEvent(new ResourceLocation(OverTheHorizonsMod.MOD_ID, "rifle_reload")));
    public static final RegistryObject<SoundEvent> RIFLE_SHOOT_BULLET = SOUND_TYPES.register("rifle_shoot_bullet",
            () -> new SoundEvent(new ResourceLocation(OverTheHorizonsMod.MOD_ID, "rifle_shoot_bullet")));
    public static final RegistryObject<SoundEvent> RIFLE_NO_AMMO = SOUND_TYPES.register("rifle_no_ammo",
            () -> new SoundEvent(new ResourceLocation(OverTheHorizonsMod.MOD_ID, "rifle_no_ammo")));
}
