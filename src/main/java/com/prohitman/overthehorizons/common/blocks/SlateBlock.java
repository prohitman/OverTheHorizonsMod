package com.prohitman.overthehorizons.common.blocks;

import com.prohitman.overthehorizons.core.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class SlateBlock extends Block {
    public static final IntegerProperty EROSION_TIMER = ModBlockStateProperties.EROSION_TIME;
    private final BlockState eroded_slate_blockstate;

    public SlateBlock(Properties properties) {
        super(properties);
        Block erodedSlate = ModBlocks.ERODED_SLATE.get();
        this.eroded_slate_blockstate = erodedSlate.defaultBlockState();
        this.registerDefaultState(this.stateDefinition.any().setValue(EROSION_TIMER, 0));
    }

    private static boolean canErode(BlockState pState) {
        return pState.getFluidState().is(FluidTags.WATER);
    }

    private static boolean touchesLiquid(BlockGetter pLevel, BlockPos pPos) {
        boolean flag = false;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = pPos.mutable();

        for(Direction direction : Direction.values()) {
            BlockState blockstate = pLevel.getBlockState(blockpos$mutableblockpos);
            if (direction != Direction.DOWN || canErode(blockstate)) {
                blockpos$mutableblockpos.setWithOffset(pPos, direction);
                blockstate = pLevel.getBlockState(blockpos$mutableblockpos);
                if (canErode(blockstate) && !blockstate.isFaceSturdy(pLevel, pPos, direction.getOpposite())) {
                    flag = true;
                    break;
                }
            }
        }

        return flag;
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        super.randomTick(pState, pLevel, pPos, pRandom);
        int i = pState.getValue(EROSION_TIMER);
        if (touchesLiquid(pLevel, pPos)) {
            if (i < 6) {
                pLevel.setBlock(pPos, pState.setValue(EROSION_TIMER, i + 1), 2);
            } else {
                pLevel.setBlockAndUpdate(pPos, this.eroded_slate_blockstate);
            }
        } else {
            pLevel.setBlock(pPos, pState.setValue(EROSION_TIMER, 0), 2);
        }
    }

    @Override
    public @NotNull BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos) {
        return pState.getValue(EROSION_TIMER) > 5 ? this.eroded_slate_blockstate :  super.updateShape(pState, pDirection, pNeighborState, pLevel, pCurrentPos, pNeighborPos);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(EROSION_TIMER);
    }
}
