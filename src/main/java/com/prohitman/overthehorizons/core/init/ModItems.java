package com.prohitman.overthehorizons.core.init;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.common.entity.ModBoat;
import com.prohitman.overthehorizons.common.entity.Perch;
import com.prohitman.overthehorizons.common.item.HuntingRifleItem;
import com.prohitman.overthehorizons.common.item.LichenCluster;
import com.prohitman.overthehorizons.common.item.ModBoatItem;
import com.prohitman.overthehorizons.common.item.PineTeaItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, OverTheHorizonsMod.MOD_ID);

    //FoodProperties
    public static final FoodProperties PINE_TEA_FOOD = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.1F).effect( () -> new MobEffectInstance(MobEffects.REGENERATION, 75, 0), 1.0F).build();
    public static final FoodProperties RAW_FISH = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.1F).build();
    public static final FoodProperties COOKED_FISH = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.6F).build();
    public static final FoodProperties COOKED_CATFISH_FOOD = (new FoodProperties.Builder()).nutrition(10).saturationMod(0.8F).build();

    //Food
    public static final RegistryObject<Item> RAW_PERCH = ITEMS.register("raw_perch", () -> new Item(new Item.Properties().tab(ModItemGroups.OVER_THE_HORIZONS).stacksTo(64).food(RAW_FISH)));
    public static final RegistryObject<Item> RAW_CATFISH = ITEMS.register("raw_catfish", () -> new Item(new Item.Properties().tab(ModItemGroups.OVER_THE_HORIZONS).stacksTo(64).food((new FoodProperties.Builder()).nutrition(6).saturationMod(0.6F).build())));
    public static final RegistryObject<Item> COOKED_PERCH = ITEMS.register("cooked_perch", () -> new Item(new Item.Properties().tab(ModItemGroups.OVER_THE_HORIZONS).stacksTo(64).food(COOKED_FISH)));
    public static final RegistryObject<Item> COOKED_CATFISH = ITEMS.register("cooked_catfish", () -> new Item(new Item.Properties().tab(ModItemGroups.OVER_THE_HORIZONS).stacksTo(64).food(COOKED_CATFISH_FOOD)));
    public static final RegistryObject<Item> PINE_TEA = ITEMS.register("pine_tea", () -> new PineTeaItem(new Item.Properties().tab(ModItemGroups.OVER_THE_HORIZONS).craftRemainder(Items.GLASS_BOTTLE).food(PINE_TEA_FOOD).stacksTo(16)));

    //Spawn Eggs
    public static final RegistryObject<Item> PERCH_SPAWN_EGG = ITEMS.register("perch_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.PERCH, /*823D38*/0x59412F, 0x512B23, new Item.Properties().tab(ModItemGroups.OVER_THE_HORIZONS).stacksTo(64)));
    public static final RegistryObject<Item> CATFISH_SPAWN_EGG = ITEMS.register("catfish_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.CATFISH, /*823D38*/0x595651, 0x776558, new Item.Properties().tab(ModItemGroups.OVER_THE_HORIZONS).stacksTo(64)));

    //Items
    public static final RegistryObject<Item> HUNTING_RIFLE_INVENTORY = ITEMS.register("hunting_rifle_inventory", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HUNTING_RIFLE_HAND = ITEMS.register("hunting_rifle_hand", () ->new Item(new Item.Properties()));
    public static final RegistryObject<Item> HUNTING_RIFLE = ITEMS.register("hunting_rifle", () -> new HuntingRifleItem(new Item.Properties().tab(ModItemGroups.OVER_THE_HORIZONS).stacksTo(1).rarity(Rarity.EPIC).durability(1000), 40));

    public static final RegistryObject<Item> PERCH_BUCKET = ITEMS.register("perch_bucket", () -> new MobBucketItem(ModEntityTypes.PERCH, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1).tab(ModItemGroups.OVER_THE_HORIZONS)));
    public static final RegistryObject<Item> COPPER_BULLET = ITEMS.register("copper_bullet", () -> new Item(new Item.Properties().tab(ModItemGroups.OVER_THE_HORIZONS)));
    public static final RegistryObject<Item> LICHEN_CLUSTER = ITEMS.register("lichen_cluster", () -> new LichenCluster(new Item.Properties().tab(ModItemGroups.OVER_THE_HORIZONS), 800));
    public static final RegistryObject<Item> PINE_SIGN = ITEMS.register("pine_sign", () -> new SignItem(new Item.Properties().tab(ModItemGroups.OVER_THE_HORIZONS).stacksTo(16), ModBlocks.PINE_STANDING_SIGN.get(), ModBlocks.PINE_WALL_SIGN.get()));
    public static final RegistryObject<Item> PINE_BOAT = ITEMS.register("pine_boat", () -> new ModBoatItem(ModBoat.ModType.PINE, (new Item.Properties()).stacksTo(1).tab(ModItemGroups.OVER_THE_HORIZONS)));
    public static final RegistryObject<Item> FISH_BONES = ITEMS.register("fish_bones", () -> new Item(new Item.Properties().tab(ModItemGroups.OVER_THE_HORIZONS)));



}
