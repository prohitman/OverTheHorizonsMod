package com.prohitman.overthehorizons.core.datagen;

import com.prohitman.overthehorizons.core.init.ModBlocks;
import com.prohitman.overthehorizons.core.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public class ModRecipes extends RecipeProvider {
    public ModRecipes(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder
                .shapeless(ModItems.PINE_TEA.get())
                .requires(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER).getItem())
                .requires(Items.SUGAR)
                .requires(ModBlocks.PINE_CONE.get())
                .unlockedBy("pine_cone", has(ModBlocks.PINE_CONE.get())).save(consumer);

        ShapedRecipeBuilder.shaped(ModBlocks.ADOBE_BRICKS.get()).define('#', ModBlocks.ADOBE.get().asItem()).pattern("##").pattern("##").unlockedBy("adobe", has(ModBlocks.ADOBE.get().asItem())).save(consumer);

        woodenBoat(consumer, ModItems.PINE_BOAT.get(), ModBlocks.PINE_PLANKS.get().asItem());
        woodFromLogs(consumer, ModBlocks.PINE_WOOD.get().asItem(), ModBlocks.PINE_LOG.get().asItem());
        planksFromLog(consumer, ModBlocks.PINE_PLANKS.get().asItem(), ModItemTags.PINE_LOGS);
        stairBuilder(ModBlocks.PINE_STAIRS.get().asItem(), Ingredient.of(ModBlocks.PINE_PLANKS.get().asItem())).unlockedBy("pine_planks", has(ModBlocks.PINE_PLANKS.get())).save(consumer);
        fenceBuilder(ModBlocks.PINE_FENCE.get().asItem(), Ingredient.of(ModBlocks.PINE_PLANKS.get().asItem())).unlockedBy("pine_planks", has(ModBlocks.PINE_PLANKS.get())).save(consumer);
        fenceGateBuilder(ModBlocks.PINE_FENCE_GATE.get().asItem(), Ingredient.of(ModBlocks.PINE_PLANKS.get().asItem())).unlockedBy("pine_planks", has(ModBlocks.PINE_PLANKS.get())).save(consumer);
        doorBuilder(ModBlocks.PINE_DOOR.get().asItem(), Ingredient.of(ModBlocks.PINE_PLANKS.get().asItem())).unlockedBy("pine_planks", has(ModBlocks.PINE_PLANKS.get())).save(consumer);
        buttonBuilder(ModBlocks.PINE_BUTTON.get().asItem(), Ingredient.of(ModBlocks.PINE_PLANKS.get().asItem())).unlockedBy("pine_planks", has(ModBlocks.PINE_PLANKS.get())).save(consumer);
        pressurePlateBuilder(ModBlocks.PINE_PRESSURE_PLATE.get().asItem(), Ingredient.of(ModBlocks.PINE_PLANKS.get().asItem())).unlockedBy("pine_planks", has(ModBlocks.PINE_PLANKS.get())).save(consumer);
        trapdoorBuilder(ModBlocks.PINE_TRAPDOOR.get().asItem(), Ingredient.of(ModBlocks.PINE_PLANKS.get().asItem())).unlockedBy("pine_planks", has(ModBlocks.PINE_PLANKS.get())).save(consumer);
        signBuilder(ModBlocks.PINE_STANDING_SIGN.get().asItem(), Ingredient.of(ModBlocks.PINE_PLANKS.get().asItem())).unlockedBy("pine_planks", has(ModBlocks.PINE_PLANKS.get())).save(consumer);
        slab(consumer, ModBlocks.PINE_SLAB.get().asItem(), ModBlocks.PINE_PLANKS.get().asItem());
        slab(consumer, ModBlocks.ADOBE_SLAB.get().asItem(), ModBlocks.ADOBE.get().asItem());
        slab(consumer, ModBlocks.SMOOTH_ADOBE_SLAB.get().asItem(), ModBlocks.SMOOTH_ADOBE.get().asItem());
        slab(consumer, ModBlocks.ADOBE_BRICKS_SLAB.get().asItem(), ModBlocks.ADOBE_BRICKS.get().asItem());
        stairBuilder(ModBlocks.SMOOTH_ADOBE_STAIRS.get().asItem(), Ingredient.of(ModBlocks.SMOOTH_ADOBE.get().asItem())).unlockedBy("smooth_adobe", has(ModBlocks.SMOOTH_ADOBE.get())).save(consumer);
        stairBuilder(ModBlocks.ADOBE_STAIRS.get().asItem(), Ingredient.of(ModBlocks.ADOBE.get().asItem())).unlockedBy("adobe", has(ModBlocks.ADOBE.get())).save(consumer);
        stairBuilder(ModBlocks.ADOBE_BRICKS_STAIRS.get().asItem(), Ingredient.of(ModBlocks.ADOBE_BRICKS.get().asItem())).unlockedBy("adobe_bricks", has(ModBlocks.ADOBE_BRICKS.get())).save(consumer);

        stonecutterResultFromBase(consumer, ModBlocks.ADOBE_SLAB.get(), ModBlocks.ADOBE.get(), 2);
        stonecutterResultFromBase(consumer, ModBlocks.ADOBE_STAIRS.get(), ModBlocks.ADOBE.get());
        stonecutterResultFromBase(consumer, ModBlocks.ADOBE_BRICKS.get(), ModBlocks.ADOBE.get());
        stonecutterResultFromBase(consumer, ModBlocks.ADOBE_BRICKS_SLAB.get(), ModBlocks.ADOBE_BRICKS.get(), 2);
        stonecutterResultFromBase(consumer, ModBlocks.ADOBE_BRICKS_STAIRS.get(), ModBlocks.ADOBE_BRICKS.get());
        stonecutterResultFromBase(consumer, ModBlocks.SMOOTH_ADOBE_SLAB.get(), ModBlocks.SMOOTH_ADOBE.get(), 2);
        stonecutterResultFromBase(consumer, ModBlocks.SMOOTH_ADOBE_STAIRS.get(), ModBlocks.SMOOTH_ADOBE.get());
        //stonecutterResultFromBase(consumer, ModBlocks.SMOOTH_ADOBE.get(), ModBlocks.ADOBE.get());
        stonecutterResultFromBase(consumer, ModBlocks.FRAMED_ADOBE.get(), ModBlocks.ADOBE.get());

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAW_PERCH.get()), ModItems.COOKED_PERCH.get(), 0.35F, 200).unlockedBy("has_raw_perch", has(ModItems.RAW_PERCH.get())).save(consumer);

        cookModRecipes(consumer, "smoking", RecipeSerializer.SMOKING_RECIPE, 100);
        cookModRecipes(consumer, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, 600);
    }

    protected static void cookModRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, String pCookingMethod, SimpleCookingSerializer<?> pCookingSerializer, int pCookingTime) {
        simpleCookingRecipe(pFinishedRecipeConsumer, pCookingMethod, pCookingSerializer, pCookingTime, ModItems.RAW_PERCH.get(), ModItems.COOKED_PERCH.get(), 0.35F);
    }
}
