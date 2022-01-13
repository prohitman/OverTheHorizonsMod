package com.prohitman.overthehorizons.client.events;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.client.particles.LeafParticle;
import com.prohitman.overthehorizons.core.init.ModBlocks;
import com.prohitman.overthehorizons.core.init.ModParticleTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = OverTheHorizonsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event){
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.FALLEN_LEAVES.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.RED_LICHEN_COVERAGE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GREEN_LICHEN_COVERAGE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.TREE_MOSS.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.DUCKWEED.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PINE_TRAPDOOR.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PINE_CONE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PINE_DOOR.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PINE_LEAVES.get(), RenderType.cutoutMipped());

    }

    @SubscribeEvent
    public static void registerParticleProviders(ParticleFactoryRegisterEvent event){
        Minecraft.getInstance().particleEngine.register(ModParticleTypes.ORANGE_LEAF_PARTICLE.get(), LeafParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticleTypes.YELLOW_LEAF_PARTICLE.get(), LeafParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticleTypes.BROWN_LEAF_PARTICLE.get(), LeafParticle.Provider::new);
    }

    @SubscribeEvent
    public static void registerLeavesColors(ColorHandlerEvent event){
        BlockColors blockcolors = new BlockColors();
        blockcolors.register((p_92626_, pLevel, blockPos, p_92629_) -> pLevel != null && blockPos != null ? BiomeColors.getAverageFoliageColor(pLevel, blockPos) : FoliageColor.getDefaultColor(), ModBlocks.PINE_LEAVES.get());
    }
}
