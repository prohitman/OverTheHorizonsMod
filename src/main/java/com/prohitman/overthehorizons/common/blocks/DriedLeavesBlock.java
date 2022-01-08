package com.prohitman.overthehorizons.common.blocks;

import com.prohitman.overthehorizons.core.init.ModParticleTypes;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;

public class DriedLeavesBlock extends LeavesBlock {
    public DriedLeavesBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void spawnDestroyParticles(Level pLevel, Player pPlayer, BlockPos pPos, BlockState pState) {
        super.spawnDestroyParticles(pLevel, pPlayer, pPos, pState);
        pLevel.addParticle(ModParticleTypes.OAK_LEAF_PARTICLE.get(), pPos.getX(), pPos.getY(), pPos.getZ(), 1, 0, 1);
    }
}
