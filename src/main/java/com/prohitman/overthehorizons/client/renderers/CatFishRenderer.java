package com.prohitman.overthehorizons.client.renderers;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.client.models.CatFishModel;
import com.prohitman.overthehorizons.client.models.PerchModel;
import com.prohitman.overthehorizons.common.entity.CatFish;
import com.prohitman.overthehorizons.common.entity.Perch;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CatFishRenderer extends MobRenderer<CatFish, CatFishModel<CatFish>>  {
    private static final ResourceLocation CATFISH_LOCATION = new ResourceLocation(OverTheHorizonsMod.MOD_ID, "textures/entity/catfish.png");

    public CatFishRenderer(EntityRendererProvider.Context context) {
        super(context, new CatFishModel<>(context.bakeLayer(CatFishModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(CatFish pEntity) {
        return CATFISH_LOCATION;
    }
}
