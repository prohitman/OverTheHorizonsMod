package com.prohitman.overthehorizons.core.init;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.common.item.LichenCluster;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, OverTheHorizonsMod.MOD_ID);

    public static final RegistryObject<Item> COPPER_BULLET = ITEMS.register("copper_bullet", () -> new Item(new Item.Properties().tab(ModItemGroups.OVER_THE_HORIZONS)));
    public static final RegistryObject<Item> LICHEN_CLUSTER = ITEMS.register("lichen_cluster", () -> new LichenCluster(new Item.Properties().tab(ModItemGroups.OVER_THE_HORIZONS)));
    public static final RegistryObject<Item> PINE_SIGN = ITEMS.register("pine_sign", () -> new SignItem(new Item.Properties().tab(ModItemGroups.OVER_THE_HORIZONS).stacksTo(16), ModBlocks.PINE_STANDING_SIGN.get(), ModBlocks.PINE_WALL_SIGN.get()));

}
