package com.prohitman.overthehorizons.common.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.Level;

public class CatFish extends WaterAnimal {
    public CatFish(EntityType<? extends WaterAnimal> p_30341_, Level p_30342_) {
        super(p_30341_, p_30342_);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 15.0D);
    }
}
