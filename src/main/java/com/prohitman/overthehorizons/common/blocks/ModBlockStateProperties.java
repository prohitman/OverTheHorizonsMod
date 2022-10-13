package com.prohitman.overthehorizons.common.blocks;

import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class ModBlockStateProperties {
   public static final IntegerProperty EROSION_TIME = IntegerProperty.create("erosion_time", 60, 120);

}
