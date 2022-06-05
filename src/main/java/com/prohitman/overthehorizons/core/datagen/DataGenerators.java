package com.prohitman.overthehorizons.core.datagen;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = OverTheHorizonsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator dataGenerator = event.getGenerator();
        if(event.includeServer()){
            dataGenerator.addProvider(new ModRecipes(dataGenerator));
            ModBlockTags blockTags = new ModBlockTags(dataGenerator, event.getExistingFileHelper());
            dataGenerator.addProvider(blockTags);
            dataGenerator.addProvider(new ModItemTags(dataGenerator, blockTags, event.getExistingFileHelper()));
            dataGenerator.addProvider(new ModLootTables(dataGenerator));
        }
        if(event.includeClient()){
            dataGenerator.addProvider(new ModBlockStates(dataGenerator, event.getExistingFileHelper()));
            dataGenerator.addProvider(new ModItemModels(dataGenerator, event.getExistingFileHelper()));
            dataGenerator.addProvider(new ModLanguageProvider(dataGenerator, "en_us"));
        }
    }
}
