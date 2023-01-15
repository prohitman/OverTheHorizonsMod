package com.prohitman.overthehorizons.core.init;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodTypes extends WoodType {
    public static final WoodType PINE = WoodType.create(new ResourceLocation(OverTheHorizonsMod.MOD_ID, "pine").toString());

    protected ModWoodTypes(String pName) {
        super(pName);
    }
}
