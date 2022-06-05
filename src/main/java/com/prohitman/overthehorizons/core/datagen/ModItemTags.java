package com.prohitman.overthehorizons.core.datagen;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.core.init.ModBlocks;
import com.sun.jna.platform.win32.WinBase;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
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
        tag(Tags.Items.FENCE_GATES).add(ModBlocks.PINE_FENCE_GATE.get().asItem());
        tag(Tags.Items.FENCES).add(ModBlocks.PINE_FENCE.get().asItem());
        tag(PINE_LOGS).add(ModBlocks.PINE_WOOD.get().asItem());
        tag(PINE_LOGS).add(ModBlocks.STRIPPED_PINE_WOOD.get().asItem());
        tag(PINE_LOGS).add(ModBlocks.PINE_LOG.get().asItem());
        tag(PINE_LOGS).add(ModBlocks.STRIPPED_PINE_LOG.get().asItem());
    }

    private static TagKey<Item> bind(String pName) {
        return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(pName));
    }

    public static TagKey<Item> create(final ResourceLocation name) {
        return TagKey.create(Registry.ITEM_REGISTRY, name);
    }
}
