package uk.jacobempire.ata.core;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.MapColor;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import uk.jacobempire.ata.ATA;

/**
 * Rift: a crystal found only in the Wilds. Its shards make armour and tools a step above iron (diamond-level
 * mining, a little less protection than diamond, but easier to enchant).
 */
public final class ATARift {
    public static Block RIFT_ORE, DEEPSLATE_RIFT_ORE, RIFT_BLOCK;

    public static final Item RIFT_SHARD = item("rift_shard", new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));

    public static final ToolMaterial TOOLS = new ToolMaterial() {
        @Override public int getDurability() { return 1200; }
        @Override public float getMiningSpeedMultiplier() { return 8.5f; }
        @Override public float getAttackDamage() { return 3.0f; }
        @Override public int getMiningLevel() { return 3; }
        @Override public int getEnchantability() { return 18; }
        @Override public Ingredient getRepairIngredient() { return Ingredient.ofItems(RIFT_SHARD); }
    };

    public static final ArmorMaterial ARMOUR = new ArmorMaterial() {
        /** Durability per piece, as a multiple of vanilla's base (iron is 15, diamond 33). */
        private static final int MULTIPLIER = 28;

        @Override
        public int getDurability(ArmorItem.Type type) {
            int base = switch (type) {
                case HELMET -> 11;
                case CHESTPLATE -> 16;
                case LEGGINGS -> 15;
                case BOOTS -> 13;
            };
            return base * MULTIPLIER;
        }

        @Override
        public int getProtection(ArmorItem.Type type) {
            return switch (type) {
                case HELMET -> 3;
                case CHESTPLATE -> 7;
                case LEGGINGS -> 6;
                case BOOTS -> 3;
            };
        }

        @Override public int getEnchantability() { return 18; }
        @Override public SoundEvent getEquipSound() { return SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND; }
        @Override public Ingredient getRepairIngredient() { return Ingredient.ofItems(RIFT_SHARD); }
        /** Textures at assets/minecraft/textures/models/armor/ata_rift_layer_1.png and _2. */
        @Override public String getName() { return "ata_rift"; }
        @Override public float getToughness() { return 1.0f; }
        @Override public float getKnockbackResistance() { return 0.0f; }
    };

    public static final Item RIFT_SWORD = item("rift_sword", new SwordItem(TOOLS, 3, -2.4f, new Item.Settings()));
    public static final Item RIFT_PICKAXE = item("rift_pickaxe", new PickaxeItem(TOOLS, 1, -2.8f, new Item.Settings()));
    public static final Item RIFT_AXE = item("rift_axe", new AxeItem(TOOLS, 5.0f, -3.0f, new Item.Settings()));
    public static final Item RIFT_SHOVEL = item("rift_shovel", new ShovelItem(TOOLS, 1.5f, -3.0f, new Item.Settings()));
    public static final Item RIFT_HOE = item("rift_hoe", new HoeItem(TOOLS, -3, 0.0f, new Item.Settings()));
    public static final Item RIFT_HELMET = item("rift_helmet", new ArmorItem(ARMOUR, ArmorItem.Type.HELMET, new Item.Settings()));
    public static final Item RIFT_CHESTPLATE = item("rift_chestplate", new ArmorItem(ARMOUR, ArmorItem.Type.CHESTPLATE, new Item.Settings()));
    public static final Item RIFT_LEGGINGS = item("rift_leggings", new ArmorItem(ARMOUR, ArmorItem.Type.LEGGINGS, new Item.Settings()));
    public static final Item RIFT_BOOTS = item("rift_boots", new ArmorItem(ARMOUR, ArmorItem.Type.BOOTS, new Item.Settings()));

    private ATARift() {
    }

    public static void register() {
        RIFT_ORE = ATABlocks.register("rift_ore", new ExperienceDroppingBlock(
                FabricBlockSettings.copyOf(Blocks.DIAMOND_ORE).mapColor(MapColor.CYAN).luminance(4).requiresTool(),
                UniformIntProvider.create(3, 7)));
        DEEPSLATE_RIFT_ORE = ATABlocks.register("deepslate_rift_ore", new ExperienceDroppingBlock(
                FabricBlockSettings.copyOf(Blocks.DEEPSLATE_DIAMOND_ORE).mapColor(MapColor.CYAN).luminance(4)
                        .sounds(BlockSoundGroup.DEEPSLATE).requiresTool(),
                UniformIntProvider.create(3, 7)));
        RIFT_BLOCK = ATABlocks.register("rift_block", new Block(FabricBlockSettings.copyOf(Blocks.DIAMOND_BLOCK)
                .mapColor(MapColor.CYAN).luminance(8)));
    }

    private static Item item(String name, Item item) {
        ATAItems.ALL.add(Registry.register(Registries.ITEM, ATA.id(name), item));
        return item;
    }
}
