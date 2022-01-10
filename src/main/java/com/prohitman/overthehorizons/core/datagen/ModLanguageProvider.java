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

        add(ModBlocks.GREEN_LICHENSTONE.get(), "Green Lichenstone");
        add(ModBlocks.RED_LICHENSTONE.get(), "Red Lichenstone");
        add(ModBlocks.GREEN_LICHEN_COVERAGE.get(), "Green Lichen Coverage");
        add(ModBlocks.RED_LICHEN_COVERAGE.get(), "Red Lichen Coverage");

        add(ModItems.LICHEN_CLUSTER.get(), "Lichen Cluster");
    }
}
