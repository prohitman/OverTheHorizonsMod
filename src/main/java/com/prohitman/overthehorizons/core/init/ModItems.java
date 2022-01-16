package com.prohitman.overthehorizons.core.init;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.common.entity.ModBoat;
import com.prohitman.overthehorizons.common.item.HuntingRifleItem;
import com.prohitman.overthehorizons.common.item.LichenCluster;
import com.prohitman.overthehorizons.common.item.ModBoatItem;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, OverTheHorizonsMod.MOD_ID);

    public static final RegistryObject<Item> HUNTING_RIFLE = ITEMS.register("hunting_rifle", () -> new HuntingRifleItem(new Item.Properties().tab(ModItemGroups.OVER_THE_HORIZONS).stacksTo(1).durability(1000), 40));
    public static final RegistryObject<Item> COPPER_BULLET = ITEMS.register("copper_bullet", () -> new Item(new Item.Properties().tab(ModItemGroups.OVER_THE_HORIZONS)));
    public static final RegistryObject<Item> LICHEN_CLUSTER = ITEMS.register("lichen_cluster", () -> new LichenCluster(new Item.Properties().tab(ModItemGroups.OVER_THE_HORIZONS)));
    public static final RegistryObject<Item> PINE_SIGN = ITEMS.register("pine_sign", () -> new SignItem(new Item.Properties().tab(ModItemGroups.OVER_THE_HORIZONS).stacksTo(16), ModBlocks.PINE_STANDING_SIGN.get(), ModBlocks.PINE_WALL_SIGN.get()));
    public static final RegistryObject<Item> PINE_BOAT = ITEMS.register("pine_boat", () -> new ModBoatItem(ModBoat.ModType.PINE, (new Item.Properties()).stacksTo(1).tab(ModItemGroups.OVER_THE_HORIZONS)));

}
