package com.prohitman.overthehorizons.core.init;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.common.blocks.ModLeavesBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks
{
    //Blocks
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, OverTheHorizonsMod.MOD_ID);

    public static final RegistryObject<Block> ADOBE = BLOCKS.register("adobe", () -> normalStoneBlock(MaterialColor.COLOR_YELLOW, SoundType.STONE));
    public static final RegistryObject<Block> FRAMED_ADOBE = BLOCKS.register("framed_adobe", () -> normalStoneBlock(MaterialColor.COLOR_YELLOW, SoundType.STONE));
    public static final RegistryObject<Block> SMOOTH_ADOBE = BLOCKS.register("smooth_adobe", () -> normalStoneBlock(MaterialColor.COLOR_YELLOW, SoundType.STONE));
    public static final RegistryObject<Block> ADOBE_BRICKS = BLOCKS.register("adobe_bricks", () -> normalStoneBlock(MaterialColor.COLOR_YELLOW, SoundType.STONE));

    //Tree
    public static final RegistryObject<Block> DRIED_BIRCH_LEAVES = BLOCKS.register("dried_birch_leaves", () -> leaves(SoundType.AZALEA_LEAVES, ModLeavesBlock.LeafParticleType.YELLOW));
    public static final RegistryObject<Block> DRIED_OAK_LEAVES = BLOCKS.register("dried_oak_leaves", () -> leaves(SoundType.AZALEA_LEAVES, ModLeavesBlock.LeafParticleType.BROWN));
    public static final RegistryObject<Block> DRIED_DARK_OAK_LEAVES = BLOCKS.register("dried_dark_oak_leaves", () -> leaves(SoundType.AZALEA_LEAVES, ModLeavesBlock.LeafParticleType.RED));

    public static final RegistryObject<Block> FALLEN_LEAVES = BLOCKS.register("fallen_leaves", () -> new CarpetBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_GREEN).noOcclusion().strength(0.1F).sound(SoundType.MOSS_CARPET)));






    private static Block normalStoneBlock(MaterialColor color, SoundType soundType){
        return new Block(BlockBehaviour.Properties.of(Material.STONE, color).requiresCorrectToolForDrops().strength(2.0F, 6.0F).sound(soundType));
    }

    private static LeavesBlock leaves(SoundType soundType, ModLeavesBlock.LeafParticleType particleType) {
        return new ModLeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(soundType).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never), particleType);
    }

    private static boolean never(BlockState state, BlockGetter getter, BlockPos pos) {
        return false;
    }

    private static Boolean ocelotOrParrot(BlockState state, BlockGetter blockGetter, BlockPos pos, EntityType<?> entityType) {
        return entityType == EntityType.OCELOT || entityType == EntityType.PARROT;
    }
}
