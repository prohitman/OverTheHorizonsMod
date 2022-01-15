package com.prohitman.overthehorizons.client.events;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.client.particles.LeafParticle;
import com.prohitman.overthehorizons.client.renderers.ModBoatRenderer;
import com.prohitman.overthehorizons.common.blocks.entity.ModSignBlockEntity;
import com.prohitman.overthehorizons.common.entity.ModBoat;
import com.prohitman.overthehorizons.core.init.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.texture.AtlasSet;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

@Mod.EventBusSubscriber(modid = OverTheHorizonsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event){
        event.enqueueWork(() -> {

            ItemBlockRenderTypes.setRenderLayer(ModBlocks.FALLEN_LEAVES.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.RED_LICHEN_COVERAGE.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.GREEN_LICHEN_COVERAGE.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.TREE_MOSS.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.DUCKWEED.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.PINE_TRAPDOOR.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.PINE_CONE.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.PINE_DOOR.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.PINE_LEAVES.get(), RenderType.cutoutMipped());

            BlockEntityRenderers.register(ModBlockEntities.MOD_SIGN.get(), SignRenderer::new);
            Sheets.addWoodType(ModWoodTypes.PINE);
        });
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(ModEntityTypes.MOD_BOAT.get(), ModBoatRenderer::new);
    }

    @SubscribeEvent
    public static void registerEntityLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event){
        for(ModBoat.ModType boat$type : ModBoat.ModType.values()) {
            event.registerLayerDefinition(ModBoatRenderer.createBoatModelName(boat$type), BoatModel::createBodyModel);
        }
    }

    @SubscribeEvent
    public static void registerParticleProviders(ParticleFactoryRegisterEvent event){
        Minecraft.getInstance().particleEngine.register(ModParticleTypes.ORANGE_LEAF_PARTICLE.get(), LeafParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticleTypes.YELLOW_LEAF_PARTICLE.get(), LeafParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticleTypes.BROWN_LEAF_PARTICLE.get(), LeafParticle.Provider::new);
    }

    @SubscribeEvent
    public static void registerBlockLeavesColors(ColorHandlerEvent.Block event){
        event.getBlockColors().register((pState, pLevel, pPos, pTintIndex) -> new Color(0x00FF00).getRGB(), ModBlocks.PINE_LEAVES.get());
    }

    @SubscribeEvent
    public static void registerBlockItemLeavesColors(ColorHandlerEvent.Item event){
        event.getItemColors().register((pState, pTintIndex) -> new Color(0x00FF00).getRGB(), ModBlocks.PINE_LEAVES.get());
    }
}
