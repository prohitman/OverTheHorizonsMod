package com.prohitman.overthehorizons.core.util;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.prohitman.overthehorizons.OverTheHorizonsMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

public class RenderUtils {
    public static void renderSpyglassOverlay(float pfloat) {
        int screenHeight = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        int screenWidth = Minecraft.getInstance().getWindow().getGuiScaledWidth();

        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, new ResourceLocation(OverTheHorizonsMod.MOD_ID + ":textures/item/rifle_scope.png"));
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tesselator.getBuilder();
        float f = (float)Math.min(Minecraft.getInstance().getWindow().getGuiScaledWidth(),screenHeight);
        float f1 = Math.min((float)screenWidth / f, (float)screenHeight / f) * pfloat;
        float f2 = f * f1;
        float f3 = f * f1;
        float f4 = ((float)screenWidth - f2) / 2.0F;
        float f5 = ((float)screenHeight - f3) / 2.0F;
        float f6 = f4 + f2;
        float f7 = f5 + f3;
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.vertex((double)f4, (double)f7, -90.0D).uv(0.0F, 1.0F).endVertex();
        bufferbuilder.vertex((double)f6, (double)f7, -90.0D).uv(1.0F, 1.0F).endVertex();
        bufferbuilder.vertex((double)f6, (double)f5, -90.0D).uv(1.0F, 0.0F).endVertex();
        bufferbuilder.vertex((double)f4, (double)f5, -90.0D).uv(0.0F, 0.0F).endVertex();
        tesselator.end();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        RenderSystem.disableTexture();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        bufferbuilder.vertex(0.0D, (double)screenHeight, -90.0D).color(0, 0, 0, 255).endVertex();
        bufferbuilder.vertex((double)screenWidth, (double)screenHeight, -90.0D).color(0, 0, 0, 255).endVertex();
        bufferbuilder.vertex((double)screenWidth, (double)f7, -90.0D).color(0, 0, 0, 255).endVertex();
        bufferbuilder.vertex(0.0D, (double)f7, -90.0D).color(0, 0, 0, 255).endVertex();
        bufferbuilder.vertex(0.0D, (double)f5, -90.0D).color(0, 0, 0, 255).endVertex();
        bufferbuilder.vertex((double)screenWidth, (double)f5, -90.0D).color(0, 0, 0, 255).endVertex();
        bufferbuilder.vertex((double)screenWidth, 0.0D, -90.0D).color(0, 0, 0, 255).endVertex();
        bufferbuilder.vertex(0.0D, 0.0D, -90.0D).color(0, 0, 0, 255).endVertex();
        bufferbuilder.vertex(0.0D, (double)f7, -90.0D).color(0, 0, 0, 255).endVertex();
        bufferbuilder.vertex((double)f4, (double)f7, -90.0D).color(0, 0, 0, 255).endVertex();
        bufferbuilder.vertex((double)f4, (double)f5, -90.0D).color(0, 0, 0, 255).endVertex();
        bufferbuilder.vertex(0.0D, (double)f5, -90.0D).color(0, 0, 0, 255).endVertex();
        bufferbuilder.vertex((double)f6, (double)f7, -90.0D).color(0, 0, 0, 255).endVertex();
        bufferbuilder.vertex((double)screenWidth, (double)f7, -90.0D).color(0, 0, 0, 255).endVertex();
        bufferbuilder.vertex((double)screenWidth, (double)f5, -90.0D).color(0, 0, 0, 255).endVertex();
        bufferbuilder.vertex((double)f6, (double)f5, -90.0D).color(0, 0, 0, 255).endVertex();
        tesselator.end();
        RenderSystem.enableTexture();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public static void setupOverlayRenderState(boolean blend, boolean depthTest, @Nullable ResourceLocation texture)
    {
        if (blend)
        {
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
        }
        else
        {
            RenderSystem.disableBlend();
        }

        if (depthTest)
        {
            RenderSystem.enableDepthTest();
        }
        else
        {
            RenderSystem.disableDepthTest();
        }

        if (texture != null)
        {
            RenderSystem.enableTexture();
            RenderSystem.setShaderTexture(0, texture);
        }
        else
        {
            RenderSystem.disableTexture();
        }

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.5F);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
    }

    public static void renderTextureOverlay(ResourceLocation pTextureLocation, float pAlpha) {
        int screenHeight = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        int screenWidth = Minecraft.getInstance().getWindow().getGuiScaledWidth();

        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, pAlpha);
        RenderSystem.setShaderTexture(0, pTextureLocation);
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tesselator.getBuilder();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.vertex(0.0D, (double)screenHeight, -90.0D).uv(0.0F, 1.0F).endVertex();
        bufferbuilder.vertex((double)screenWidth, (double)screenHeight, -90.0D).uv(1.0F, 1.0F).endVertex();
        bufferbuilder.vertex((double)screenWidth, 0.0D, -90.0D).uv(1.0F, 0.0F).endVertex();
        bufferbuilder.vertex(0.0D, 0.0D, -90.0D).uv(0.0F, 0.0F).endVertex();
        tesselator.end();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
