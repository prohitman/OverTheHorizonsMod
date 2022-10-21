package com.prohitman.overthehorizons.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.client.models.CatFishModel;
import com.prohitman.overthehorizons.client.models.PerchModel;
import com.prohitman.overthehorizons.common.entity.CatFish;
import com.prohitman.overthehorizons.common.entity.Perch;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class CatFishRenderer extends MobRenderer<CatFish, CatFishModel<CatFish>>  {
    private static final ResourceLocation GREY_CATFISH_LOCATION = new ResourceLocation(OverTheHorizonsMod.MOD_ID, "textures/entity/catfish/grey_catfish.png");
    private static final ResourceLocation BROWN_CATFISH_LOCATION = new ResourceLocation(OverTheHorizonsMod.MOD_ID, "textures/entity/catfish/brown_catfish.png");
    private static final ResourceLocation BLUE_CATFISH_LOCATION = new ResourceLocation(OverTheHorizonsMod.MOD_ID, "textures/entity/catfish/blue_catfish.png");
    private static final ResourceLocation SPOTTED_CATFISH_LOCATION = new ResourceLocation(OverTheHorizonsMod.MOD_ID, "textures/entity/catfish/spotted_catfish.png");
    private static final ResourceLocation WADE_CATFISH_LOCATION = new ResourceLocation(OverTheHorizonsMod.MOD_ID, "textures/entity/catfish/wade_catfish.png");

    public CatFishRenderer(EntityRendererProvider.Context context) {
        super(context, new CatFishModel<>(context.bakeLayer(CatFishModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(CatFish pEntity) {
        String s = ChatFormatting.stripFormatting(pEntity.getName().getString());
        if ("Wade".equals(s)) {
            return WADE_CATFISH_LOCATION;
        } else {
            return switch (pEntity.getVariant()) {
                default -> GREY_CATFISH_LOCATION;
                case 1 -> BROWN_CATFISH_LOCATION;
                case 2 -> BLUE_CATFISH_LOCATION;
                case 3 -> SPOTTED_CATFISH_LOCATION;
                case 4 -> WADE_CATFISH_LOCATION;
            };
        }
    }
}
