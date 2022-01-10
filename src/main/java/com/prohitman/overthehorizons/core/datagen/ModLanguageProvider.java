package com.prohitman.overthehorizons.core.datagen;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.core.init.ModBlocks;
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

        add(ModItems.LICHEN_CLUSTER.get(), "Lichen Cluster");
    }
}
