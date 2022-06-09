package com.prohitman.overthehorizons.core.datagen;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.core.init.ModBlocks;
import com.prohitman.overthehorizons.core.init.ModItems;
import com.sun.jna.platform.win32.WinBase;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class ModItemTags extends ItemTagsProvider {
    public static final TagKey<Item> PINE_LOGS = bind("pine_logs");

    public ModItemTags(DataGenerator generator, BlockTagsProvider blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, blockTags, OverTheHorizonsMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(Tags.Items.FENCE_GATES_WOODEN).add(ModBlocks.PINE_FENCE_GATE.get().asItem());
        tag(Tags.Items.FENCES_WOODEN).add(ModBlocks.PINE_FENCE.get().asItem());
        tag(ItemTags.LOGS_THAT_BURN).add(ModBlocks.PINE_WOOD.get().asItem());
        tag(ItemTags.LOGS_THAT_BURN).add(ModBlocks.STRIPPED_PINE_WOOD.get().asItem());
        tag(ItemTags.LOGS_THAT_BURN).add(ModBlocks.PINE_LOG.get().asItem());
        tag(ItemTags.LOGS_THAT_BURN).add(ModBlocks.STRIPPED_PINE_LOG.get().asItem());
        tag(ItemTags.BOATS).add(ModItems.PINE_BOAT.get());
        tag(ItemTags.LOGS).add(ModBlocks.PINE_WOOD.get().asItem());
        tag(ItemTags.LOGS).add(ModBlocks.STRIPPED_PINE_LOG.get().asItem());
        tag(ItemTags.LOGS).add(ModBlocks.PINE_LOG.get().asItem());
        tag(ItemTags.LOGS).add(ModBlocks.STRIPPED_PINE_WOOD.get().asItem());
        //tag(ItemTags.FENCES).add(ModBlocks.PINE_FENCE_GATE.get().asItem());
        //tag(ItemTags.FENCES).add(ModBlocks.PINE_FENCE.get().asItem());
        tag(ItemTags.WOODEN_FENCES).add(ModBlocks.PINE_FENCE_GATE.get().asItem());
        tag(ItemTags.WOODEN_FENCES).add(ModBlocks.PINE_FENCE.get().asItem());
        tag(ItemTags.WOODEN_PRESSURE_PLATES).add(ModBlocks.PINE_PRESSURE_PLATE.get().asItem());
        tag(ItemTags.WOODEN_BUTTONS).add(ModBlocks.PINE_BUTTON.get().asItem());
        tag(ItemTags.WOODEN_DOORS).add(ModBlocks.PINE_DOOR.get().asItem());
        tag(ItemTags.WOODEN_TRAPDOORS).add(ModBlocks.PINE_TRAPDOOR.get().asItem());
        tag(ItemTags.DIRT).add(ModBlocks.ARID_SOIL.get().asItem());
        tag(ItemTags.LEAVES).add(ModBlocks.DRIED_OAK_LEAVES.get().asItem());
        tag(ItemTags.LEAVES).add(ModBlocks.DRIED_DARK_OAK_LEAVES.get().asItem());
        tag(ItemTags.LEAVES).add(ModBlocks.DRIED_BIRCH_LEAVES.get().asItem());
        tag(ItemTags.LEAVES).add(ModBlocks.FALLEN_LEAVES.get().asItem());
        tag(ItemTags.LEAVES).add(ModBlocks.PINE_LEAVES.get().asItem());
        tag(ItemTags.PLANKS).add(ModBlocks.PINE_PLANKS.get().asItem());
        tag(ItemTags.SIGNS).add(ModItems.PINE_SIGN.get());
        tag(ItemTags.WOODEN_SLABS).add(ModBlocks.PINE_SLAB.get().asItem());
        tag(ItemTags.SLABS).add(ModBlocks.ADOBE_SLAB.get().asItem());
        tag(ItemTags.SLABS).add(ModBlocks.ADOBE_BRICKS_SLAB.get().asItem());
        tag(ItemTags.SLABS).add(ModBlocks.SMOOTH_ADOBE_SLAB.get().asItem());
        tag(ItemTags.WOODEN_STAIRS).add(ModBlocks.PINE_STAIRS.get().asItem());
        tag(ItemTags.STAIRS).add(ModBlocks.ADOBE_STAIRS.get().asItem());
        tag(ItemTags.STAIRS).add(ModBlocks.SMOOTH_ADOBE_STAIRS.get().asItem());
        tag(ItemTags.STAIRS).add(ModBlocks.ADOBE_BRICKS_STAIRS.get().asItem());
        tag(ItemTags.FLOWERS).add(ModBlocks.ROSE.get().asItem());

        tag(PINE_LOGS).add(ModBlocks.PINE_WOOD.get().asItem());
        tag(PINE_LOGS).add(ModBlocks.STRIPPED_PINE_WOOD.get().asItem());
        tag(PINE_LOGS).add(ModBlocks.PINE_LOG.get().asItem());
        tag(PINE_LOGS).add(ModBlocks.STRIPPED_PINE_LOG.get().asItem());
    }

    private static TagKey<Item> bind(String pName) {
        return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(pName));
    }
}
