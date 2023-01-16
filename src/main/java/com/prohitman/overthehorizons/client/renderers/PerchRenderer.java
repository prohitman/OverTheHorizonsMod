package com.prohitman.overthehorizons.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.client.models.PerchModel;
import com.prohitman.overthehorizons.common.entity.Perch;
import net.minecraft.client.model.CodModel;
import net.minecraft.client.renderer.entity.CodRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Cod;
import net.minecraft.world.entity.animal.TropicalFish;
import org.jetbrains.annotations.NotNull;

public class PerchRenderer extends MobRenderer<Perch, PerchModel<Perch>> {
    private static final ResourceLocation PERCH_LOCATION = new ResourceLocation(OverTheHorizonsMod.MOD_ID, "textures/entity/perch.png");

    public PerchRenderer(EntityRendererProvider.Context context) {
        super(context, new PerchModel<>(context.bakeLayer(PerchModel.LAYER_LOCATION)), 0.2f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(Perch pEntity) {
        return PERCH_LOCATION;
    }

    protected void setupRotations(Perch pEntityLiving, PoseStack pMatrixStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pMatrixStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        float f = 4.3F * Mth.sin(0.6F * pAgeInTicks);
        pMatrixStack.mulPose(Axis.YP.rotationDegrees(f));
        if (!pEntityLiving.isInWater()) {
            pMatrixStack.translate((double)0.2F, (double)0.1F, 0.0D);
            pMatrixStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
        }

    }
}
