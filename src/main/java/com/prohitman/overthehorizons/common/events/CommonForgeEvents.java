package com.prohitman.overthehorizons.common.events;

import com.google.common.collect.ImmutableMap;
import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.common.network.OTHPacketHandler;
import com.prohitman.overthehorizons.core.init.ModBlocks;
import com.prohitman.overthehorizons.core.init.ModItems;
import com.prohitman.overthehorizons.core.init.ModWoodTypes;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = OverTheHorizonsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonForgeEvents {

    @SubscribeEvent
    public static void setup(final FMLCommonSetupEvent event){
        event.enqueueWork(() -> {
            OTHPacketHandler.init();

            registerCompostables();
            AxeItem.STRIPPABLES = new ImmutableMap.Builder<Block, Block>().putAll(AxeItem.STRIPPABLES)
                    .put(ModBlocks.PINE_LOG.get(), ModBlocks.STRIPPED_PINE_LOG.get())
                    .put(ModBlocks.PINE_WOOD.get(), ModBlocks.STRIPPED_PINE_WOOD.get()).build();
            registerFlammables();

            WoodType.register(ModWoodTypes.PINE);
        });
    }

    public static void registerCompostables(){
        ComposterBlock.COMPOSTABLES.put(ModBlocks.DUNE_GRASS.get().asItem(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(ModBlocks.WILD_WHEAT.get().asItem(), 0.4F);
        ComposterBlock.COMPOSTABLES.put(ModBlocks.TALL_WILD_WHEAT.get().asItem(), 0.5F);
        ComposterBlock.COMPOSTABLES.put(ModBlocks.FALLEN_LEAVES.get().asItem(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(ModBlocks.DRIED_BIRCH_LEAVES.get().asItem(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(ModBlocks.DRIED_OAK_LEAVES.get().asItem(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(ModBlocks.DRIED_DARK_OAK_LEAVES.get().asItem(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(ModBlocks.RED_LICHEN_COVERAGE.get().asItem(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(ModBlocks.GREEN_LICHEN_COVERAGE.get().asItem(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(ModBlocks.PINE_LEAVES.get().asItem(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(ModBlocks.TREE_MOSS.get().asItem(), 0.5F);
        ComposterBlock.COMPOSTABLES.put(ModBlocks.PINE_CONE.get().asItem(), 0.5F);
        ComposterBlock.COMPOSTABLES.put(ModItems.LICHEN_CLUSTER.get().asItem(), 0.8F);
    }

    public static void registerFlammables(){

        flammableBlock(ModBlocks.PINE_PLANKS.get(), 5, 20);
        flammableBlock(ModBlocks.PINE_DOOR.get(), 5, 20);
        flammableBlock(ModBlocks.PINE_SLAB.get(), 5, 20);
        flammableBlock(ModBlocks.PINE_FENCE_GATE.get(), 5, 20);
        flammableBlock(ModBlocks.PINE_FENCE.get(), 5, 20);
        flammableBlock(ModBlocks.PINE_STAIRS.get(), 5, 20);
        flammableBlock(ModBlocks.PINE_LEAVES.get(), 30, 60);
        flammableBlock(ModBlocks.FALLEN_LEAVES.get(), 30, 60);
        flammableBlock(ModBlocks.DRIED_OAK_LEAVES.get(), 30, 60);
        flammableBlock(ModBlocks.DRIED_BIRCH_LEAVES.get(), 30, 60);
        flammableBlock(ModBlocks.DRIED_DARK_OAK_LEAVES.get(), 30, 60);
        flammableBlock(ModBlocks.PINE_LOG.get(), 5, 5);
        flammableBlock(ModBlocks.STRIPPED_PINE_LOG.get(), 5, 5);
        flammableBlock(ModBlocks.STRIPPED_PINE_WOOD.get(), 5, 5);
        flammableBlock(ModBlocks.PINE_WOOD.get(), 5, 5);

    }

    public static void flammableBlock(Block block, int flameOdds, int burnOdds) {
        FireBlock fire = (FireBlock) Blocks.FIRE;
        fire.setFlammable(block, flameOdds, burnOdds);
    }
}
