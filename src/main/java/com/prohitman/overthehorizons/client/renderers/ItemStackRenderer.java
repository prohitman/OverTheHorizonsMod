package com.prohitman.overthehorizons.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.prohitman.overthehorizons.core.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.world.item.ItemStack;

public class ItemStackRenderer extends BlockEntityWithoutLevelRenderer {
    public ItemStackRenderer() {
        super(null, null);
    }

    @Override
    public void renderByItem(ItemStack pStack, ItemTransforms.TransformType pTransformType, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        //super.renderByItem(pStack, pTransformType, pPoseStack, pBuffer, pPackedLight, pPackedOverlay);
        if(pStack.getItem() == ModItems.HUNTING_RIFLE.get()){
            pPoseStack.translate(0.5F, 0.5f, 0.5f);
            if(pTransformType == ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND || pTransformType == ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND || pTransformType == ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND || pTransformType == ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND){
                Minecraft.getInstance().getItemRenderer().renderStatic(new ItemStack(ModItems.HUNTING_RIFLE_HAND.get(), 1, pStack.getTag()), pTransformType, pPackedLight, pPackedOverlay, pPoseStack, pBuffer, 0);
            }else{
                Minecraft.getInstance().getItemRenderer().renderStatic(new ItemStack(ModItems.HUNTING_RIFLE_INVENTORY.get(), 1, pStack.getTag()), pTransformType, pTransformType == ItemTransforms.TransformType.GROUND ? pPackedLight : 240, pPackedOverlay, pPoseStack, pBuffer, 0);
            }
        }
    }
}
