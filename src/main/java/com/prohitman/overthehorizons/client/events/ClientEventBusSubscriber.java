package com.prohitman.overthehorizons.client.events;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.client.keybinds.ModKeyBindings;
import com.prohitman.overthehorizons.client.models.CatFishModel;
import com.prohitman.overthehorizons.client.models.FennecFoxModel;
import com.prohitman.overthehorizons.client.models.PerchModel;
import com.prohitman.overthehorizons.client.particles.LeafParticle;
import com.prohitman.overthehorizons.client.renderers.CatFishRenderer;
import com.prohitman.overthehorizons.client.renderers.FennecFoxRenderer;
import com.prohitman.overthehorizons.client.renderers.ModBoatRenderer;
import com.prohitman.overthehorizons.client.renderers.PerchRenderer;
import com.prohitman.overthehorizons.common.blocks.entity.ModSignBlockEntity;
import com.prohitman.overthehorizons.common.entity.ModBoat;
import com.prohitman.overthehorizons.common.item.HuntingRifleItem;
import com.prohitman.overthehorizons.common.network.MessageExtendedReachAttack;
import com.prohitman.overthehorizons.common.network.MessageReloadRifle;
import com.prohitman.overthehorizons.common.network.OTHPacketHandler;
import com.prohitman.overthehorizons.core.init.*;
import com.prohitman.overthehorizons.core.util.ModOverlayRenderer;
import com.prohitman.overthehorizons.core.util.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.texture.AtlasSet;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.IIngameOverlay;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

@Mod.EventBusSubscriber(modid = OverTheHorizonsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    public static final IIngameOverlay RIFLE_SCOPE_ELEMENT = OverlayRegistry.registerOverlayTop("Rifle", (gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        gui.setupOverlayRenderState(true, false);
        if(ModKeyBindings.zoomRifleKeyMapping.isDown() && Minecraft.getInstance().player.getMainHandItem().getItem() instanceof HuntingRifleItem){
            ModOverlayRenderer.renderSpyglassOverlay();
            //gui.renderSpyglassOverlay(OverTheHorizonsMod.scopeScale);
        }
    });

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event){
        event.enqueueWork(() -> {
            ModKeyBindings.init();

            ItemBlockRenderTypes.setRenderLayer(ModBlocks.FALLEN_LEAVES.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.RED_LICHEN_COVERAGE.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.GREEN_LICHEN_COVERAGE.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.TREE_MOSS.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.DUCKWEED.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.PINE_TRAPDOOR.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.PINE_CONE.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.PINE_DOOR.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.PINE_LEAVES.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.ROSE.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.WILD_WHEAT.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.DUNE_GRASS.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.TALL_WILD_WHEAT.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.SPROUTS.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.HEDGEHOG_MUSHROOM.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.HEDGEHOG_MUSHROOM_TALL.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.TRAMPLED_GRASS.get(), RenderType.cutoutMipped());

            BlockEntityRenderers.register(ModBlockEntities.MOD_SIGN.get(), SignRenderer::new);
            Sheets.addWoodType(ModWoodTypes.PINE);
        });
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(ModEntityTypes.MOD_BOAT.get(), ModBoatRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.PERCH.get(), PerchRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.CATFISH.get(), CatFishRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.FENNEC_FOX.get(), FennecFoxRenderer::new);
    }

    @SubscribeEvent
    public static void registerEntityLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event){
        for(ModBoat.ModType boat$type : ModBoat.ModType.values()) {
            event.registerLayerDefinition(ModBoatRenderer.createBoatModelName(boat$type), BoatModel::createBodyModel);
        }
        event.registerLayerDefinition(PerchModel.LAYER_LOCATION, PerchModel::createBodyLayer);
        event.registerLayerDefinition(CatFishModel.LAYER_LOCATION, CatFishModel::createBodyLayer);
        event.registerLayerDefinition(FennecFoxModel.LAYER_LOCATION, FennecFoxModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerParticleProviders(ParticleFactoryRegisterEvent event){
        Minecraft.getInstance().particleEngine.register(ModParticleTypes.ORANGE_LEAF_PARTICLE.get(), LeafParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticleTypes.YELLOW_LEAF_PARTICLE.get(), LeafParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticleTypes.BROWN_LEAF_PARTICLE.get(), LeafParticle.Provider::new);
    }

    @SubscribeEvent
    public static void registerBlockColors(ColorHandlerEvent.Block event){
        event.getBlockColors().register((pState, pLevel, pPos, pTintIndex) ->
                pLevel != null && pPos != null ? BiomeColors.getAverageFoliageColor(pLevel, pPos) : FoliageColor.getDefaultColor(),
                ModBlocks.PINE_LEAVES.get());
        event.getBlockColors().register((pState, pLevel, pPos, pTintIndex) -> pLevel != null &&
                pPos != null ? BiomeColors.getAverageGrassColor(pLevel, pPos) :
                GrassColor.get(0.5D, 1.0D), ModBlocks.TRAMPLED_GRASS.get());

        //event.getBlockColors().register((pState, pLevel, pPos, pTintIndex) -> new Color(0x358539FF).getRGB(), ModBlocks.PINE_LEAVES.get());
    }

    @SubscribeEvent
    public static void registerBlockItemColors(ColorHandlerEvent.Item event){
        event.getItemColors().register((pState, pTintIndex) -> new Color(0x408143).getRGB(), ModBlocks.PINE_LEAVES.get());
        event.getItemColors().register((p_92687_, p_92688_) -> {
            BlockState blockstate = ((BlockItem)p_92687_.getItem()).getBlock().defaultBlockState();
            return event.getBlockColors().getColor(blockstate, (BlockAndTintGetter)null, (BlockPos)null, p_92688_);
        }, ModBlocks.TRAMPLED_GRASS.get());

    }//4d6031
}
