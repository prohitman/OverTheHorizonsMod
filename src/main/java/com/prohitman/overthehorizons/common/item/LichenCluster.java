package com.prohitman.overthehorizons.common.item;

import com.prohitman.overthehorizons.core.init.ModBlocks;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;

import java.util.Random;

public class LichenCluster extends BaseFuelItem {
    Random random = new Random();

    public LichenCluster(Properties pProperties, int fuelValue) {
        super(pProperties, fuelValue);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().getBlockState(pContext.getClickedPos()).is(Blocks.STONE)) {
            return super.useOn(pContext);
        } else {
            Player player = pContext.getPlayer();
            ItemStack itemstack = pContext.getItemInHand();
            assert player != null;
            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }
            pContext.getLevel().playSound((Player) null, pContext.getClickedPos(), SoundEvents.MOSS_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
            pContext.getLevel().setBlock(pContext.getClickedPos(), random.nextBoolean() ? ModBlocks.RED_LICHENSTONE.get().defaultBlockState() : ModBlocks.GREEN_LICHENSTONE.get().defaultBlockState(), 2);

            return InteractionResult.sidedSuccess(pContext.getLevel().isClientSide);
        }
    }
}
