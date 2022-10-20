package com.prohitman.overthehorizons.core.datagen;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.client.keybinds.ModKeyBindings;
import com.prohitman.overthehorizons.core.init.ModBlocks;
import com.prohitman.overthehorizons.core.init.ModEntityTypes;
import com.prohitman.overthehorizons.core.init.ModItems;
import com.prohitman.overthehorizons.core.init.ModSounds;
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
        add(ModBlocks.ADOBE_STAIRS.get(), "Adobe Stairs");
        add(ModBlocks.SMOOTH_ADOBE_STAIRS.get(), "Smooth Adobe Stairs");
        add(ModBlocks.ADOBE_BRICKS_STAIRS.get(), "Adobe Bricks Stairs");
        add(ModBlocks.ADOBE_BRICKS_SLAB.get(), "Adobe Bricks Slab");
        add(ModBlocks.SMOOTH_ADOBE_SLAB.get(), "Smooth Adobe Slab");
        add(ModBlocks.ADOBE_SLAB.get(), "Adobe Slab");
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
        add(ModBlocks.ARID_SOIL.get(), "Arid Soil");
        add(ModBlocks.PACKED_ROOTS.get(), "Packed Roots");
        add(ModBlocks.ROSE.get(), "Rose");
        add(ModBlocks.DUNE_GRASS.get(), "Dune Grass");
        add(ModBlocks.TALL_WILD_WHEAT.get(), "Tall Wild Wheat");
        add(ModBlocks.WILD_WHEAT.get(), "Wild Wheat");
        add(ModBlocks.SPROUTS.get(), "Sprouts");
        add(ModBlocks.SLATE.get(), "Slate");
        add(ModBlocks.SLATE_TILES.get(), "Slate Tiles");
        add(ModBlocks.SLATE_BRICKS.get(), "Slate Bricks");
        add(ModBlocks.ERODED_SLATE.get(), "Eroded Slate");

        add(ModBlocks.PINE_LEAVES.get(), "Pine Leaves");
        add(ModBlocks.PINE_SLAB.get(), "Pine Slab");
        add(ModBlocks.STRIPPED_PINE_LOG.get(), "Stripped Pine Log");
        add(ModBlocks.PINE_STAIRS.get(), "Pine Stairs");
        add(ModBlocks.PINE_BUTTON.get(), "Pine Button");
        add(ModBlocks.PINE_DOOR.get(), "Pine Door");
        add(ModBlocks.PINE_PRESSURE_PLATE.get(), "Pine Pressure Plate");
        add(ModBlocks.PINE_WOOD.get(), "Pine Wood");
        add(ModBlocks.STRIPPED_PINE_WOOD.get(), "Stripped Pine Wood");
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
        add(ModItems.PINE_TEA.get(), "Pine Tea");
        add(ModItems.PERCH_SPAWN_EGG.get(), "Perch Spawn Egg");
        add(ModItems.PERCH_BUCKET.get(), "Perch Bucket");
        add(ModItems.RAW_PERCH.get(), "Raw Perch");
        add(ModItems.COOKED_PERCH.get(), "Cooked Perch");
        add(ModItems.RAW_CATFISH.get(), "Raw Catfish");
        add(ModItems.CATFISH_SPAWN_EGG.get(), "Catfish Spawn Egg");
        add(ModItems.COOKED_CATFISH.get(), "Cooked Catfish");
        add(ModItems.FISH_BONES.get(), "Fish Bones");
        add(ModItems.FENNEC_FOX_SPAWN_EGG.get(), "Fennec Fox Spawn Egg");

        add(ModEntityTypes.PERCH.get(), "Perch");
        add(ModEntityTypes.CATFISH.get(), "Catfish");
        add(ModEntityTypes.FENNEC_FOX.get(), "Fennec Fox");

        add("subtitles.overthehorizons.rifle_shoot_bullet", "Rifle Shooting Bullet");
        add("subtitles.overthehorizons.rifle_reload", "Reloading Rifle");
        add("subtitles.overthehorizons.rifle_no_ammo", "Rifle No Ammo");

        add("overthehorizons.message.no_ammo", "No Ammo!!!");

        //add("overthehorizons.tooltip.press_shift", "press\\u00A79 [SHIFT]\\u00A77 for more info.");
        add("overthehorizons.tooltip.rifle_shooting_range", "Rifle Shooting Range: ");
        add("overthehorizons.tooltip.bullet_capacity", "Bullet Capacity: ");
        add("overthehorizons.tooltip.current_bullets", "Current Bullets: ");

        add("key.overthehorizons.reload_rifle","Reload Rifle");
        add("key.overthehorizons.zoom_rifle","Zoom Rifle");
    }
}
