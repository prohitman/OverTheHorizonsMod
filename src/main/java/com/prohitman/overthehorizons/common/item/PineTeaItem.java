package com.prohitman.overthehorizons.common.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.HoneyBottleItem;
import net.minecraft.world.item.Item;

public class PineTeaItem extends HoneyBottleItem {
    public PineTeaItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public SoundEvent getDrinkingSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    @Override
    public SoundEvent getEatingSound() {
        return SoundEvents.GENERIC_DRINK;
    }
}
