package com.prohitman.overthehorizons.common.blocks;

import com.prohitman.overthehorizons.core.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class ModTallGrassBlock extends TallGrassBlock {
    public ModTallGrassBlock(Properties p_57318_) {
        super(p_57318_);
    }

    @Override
    public void performBonemeal(ServerLevel pLevel, Random pRandom, BlockPos pPos, BlockState pState) {
        if((pState.is(ModBlocks.WILD_WHEAT.get()))){
            DoublePlantBlock doubleplantblock = ModBlocks.TALL_WILD_WHEAT.get();
            if (doubleplantblock.defaultBlockState().canSurvive(pLevel, pPos) && pLevel.isEmptyBlock(pPos.above())) {
                DoublePlantBlock.placeAt(pLevel, doubleplantblock.defaultBlockState(), pPos, 2);
            }
        }
    }
}
