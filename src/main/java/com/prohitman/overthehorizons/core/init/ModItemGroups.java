package com.prohitman.overthehorizons.core.init;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = OverTheHorizonsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItemGroups {

    @SubscribeEvent
    public static void registerTab(CreativeModeTabEvent.Register event){
        event.registerCreativeModeTab(new ResourceLocation(OverTheHorizonsMod.MOD_ID, "oth_tab"), builder -> builder
                .icon(() -> ModItems.PINE_TEA.get().getDefaultInstance())
                .title(Component.translatable("tabs.overthehorizons.oth_tab"))
                .displayItems((featureFlags, output, hasOp) -> {
                            output.acceptAll(ModItems.ITEMS.getEntries().stream().map(RegistryObject::get).map(Item::getDefaultInstance).toList());
                            output.acceptAll(ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).map(Block::asItem).map(Item::getDefaultInstance).toList());
                        }).build());
    }
}
