package com.prohitman.overthehorizons.common.events;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.core.init.ModBlocks;
import com.prohitman.overthehorizons.core.init.ModItems;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = OverTheHorizonsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonForgeEvents {

    @SubscribeEvent
    public static void setup(final FMLCommonSetupEvent event){
        event.enqueueWork(() -> {
            ComposterBlock.COMPOSTABLES.put(ModBlocks.FALLEN_LEAVES.get().asItem(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.DRIED_BIRCH_LEAVES.get().asItem(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.DRIED_OAK_LEAVES.get().asItem(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.DRIED_DARK_OAK_LEAVES.get().asItem(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.RED_LICHEN_COVERAGE.get().asItem(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.GREEN_LICHEN_COVERAGE.get().asItem(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(ModItems.LICHEN_CLUSTER.get().asItem(), 0.8F);
        });
    }
}
