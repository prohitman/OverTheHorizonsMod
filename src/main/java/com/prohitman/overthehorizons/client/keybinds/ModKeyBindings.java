package com.prohitman.overthehorizons.client.keybinds;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import com.mojang.blaze3d.platform.InputConstants;
import com.prohitman.overthehorizons.OverTheHorizonsMod;
import net.minecraft.client.KeyMapping;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OverTheHorizonsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ModKeyBindings {
    public static Lazy<KeyMapping> reloadRifleKeyMapping = Lazy.of(() -> registerKey("reload_rifle", KeyMapping.CATEGORY_GAMEPLAY, InputConstants.KEY_R));
    public static Lazy<KeyMapping> zoomRifleKeyMapping = Lazy.of(() -> registerKey("zoom_rifle", KeyMapping.CATEGORY_GAMEPLAY, InputConstants.KEY_B));

    private ModKeyBindings() {}

    @SubscribeEvent
    public static void registerKeyBindings(RegisterKeyMappingsEvent event){
        event.register(reloadRifleKeyMapping.get());
        event.register(zoomRifleKeyMapping.get());
    }

    private static KeyMapping registerKey(String name, String category, int keycode) {
        final var key = new KeyMapping("key." + OverTheHorizonsMod.MOD_ID + "." + name, keycode, category);

        return key;
    }
}
