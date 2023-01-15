package com.prohitman.overthehorizons;

import com.prohitman.overthehorizons.client.events.ClientEventBusSubscriber;
import com.prohitman.overthehorizons.common.events.CommonForgeEvents;
import com.prohitman.overthehorizons.core.init.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

@Mod("overthehorizons")
@Mod.EventBusSubscriber(modid = OverTheHorizonsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OverTheHorizonsMod
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "overthehorizons";
    public static float scopeScale = 0.5F;


    public OverTheHorizonsMod() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(CommonForgeEvents::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientEventBusSubscriber::clientSetup);

        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModParticleTypes.PARTICLES.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModEntityTypes.ENTITY_TYPES.register(modEventBus);
        ModSounds.SOUND_TYPES.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
    }

    private void processIMC(final InterModProcessEvent event)
    {

    }
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
}
