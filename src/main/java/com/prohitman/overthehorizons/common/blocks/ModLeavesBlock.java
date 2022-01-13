package com.prohitman.overthehorizons.common.blocks;

import com.prohitman.overthehorizons.core.init.ModBlocks;
import com.prohitman.overthehorizons.core.init.ModParticleTypes;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class ModLeavesBlock extends LeavesBlock{
    private final LeafParticleType leafParticleType;
    private static final Logger LOGGER = LogManager.getLogger();

    public ModLeavesBlock(Properties properties, LeafParticleType leafParticleType) {
        super(properties);
        this.leafParticleType = leafParticleType;
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        Random rand = new Random();
        boolean flag = false;
        int y = pPos.getY();
        int counter = 0;
        int random = rand.nextInt(15);
        if(random == 1){
            do{
                //LOGGER.info("The method is called!");
                y--;
                counter++;
                BlockPos pos = new BlockPos(pPos.getX(), y, pPos.getZ());
                BlockPos underPos = new BlockPos(pPos.getX(), y - 1, pPos.getZ());
                Block block = pLevel.getBlockState(pos).getBlock();
                if(!(block instanceof AirBlock || !block.defaultBlockState().isSolidRender(pLevel, underPos)/* block.defaultBlockState().isFaceSturdy(pLevel, underPos, Direction.UP)*/)){
                    flag = true;
                    BlockPos leavesPos = new BlockPos(pPos.getX(), y + 1, pPos.getZ());
                    if(pLevel.getBlockState(leavesPos).getBlock() instanceof AirBlock){
                        pLevel.setBlock(leavesPos, ModBlocks.FALLEN_LEAVES.get().defaultBlockState(), 2);
                        //LOGGER.info("The block should be placed!");
                    }
                }else if(block.defaultBlockState().isSolidRender(pLevel, underPos)){
                    flag = true;
                }

            }while(!flag && counter <= 50);
        }

        super.tick(pState, pLevel, pPos, pRandom);
    }

    /**
     * Called periodically clientside on blocks near the player to show effects (like furnace fire particles).
     */
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, Random pRand) {
        if (this.leafParticleType != LeafParticleType.NONE) {
            if(pRand.nextInt(10) == 0){
                this.trySpawnLeavesParticles(pLevel, pPos, pState, this.leafParticleType);
            }
        }

    }

    private void trySpawnLeavesParticles(Level pLevel, BlockPos pPos, BlockState pState, LeafParticleType type) {
        if (pState.getFluidState().isEmpty() && !(pLevel.random.nextFloat() < 0.3F)) {
            VoxelShape voxelshape = pState.getCollisionShape(pLevel, pPos);
            double d0 = voxelshape.max(Direction.Axis.Y);
            if (d0 >= 1.0D && !pState.is(BlockTags.IMPERMEABLE)) {
                double d1 = voxelshape.min(Direction.Axis.Y);
                if (d1 > 0.0D) {
                    this.spawnParticle(pLevel, pPos, voxelshape, (double)pPos.getY() + d1 - 0.05D, type);
                } else {
                    BlockPos blockpos = pPos.below();
                    BlockState blockstate = pLevel.getBlockState(blockpos);
                    VoxelShape voxelshape1 = blockstate.getCollisionShape(pLevel, blockpos);
                    double d2 = voxelshape1.max(Direction.Axis.Y);
                    if ((d2 < 1.0D || !blockstate.isCollisionShapeFullBlock(pLevel, blockpos)) && blockstate.getFluidState().isEmpty()) {
                        this.spawnParticle(pLevel, pPos, voxelshape, (double)pPos.getY() - 0.05D, type);
                    }
                }
            }

        }
    }

    private void spawnParticle(Level pLevel, BlockPos pPos, VoxelShape pShape, double pY, LeafParticleType type) {
        this.spawnFluidParticle(pLevel, type, (double)pPos.getX() + pShape.min(Direction.Axis.X), (double)pPos.getX() + pShape.max(Direction.Axis.X), (double)pPos.getZ() + pShape.min(Direction.Axis.Z), (double)pPos.getZ() + pShape.max(Direction.Axis.Z), pY);
    }

    private void spawnFluidParticle(Level pParticleData, LeafParticleType type, double pX1, double pX2, double pZ1, double pZ2, double pY) {
        pParticleData.addParticle(this.getLeafParticleType(type), Mth.lerp(pParticleData.random.nextDouble(), pX1, pX2), pY, Mth.lerp(pParticleData.random.nextDouble(), pZ1, pZ2), 0.0D, 0.0D, 0.0D);
    }

    public SimpleParticleType getLeafParticleType(LeafParticleType type){
        return switch (type) {
            case RED -> ModParticleTypes.ORANGE_LEAF_PARTICLE.get();
            case YELLOW -> ModParticleTypes.YELLOW_LEAF_PARTICLE.get();
            case BROWN -> ModParticleTypes.BROWN_LEAF_PARTICLE.get();
            default -> null;
        };
    }

    public enum LeafParticleType{
        NONE,
        RED,
        YELLOW,
        BROWN
    }
}
