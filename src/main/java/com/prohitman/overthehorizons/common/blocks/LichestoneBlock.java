package com.prohitman.overthehorizons.common.blocks;

import com.prohitman.overthehorizons.core.init.ModBlocks;
import com.prohitman.overthehorizons.core.init.ModFeatures;
import com.prohitman.overthehorizons.core.init.ModItems;
import com.prohitman.overthehorizons.core.util.ModFeatureUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.data.worldgen.features.CaveFeatures;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.lighting.LayerLightEngine;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.util.ForgeSoundType;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class LichestoneBlock extends Block implements BonemealableBlock{
    public LichestoneBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(!pPlayer.level.isClientSide){
            ItemStack stack = pPlayer.getItemInHand(pHand);
            if(stack.is(Tags.Items.SHEARS)){
                pLevel.playSound((Player) null, pPos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1.0F, 1.0F);
                pPlayer.level.setBlock(pPos, Blocks.STONE.defaultBlockState(), 2);
                ItemStack cluster = new ItemStack(ModItems.LICHEN_CLUSTER.get());
                ItemEntity itemEntity = new ItemEntity(pPlayer.level, pPos.getX() + 0.5, pPos.getY() + 1, pPos.getZ() + 0.5, cluster);
                pLevel.addFreshEntity(itemEntity);
                pLevel.gameEvent(pPlayer, GameEvent.SHEAR, pPos);
                pPlayer.awardStat(Stats.ITEM_USED.get(Items.SHEARS));
                stack.hurtAndBreak(1, pPlayer, e -> e.broadcastBreakEvent(pHand));
                return  InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public SoundType getSoundType(BlockState state, LevelReader world, BlockPos pos, @Nullable Entity entity) {
        return new SoundType(1.0f, 1.0f, SoundEvents.CALCITE_BREAK, SoundEvents.MOSS_STEP, SoundEvents.CALCITE_PLACE, SoundEvents.STONE_HIT, SoundEvents.STONE_FALL);
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }

    private static boolean canBeGrass(BlockState pState, LevelReader pLevelReader, BlockPos pPos) {
        BlockPos blockpos = pPos.above();
        BlockState blockstate = pLevelReader.getBlockState(blockpos);
        if (blockstate.getFluidState().getAmount() == 8) {
            return false;
        } else {
            int i = LayerLightEngine.getLightBlockInto(pLevelReader, pState, pPos, blockstate, blockpos, Direction.UP, blockstate.getLightBlock(pLevelReader, blockpos));
            return i < pLevelReader.getMaxLightLevel();
        }
    }

    private static boolean canPropagate(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockPos blockpos = pPos.above();
        return canBeGrass(pState, pLevel, pPos) && !pLevel.getFluidState(blockpos).is(FluidTags.WATER);
    }

    /**
     * Performs a random tick on a block.
     */
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        if (!canBeGrass(pState, pLevel, pPos)) {
            if (!pLevel.isAreaLoaded(pPos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            pLevel.setBlockAndUpdate(pPos, Blocks.STONE.defaultBlockState());
        } else {
            if (!pLevel.isAreaLoaded(pPos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            if (pLevel.getMaxLocalRawBrightness(pPos.above()) >= 9) {
                BlockState blockstate = this.defaultBlockState();

                for(int i = 0; i < 4; ++i) {
                    BlockPos blockpos = pPos.offset(pRandom.nextInt(3) - 1, pRandom.nextInt(5) - 3, pRandom.nextInt(3) - 1);
                    if (pLevel.getBlockState(blockpos).is(Blocks.STONE) && canPropagate(blockstate, pLevel, blockpos)) {
                        pLevel.setBlockAndUpdate(blockpos, blockstate);
                    }
                }
            }

        }
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        return pLevel.getBlockState(pPos.above()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level pLevel, Random pRandom, BlockPos pPos, BlockState pState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel pLevel, Random pRandom, BlockPos pPos, BlockState pState) {
        if(pState.is(ModBlocks.GREEN_LICHENSTONE.get())){
            ModFeatureUtils.GREEN_LICHENSTONE_PATCH_BONEMEAL.value().place(pLevel, pLevel.getChunkSource().getGenerator(), pRandom, pPos.above());
        } else if(pState.is(ModBlocks.RED_LICHENSTONE.get())){
            ModFeatureUtils.RED_LICHENSTONE_PATCH_BONEMEAL.value().place(pLevel, pLevel.getChunkSource().getGenerator(), pRandom, pPos.above());
        }
    }
}
