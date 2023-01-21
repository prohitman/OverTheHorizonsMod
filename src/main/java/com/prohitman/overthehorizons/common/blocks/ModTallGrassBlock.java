package com.prohitman.overthehorizons.common.blocks;

import com.prohitman.overthehorizons.core.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class ModTallGrassBlock extends TallGrassBlock {
    protected static final VoxelShape WILD_WHEAT_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 13.0D, 15.0D, 13.0D);
    protected static final VoxelShape TRAMPLED_GRASS_SHAPE = Block.box(2.0D, 0.0D, 2.0D, 15.0D, 10.0D, 15.0D);
    protected static final VoxelShape SPROUT_SHAPE = Block.box(2.0D, 0.0D, 2.0D, 15.0D, 10.0D, 15.0D);

    public ModTallGrassBlock(Properties p_57318_) {
        super(p_57318_);
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Vec3 vec3 = pState.getOffset(pLevel, pPos);

        if (pState.is(ModBlocks.WILD_WHEAT.get())) {//optimize this.
            return WILD_WHEAT_SHAPE.move(vec3.x, vec3.y, vec3.z);
        } else if (pState.is(ModBlocks.TRAMPLED_GRASS.get())) {
            return TRAMPLED_GRASS_SHAPE.move(vec3.x, vec3.y, vec3.z);
        } else if (pState.is(ModBlocks.SPROUTS.get())) {
            return SPROUT_SHAPE.move(vec3.x, vec3.y, vec3.z);
        }
        return SHAPE;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        return pState.is(ModBlocks.WILD_WHEAT.get());
    }

    @Override
    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        return pState.is(ModBlocks.WILD_WHEAT.get());
    }

    @Override
    public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        if ((pState.is(ModBlocks.WILD_WHEAT.get()))) {
            DoublePlantBlock doubleplantblock = (DoublePlantBlock) ModBlocks.TALL_WILD_WHEAT.get();
            if (doubleplantblock.defaultBlockState().canSurvive(pLevel, pPos) && pLevel.isEmptyBlock(pPos.above())) {
                DoublePlantBlock.placeAt(pLevel, doubleplantblock.defaultBlockState(), pPos, 2);
            }
        }
    }
}
