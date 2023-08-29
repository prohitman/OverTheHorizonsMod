package com.prohitman.overthehorizons.core.datagen;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.core.datagen.loottables.CreateLTProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = OverTheHorizonsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator dataGenerator = event.getGenerator();

        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ModBlockTags blockTags = new ModBlockTags(dataGenerator.getPackOutput(), lookupProvider, event.getExistingFileHelper());

        //Server
        dataGenerator.addProvider(event.includeServer(), (DataProvider.Factory<ModRecipes>)
                output -> new ModRecipes(dataGenerator.getPackOutput()));

        dataGenerator.addProvider(event.includeServer(), (DataProvider.Factory<ModBlockTags>)
                output -> blockTags);

        dataGenerator.addProvider(event.includeServer(), (DataProvider.Factory<ModItemTags>)
                output -> new ModItemTags(dataGenerator.getPackOutput(), lookupProvider, blockTags.contentsGetter(), event.getExistingFileHelper()));

        dataGenerator.addProvider(event.includeServer(), (DataProvider.Factory<LootTableProvider>) CreateLTProvider::create);

        //Client
        dataGenerator.addProvider(event.includeClient(), (DataProvider.Factory<ModBlockStates>)
                output -> new ModBlockStates(dataGenerator.getPackOutput(), event.getExistingFileHelper()));

        dataGenerator.addProvider(event.includeClient(), (DataProvider.Factory<ModItemModels>)
                output -> new ModItemModels(dataGenerator.getPackOutput(), event.getExistingFileHelper()));

        dataGenerator.addProvider(event.includeClient(), (DataProvider.Factory<ModLanguageProvider>)
                output -> new ModLanguageProvider(dataGenerator.getPackOutput(), "en_us"));
    }
}
