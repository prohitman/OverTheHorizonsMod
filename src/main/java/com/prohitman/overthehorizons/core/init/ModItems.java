package com.prohitman.overthehorizons.core.init;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.common.entity.ModBoat;
import com.prohitman.overthehorizons.common.item.HuntingRifleItem;
import com.prohitman.overthehorizons.common.item.LichenCluster;
import com.prohitman.overthehorizons.common.item.ModBoatItem;
import com.prohitman.overthehorizons.common.item.PineTeaItem;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, OverTheHorizonsMod.MOD_ID);

    public static final RegistryObject<Item> HUNTING_RIFLE = ITEMS.register("hunting_rifle", () -> new HuntingRifleItem(new Item.Properties().tab(ModItemGroups.OVER_THE_HORIZONS).stacksTo(1).rarity(Rarity.EPIC).durability(1000), 40));
    public static final RegistryObject<Item> COPPER_BULLET = ITEMS.register("copper_bullet", () -> new Item(new Item.Properties().tab(ModItemGroups.OVER_THE_HORIZONS)));
    public static final RegistryObject<Item> PINE_TEA = ITEMS.register("pine_tea", () -> new HoneyBottleItem(new Item.Properties().tab(ModItemGroups.OVER_THE_HORIZONS).craftRemainder(Items.GLASS_BOTTLE).food(Foods.HONEY_BOTTLE).stacksTo(16)));
    public static final RegistryObject<Item> LICHEN_CLUSTER = ITEMS.register("lichen_cluster", () -> new LichenCluster(new Item.Properties().tab(ModItemGroups.OVER_THE_HORIZONS)));
    public static final RegistryObject<Item> PINE_SIGN = ITEMS.register("pine_sign", () -> new SignItem(new Item.Properties().tab(ModItemGroups.OVER_THE_HORIZONS).stacksTo(16), ModBlocks.PINE_STANDING_SIGN.get(), ModBlocks.PINE_WALL_SIGN.get()));
    public static final RegistryObject<Item> PINE_BOAT = ITEMS.register("pine_boat", () -> new ModBoatItem(ModBoat.ModType.PINE, (new Item.Properties()).stacksTo(1).tab(ModItemGroups.OVER_THE_HORIZONS)));

}
