package com.prohitman.overthehorizons.client.keybinds;

import com.mojang.blaze3d.platform.InputConstants;
import com.prohitman.overthehorizons.OverTheHorizonsMod;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;

public final class ModKeyBindings {
    public static KeyMapping reloadRifleKeyMapping;
    public static KeyMapping zoomRifleKeyMapping;


    private ModKeyBindings() {
    }

    public static void init() {
        reloadRifleKeyMapping = registerKey("reload_rifle", KeyMapping.CATEGORY_GAMEPLAY, InputConstants.KEY_R);
        zoomRifleKeyMapping = registerKey("zoom_rifle", KeyMapping.CATEGORY_GAMEPLAY, InputConstants.KEY_B);
    }

    private static KeyMapping registerKey(String name, String category, int keycode) {
        final var key = new KeyMapping("key." + OverTheHorizonsMod.MOD_ID + "." + name, keycode, category);
        ClientRegistry.registerKeyBinding(key);
        return key;
    }
}
