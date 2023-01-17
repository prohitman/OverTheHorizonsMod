package com.prohitman.overthehorizons.core.datagen.loottables;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.packs.*;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class CreateLTProvider {
    public static @NotNull LootTableProvider create(PackOutput pOutput) {
        return new LootTableProvider(pOutput, Collections.emptySet(), List.of(new LootTableProvider.SubProviderEntry(ModBlockLootSubProvider::new, LootContextParamSets.BLOCK)));
    }
}
