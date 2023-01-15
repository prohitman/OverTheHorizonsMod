package com.prohitman.overthehorizons.core.datagen;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OverTheHorizonsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator dataGenerator = event.getGenerator();

        dataGenerator.addProvider(event.includeServer(), (DataProvider.Factory<ModRecipes>) output -> new ModRecipes(dataGenerator.getPackOutput()));
        ModBlockTags blockTags = new ModBlockTags(dataGenerator.getPackOutput(), event.getLookupProvider(), event.getExistingFileHelper());
        dataGenerator.addProvider(event.includeServer(), (DataProvider.Factory<ModBlockTags>) output -> blockTags);
        dataGenerator.addProvider(event.includeServer(), (DataProvider.Factory<ModItemTags>) output -> new ModItemTags(dataGenerator.getPackOutput(), event.getLookupProvider(), blockTags, event.getExistingFileHelper()));
        dataGenerator.addProvider(event.includeServer(), (DataProvider.Factory<ModLootTables>) output -> new ModLootTables(dataGenerator));

        dataGenerator.addProvider(event.includeClient(), (DataProvider.Factory<ModBlockStates>) output -> new ModBlockStates(dataGenerator.getPackOutput(), event.getExistingFileHelper()));
        dataGenerator.addProvider(event.includeClient(), (DataProvider.Factory<ModItemModels>) output -> new ModItemModels(dataGenerator.getPackOutput(), event.getExistingFileHelper()));
        dataGenerator.addProvider(event.includeClient(), (DataProvider.Factory<ModLanguageProvider>) output -> new ModLanguageProvider(dataGenerator.getPackOutput(), "en_us"));
    }
}
