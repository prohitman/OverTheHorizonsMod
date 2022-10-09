package com.prohitman.overthehorizons.client.models;

import com.ibm.icu.text.Normalizer2;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.prohitman.overthehorizons.OverTheHorizonsMod;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class CatFishModel<T extends Entity> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(OverTheHorizonsMod.MOD_ID, "catfish"), "main");
    private final ModelPart Body;
    private final ModelPart Head;
    private final ModelPart Mouth;

    public CatFishModel(ModelPart root) {
        this.Body = root.getChild("Body");
        this.Head = Body.getChild("Head");
        this.Mouth = Head.getChild("Mouth");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -9.0F, -6.0F, 8.0F, 9.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(18, 21).addBox(-3.0F, 0.0F, 3.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition Tail = Body.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(0, 21).addBox(-2.0F, -3.0F, 0.0F, 4.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 6.0F));

        PartDefinition TailFin = Tail.addOrReplaceChild("TailFin", CubeListBuilder.create().texOffs(28, 29).addBox(0.0F, -6.0F, -2.0F, 0.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 9.0F));

        PartDefinition Flipper1 = Body.addOrReplaceChild("Flipper1", CubeListBuilder.create().texOffs(0, 4).addBox(0.0F, 0.0F, -1.0F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -1.0F, -4.0F, 0.0F, 0.0F, -0.2618F));

        PartDefinition Flipper2 = Body.addOrReplaceChild("Flipper2", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, -1.0F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -1.0F, -4.0F, 0.0F, 0.0F, 0.2618F));

        PartDefinition Head = Body.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(28, 21).addBox(-4.0F, -3.0F, -6.0F, 8.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -7.0F));

        PartDefinition Mustache1 = Head.addOrReplaceChild("Mustache1", CubeListBuilder.create().texOffs(0, 19).addBox(0.0F, 0.0F, -1.0F, 0.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 0.0F, -4.0F, 0.0F, 0.0F, -0.2618F));

        PartDefinition Mustache2 = Head.addOrReplaceChild("Mustache2", CubeListBuilder.create().texOffs(6, 4).addBox(0.0F, 0.0F, -1.0F, 0.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.0F, -4.0F, 0.0F, 0.0F, 0.2618F));

        PartDefinition Mouth = Head.addOrReplaceChild("Mouth", CubeListBuilder.create().texOffs(28, 0).addBox(-5.0F, -2.0F, -1.0F, 10.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -6.0F));

        PartDefinition DorsalFin = Body.addOrReplaceChild("DorsalFin", CubeListBuilder.create().texOffs(6, 0).addBox(0.0F, -3.0F, -1.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, -2.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        Mouth.visible = false;
    }
}
