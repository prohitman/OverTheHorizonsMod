package com.prohitman.overthehorizons.client.events;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.client.particles.LeafParticle;
import com.prohitman.overthehorizons.core.init.ModParticleTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OverTheHorizonsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void registerParticleProviders(ParticleFactoryRegisterEvent event){
        Minecraft.getInstance().particleEngine.register(ModParticleTypes.OAK_LEAF_PARTICLE.get(), LeafParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticleTypes.BIRCH_LEAF_PARTICLE.get(), LeafParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticleTypes.DARK_OAK_LEAF_PARTICLE.get(), LeafParticle.Provider::new);
    }
}
