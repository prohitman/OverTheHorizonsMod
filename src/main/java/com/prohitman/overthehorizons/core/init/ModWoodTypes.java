package com.prohitman.overthehorizons.core.init;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodTypes{
    public static final WoodType PINE = WoodType.register(new WoodType(OverTheHorizonsMod.MOD_ID + ":pine", ModBlockSetTypes.PINE));
}
