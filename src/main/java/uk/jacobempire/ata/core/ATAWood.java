package uk.jacobempire.ata.core;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.Blocks;
import net.minecraft.block.ButtonBlock;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.block.WoodType;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;
import uk.jacobempire.ata.ATA;

/**
 * Torchwood: a dark, red-brown wood that grows in the Wilds' jungle. A full set like a vanilla wood (no signs
 * or boats).
 */
public final class ATAWood {
    public static final BlockSetType TORCHWOOD_SET = BlockSetTypeBuilder.copyOf(BlockSetType.OAK).register(ATA.id("torchwood"));
    public static final WoodType TORCHWOOD_TYPE = WoodTypeBuilder.copyOf(WoodType.OAK).register(ATA.id("torchwood"), TORCHWOOD_SET);

    /** The tree a Torchwood Sapling grows into (data/ata/worldgen/configured_feature/torchwood_tree.json). */
    public static final RegistryKey<ConfiguredFeature<?, ?>> TORCHWOOD_TREE =
            RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, ATA.id("torchwood_tree"));

    private static final MapColor BARK = MapColor.TERRACOTTA_BROWN;
    private static final MapColor WOOD = MapColor.DARK_RED;

    public static Block LOG, WOOD_BLOCK, STRIPPED_LOG, STRIPPED_WOOD, PLANKS, LEAVES, SAPLING, POTTED_SAPLING,
            STAIRS, SLAB, FENCE, FENCE_GATE, DOOR, TRAPDOOR, BUTTON, PRESSURE_PLATE;

    private ATAWood() {
    }

    public static void register() {
        LOG = ATABlocks.register("torchwood_log", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)
                .mapColor(state -> state.get(PillarBlock.AXIS) == net.minecraft.util.math.Direction.Axis.Y ? WOOD : BARK)));
        WOOD_BLOCK = ATABlocks.register("torchwood_wood", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD).mapColor(BARK)));
        STRIPPED_LOG = ATABlocks.register("stripped_torchwood_log", new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_LOG).mapColor(WOOD)));
        STRIPPED_WOOD = ATABlocks.register("stripped_torchwood_wood", new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_WOOD).mapColor(WOOD)));
        PLANKS = ATABlocks.register("torchwood_planks", new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).mapColor(WOOD)));
        LEAVES = ATABlocks.register("torchwood_leaves", new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES)));
        SAPLING = ATABlocks.register("torchwood_sapling", new SaplingBlock(new SaplingGenerator() {
            @Nullable
            @Override
            protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
                return TORCHWOOD_TREE;
            }
        }, FabricBlockSettings.copyOf(Blocks.OAK_SAPLING)));
        POTTED_SAPLING = ATABlocks.registerNoItem("potted_torchwood_sapling",
                new FlowerPotBlock(SAPLING, FabricBlockSettings.copyOf(Blocks.POTTED_OAK_SAPLING)));
        STAIRS = ATABlocks.register("torchwood_stairs", new StairsBlock(PLANKS.getDefaultState(), FabricBlockSettings.copyOf(PLANKS)));
        SLAB = ATABlocks.register("torchwood_slab", new SlabBlock(FabricBlockSettings.copyOf(PLANKS)));
        FENCE = ATABlocks.register("torchwood_fence", new FenceBlock(FabricBlockSettings.copyOf(PLANKS)));
        FENCE_GATE = ATABlocks.register("torchwood_fence_gate", new FenceGateBlock(FabricBlockSettings.copyOf(PLANKS), TORCHWOOD_TYPE));
        DOOR = ATABlocks.register("torchwood_door", new DoorBlock(FabricBlockSettings.copyOf(Blocks.OAK_DOOR).mapColor(WOOD), TORCHWOOD_SET));
        TRAPDOOR = ATABlocks.register("torchwood_trapdoor", new TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.OAK_TRAPDOOR).mapColor(WOOD), TORCHWOOD_SET));
        BUTTON = ATABlocks.register("torchwood_button", new ButtonBlock(FabricBlockSettings.copyOf(Blocks.OAK_BUTTON), TORCHWOOD_SET, 30, true));
        PRESSURE_PLATE = ATABlocks.register("torchwood_pressure_plate", new PressurePlateBlock(
                PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copyOf(Blocks.OAK_PRESSURE_PLATE).mapColor(WOOD), TORCHWOOD_SET));

        for (Block b : new Block[]{LEAVES, SAPLING, POTTED_SAPLING, DOOR, TRAPDOOR}) ATABlocks.CUTOUT.add(b);

        StrippableBlockRegistry.register(LOG, STRIPPED_LOG);
        StrippableBlockRegistry.register(WOOD_BLOCK, STRIPPED_WOOD);

        FlammableBlockRegistry fire = FlammableBlockRegistry.getDefaultInstance();
        for (Block b : new Block[]{LOG, WOOD_BLOCK, STRIPPED_LOG, STRIPPED_WOOD}) fire.add(b, 5, 5);
        for (Block b : new Block[]{PLANKS, STAIRS, SLAB, FENCE, FENCE_GATE}) fire.add(b, 5, 20);
        fire.add(LEAVES, 30, 60);
    }
}
