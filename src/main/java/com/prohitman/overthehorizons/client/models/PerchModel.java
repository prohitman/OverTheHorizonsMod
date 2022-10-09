package com.prohitman.overthehorizons.client.models;

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

public class PerchModel<T extends Entity> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(OverTheHorizonsMod.MOD_ID, "perch"), "main");
    private final ModelPart body;

    public PerchModel(ModelPart root) {
        this.body = root.getChild("body");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -4.0F, -2.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(8, 10).addBox(-1.0F, -3.0F, -4.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition Tail = body.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(0, 4).addBox(0.0F, -2.5F, 0.0F, 0.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 2.0F));

        PartDefinition dorsalfin = body.addOrReplaceChild("dorsalfin", CubeListBuilder.create().texOffs(8, 4).addBox(0.0F, -2.0F, -2.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));

        PartDefinition flipper1 = body.addOrReplaceChild("flipper1", CubeListBuilder.create().texOffs(8, 0).addBox(0.0F, 0.0F, -1.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 0.0F, -1.0F));

        PartDefinition flipper2 = body.addOrReplaceChild("flipper2", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, -1.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 0.0F, -1.0F));

        return LayerDefinition.create(meshdefinition, 16, 16);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
