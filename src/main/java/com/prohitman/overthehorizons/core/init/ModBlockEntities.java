package com.prohitman.overthehorizons.core.init;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.common.blocks.entity.ModSignBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, OverTheHorizonsMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<ModSignBlockEntity>> MOD_SIGN = BLOCK_ENTITIES.register("sign", () -> BlockEntityType.Builder.of(ModSignBlockEntity::new, ModBlocks.PINE_STANDING_SIGN.get(), ModBlocks.PINE_WALL_SIGN.get()).build(null));
}
