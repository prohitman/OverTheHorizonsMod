package com.prohitman.overthehorizons.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.client.models.CatFishModel;
import com.prohitman.overthehorizons.client.models.FennecFoxModel;
import com.prohitman.overthehorizons.client.renderers.layers.FennecFoxHeldItemLayer;
import com.prohitman.overthehorizons.common.entity.CatFish;
import com.prohitman.overthehorizons.common.entity.FennecFox;
import net.minecraft.client.model.FoxModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.FoxHeldItemLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Fox;

public class FennecFoxRenderer extends MobRenderer<FennecFox, FennecFoxModel<FennecFox>> {
    private static final ResourceLocation FENNEC_FOX_LOCATION = new ResourceLocation(OverTheHorizonsMod.MOD_ID, "textures/entity/fennecfox/fennec_fox.png");

    public FennecFoxRenderer(EntityRendererProvider.Context context) {
        super(context, new FennecFoxModel<>(context.bakeLayer(FennecFoxModel.LAYER_LOCATION)), 0.3F);
        this.addLayer(new FennecFoxHeldItemLayer(this, context.getItemInHandRenderer()));
    }

    protected void setupRotations(FennecFox pEntityLiving, PoseStack pMatrixStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pMatrixStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        if (pEntityLiving.isPouncing() || pEntityLiving.isFaceplanted()) {
            float f = -Mth.lerp(pPartialTicks, pEntityLiving.xRotO, pEntityLiving.getXRot());
            pMatrixStack.mulPose(Axis.XP.rotationDegrees(f));
        }

    }

    @Override
    public ResourceLocation getTextureLocation(FennecFox pEntity) {
        return FENNEC_FOX_LOCATION;
    }
}
