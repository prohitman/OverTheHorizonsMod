package com.prohitman.overthehorizons.core.init;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedList;
import java.util.List;

@Mod.EventBusSubscriber(modid = OverTheHorizonsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItemGroups {

    @SubscribeEvent
    public static void registerTab(CreativeModeTabEvent.Register event){
        event.registerCreativeModeTab(new ResourceLocation(OverTheHorizonsMod.MOD_ID, "oth_tab"), builder -> builder
                .icon(() -> ModItems.PINE_TEA.get().getDefaultInstance())
                .title(Component.translatable("itemGroup.overthehorizons"))
                .displayItems((featureFlags, output, hasOp) -> {
                    output.acceptAll(getTabItems());
                        }).build());
    }

    private static List<ItemStack> getTabItems(){
        List<ItemStack> list = new LinkedList<>();
        list.addAll(ModItems.ITEMS.getEntries().stream().map(RegistryObject::get).filter((item) -> item != ModItems.HUNTING_RIFLE_HAND.get() && item != ModItems.HUNTING_RIFLE_INVENTORY.get() && (!(item instanceof BlockItem) || item == ModItems.PINE_SIGN.get())).map(Item::getDefaultInstance).toList());
        list.addAll(ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).filter((block) -> block != ModBlocks.PINE_STANDING_SIGN.get() && block != ModBlocks.PINE_WALL_SIGN.get()).map(Block::asItem).map(Item::getDefaultInstance).toList());

        return list;
    }
}
