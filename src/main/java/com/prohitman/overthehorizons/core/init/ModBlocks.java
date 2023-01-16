package com.prohitman.overthehorizons.core.init;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.common.blocks.*;
import com.prohitman.overthehorizons.common.item.ModBlockItem;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    //Blocks
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, OverTheHorizonsMod.MOD_ID);

    public static final RegistryObject<Block> ADOBE = createRegistry("adobe", () -> normalStoneBlock(MaterialColor.COLOR_YELLOW, SoundType.STONE), new Item.Properties());
    public static final RegistryObject<Block> FRAMED_ADOBE = createRegistry("framed_adobe", () -> normalStoneBlock(MaterialColor.COLOR_YELLOW, SoundType.STONE), new Item.Properties());
    public static final RegistryObject<Block> SMOOTH_ADOBE = createRegistry("smooth_adobe", () -> normalStoneBlock(MaterialColor.COLOR_YELLOW, SoundType.STONE), new Item.Properties());
    public static final RegistryObject<Block> ADOBE_BRICKS = createRegistry("adobe_bricks", () -> normalStoneBlock(MaterialColor.COLOR_YELLOW, SoundType.STONE), new Item.Properties());
    public static final RegistryObject<Block> RED_LICHENSTONE = createRegistry("red_lichenstone", () -> new LichestoneBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).randomTicks().requiresCorrectToolForDrops().strength(2.0F, 6.0F)), new Item.Properties());
    public static final RegistryObject<Block> GREEN_LICHENSTONE = createRegistry("green_lichenstone", () -> new LichestoneBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).randomTicks().requiresCorrectToolForDrops().strength(2.0F, 6.0F)), new Item.Properties());
    public static final RegistryObject<Block> PEBBLES = createRegistry("pebbles", () -> normalStoneBlock(MaterialColor.STONE, SoundType.STONE), new Item.Properties());
    public static final RegistryObject<Block> RIVER_ROCKS = createRegistry("river_rocks", () -> new RiverRocks(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).strength(0.1F).dynamicShape().sound(SoundType.STONE)), new Item.Properties());
    public static final RegistryObject<Block> ADOBE_STAIRS = createRegistry("adobe_stairs", () -> new StairBlock(() -> ADOBE.get().defaultBlockState(), BlockBehaviour.Properties.copy(ADOBE.get())), new Item.Properties());
    public static final RegistryObject<Block> ADOBE_SLAB = createRegistry("adobe_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_YELLOW).strength(2.0F, 6.0F).sound(SoundType.STONE)), new Item.Properties());
    public static final RegistryObject<Block> SMOOTH_ADOBE_STAIRS = createRegistry("smooth_adobe_stairs", () -> new StairBlock(() -> SMOOTH_ADOBE.get().defaultBlockState(), BlockBehaviour.Properties.copy(SMOOTH_ADOBE.get())), new Item.Properties());
    public static final RegistryObject<Block> SMOOTH_ADOBE_SLAB = createRegistry("smooth_adobe_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_YELLOW).strength(2.0F, 6.0F).sound(SoundType.STONE)), new Item.Properties());
    public static final RegistryObject<Block> ADOBE_BRICKS_STAIRS = createRegistry("adobe_bricks_stairs", () -> new StairBlock(() -> ADOBE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(ADOBE_BRICKS.get())), new Item.Properties());
    public static final RegistryObject<Block> ADOBE_BRICKS_SLAB = createRegistry("adobe_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_YELLOW).strength(2.0F, 6.0F).sound(SoundType.STONE)), new Item.Properties());
    public static final RegistryObject<Block> ARID_SOIL = createRegistry("arid_soil", () -> new Block(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.5F).sound(SoundType.GRAVEL)), new Item.Properties());
    public static final RegistryObject<Block> PACKED_ROOTS = createRegistry("packed_roots", () -> new RootedDirtBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(0.5F).sound(SoundType.HANGING_ROOTS)), new Item.Properties());
    public static final RegistryObject<Block> ERODED_SLATE = createRegistry("eroded_slate", () -> normalStoneBlock(MaterialColor.STONE, SoundType.STONE), new Item.Properties());
    public static final RegistryObject<Block> SLATE_BRICKS = createRegistry("slate_bricks", () -> normalStoneBlock(MaterialColor.STONE, SoundType.STONE), new Item.Properties());
    public static final RegistryObject<Block> SLATE_TILES = createRegistry("slate_tiles", () -> normalStoneBlock(MaterialColor.STONE, SoundType.STONE), new Item.Properties());
    public static final RegistryObject<Block> SLATE = createRegistry("slate", () -> new SlateBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(2.0F, 6.0F).sound(SoundType.STONE)), new Item.Properties());

    //Tree
    public static final RegistryObject<Block> PINE_LEAVES = createRegistry("pine_leaves", () -> leaves(SoundType.GRASS, ModLeavesBlock.LeafParticleType.NONE), new Item.Properties());
    public static final RegistryObject<Block> DRIED_BIRCH_LEAVES = createRegistry("dried_birch_leaves", () -> leaves(SoundType.AZALEA_LEAVES, ModLeavesBlock.LeafParticleType.YELLOW), new Item.Properties());
    public static final RegistryObject<Block> DRIED_OAK_LEAVES = createRegistry("dried_oak_leaves", () -> leaves(SoundType.AZALEA_LEAVES, ModLeavesBlock.LeafParticleType.BROWN), new Item.Properties());
    public static final RegistryObject<Block> DRIED_DARK_OAK_LEAVES = createRegistry("dried_dark_oak_leaves", () -> leaves(SoundType.AZALEA_LEAVES, ModLeavesBlock.LeafParticleType.RED), new Item.Properties());
    public static final RegistryObject<Block> FALLEN_LEAVES = createRegistry("fallen_leaves", () -> new CarpetBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_GREEN).noOcclusion().strength(0.1F).sound(SoundType.MOSS_CARPET)), new Item.Properties());
    public static final RegistryObject<Block> TREE_MOSS = createRegistry("tree_moss", () -> new VineBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.PLANT).noCollission().strength(0.1F).sound(SoundType.VINE)), new Item.Properties());
    public static final RegistryObject<Block> PINE_LOG = createRegistry("pine_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)), new Item.Properties());
    public static final RegistryObject<Block> PINE_WOOD = createRegistry("pine_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)), new Item.Properties());
    public static final RegistryObject<Block> STRIPPED_PINE_LOG = createRegistry("stripped_pine_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)), new Item.Properties());
    public static final RegistryObject<Block> STRIPPED_PINE_WOOD = createRegistry("stripped_pine_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)), new Item.Properties());
    public static final RegistryObject<Block> PINE_PLANKS = createRegistry("pine_planks", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)), new Item.Properties());
    public static final RegistryObject<Block> PINE_STAIRS = createRegistry("pine_stairs", () -> new StairBlock(() -> PINE_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(PINE_PLANKS.get())), new Item.Properties());
    public static final RegistryObject<Block> PINE_SLAB = createRegistry("pine_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)), new Item.Properties());
    public static final RegistryObject<Block> PINE_PRESSURE_PLATE = createRegistry("pine_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, PINE_PLANKS.get().defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD), SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON), new Item.Properties());
    public static final RegistryObject<Block> PINE_BUTTON = createRegistry("pine_button", ModBlocks::woodenButton, new Item.Properties());
    public static final RegistryObject<Block> PINE_DOOR = createRegistry("pine_door", () -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(3.0F).sound(SoundType.WOOD).noOcclusion(), SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_DOOR_OPEN), new Item.Properties());
    public static final RegistryObject<Block> PINE_TRAPDOOR = createRegistry("pine_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, PINE_PLANKS.get().defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(ModBlocks::never), SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN), new Item.Properties());
    public static final RegistryObject<Block> PINE_CONE = createRegistry("pine_cone", () -> new PineConeBlock(BlockBehaviour.Properties.of(Material.VEGETABLE, MaterialColor.WOOD).noCollission().instabreak().sound(SoundType.GRASS)), new Item.Properties());
    public static final RegistryObject<Block> PINE_FENCE = createRegistry("pine_fence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, PINE_PLANKS.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), new Item.Properties());
    public static final RegistryObject<Block> PINE_FENCE_GATE = createRegistry("pine_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, PINE_PLANKS.get().defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(ModBlocks::never), SoundEvents.FENCE_GATE_CLOSE, SoundEvents.FENCE_GATE_OPEN), new Item.Properties());
    public static final RegistryObject<Block> PINE_STANDING_SIGN = createRegistryWithoutBlockItem("pine_standing_sign", () -> new ModStandingSignBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), ModWoodTypes.PINE));
    public static final RegistryObject<Block> PINE_WALL_SIGN = createRegistryWithoutBlockItem("pine_wall_sign", () -> new ModWallSignBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), ModWoodTypes.PINE));

    //Plants
    public static final RegistryObject<Block> ROSE = createRegistry("rose", () -> new FlowerBlock(() -> MobEffects.LUCK, 9, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), new Item.Properties());
    public static final RegistryObject<Block> RED_LICHEN_COVERAGE = createRegistry("red_lichen_coverage", () -> new LichenCoverageBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_ORANGE).noCollission().instabreak().sound(SoundType.AZALEA_LEAVES).offsetType(BlockBehaviour.OffsetType.XZ)), new Item.Properties());
    public static final RegistryObject<Block> GREEN_LICHEN_COVERAGE = createRegistry("green_lichen_coverage", () -> new LichenCoverageBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_GREEN).noCollission().instabreak().sound(SoundType.AZALEA_LEAVES).offsetType(BlockBehaviour.OffsetType.XZ)), new Item.Properties());
    public static final RegistryObject<Block> DUCKWEED = createRegistry("duckweed", () -> new WaterlilyBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_GREEN).instabreak().sound(SoundType.LILY_PAD).noOcclusion()), new Item.Properties());
    public static final RegistryObject<Block> DUNE_GRASS = createRegistry("dune_grass", () -> new DuneGrassBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), new Item.Properties());
    public static final RegistryObject<Block> WILD_WHEAT = createRegistry("wild_wheat", () -> new ModTallGrassBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS)), new Item.Properties());
    public static final RegistryObject<Block> TALL_WILD_WHEAT = createRegistry("tall_wild_wheat", () -> new TallWildWheatBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS)), new Item.Properties());
    public static final RegistryObject<Block> SPROUTS = createRegistry("sprouts", () -> new ModTallGrassBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.MOSS)), new Item.Properties());
    public static final RegistryObject<Block> HEDGEHOG_MUSHROOM = createRegistry("hedgehog_mushroom", () -> new MushroomBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_BROWN).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)
            .lightLevel((p_50892_) -> 1)
            .hasPostProcess(ModBlocks::always),
            TreeFeatures.HUGE_BROWN_MUSHROOM), new Item.Properties());
    public static final RegistryObject<Block> HEDGEHOG_MUSHROOM_TALL = createRegistry("hedgehog_mushroom_tall", () -> new ModTallMushroomBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_BROWN).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)
            .lightLevel((p_50892_) -> 1)
            .hasPostProcess(ModBlocks::always),
            TreeFeatures.HUGE_BROWN_MUSHROOM), new Item.Properties());
    public static final RegistryObject<Block> TRAMPLED_GRASS = createRegistry("trampled_grass", () -> new ModTallGrassBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), new Item.Properties());
    public static final RegistryObject<Block> LAND_REEDS = createRegistry("land_reeds", () -> new LandReedsBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS)), new Item.Properties());
    public static final RegistryObject<Block> WATER_REEDS = createRegistry("water_reeds", () -> new WaterReedsBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.WET_GRASS)), new Item.Properties());

    public static <T extends Block> RegistryObject<Block> createRegistry(String name, Supplier<T> block, Item.Properties properties) {
        RegistryObject<Block> object = BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, () -> new ModBlockItem(object.get(), properties));

        return object;
    }

    public static <T extends Block> RegistryObject<Block> createRegistryWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    private static Block normalStoneBlock(MaterialColor color, SoundType soundType) {
        return new Block(BlockBehaviour.Properties.of(Material.STONE, color).requiresCorrectToolForDrops().strength(2.0F, 6.0F).sound(soundType));
    }

    private static LeavesBlock leaves(SoundType soundType, ModLeavesBlock.LeafParticleType particleType) {
        return new ModLeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(soundType).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never), particleType);
    }

    private static boolean always(BlockState p_50775_, BlockGetter p_50776_, BlockPos p_50777_) {
        return true;
    }

    private static boolean never(BlockState state, BlockGetter getter, BlockPos pos) {
        return false;
    }

    private static Boolean never(BlockState state, BlockGetter getter, BlockPos pos, EntityType<?> entityType) {
        return false;
    }

    private static Boolean ocelotOrParrot(BlockState state, BlockGetter blockGetter, BlockPos pos, EntityType<?> entityType) {
        return entityType == EntityType.OCELOT || entityType == EntityType.PARROT;
    }

    private static ButtonBlock woodenButton() {
        return woodenButton(SoundType.WOOD, SoundEvents.WOODEN_BUTTON_CLICK_OFF, SoundEvents.WOODEN_BUTTON_CLICK_ON);
    }

    private static ButtonBlock woodenButton(SoundType p_249282_, SoundEvent p_251988_, SoundEvent p_251887_) {
        return new ButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(p_249282_), 30, true, p_251988_, p_251887_);
    }
}
