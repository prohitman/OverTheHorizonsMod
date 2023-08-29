package com.prohitman.overthehorizons.client.renderers;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Axis;
import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.client.models.ModBoatModel;
import com.prohitman.overthehorizons.client.models.ModChestBoatModel;
import com.prohitman.overthehorizons.common.entity.ModBoat;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.WaterPatchModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Quaternionf;

import java.util.Map;
import java.util.stream.Stream;

@OnlyIn(Dist.CLIENT)
public class ModBoatRenderer extends EntityRenderer<ModBoat> {
    private final Map<ModBoat.ModType, Pair<ResourceLocation, ListModel<ModBoat>>> boatResources;

    public ModBoatRenderer(EntityRendererProvider.Context pContext, boolean pChestBoat) {
        super(pContext);
        this.shadowRadius = 0.8F;
        this.boatResources = Stream.of(ModBoat.ModType.values()).collect(ImmutableMap.toImmutableMap((p_173938_) -> {
            return p_173938_;
        }, (modType) -> {
            return Pair.of(new ResourceLocation(OverTheHorizonsMod.MOD_ID, getTextureLocation(modType, pChestBoat)), this.createBoatModel(pContext, modType, pChestBoat));
        }));
    }

    private ListModel<ModBoat> createBoatModel(EntityRendererProvider.Context pContext, ModBoat.ModType pType, boolean pChestBoat) {
        ModelLayerLocation modellayerlocation = pChestBoat ? createChestBoatModelName(pType) : createBoatModelName(pType);
        ModelPart modelpart = pContext.bakeLayer(modellayerlocation);

        return (ListModel<ModBoat>)(pChestBoat ? new ModChestBoatModel(modelpart) : new ModBoatModel(modelpart));
    }

    public static ModelLayerLocation createChestBoatModelName(ModBoat.ModType pType) {
        return createLocation("chest_boat/" + pType.getName(), "main");
    }

    public static ModelLayerLocation createBoatModelName(ModBoat.ModType pType) {
        return createLocation("boat/" + pType.getName(), "main");
    }

    private static ModelLayerLocation createLocation(String pPath, String pModel) {
        return new ModelLayerLocation(new ResourceLocation(OverTheHorizonsMod.MOD_ID, pPath), pModel);
    }

    private static String getTextureLocation(ModBoat.ModType pType, boolean pChestBoat) {
        return pChestBoat ? "textures/entity/chest_boat/" + pType.getName() + ".png" : "textures/entity/boat/" + pType.getName() + ".png";
    }

    public void render(ModBoat pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        pMatrixStack.pushPose();
        pMatrixStack.translate(0.0F, 0.375F, 0.0F);
        pMatrixStack.mulPose(Axis.YP.rotationDegrees(180.0F - pEntityYaw));
        float f = (float)pEntity.getHurtTime() - pPartialTicks;
        float f1 = pEntity.getDamage() - pPartialTicks;
        if (f1 < 0.0F) {
            f1 = 0.0F;
        }

        if (f > 0.0F) {
            pMatrixStack.mulPose(Axis.XP.rotationDegrees(Mth.sin(f) * f * f1 / 10.0F * (float)pEntity.getHurtDir()));
        }

        float f2 = pEntity.getBubbleAngle(pPartialTicks);
        if (!Mth.equal(f2, 0.0F)) {
            pMatrixStack.mulPose((new Quaternionf()).setAngleAxis(pEntity.getBubbleAngle(pPartialTicks) * ((float)Math.PI / 180F), 1.0F, 0.0F, 1.0F));
        }

        Pair<ResourceLocation, ListModel<ModBoat>> pair = getModelWithLocation(pEntity);
        ResourceLocation resourcelocation = pair.getFirst();
        ListModel<ModBoat> listmodel = pair.getSecond();
        pMatrixStack.scale(-1.0F, -1.0F, 1.0F);
        pMatrixStack.mulPose(Axis.YP.rotationDegrees(90.0F));
        listmodel.setupAnim(pEntity, pPartialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer vertexconsumer = pBuffer.getBuffer(listmodel.renderType(resourcelocation));
        listmodel.renderToBuffer(pMatrixStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        if (!pEntity.isUnderWater()) {
            VertexConsumer vertexconsumer1 = pBuffer.getBuffer(RenderType.waterMask());
            if (listmodel instanceof WaterPatchModel) {
                WaterPatchModel waterpatchmodel = (WaterPatchModel)listmodel;
                waterpatchmodel.waterPatch().render(pMatrixStack, vertexconsumer1, pPackedLight, OverlayTexture.NO_OVERLAY);
            }
        }

        pMatrixStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    /**
     * Returns the location of an entity's texture.
     */
    @Deprecated // forge: override getModelWithLocation to change the texture / model
    public ResourceLocation getTextureLocation(ModBoat pEntity) {
        return getModelWithLocation(pEntity).getFirst();
    }

    public Pair<ResourceLocation, ListModel<ModBoat>> getModelWithLocation(ModBoat boat) { return this.boatResources.get(boat.getModBoatType()); }
}
