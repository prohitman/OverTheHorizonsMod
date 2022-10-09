package com.prohitman.overthehorizons.common.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.client.event.RenderNameplateEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class CatFish extends WaterAnimal {
    private final Random rand = new Random();
    private static final EntityDataAccessor<Integer> DATA_VARIANT_ID = SynchedEntityData.defineId(CatFish.class, EntityDataSerializers.INT);

    public CatFish(EntityType<? extends WaterAnimal> p_30341_, Level p_30342_) {
        super(p_30341_, p_30342_);
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        this.setVariant(rand.nextInt(2));
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 15.0D);
    }

    public int getVariant() {
        return Mth.clamp(this.entityData.get(DATA_VARIANT_ID), 0, 1);
    }

    public void setVariant(int pVariant) {
        this.entityData.set(DATA_VARIANT_ID, pVariant);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_VARIANT_ID, 0);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("Variant", this.getVariant());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setVariant(pCompound.getInt("Variant"));
    }

    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
        return pSize.height * 0.65F;
    }

}
