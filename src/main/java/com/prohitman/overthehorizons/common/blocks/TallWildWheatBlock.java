package com.prohitman.overthehorizons.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class TallWildWheatBlock extends DoublePlantBlock {
    protected static final VoxelShape UPPER_SHAPE = Block.box(3.0D, 0.0D, 3.0D, 15.0D, 13.0D, 15.0D);
    protected static final VoxelShape LOWER_SHAPE = Block.box(2.0D, 0.0D, 2.0D, 15.0D, 16.0D, 15.0D);

    public TallWildWheatBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Vec3 vec3 = pState.getOffset(pLevel, pPos);
        return pState.getValue(HALF) == DoubleBlockHalf.LOWER ? LOWER_SHAPE.move(vec3.x, vec3.y, vec3.z) : UPPER_SHAPE.move(vec3.x, vec3.y, vec3.z);
    }
}
