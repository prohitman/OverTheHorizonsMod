package com.prohitman.overthehorizons.common.events;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OverTheHorizonsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonForgeEvents {

    @SubscribeEvent
    public static void spawnParticles(TickEvent.WorldTickEvent event){
    }
}
