package com.prohitman.overthehorizons.common.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.Nullable;

public class BaseFuelItem extends Item {
    protected int fuelValue;

    public BaseFuelItem(Properties pProperties, int fuelValue) {
        super(pProperties);
        this.fuelValue = fuelValue;
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return fuelValue;
    }
}
