package com.prohitman.overthehorizons.core.datagen;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.core.init.ModBlocks;
import com.prohitman.overthehorizons.core.init.ModEntityTypes;
import com.prohitman.overthehorizons.core.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(DataGenerator gen, String locale) {
        super(gen, OverTheHorizonsMod.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.overthehorizons", "Over The Horizons Mod");

        add(ModBlocks.ADOBE.get(), "Adobe");
        add(ModBlocks.ADOBE_BRICKS.get(), "Adobe Bricks");
        add(ModBlocks.FRAMED_ADOBE.get(), "Framed Adobe");
        add(ModBlocks.SMOOTH_ADOBE.get(), "Smooth Adobe");
        add(ModBlocks.DRIED_DARK_OAK_LEAVES.get(), "Dried Dark Oak Leaves");
        add(ModBlocks.DRIED_OAK_LEAVES.get(), "Dried Oak Leaves");
        add(ModBlocks.DRIED_BIRCH_LEAVES.get(), "Dried Birch Leaves");
        add(ModBlocks.GREEN_LICHENSTONE.get(), "Green Lichenstone");
        add(ModBlocks.RED_LICHENSTONE.get(), "Red Lichenstone");
        add(ModBlocks.GREEN_LICHEN_COVERAGE.get(), "Green Lichen Coverage");
        add(ModBlocks.RED_LICHEN_COVERAGE.get(), "Red Lichen Coverage");
        add(ModBlocks.TREE_MOSS.get(), "Tree Moss");
        add(ModBlocks.PEBBLES.get(), "Pebbles");
        add(ModBlocks.RIVER_ROCKS.get(), "River Rocks");
        add(ModBlocks.DUCKWEED.get(), "Duckweed");
        add(ModBlocks.FALLEN_LEAVES.get(), "Fallen Leaves");

        add(ModBlocks.PINE_LEAVES.get(), "Pine Leaves");
        add(ModBlocks.PINE_SLAB.get(), "Pine Slab");
        add(ModBlocks.STRIPPED_PINE_LOG.get(), "Stripped Pine Log");
        add(ModBlocks.PINE_STAIRS.get(), "Pine Stairs");
        add(ModBlocks.PINE_BUTTON.get(), "Pine Button");
        add(ModBlocks.PINE_DOOR.get(), "Pine Door");
        add(ModBlocks.PINE_PRESSURE_PLATE.get(), "Pine Pressure Plate");
        add(ModBlocks.PINE_LOG.get(), "Pine Log");
        add(ModBlocks.PINE_CONE.get(), "Pine Cone");
        add(ModBlocks.PINE_TRAPDOOR.get(), "Pine Trapdoor");
        add(ModBlocks.PINE_PLANKS.get(), "Pine Planks");
        add(ModBlocks.PINE_FENCE.get(), "Pine Fence");
        add(ModBlocks.PINE_FENCE_GATE.get(), "Pine Fence Gate");

        add(ModItems.LICHEN_CLUSTER.get(), "Lichen Cluster");
        add(ModItems.COPPER_BULLET.get(), "Copper Bullet");
        add(ModItems.PINE_SIGN.get(), "Pine Sign");
        add(ModItems.PINE_BOAT.get(), "Pine Boat");
        add(ModItems.HUNTING_RIFLE.get(), "Hunting Rifle");

    }
}
