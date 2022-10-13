package com.prohitman.overthehorizons.common.blocks;

import com.prohitman.overthehorizons.core.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

import java.util.Random;

public class SlateBlock extends Block {
    //public static final IntegerProperty EROSION_TIMER = ModBlockStateProperties.EROSION_TIME;
    //private final Random rand = new Random();
    private int erosionTicks = 0;
    private final Block erodedSlate = ModBlocks.ERODED_SLATE.get();

    public SlateBlock(Properties properties) {
        super(properties);
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
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random rand) {
        if(touchesLiquid(pLevel, pPos)){
            erosionTicks++;
        }
        else if(erosionTicks == 60 + rand.nextInt(60)){
            BlockState state = erodedSlate.defaultBlockState();
            pLevel.setBlock(pPos, state, 1);
        }
        super.tick(pState, pLevel, pPos, rand);
    }
}
