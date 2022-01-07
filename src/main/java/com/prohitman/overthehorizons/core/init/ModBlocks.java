package com.prohitman.overthehorizons.core.init;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks
{
    //Blocks
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, OverTheHorizonsMod.MOD_ID);

    public static final RegistryObject<Block> ADOBE = BLOCKS.register("adobe", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_YELLOW).requiresCorrectToolForDrops().strength(2.0F, 6.0F).sound(SoundType.STONE)));

}
