package com.prohitman.overthehorizons.common.events;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.common.entity.Perch;
import com.prohitman.overthehorizons.common.item.ModBlockItem;
import com.prohitman.overthehorizons.core.init.ModBlocks;
import com.prohitman.overthehorizons.core.init.ModEntityTypes;
import com.prohitman.overthehorizons.core.init.ModItemGroups;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.*;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = OverTheHorizonsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {
    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
            if(!(block instanceof StandingSignBlock || block instanceof WallSignBlock)){
                final Item.Properties properties = new Item.Properties().tab(ModItemGroups.OVER_THE_HORIZONS);
                final ModBlockItem blockItem = new ModBlockItem(block, properties);
                blockItem.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
                registry.register(blockItem);
            }
        });
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.PERCH.get(), Perch.createAttributes().build());
    }


    /*@SubscribeEvent
    public static void onRegisterItems(final RegisterEvent event) {
        final IForgeRegistry<Item> registry = event.getForgeRegistry();

        ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
            if(!(block instanceof StandingSignBlock || block instanceof WallSignBlock)){
                final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, OverTheHorizonsMod.MOD_ID);

                final Item.Properties properties = new Item.Properties().tab(ModItemGroups.OVER_THE_HORIZONS);
                final ModBlockItem blockItem = new ModBlockItem(block, properties);
                final RegistryObject<Item> BLOCK_ITEM = ITEMS.register(blockItem.getBlock().getName(), () -> blockItem);
                assert registry != null;
                registry.register(Objects.requireNonNull(block.getName().toString()), BLOCK_ITEM);
            }
        });
    }*/
}
