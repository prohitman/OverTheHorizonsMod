package com.prohitman.overthehorizons.common.blocks;

import com.prohitman.overthehorizons.core.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IPlantable;

import javax.annotation.Nullable;

public class WaterReedsBlock extends DoublePlantBlock implements LiquidBlockContainer, SimpleWaterloggedBlock {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape UPPER_SHAPE = Block.box(2.0D, 0.0D, 2.0D, 15.0D, 15.0D, 15.0D);
    protected static final VoxelShape LOWER_SHAPE = Block.box(2.0D, 0.0D, 2.0D, 15.0D, 16.0D, 15.0D);

    public WaterReedsBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(HALF, DoubleBlockHalf.LOWER).setValue(WATERLOGGED, false));
    }

    /*@Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        if (this.mayPlaceOn(state, world, pos)) {
            return true;
        }
        return super.canSustainPlant(state, world, pos, facing, plantable);
    }*/

    @Override
    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        if (pLevel.getFluidState(pPos.above(2)).is(FluidTags.WATER)) {
            return false;
        }

        return this.canSurvive(pState, (LevelReader) pLevel, pPos);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world,
                                  BlockPos currentPos, BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }

        return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
    }

    @Override
    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (!canSurvive(state, world, pos)) {
            if (state.getValue(WATERLOGGED)) {
                world.setBlockAndUpdate(pos, Blocks.WATER.defaultBlockState());
            } else {
                world.destroyBlock(pos, false);
            }
        }
        if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
            BlockState stateUpper = world.getBlockState(pos.above());
            if (stateUpper.getBlock() instanceof WaterReedsBlock) {
                if (!canSurvive(stateUpper, world, pos.above())) {
                    world.destroyBlock(pos.above(), false);
                }
            }
        }
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState FluidState = context.getLevel().getFluidState(context.getClickedPos());
        BlockState state = super.getStateForPlacement(context);
        if (state != null) {
            return state.setValue(WATERLOGGED, FluidState.getType() == Fluids.WATER);
        } else {
            return null;
        }
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        if (state.getValue(HALF) == DoubleBlockHalf.UPPER && state.getValue(WATERLOGGED)) {
            return false;
        }
        if (state.getValue(HALF) != DoubleBlockHalf.UPPER) {

            BlockPos groundPos = pos.below();
            Block ground = world.getBlockState(groundPos).getBlock();

            if (world.getFluidState(pos).getType() == Fluids.WATER)
                return canGrow(ground);

            return false;
        } else {

            BlockState blockstate = world.getBlockState(pos.below());
            if (state.getBlock() != this)
                return false;
            if (world.isWaterAt(pos.above(1))) {
                return false;
            }
            return blockstate.getBlock() == this && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER;
        }
    }

    public static boolean canGrow(Block ground) {
        return ground == Blocks.DIRT || ground instanceof GrassBlock || ground instanceof SandBlock
                || ground == Blocks.GRAVEL || ground == Blocks.CLAY;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext p_154766_) {
        Vec3 vec3 = pState.getOffset(pLevel, pPos);
        return pState.getValue(HALF) == DoubleBlockHalf.LOWER ? LOWER_SHAPE.move(vec3.x, vec3.y, vec3.z) : UPPER_SHAPE.move(vec3.x, vec3.y, vec3.z);
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter p_154749_, BlockPos p_154750_, BlockState p_154751_) {
        return new ItemStack(ModItems.CATTAIL_SEEDS.get());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HALF, WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public boolean canPlaceLiquid(BlockGetter pLevel, BlockPos pPos, BlockState pState, Fluid pFluid) {
        return false;
    }

    @Override
    public boolean placeLiquid(LevelAccessor pLevel, BlockPos pPos, BlockState pState, FluidState pFluidState) {
        return false;
    }
}
