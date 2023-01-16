package com.prohitman.overthehorizons.client.renderers.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.prohitman.overthehorizons.client.models.FennecFoxModel;
import com.prohitman.overthehorizons.common.entity.FennecFox;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.FoxModel;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.FoxHeldItemLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.item.ItemStack;

public class FennecFoxHeldItemLayer extends RenderLayer<FennecFox, FennecFoxModel<FennecFox>> {
    private final ItemInHandRenderer itemInHandRenderer;

    public FennecFoxHeldItemLayer(RenderLayerParent<FennecFox, FennecFoxModel<FennecFox>> p_116994_, ItemInHandRenderer renderer) {
        super(p_116994_);
        this.itemInHandRenderer = renderer;
    }

    @Override
    public void render(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, FennecFox pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        boolean flag = pLivingEntity.isSleeping();
        boolean flag1 = pLivingEntity.isBaby();
        pMatrixStack.pushPose();
        if (flag1) {
            float f = 0.75F;
            pMatrixStack.scale(0.75F, 0.75F, 0.75F);
            pMatrixStack.translate(0.0D, 0.5D, (double)0.209375F);
        }

        pMatrixStack.translate((double)((this.getParentModel()).head.x / 16.0F), (double)((this.getParentModel()).head.y / 16.0F), (double)((this.getParentModel()).head.z / 16.0F));
        float f1 = pLivingEntity.getHeadRollAngle(pPartialTicks);
        pMatrixStack.mulPose(Axis.ZP.rotation(f1));
        pMatrixStack.mulPose(Axis.YP.rotationDegrees(pNetHeadYaw));
        pMatrixStack.mulPose(Axis.XP.rotationDegrees(pHeadPitch));
        if (pLivingEntity.isBaby()) {
            if (flag) {
                pMatrixStack.translate((double)0.4F, (double)0.26F, (double)0.15F);
            } else {
                pMatrixStack.translate((double)0.06F, (double)0.26F, -0.5D);
            }
        } else if (flag) {
            pMatrixStack.translate((double)0.46F, (double)0.26F, (double)0.22F);
        } else {
            pMatrixStack.translate((double)0.06F, (double)0.27F, -0.5D);
        }

        pMatrixStack.mulPose(Axis.XP.rotationDegrees(90.0F));
        if (flag) {
            pMatrixStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
        }

        ItemStack itemstack = pLivingEntity.getItemBySlot(EquipmentSlot.MAINHAND);
        this.itemInHandRenderer.renderItem(pLivingEntity, itemstack, ItemTransforms.TransformType.GROUND, false, pMatrixStack, pBuffer, pPackedLight);
        pMatrixStack.popPose();
    }
}
