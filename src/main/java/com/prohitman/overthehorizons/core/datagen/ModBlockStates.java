package com.prohitman.overthehorizons.core.datagen;

import com.prohitman.overthehorizons.OverTheHorizonsMod;
import com.prohitman.overthehorizons.common.blocks.ModStandingSignBlock;
import com.prohitman.overthehorizons.common.blocks.ModWallSignBlock;
import com.prohitman.overthehorizons.core.init.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.StartupMessageManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStates extends BlockStateProvider {

    public ModBlockStates(PackOutput gen, ExistingFileHelper exFileHelper) {
        super(gen, OverTheHorizonsMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.RED_LICHENSTONE.get(), models().cubeBottomTop("red_lichenstone",modLoc("block/red_lichenstone_side"), mcLoc("block/stone"), modLoc("block/red_lichenstone_top")));
        simpleBlock(ModBlocks.GREEN_LICHENSTONE.get(), models().cubeBottomTop("green_lichenstone", modLoc("block/green_lichenstone_side"), mcLoc("block/stone"), modLoc("block/green_lichenstone_top")));
        simpleBlock(ModBlocks.RED_LICHEN_COVERAGE.get(), models().cross("red_lichen_coverage", modLoc("block/red_lichen_coverage")).renderType("cutout_mipped"));
        simpleBlock(ModBlocks.GREEN_LICHEN_COVERAGE.get(), models().cross("green_lichen_coverage", modLoc("block/green_lichen_coverage")).renderType("cutout_mipped"));
        simpleBlock(ModBlocks.PEBBLES.get(), models().cubeColumn("pebbles", modLoc("block/pebbles_side"), modLoc("block/pebbles_top")));
        simpleBlock(ModBlocks.ARID_SOIL.get(), models().cubeAll("arid_soil", modLoc("block/arid_soil")));
        simpleBlock(ModBlocks.PACKED_ROOTS.get(), models().cubeAll("packed_roots", modLoc("block/packed_roots")));
        simpleBlock(ModBlocks.ROSE.get(), models().cross("rose", modLoc("block/rose")).renderType("cutout_mipped"));
        simpleBlock(ModBlocks.SLATE_BRICKS.get(), models().cubeAll("slate_bricks", modLoc("block/slate_bricks")));
        simpleBlock(ModBlocks.SLATE_TILES.get(), models().cubeAll("slate_tiles", modLoc("block/slate_tiles")));
        simpleBlock(ModBlocks.SLATE.get(), models().cubeAll("slate", modLoc("block/slate")));
        simpleBlock(ModBlocks.ERODED_SLATE.get(), models().cubeAll("eroded_slate", modLoc("block/eroded_slate")));

        simpleBlock(ModBlocks.HEDGEHOG_MUSHROOM_TALL.get(), models().cross("hedgehog_mushroom_tall", modLoc("block/hedgehog_mushroom_tall")).renderType("cutout_mipped"));
        simpleBlock(ModBlocks.HEDGEHOG_MUSHROOM.get(), models().cross("hedgehog_mushroom", modLoc("block/hedgehog_mushroom")).renderType("cutout_mipped"));

        simpleBlock(ModBlocks.DUNE_GRASS.get(), models().cross("dune_grass", modLoc("block/dune_grass")).renderType("cutout_mipped"));
        simpleBlock(ModBlocks.WILD_WHEAT.get(), models().cross("wild_wheat", modLoc("block/wild_wheat")).renderType("cutout_mipped"));
        simpleBlock(ModBlocks.SPROUTS.get(), models().cross("sprouts", modLoc("block/sprouts")).renderType("cutout_mipped"));
        simpleBlock(ModBlocks.TRAMPLED_GRASS.get(), models().singleTexture("trampled_grass", mcLoc("block/tinted_cross"), "cross", modLoc("block/trampled_grass")).renderType("cutout_mipped"));
        createDoublePlantBlock(ModBlocks.LAND_REEDS);
        createDoublePlantBlock(ModBlocks.WATER_REEDS);
        createDoublePlantBlock(ModBlocks.TALL_WILD_WHEAT);
        createCarpetBlock(ModBlocks.FALLEN_LEAVES);

        slabBlock((SlabBlock) ModBlocks.ADOBE_SLAB.get(), modLoc("block/adobe"), modLoc("block/adobe"));
        stairsBlock((StairBlock) ModBlocks.ADOBE_STAIRS.get(), modLoc("block/adobe"));
        slabBlock((SlabBlock) ModBlocks.ADOBE_BRICKS_SLAB.get(), modLoc("block/adobe_bricks"), modLoc("block/adobe_bricks"));
        stairsBlock((StairBlock) ModBlocks.ADOBE_BRICKS_STAIRS.get(), modLoc("block/adobe_bricks"));
        slabBlock((SlabBlock) ModBlocks.SMOOTH_ADOBE_SLAB.get(), modLoc("block/smooth_adobe"), modLoc("block/smooth_adobe"));
        stairsBlock((StairBlock) ModBlocks.SMOOTH_ADOBE_STAIRS.get(), modLoc("block/smooth_adobe"));

        simpleBlock(ModBlocks.PINE_PLANKS.get(), models().cubeAll("pine_planks", modLoc("block/pine_planks")));
        trapdoorBlockWithRenderType((TrapDoorBlock) ModBlocks.PINE_TRAPDOOR.get(), modLoc("block/pine_trapdoor"), true, "cutout_mipped");
        doorBlockWithRenderType((DoorBlock) ModBlocks.PINE_DOOR.get(), modLoc("block/pine_door_bottom"), modLoc("block/pine_door_top"), "cutout_mipped");
        buttonBlock((ButtonBlock) ModBlocks.PINE_BUTTON.get(), modLoc("block/pine_planks"));
        slabBlock((SlabBlock) ModBlocks.PINE_SLAB.get(), modLoc("block/pine_planks"), modLoc("block/pine_planks"));
        stairsBlock((StairBlock) ModBlocks.PINE_STAIRS.get(), modLoc("block/pine_planks"));
        pressurePlateBlock((PressurePlateBlock) ModBlocks.PINE_PRESSURE_PLATE.get(), modLoc("block/pine_planks"));
        logBlock((RotatedPillarBlock) ModBlocks.PINE_LOG.get());
        logBlock((RotatedPillarBlock) ModBlocks.STRIPPED_PINE_LOG.get());
        simpleBlock(ModBlocks.PINE_WOOD.get(), models().cubeAll("pine_wood", modLoc("block/pine_log")));
        simpleBlock(ModBlocks.STRIPPED_PINE_WOOD.get(), models().cubeAll("stripped_pine_wood", modLoc("block/stripped_pine_log")));
        simpleBlock(ModBlocks.PINE_CONE.get(), models().cross("pine_cone", modLoc("block/pine_cone")).renderType("cutout_mipped"));
        fenceBlock((FenceBlock) ModBlocks.PINE_FENCE.get(), modLoc("block/pine_planks"));
        fenceGateBlock((FenceGateBlock) ModBlocks.PINE_FENCE_GATE.get(), modLoc("block/pine_planks"));
        signBlock((ModStandingSignBlock)ModBlocks.PINE_STANDING_SIGN.get(), (ModWallSignBlock) ModBlocks.PINE_WALL_SIGN.get(), modLoc("block/pine_planks"));
        hangingSign(ModBlocks.PINE_HANGING_SIGN, ModBlocks.PINE_WALL_HANGING_SIGN, modLoc("block/stripped_pine_log"));
        createBushBlock(ModBlocks.PINE_SAPLING);
    }

    private void createBushBlock(RegistryObject<Block> block) {
        simpleBlock(block.get(), models().cross(block.getId().getPath(),
                modLoc("block/" + block.getId().getPath())).renderType("cutout_mipped"));
    }

    public void hangingSign(RegistryObject<Block> hangingSignBlock, RegistryObject<Block> wallHangingSignBlock, ResourceLocation texture){
        ModelFile sign = models().sign(hangingSignBlock.getId().getPath(), texture);
        hangingSign(hangingSignBlock, wallHangingSignBlock, sign);
    }

    public void hangingSign(RegistryObject<Block> hangingSignBlock, RegistryObject<Block> wallHangingSignBlock, ModelFile sign) {
        simpleBlock(hangingSignBlock.get(), sign);
        simpleBlock(wallHangingSignBlock.get(), sign);
    }

    private void createDoublePlantBlock(RegistryObject<Block> block) {
        createDoublePlantBlock(block,
                models().cross(block.getId().getPath() + "_top",
                        modLoc("block/" + block.getId().getPath() + "_top")).renderType("cutout_mipped"),
                models().cross(block.getId().getPath() + "_bottom",
                        modLoc("block/" + block.getId().getPath() + "_bottom")).renderType("cutout_mipped"));
    }

    private void createDoublePlantBlock(RegistryObject<Block> block, ModelFile upper, ModelFile bottom) {
        getVariantBuilder(block.get()).forAllStatesExcept(state -> {
            ModelFile model = bottom;
            if (state.getValue(DoublePlantBlock.HALF) == DoubleBlockHalf.UPPER) {
                model = upper;
            }
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .uvLock(true)
                    .build();
        });
    }


    private void createCarpetBlock(RegistryObject<Block> block){
        simpleBlock(block.get(), models().withExistingParent(block.getId().getPath(), mcLoc("block/carpet")).renderType("cutout_mipped").texture("wool", modLoc("block/fallen_leaves")));
    }

//    private void createVineBlock(RegistryObject<Block> block){
//        ModelFile model = models().withExistingParent(block.getId().getPath(), modLoc("block/" + block.getId().getPath()))
//                .ao(false).renderType("cutout_mipped")
//                .texture("particle", modLoc("block/" + block.getId().getPath()))
//                .texture("vine", modLoc("block/" + block.getId().getPath()))
//                .element()
//                .from( 0, 0, 0.8F)
//                .to(16f,16f,0.8f)
//                .shade(false)
//                .face(Direction.NORTH)
//                .uvs(16F, 0F, 0F, 16F).texture("#vine").tintindex(0).end()
//                .face(Direction.SOUTH)
//                .uvs(0F, 0F, 16F, 16F).texture("#vine").tintindex(0).end()
//                .end();
//        MultiPartBlockStateBuilder builder = getMultipartBuilder(block.get())
//                .part().modelFile(model).addModel().end();
//        PipeBlock.PROPERTY_BY_DIRECTION.entrySet().forEach(e -> {
//            Direction dir = e.getKey();
//            /*if (dir.getAxis().isHorizontal()) {
//                builder.part().modelFile(model).rotationY(dir.getAxis() == Direction.Axis.X ? 90 : 0).addModel()
//                        .condition(e.getValue(), true).end()
//                        .part().modelFile(model).rotationY(dir == Direction.WEST ? 270 : dir == Direction.SOUTH ? 90 : 0).addModel()
//                        .condition(e.getValue(), false);
//            }*/
//
//            if (dir != Direction.DOWN) {
//                builder.part().modelFile(model).rotationX(dir == Direction.UP ? 270 : 0).uvLock(true).addModel()
//                        .condition(e.getValue(), true).end()
//                        .part().modelFile(model).rotationY(dir == Direction.WEST ? 270 : dir == Direction.SOUTH ? 180 : dir == Direction.EAST ? 90 : 0).uvLock(true).addModel()
//                        .condition(e.getValue(), false);
//            }
//        });
//    }
}
