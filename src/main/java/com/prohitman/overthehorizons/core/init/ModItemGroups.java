package com.prohitman.overthehorizons.core.init;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.common.blocks.ModWallHangingSignBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedList;
import java.util.List;

@Mod.EventBusSubscriber(modid = OverTheHorizonsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItemGroups {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,
            OverTheHorizonsMod.MOD_ID);

    public static RegistryObject<CreativeModeTab> OTH_TAB = CREATIVE_MODE_TABS.register("oth_tab", () ->
            CreativeModeTab.builder().icon(() -> ModItems.PINE_TEA.get().getDefaultInstance())
                    .title(Component.translatable("itemGroup.overthehorizons"))
                    .displayItems((featureFlags, output) -> {
                        output.acceptAll(getTabItems());
                    }).build());

    private static List<ItemStack> getTabItems(){
        List<ItemStack> list = new LinkedList<>();
        list.addAll(ModItems.ITEMS.getEntries().stream().map(RegistryObject::get)
                .filter((item) -> item != ModItems.HUNTING_RIFLE_HAND.get()
                        && item != ModItems.HUNTING_RIFLE_INVENTORY.get()
                        && (!(item instanceof BlockItem)
                        || item instanceof SignItem))
                .map(Item::getDefaultInstance).toList());

        list.addAll(ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)
                .filter((block) -> !(block instanceof StandingSignBlock)
                        && !(block instanceof WallSignBlock)
                        && !(block instanceof WallHangingSignBlock)
                        && !(block instanceof CeilingHangingSignBlock))
                .map(Block::asItem).map(Item::getDefaultInstance).toList());

        return list;
    }
}
