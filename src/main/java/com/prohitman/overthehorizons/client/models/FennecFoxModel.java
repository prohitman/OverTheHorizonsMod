package com.prohitman.overthehorizons.client.models;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.common.entity.FennecFox;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class FennecFoxModel<T extends FennecFox> extends AgeableListModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(OverTheHorizonsMod.MOD_ID, "fennec_fox"), "main");
    private final ModelPart body;
    public final ModelPart head;
    private final ModelPart rightHindLeg;
    private final ModelPart leftHindLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftFrontLeg;
    private final ModelPart tail;
    private static final int LEG_SIZE = 6;
    private static final float HEAD_HEIGHT = 16.5F;
    private static final float LEG_POS = 17.5F;
    private float legMotionPos;

    public FennecFoxModel(ModelPart pRoot) {
        super(true, 8.0F, 3.35F);

        this.body = pRoot.getChild("Body");
        this.head = body.getChild("Head");
        this.rightHindLeg = body.getChild("right_hind_leg");
        this.leftHindLeg = body.getChild("left_hind_leg");
        this.rightFrontLeg = body.getChild("right_front_leg");
        this.leftFrontLeg = body.getChild("left_front_leg");
        this.tail = body.getChild("tail");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -3.0F, -4.5F, 6.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.0F, 0.0F));

        PartDefinition Tail = Body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 15).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 4.5F, -0.1309F, 0.0F, 0.0F));

        PartDefinition Head = Body.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(17, 15).addBox(-3.0F, -2.0F, -4.0F, 6.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(8, 28).addBox(-1.0F, 1.0F, -6.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -4.5F));

        PartDefinition EarR = Head.addOrReplaceChild("EarR", CubeListBuilder.create().texOffs(0, 15).addBox(-1.5F, -4.0F, -0.5F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, -2.0F, -1.5F));

        PartDefinition EarL = Head.addOrReplaceChild("EarL", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -4.0F, -0.5F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, -2.0F, -1.5F));

        PartDefinition LegFrontL = Body.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(0, 28).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 3.0F, -3.5F));

        PartDefinition LegHindL = Body.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(24, 26).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 3.0F, 3.5F));

        PartDefinition LegHindR = Body.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(21, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 3.0F, 3.5F));

        PartDefinition LegFrontR = Body.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(27, 3).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 3.0F, -3.5F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public void prepareMobModel(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick) {
        /*this.body.xRot = ((float)Math.PI / 2F);
        this.tail.xRot = -0.05235988F;
        this.rightHindLeg.xRot = Mth.cos(pLimbSwing * 0.6662F) * 1.4F * pLimbSwingAmount;
        this.leftHindLeg.xRot = Mth.cos(pLimbSwing * 0.6662F + (float)Math.PI) * 1.4F * pLimbSwingAmount;
        this.rightFrontLeg.xRot = Mth.cos(pLimbSwing * 0.6662F + (float)Math.PI) * 1.4F * pLimbSwingAmount;
        this.leftFrontLeg.xRot = Mth.cos(pLimbSwing * 0.6662F) * 1.4F * pLimbSwingAmount;
        this.head.setPos(-1.0F, 16.5F, -3.0F);
        this.head.yRot = 0.0F;
        this.head.zRot = pEntity.getHeadRollAngle(pPartialTick);
        this.rightHindLeg.visible = true;
        this.leftHindLeg.visible = true;
        this.rightFrontLeg.visible = true;
        this.leftFrontLeg.visible = true;
        this.body.setPos(0.0F, 16.0F, -6.0F);
        this.body.zRot = 0.0F;
        this.rightHindLeg.setPos(-5.0F, 17.5F, 7.0F);
        this.leftHindLeg.setPos(-1.0F, 17.5F, 7.0F);
        */if (pEntity.isCrouching()) {
            this.body.xRot = 1.6755161F;
            float f = pEntity.getCrouchAmount(pPartialTick);
            this.body.setPos(0.0F, 16.0F + pEntity.getCrouchAmount(pPartialTick), -6.0F);
            this.head.setPos(-1.0F, 16.5F + f, -3.0F);
            this.head.yRot = 0.0F;
        } else if (pEntity.isSleeping()) {
            this.body.zRot = (-(float)Math.PI / 2F);
            this.body.setPos(0.0F, 21.0F, -6.0F);
            this.tail.xRot = -2.6179938F;
            if (this.young) {
                this.tail.xRot = -2.1816616F;
                this.body.setPos(0.0F, 21.0F, -2.0F);
            }

            this.head.setPos(1.0F, 19.49F, -3.0F);
            this.head.xRot = 0.0F;
            this.head.yRot = -2.0943952F;
            this.head.zRot = 0.0F;
            this.rightHindLeg.visible = false;
            this.leftHindLeg.visible = false;
            this.rightFrontLeg.visible = false;
            this.leftFrontLeg.visible = false;
        } else if (pEntity.isSitting()) {
            this.body.xRot = ((float)Math.PI / 6F);
            this.body.setPos(0.0F, 9.0F, -3.0F);
            this.tail.xRot = ((float)Math.PI / 4F);
            this.tail.setPos(-4.0F, 15.0F, -2.0F);
            this.head.setPos(-1.0F, 10.0F, -0.25F);
            this.head.xRot = 0.0F;
            this.head.yRot = 0.0F;
            if (this.young) {
                this.head.setPos(-1.0F, 13.0F, -3.75F);
            }

            this.rightHindLeg.xRot = -1.3089969F;
            this.rightHindLeg.setPos(-5.0F, 21.5F, 6.75F);
            this.leftHindLeg.xRot = -1.3089969F;
            this.leftHindLeg.setPos(-1.0F, 21.5F, 6.75F);
            this.rightFrontLeg.xRot = -0.2617994F;
            this.leftFrontLeg.xRot = -0.2617994F;
        }

    }

    @Override
    public void setupAnim(FennecFox pEntity, float limbSwing, float limbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        if (!pEntity.isSleeping() && !pEntity.isFaceplanted() && !pEntity.isCrouching()) {
            this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
            this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        }

        if (pEntity.isSleeping()) {
            this.head.xRot = 0.0F;
            this.head.yRot = -2.0943952F;
            this.head.zRot = Mth.cos(pAgeInTicks * 0.027F) / 22.0F;
        }

        if (pEntity.isCrouching()) {
            float f = Mth.cos(pAgeInTicks) * 0.01F;
            this.body.yRot = f;
            this.rightHindLeg.zRot = f;
            this.leftHindLeg.zRot = f;
            this.rightFrontLeg.zRot = f / 2.0F;
            this.leftFrontLeg.zRot = f / 2.0F;
        }

        if (pEntity.isFaceplanted()) {
            float f1 = 0.1F;
            this.legMotionPos += 0.67F;
            this.rightHindLeg.xRot = Mth.cos(this.legMotionPos * 0.4662F) * 0.1F;
            this.leftHindLeg.xRot = Mth.cos(this.legMotionPos * 0.4662F + (float)Math.PI) * 0.1F;
            this.rightFrontLeg.xRot = Mth.cos(this.legMotionPos * 0.4662F + (float)Math.PI) * 0.1F;
            this.leftFrontLeg.xRot = Mth.cos(this.legMotionPos * 0.4662F) * 0.1F;
        }
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of(this.head);
    }

    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.body, this.rightHindLeg, this.leftHindLeg, this.rightFrontLeg, this.leftFrontLeg);
    }
}
