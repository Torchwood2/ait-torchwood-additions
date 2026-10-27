package uk.jacobempire.ata.core;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.TrapdoorBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import uk.jacobempire.ata.ATA;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Every block in the mod. Each roundel comes as {@code <name>_roundel} (one big roundel),
 * {@code <name>_roundel_half} (half roundels top and bottom, for stacking into walls), and stairs and a
 * slab of the full roundel. They take
 * their hardness, sound and tool from the vanilla block they're made of.
 */
public final class ATABlocks {
    /** Roundel name to the vanilla block it's made from. */
    public static final Map<String, Block> ROUNDEL_MATERIALS = new LinkedHashMap<>();

    static {
        for (String colour : List.of("white", "light_gray", "gray", "black", "brown", "red", "orange", "yellow", "lime",
                "green", "cyan", "light_blue", "blue", "purple", "magenta", "pink")) {
            ROUNDEL_MATERIALS.put(colour + "_concrete", Registries.BLOCK.get(new net.minecraft.util.Identifier(colour + "_concrete")));
        }
        ROUNDEL_MATERIALS.put("quartz", Blocks.QUARTZ_BLOCK);
        ROUNDEL_MATERIALS.put("ambquartz", Blocks.QUARTZ_BLOCK);
        ROUNDEL_MATERIALS.put("bluequartz", Blocks.QUARTZ_BLOCK);
        ROUNDEL_MATERIALS.put("crimquartz", Blocks.QUARTZ_BLOCK);
        ROUNDEL_MATERIALS.put("obsiquartz", Blocks.QUARTZ_BLOCK);
        ROUNDEL_MATERIALS.put("verdquartz", Blocks.QUARTZ_BLOCK);
        ROUNDEL_MATERIALS.put("vioquartz", Blocks.QUARTZ_BLOCK);
        ROUNDEL_MATERIALS.put("copper", Blocks.COPPER_BLOCK);
        ROUNDEL_MATERIALS.put("exposed_copper", Blocks.EXPOSED_COPPER);
        ROUNDEL_MATERIALS.put("weathered_copper", Blocks.WEATHERED_COPPER);
        ROUNDEL_MATERIALS.put("oxidized_copper", Blocks.OXIDIZED_COPPER);
        ROUNDEL_MATERIALS.put("dirt", Blocks.DIRT);
        ROUNDEL_MATERIALS.put("moss", Blocks.MOSS_BLOCK);
        ROUNDEL_MATERIALS.put("end_stone_bricks", Blocks.END_STONE_BRICKS);
        ROUNDEL_MATERIALS.put("sandstone", Blocks.SANDSTONE);
        ROUNDEL_MATERIALS.put("polished_andesite", Blocks.POLISHED_ANDESITE);
        ROUNDEL_MATERIALS.put("polished_diorite", Blocks.POLISHED_DIORITE);
        ROUNDEL_MATERIALS.put("polished_granite", Blocks.POLISHED_GRANITE);
        ROUNDEL_MATERIALS.put("polished_deepslate", Blocks.POLISHED_DEEPSLATE);
        for (String wood : List.of("oak", "spruce", "birch", "jungle", "acacia", "dark_oak", "mangrove", "cherry")) {
            ROUNDEL_MATERIALS.put("stripped_" + wood + "_log", Registries.BLOCK.get(new net.minecraft.util.Identifier("stripped_" + wood + "_log")));
        }
        ROUNDEL_MATERIALS.put("hartnell", Blocks.WHITE_CONCRETE);
    }

    /** Everything registered, in creative tab order. */
    public static final List<Block> ALL = new ArrayList<>();
    /** Blocks with see-through gaps (the grates). */
    public static final List<Block> CUTOUT = new ArrayList<>();

    /** Grate trapdoors open by hand, with iron trapdoor sounds. */
    private static final BlockSetType GRATE = BlockSetTypeBuilder.copyOf(BlockSetType.IRON).openableByHand(true)
            .register(ATA.id("grate"));

    public static Block HARTNELL_WALL;
    public static Block ATRIUM_BLOCK;
    public static Block ATRIUM_ORE;
    public static Block DEEPSLATE_ATRIUM_ORE;

    private ATABlocks() {
    }

    public static void register() {
        for (Map.Entry<String, Block> roundel : ROUNDEL_MATERIALS.entrySet()) {
            Block full = register(roundel.getKey() + "_roundel", new Block(copyOf(roundel.getValue())));
            register(roundel.getKey() + "_roundel_half", new Block(copyOf(roundel.getValue())));
            register(roundel.getKey() + "_roundel_stairs", new StairsBlock(full.getDefaultState(), copyOf(roundel.getValue())));
            register(roundel.getKey() + "_roundel_slab", new SlabBlock(copyOf(roundel.getValue())));
        }
        HARTNELL_WALL = register("hartnell_wall", new Block(FabricBlockSettings.copyOf(Blocks.WHITE_CONCRETE)));

        for (String grate : List.of("grate_block", "thick_grate_block", "rusty_grate_block", "rusty_thick_grate_block")) {
            CUTOUT.add(register(grate, new Block(FabricBlockSettings.copyOf(Blocks.IRON_BARS).nonOpaque()
                    .allowsSpawning((state, world, pos, type) -> false)
                    .solidBlock((state, world, pos) -> false)
                    .suffocates((state, world, pos) -> false)
                    .blockVision((state, world, pos) -> false))));
        }

        for (String grate : List.of("grate", "thick_grate", "rusty_grate", "rusty_thick_grate")) {
            CUTOUT.add(register(grate + "_trapdoor", new TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.IRON_TRAPDOOR)
                    .allowsSpawning((state, world, pos, type) -> false), GRATE)));
        }

        ATRIUM_BLOCK = register("atrium_block", new Block(FabricBlockSettings.copyOf(Blocks.GOLD_BLOCK)));
        ATRIUM_ORE = register("atrium_ore", new ExperienceDroppingBlock(
                FabricBlockSettings.copyOf(Blocks.GOLD_ORE).requiresTool(), UniformIntProvider.create(2, 5)));
        DEEPSLATE_ATRIUM_ORE = register("deepslate_atrium_ore", new ExperienceDroppingBlock(
                FabricBlockSettings.copyOf(Blocks.DEEPSLATE_GOLD_ORE).sounds(BlockSoundGroup.DEEPSLATE).requiresTool(),
                UniformIntProvider.create(2, 5)));
    }

    /**
     * The vanilla block's settings, but with a fixed map colour: logs pick theirs from their AXIS
     * property, which a plain block doesn't have (that crashed on startup).
     */
    private static FabricBlockSettings copyOf(Block base) {
        return FabricBlockSettings.copyOf(base).mapColor(base.getDefaultMapColor());
    }

    private static Block register(String name, Block block) {
        Registry.register(Registries.BLOCK, ATA.id(name), block);
        Registry.register(Registries.ITEM, ATA.id(name), new BlockItem(block, new Item.Settings()));
        ALL.add(block);
        return block;
    }
}
