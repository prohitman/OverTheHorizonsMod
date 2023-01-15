package com.prohitman.overthehorizons.client.events;

import com.mojang.blaze3d.vertex.PoseStack;
import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.client.keybinds.ModKeyBindings;
import com.prohitman.overthehorizons.common.item.HuntingRifleItem;
import com.prohitman.overthehorizons.common.network.MessageReloadRifle;
import com.prohitman.overthehorizons.common.network.OTHPacketHandler;
import com.prohitman.overthehorizons.common.util.IExtendedReach;
import com.prohitman.overthehorizons.core.util.ExtendedReachUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ComputeFovModifierEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = OverTheHorizonsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public final class ForgeClientEventBusSubscriber {

    private ForgeClientEventBusSubscriber() {
    }

    @SubscribeEvent
    public static void clientTickEvents(TickEvent.ClientTickEvent event) {
        if (ModKeyBindings.reloadRifleKeyMapping.consumeClick()) {
            OTHPacketHandler.HANDLER.sendToServer(new MessageReloadRifle());
            System.out.println("Keybinding pressed");
        }
        if (Minecraft.getInstance().player != null) {
            //System.out.println("Called.");
            if (!ModKeyBindings.zoomRifleKeyMapping.isDown()) {
                OverTheHorizonsMod.scopeScale = 0.5f;
            }
        }
    }

    @SubscribeEvent
    public static void renderRifle(RenderHandEvent event) {
        if (ModKeyBindings.zoomRifleKeyMapping.isDown()) {
            assert Minecraft.getInstance().player != null;
            if (Minecraft.getInstance().player.getMainHandItem().getItem() instanceof HuntingRifleItem) {
                event.setCanceled(true);
            }
        }
    }

    /*@SubscribeEvent
    public static void mouseHandler(ScreenEvent.MouseDragEvent event){
        if(Minecraft.getInstance().player != null){
            Minecraft.getInstance().getProfiler().push("mouse");
            double d4 = Minecraft.getInstance().options.sensitivity * (double)0.6F + (double)0.2F;
            double d5 = d4 * d4 * d4;
            double d6 = d5 * 8.0D;
            double d2;
            double d3;
            double accumulatedDX = Minecraft.getInstance().mouseHandler.getXVelocity();
            double accumulatedDY = Minecraft.getInstance().mouseHandler.getYVelocity();

            if(Minecraft.getInstance().options.getCameraType().isFirstPerson()
                    && Minecraft.getInstance().player.getMainHandItem().getItem() instanceof HuntingRifleItem
                    && ModKeyBindings.zoomRifleKeyMapping.isDown()){
                Minecraft.getInstance().mouseHandler.turnPlayer();
                d2 = accumulatedDX * d5;
                d3 = accumulatedDY * d5;

                accumulatedDX = 0;
                accumulatedDY = 0;

                int i = 1;
                if (Minecraft.getInstance().options.invertYMouse) {
                    i = -1;
                }
                Minecraft.getInstance().getTutorial().onMouse(d2, d3);
                if (Minecraft.getInstance().player != null) {
                    Minecraft.getInstance().player.turn(d2, d3 * (double)i);
                }
            }
            Minecraft.getInstance().getProfiler().pop();
        }
    }*/

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public static void onShootEvent(InputEvent event) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            if (player.getMainHandItem().getItem() instanceof IExtendedReach) {
                Item item = player.getMainHandItem().getItem();
                if (((IExtendedReach) player.getMainHandItem().getItem()).getReach() > 5.0f) {
                    Options keys = Minecraft.getInstance().options;
                    if (keys.keyUse.consumeClick() && !player.getCooldowns().isOnCooldown(item)) {
                        ExtendedReachUtils.extendAttackReach(player);
                    }
                }
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public static void playZoomSound(InputEvent.KeyInputEvent event) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            Item item = player.getMainHandItem().getItem();
            if (item instanceof HuntingRifleItem) {
                if (ModKeyBindings.zoomRifleKeyMapping.consumeClick() && event.getAction() == GLFW.GLFW_PRESS) {
                    Minecraft.getInstance().player.playSound(SoundEvents.SPYGLASS_USE, 1.0F, 1.0F);
                }
            }
        }
    }

    @SubscribeEvent
    public static void modifyFOV(ComputeFovModifierEvent event) {
        if (Minecraft.getInstance().options.getCameraType().isFirstPerson() && event.getPlayer().getMainHandItem().getItem() instanceof HuntingRifleItem && ModKeyBindings.zoomRifleKeyMapping.isDown()) {
            event.setNewFovModifier(0.1f);
        }
    }

    @SubscribeEvent
    public static void renderAmmoCount(RenderEve/*RenderGameOverlayEvent*/.Text event) {
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
