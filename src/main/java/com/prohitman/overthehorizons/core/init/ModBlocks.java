package com.prohitman.overthehorizons.core.init;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.common.blocks.*;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    //Blocks
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, OverTheHorizonsMod.MOD_ID);

    public static final RegistryObject<Block> ADOBE = BLOCKS.register("adobe", () -> normalStoneBlock(MaterialColor.COLOR_YELLOW, SoundType.STONE));
    public static final RegistryObject<Block> FRAMED_ADOBE = BLOCKS.register("framed_adobe", () -> normalStoneBlock(MaterialColor.COLOR_YELLOW, SoundType.STONE));
    public static final RegistryObject<Block> SMOOTH_ADOBE = BLOCKS.register("smooth_adobe", () -> normalStoneBlock(MaterialColor.COLOR_YELLOW, SoundType.STONE));
    public static final RegistryObject<Block> ADOBE_BRICKS = BLOCKS.register("adobe_bricks", () -> normalStoneBlock(MaterialColor.COLOR_YELLOW, SoundType.STONE));
    public static final RegistryObject<Block> RED_LICHENSTONE = BLOCKS.register("red_lichenstone", () -> new LichestoneBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).randomTicks().requiresCorrectToolForDrops().strength(2.0F, 6.0F)));
    public static final RegistryObject<Block> GREEN_LICHENSTONE = BLOCKS.register("green_lichenstone", () -> new LichestoneBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).randomTicks().requiresCorrectToolForDrops().strength(2.0F, 6.0F)));
    public static final RegistryObject<Block> PEBBLES = BLOCKS.register("pebbles", () -> normalStoneBlock(MaterialColor.STONE, SoundType.STONE));
    public static final RegistryObject<Block> RIVER_ROCKS = BLOCKS.register("river_rocks", () -> new RiverRocks(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).strength(0.1F).dynamicShape().sound(SoundType.STONE)));
    public static final RegistryObject<StairBlock> ADOBE_STAIRS = BLOCKS.register("adobe_stairs", () -> new StairBlock(() -> ADOBE.get().defaultBlockState(), BlockBehaviour.Properties.copy(ADOBE.get())));
    public static final RegistryObject<SlabBlock> ADOBE_SLAB = BLOCKS.register("adobe_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_YELLOW).strength(2.0F, 6.0F).sound(SoundType.STONE)));
    public static final RegistryObject<StairBlock> SMOOTH_ADOBE_STAIRS = BLOCKS.register("smooth_adobe_stairs", () -> new StairBlock(() -> SMOOTH_ADOBE.get().defaultBlockState(), BlockBehaviour.Properties.copy(SMOOTH_ADOBE.get())));
    public static final RegistryObject<SlabBlock> SMOOTH_ADOBE_SLAB = BLOCKS.register("smooth_adobe_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_YELLOW).strength(2.0F, 6.0F).sound(SoundType.STONE)));
    public static final RegistryObject<StairBlock> ADOBE_BRICKS_STAIRS = BLOCKS.register("adobe_bricks_stairs", () -> new StairBlock(() -> ADOBE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(ADOBE_BRICKS.get())));
    public static final RegistryObject<SlabBlock> ADOBE_BRICKS_SLAB = BLOCKS.register("adobe_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_YELLOW).strength(2.0F, 6.0F).sound(SoundType.STONE)));
    public static final RegistryObject<Block> ARID_SOIL = BLOCKS.register("arid_soil", () -> new Block(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.5F).sound(SoundType.GRAVEL)));
    public static final RegistryObject<Block> PACKED_ROOTS = BLOCKS.register("packed_roots", () -> new RootedDirtBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(0.5F).sound(SoundType.ANVIL)));
    public static final RegistryObject<Block> ERODED_SLATE = BLOCKS.register("eroded_slate", () -> normalStoneBlock(MaterialColor.STONE, SoundType.STONE));
    public static final RegistryObject<Block> SLATE_BRICKS = BLOCKS.register("slate_bricks", () -> normalStoneBlock(MaterialColor.STONE, SoundType.STONE));
    public static final RegistryObject<Block> SLATE_TILES = BLOCKS.register("slate_tiles", () -> normalStoneBlock(MaterialColor.STONE, SoundType.STONE));
    public static final RegistryObject<Block> SLATE = BLOCKS.register("slate", () -> new SlateBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(2.0F, 6.0F).sound(SoundType.STONE)));

    //Tree
    public static final RegistryObject<Block> PINE_LEAVES = BLOCKS.register("pine_leaves", () -> leaves(SoundType.GRASS, ModLeavesBlock.LeafParticleType.NONE));
    public static final RegistryObject<Block> DRIED_BIRCH_LEAVES = BLOCKS.register("dried_birch_leaves", () -> leaves(SoundType.AZALEA_LEAVES, ModLeavesBlock.LeafParticleType.YELLOW));
    public static final RegistryObject<Block> DRIED_OAK_LEAVES = BLOCKS.register("dried_oak_leaves", () -> leaves(SoundType.AZALEA_LEAVES, ModLeavesBlock.LeafParticleType.BROWN));
    public static final RegistryObject<Block> DRIED_DARK_OAK_LEAVES = BLOCKS.register("dried_dark_oak_leaves", () -> leaves(SoundType.AZALEA_LEAVES, ModLeavesBlock.LeafParticleType.RED));
    public static final RegistryObject<Block> FALLEN_LEAVES = BLOCKS.register("fallen_leaves", () -> new CarpetBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_GREEN).noOcclusion().strength(0.1F).sound(SoundType.MOSS_CARPET)));
    public static final RegistryObject<Block> TREE_MOSS = BLOCKS.register("tree_moss", () -> new VineBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.PLANT).noCollission().strength(0.1F).sound(SoundType.VINE)));
    public static final RegistryObject<RotatedPillarBlock> PINE_LOG = BLOCKS.register("pine_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<RotatedPillarBlock> PINE_WOOD = BLOCKS.register("pine_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_PINE_LOG = BLOCKS.register("stripped_pine_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_PINE_WOOD = BLOCKS.register("stripped_pine_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> PINE_PLANKS = BLOCKS.register("pine_planks", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<StairBlock> PINE_STAIRS = BLOCKS.register("pine_stairs", () -> new StairBlock(() -> PINE_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(PINE_PLANKS.get())));
    public static final RegistryObject<SlabBlock> PINE_SLAB = BLOCKS.register("pine_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<PressurePlateBlock> PINE_PRESSURE_PLATE = BLOCKS.register("pine_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, PINE_PLANKS.get().defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<WoodButtonBlock> PINE_BUTTON = BLOCKS.register("pine_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<DoorBlock> PINE_DOOR = BLOCKS.register("pine_door", () -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<TrapDoorBlock> PINE_TRAPDOOR = BLOCKS.register("pine_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, PINE_PLANKS.get().defaultMaterialColor()).strength( 3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(ModBlocks::never)));
    public static final RegistryObject<Block> PINE_CONE = BLOCKS.register("pine_cone", () -> new PineConeBlock(BlockBehaviour.Properties.of(Material.VEGETABLE, MaterialColor.WOOD).noCollission().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<FenceBlock> PINE_FENCE = BLOCKS.register("pine_fence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, PINE_PLANKS.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<FenceGateBlock> PINE_FENCE_GATE = BLOCKS.register("pine_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, PINE_PLANKS.get().defaultMaterialColor()).strength( 3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(ModBlocks::never)));
    public static final RegistryObject<ModStandingSignBlock> PINE_STANDING_SIGN = BLOCKS.register("pine_standing_sign", () -> new ModStandingSignBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), ModWoodTypes.PINE));
    public static final RegistryObject<ModWallSignBlock> PINE_WALL_SIGN = BLOCKS.register("pine_wall_sign", () -> new ModWallSignBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), ModWoodTypes.PINE));

    //Plants
    public static final RegistryObject<Block> ROSE = BLOCKS.register("rose", () -> new FlowerBlock(MobEffects.LUCK, 9, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> RED_LICHEN_COVERAGE = BLOCKS.register("red_lichen_coverage", () -> new LichenCoverageBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_ORANGE).noCollission().instabreak().sound(SoundType.AZALEA_LEAVES)));
    public static final RegistryObject<Block> GREEN_LICHEN_COVERAGE = BLOCKS.register("green_lichen_coverage", () -> new LichenCoverageBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_GREEN).noCollission().instabreak().sound(SoundType.AZALEA_LEAVES)));
    public static final RegistryObject<WaterlilyBlock> DUCKWEED = BLOCKS.register("duckweed", () -> new WaterlilyBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_GREEN).instabreak().sound(SoundType.LILY_PAD).noOcclusion()));
    public static final RegistryObject<Block> DUNE_GRASS = BLOCKS.register("dune_grass", () -> new ModTallGrassBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> WILD_WHEAT = BLOCKS.register("wild_wheat", () -> new ModTallGrassBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<DoublePlantBlock> TALL_WILD_WHEAT = BLOCKS.register("tall_wild_wheat", () -> new DoublePlantBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> SPROUTS = BLOCKS.register("sprouts", () -> new ModTallGrassBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.MOSS)));
    public static final RegistryObject<Block> HEDGEHOG_MUSHROOM = BLOCKS.register("hedgehog_mushroom", () -> new MushroomBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_BROWN).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)
            .lightLevel((p_50892_) -> 1)
            .hasPostProcess(ModBlocks::always),
            () -> TreeFeatures.HUGE_BROWN_MUSHROOM));
    public static final RegistryObject<Block> HEDGEHOG_MUSHROOM_TALL = BLOCKS.register("hedgehog_mushroom_tall", () -> new OTHTallMushroomBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_BROWN).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)
            .lightLevel((p_50892_) -> 1)
            .hasPostProcess(ModBlocks::always),
            () -> TreeFeatures.HUGE_BROWN_MUSHROOM));
    public static final RegistryObject<Block> TRAMPLED_GRASS = BLOCKS.register("trampled_grass", () -> new ModTallGrassBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS)));

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
}
