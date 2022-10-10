package com.prohitman.overthehorizons.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.client.models.CatFishModel;
import com.prohitman.overthehorizons.client.models.PerchModel;
import com.prohitman.overthehorizons.common.entity.CatFish;
import com.prohitman.overthehorizons.common.entity.Perch;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class CatFishRenderer extends MobRenderer<CatFish, CatFishModel<CatFish>>  {
    private static final ResourceLocation GREY_CATFISH_LOCATION = new ResourceLocation(OverTheHorizonsMod.MOD_ID, "textures/entity/catfish/grey_catfish.png");
    private static final ResourceLocation BROWN_CATFISH_LOCATION = new ResourceLocation(OverTheHorizonsMod.MOD_ID, "textures/entity/catfish/brown_catfish.png");

    public CatFishRenderer(EntityRendererProvider.Context context) {
        super(context, new CatFishModel<>(context.bakeLayer(CatFishModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(CatFish pEntity) {
        return pEntity.getVariant() == 1 ? GREY_CATFISH_LOCATION : BROWN_CATFISH_LOCATION;
    }
}
