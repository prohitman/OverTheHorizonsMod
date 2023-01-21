package com.prohitman.overthehorizons.client.renderers.overlay;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class AmmoCountOverlay implements IGuiOverlay {
    @Override
    public void render(ForgeGui gui, PoseStack poseStack, float partialTick, int screenWidth, int screenHeight) {
        PoseStack textmatrix = new PoseStack();
        textmatrix.scale(2.0F, 2.0F, 2.0F);
        //Minecraft.getInstance().font.draw(textmatrix, "\u00A7e" + tag.getInt("AmmoCount") + "/" + 25, 0, 0, 0);
    }
}
