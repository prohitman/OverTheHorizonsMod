package com.prohitman.overthehorizons.client.events;

import com.mojang.blaze3d.vertex.PoseStack;
import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.client.keybinds.ModKeyBindings;
import com.prohitman.overthehorizons.client.models.*;
import com.prohitman.overthehorizons.client.particles.LeafParticle;
import com.prohitman.overthehorizons.client.renderers.CatFishRenderer;
import com.prohitman.overthehorizons.client.renderers.FennecFoxRenderer;
import com.prohitman.overthehorizons.client.renderers.ModBoatRenderer;
import com.prohitman.overthehorizons.client.renderers.PerchRenderer;
import com.prohitman.overthehorizons.common.entity.ModBoat;
import com.prohitman.overthehorizons.common.item.HuntingRifleItem;
import com.prohitman.overthehorizons.core.init.*;
import com.prohitman.overthehorizons.core.util.ModOverlayRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.awt.*;

@Mod.EventBusSubscriber(modid = OverTheHorizonsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    public static final IGuiOverlay RIFLE_SCOPE_ELEMENT = ((gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        gui.setupOverlayRenderState(true, false);
        if (ModKeyBindings.zoomRifleKeyMapping.get().isDown() && Minecraft.getInstance().player.getMainHandItem().getItem() instanceof HuntingRifleItem) {
            ModOverlayRenderer.renderSpyglassOverlay();
        }
    });

    public static final IGuiOverlay AMMO_COUNT = ((gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        gui.setupOverlayRenderState(true, false);
        AbstractClientPlayer clientplayer = Minecraft.getInstance().player;
        if (clientplayer != null && !gui.getMinecraft().options.hideGui) {
            ItemStack stack = clientplayer.getMainHandItem();
            Item heldItem = stack.getItem();
            CompoundTag tag = stack.getTag();

            if (heldItem instanceof HuntingRifleItem) {
                if (tag != null) {
                    PoseStack textmatrix = new PoseStack();
                    textmatrix.scale(2.0F, 2.0F, 2.0F);
                    poseStack.drawString(gui.getFont(), "\u00A7e" + tag.getInt("AmmoCount") + "/" + 25, 0, 0, 0);
                    poseStack.pose().scale(2,2,2);
                    //Minecraft.getInstance().font.drawInBatch(textmatrix, "\u00A7e" + tag.getInt("AmmoCount") + "/" + 25, 0, 0, 0);
                }
            }
        }
    });

    @SubscribeEvent
    public static void registerOverlays(RegisterGuiOverlaysEvent event){
        event.registerBelow(VanillaGuiOverlay.HOTBAR.id(), "rifle", RIFLE_SCOPE_ELEMENT);
        event.registerAbove(VanillaGuiOverlay.TITLE_TEXT.id(), "ammocount", AMMO_COUNT);
    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            BlockEntityRenderers.register(ModBlockEntities.MOD_SIGN.get(), SignRenderer::new);
            BlockEntityRenderers.register(ModBlockEntities.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
            Sheets.addWoodType(ModWoodTypes.PINE);
        });
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityTypes.MOD_BOAT.get(), (pContext -> new ModBoatRenderer(pContext, false)));
        event.registerEntityRenderer(ModEntityTypes.MOD_CHEST_BOAT.get(), (pContext -> new ModBoatRenderer(pContext, true)));
        event.registerEntityRenderer(ModEntityTypes.PERCH.get(), PerchRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.CATFISH.get(), CatFishRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.FENNEC_FOX.get(), FennecFoxRenderer::new);
    }

    @SubscribeEvent
    public static void registerEntityLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        for (ModBoat.ModType boat$type : ModBoat.ModType.values()) {
            event.registerLayerDefinition(ModBoatRenderer.createBoatModelName(boat$type), ModBoatModel::createBodyModel);
            event.registerLayerDefinition(ModBoatRenderer.createChestBoatModelName(boat$type), ModChestBoatModel::createBodyModel);
        }
        event.registerLayerDefinition(PerchModel.LAYER_LOCATION, PerchModel::createBodyLayer);
        event.registerLayerDefinition(CatFishModel.LAYER_LOCATION, CatFishModel::createBodyLayer);
        event.registerLayerDefinition(FennecFoxModel.LAYER_LOCATION, FennecFoxModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticleTypes.ORANGE_LEAF_PARTICLE.get(), LeafParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.YELLOW_LEAF_PARTICLE.get(), LeafParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.BROWN_LEAF_PARTICLE.get(), LeafParticle.Provider::new);
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((pState, pLevel, pPos, pTintIndex) ->
                        pLevel != null && pPos != null ? BiomeColors.getAverageFoliageColor(pLevel, pPos) : FoliageColor.getDefaultColor(),
                ModBlocks.PINE_LEAVES.get());
        event.register((pState, pLevel, pPos, pTintIndex) -> pLevel != null &&
                pPos != null ? BiomeColors.getAverageGrassColor(pLevel, pPos) :
                GrassColor.get(0.5D, 1.0D), ModBlocks.TRAMPLED_GRASS.get());

        //event.getBlockColors().register((pState, pLevel, pPos, pTintIndex) -> new Color(0x358539FF).getRGB(), ModBlocks.PINE_LEAVES.get());
    }

    @SubscribeEvent
    public static void registerBlockItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((pState, pTintIndex) -> new Color(0x408143).getRGB(), ModBlocks.PINE_LEAVES.get());
        event.register((p_92687_, p_92688_) -> {
            BlockState blockstate = ((BlockItem) p_92687_.getItem()).getBlock().defaultBlockState();
            return event.getBlockColors().getColor(blockstate, (BlockAndTintGetter) null, (BlockPos) null, p_92688_);
        }, ModBlocks.TRAMPLED_GRASS.get());

    }//4d6031*/
}
