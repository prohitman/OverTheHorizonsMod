package com.prohitman.overthehorizons.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.prohitman.overthehorizons.core.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;

public class ItemStackRenderer extends BlockEntityWithoutLevelRenderer {
    public ItemStackRenderer() {
        super(null, null);
    }

    @Override
    public void renderByItem(ItemStack pStack, ItemDisplayContext pTransformType, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        //super.renderByItem(pStack, pTransformType, pPoseStack, pBuffer, pPackedLight, pPackedOverlay);
        if(pStack.getItem() == ModItems.HUNTING_RIFLE.get()){
            pPoseStack.translate(0.5F, 0.5f, 0.5f);
            if(pTransformType == ItemDisplayContext.THIRD_PERSON_LEFT_HAND || pTransformType == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND || pTransformType == ItemDisplayContext.FIRST_PERSON_RIGHT_HAND || pTransformType == ItemDisplayContext.FIRST_PERSON_LEFT_HAND){
                /*
                Minecraft.getInstance().getItemRenderer().renderStatic
                        (new ItemStack(ModItems.HUNTING_RIFLE_HAND.get(), 1,
                                pStack.getTag()),
                                pTransformType, pPackedLight, OverlayTexture.NO_OVERLAY, pPoseStack, pBuffer, 0);*/
            }else{
                /*Minecraft.getInstance().getItemRenderer().renderStatic(
                        new ItemStack(ModItems.HUNTING_RIFLE_INVENTORY.get(), 1,
                                pStack.getTag()), pTransformType,
                        pTransformType == ItemDisplayContext.GROUND ? pPackedLight : 240, OverlayTexture.NO_OVERLAY, pPoseStack, pBuffer, 0);*/
            }
        }
    }
}
