package com.prohitman.overthehorizons.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LandReedsBlock extends DoublePlantBlock {
    protected static final VoxelShape UPPER_SHAPE = Block.box(2.0D, 0.0D, 2.0D, 15.0D, 13.0D, 15.0D);
    protected static final VoxelShape LOWER_SHAPE = Block.box(2.0D, 0.0D, 2.0D, 15.0D, 16.0D, 15.0D);

    public LandReedsBlock(Properties p_52861_) {
        super(p_52861_);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return pState.getValue(HALF) == DoubleBlockHalf.LOWER ? LOWER_SHAPE : UPPER_SHAPE;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        if (state.getValue(HALF) != DoubleBlockHalf.UPPER) {
            BlockPos groundPos = pos.below();
            Block ground = world.getBlockState(groundPos).getBlock();

            if (world.getFluidState(pos).getType() == Fluids.WATER)
                return canGrow(ground);
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                if (world.getFluidState(groundPos.relative(direction)).getType() == Fluids.WATER) {
                    return canGrow(ground);
                }
            }

            return false;
        } else {
            if (state.getBlock() != this)
                return false;
            return super.canSurvive(state, world, pos);
        }
    }

    public boolean canGrow(Block ground) {
        return ground == Blocks.DIRT || ground instanceof GrassBlock || ground instanceof SandBlock
                || ground == Blocks.GRAVEL || ground == Blocks.CLAY;
    }
}
