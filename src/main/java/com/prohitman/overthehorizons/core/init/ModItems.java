package com.prohitman.overthehorizons.core.init;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, OverTheHorizonsMod.MOD_ID);

    public static final RegistryObject<Item> LICHEN_CLUSTER = ITEMS.register("lichen_cluster", () -> new Item(new Item.Properties().tab(ModItemGroups.OVER_THE_HORIZONS)));
}
