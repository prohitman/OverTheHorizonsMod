package com.prohitman.overthehorizons.common.item;

import com.prohitman.overthehorizons.common.blocks.ModLeavesBlock;
import com.prohitman.overthehorizons.core.init.ModBlocks;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.WaterLilyBlockItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

public class ModBlockItem extends BlockItem {
    public ModBlockItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Block block = this.getBlock();
        if(block == ModBlocks.DUCKWEED.get()){
            return InteractionResult.PASS;
        }
        return super.useOn(pContext);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        Block block = this.getBlock();
        if(block == ModBlocks.DUCKWEED.get()){
            BlockHitResult blockhitresult = getPlayerPOVHitResult(pLevel, pPlayer, ClipContext.Fluid.SOURCE_ONLY);
            BlockHitResult blockhitresult1 = blockhitresult.withPosition(blockhitresult.getBlockPos().above());
            InteractionResult interactionresult = super.useOn(new UseOnContext(pPlayer, pHand, blockhitresult1));
            return new InteractionResultHolder<>(interactionresult, pPlayer.getItemInHand(pHand));
        }
        return super.use(pLevel, pPlayer, pHand);
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        Block block = this.getBlock();
        return super.getBurnTime(itemStack, recipeType);
    }
}
