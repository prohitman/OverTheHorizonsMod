package com.prohitman.overthehorizons.client.events;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.client.keybinds.ModKeyBindings;
import com.prohitman.overthehorizons.common.item.HuntingRifleItem;
import com.prohitman.overthehorizons.common.network.MessageReloadRifle;
import com.prohitman.overthehorizons.common.network.OTHPacketHandler;
import com.prohitman.overthehorizons.core.util.RenderUtils;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.*;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.awt.event.MouseEvent;

@Mod.EventBusSubscriber(modid = OverTheHorizonsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public final class ForgeClientEventBusSubscriber {
    private ForgeClientEventBusSubscriber(){

    }

    @SubscribeEvent
    public static void clientTickEvents(TickEvent.ClientTickEvent event){
        if (ModKeyBindings.reloadRifleKeyMapping.consumeClick()) {
            OTHPacketHandler.HANDLER.sendToServer(new MessageReloadRifle());
            System.out.println("Keybinding pressed");
        }
    }

    @SubscribeEvent
    public static void renderRifle(RenderHandEvent event){
        if(ModKeyBindings.zoomRifleKeyMapping.isDown()) {
            assert Minecraft.getInstance().player != null;
            if (Minecraft.getInstance().player.getMainHandItem().getItem() instanceof HuntingRifleItem) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void renderScope(RenderGameOverlayEvent.PreLayer event){
        //float f = Minecraft.getInstance().getDeltaFrameTime();
        //float scopeScale = Mth.lerp(0.5F * f,1.0f, 1.125F);
        if (Minecraft.getInstance().options.getCameraType().isFirstPerson() && event.getType() == RenderGameOverlayEvent.ElementType.LAYER
                && ModKeyBindings.zoomRifleKeyMapping.isDown()) {
            assert Minecraft.getInstance().player != null;
            if (Minecraft.getInstance().player.getMainHandItem().getItem() instanceof HuntingRifleItem) {
                RenderUtils.setupOverlayRenderState(true, false, Gui.GUI_ICONS_LOCATION);
                RenderUtils.renderSpyglassOverlay(1.0f);
                //renderTextureOverlay(new ResourceLocation("textures/misc/spyglass_scope.png"), 1.0f);
            }
        }
    }

//    @SubscribeEvent
//    public static void mouseHandler(ScreenEvent.MouseDragEvent event){
//        if(Minecraft.getInstance().options.getCameraType().isFirstPerson()
//                && Minecraft.getInstance().player.getMainHandItem().getItem() instanceof HuntingRifleItem){
//
//        }
//    }

    @SubscribeEvent
    public static void modifyFOV(FOVModifierEvent event){
        if(Minecraft.getInstance().options.getCameraType().isFirstPerson() && event.getEntity().getMainHandItem().getItem() instanceof HuntingRifleItem && ModKeyBindings.zoomRifleKeyMapping.isDown()){
            event.setNewfov(0.1f);
        }
    }

    @SubscribeEvent
    public static void renderAmmoCount(RenderGameOverlayEvent.Text event){
        AbstractClientPlayer clientplayer = Minecraft.getInstance().player;
        if (clientplayer != null) {
            ItemStack stack = clientplayer.getMainHandItem();
            Item heldItem = stack.getItem();
            CompoundTag tag = stack.getTag();

            if (heldItem instanceof HuntingRifleItem) {
                if (tag != null) {
                    PoseStack textmatrix = new PoseStack();
                    textmatrix.scale(2.0F, 2.0F, 2.0F);
                    Minecraft.getInstance().font.draw(textmatrix, "\u00A7e" + tag.getInt("AmmoCount") + "/" + 25, 0, 0, 0);
                }
            }
        }
    }
}
