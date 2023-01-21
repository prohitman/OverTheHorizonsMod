package com.prohitman.overthehorizons.common.item;

import com.prohitman.overthehorizons.common.blocks.LandReedsBlock;
import com.prohitman.overthehorizons.core.init.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class CattailSeeds extends Item {
    public CattailSeeds(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        BlockState landreeds = ModBlocks.LAND_REEDS.get().defaultBlockState();
        BlockState waterreeds = ModBlocks.WATER_REEDS.get().defaultBlockState();

        List<BlockState> reeds = new LinkedList<>();

        reeds.add(landreeds);
        reeds.add(waterreeds);

        for (BlockState reed : reeds) {
            if (reed.canSurvive(pContext.getLevel(), pContext.getClickedPos().above())
                    && pContext.getClickedFace() == Direction.UP) {
                DoublePlantBlock.placeAt(pContext.getLevel(), reed, pContext.getClickedPos().above(), 2);
                Objects.requireNonNull(pContext.getPlayer()).awardStat(Stats.ITEM_USED.get(this));
                if (!pContext.getPlayer().getAbilities().instabuild) {
                    pContext.getItemInHand().shrink(1);
                }
                pContext.getLevel().playSound(pContext.getPlayer(), pContext.getClickedPos().above(), reed.getSoundType().getPlaceSound(), SoundSource.MASTER, 1.0F, 0.8F);
                return InteractionResult.SUCCESS;
            }

        }

        return super.useOn(pContext);
    }
}
