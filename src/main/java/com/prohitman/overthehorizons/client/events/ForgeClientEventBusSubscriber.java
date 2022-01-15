package com.prohitman.overthehorizons.client.events;

import com.mojang.blaze3d.vertex.PoseStack;
import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.client.keybinds.ModKeyBindings;
import com.prohitman.overthehorizons.common.item.HuntingRifleItem;
import com.prohitman.overthehorizons.common.network.MessageReloadRifle;
import com.prohitman.overthehorizons.common.network.OTHPacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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
                    Minecraft.getInstance().font.draw(textmatrix, "\u00A7e" + tag.getInt("AmmoCount") + "/" + 20, 0, 0, 0);
                }
            }
        }
    }
}
