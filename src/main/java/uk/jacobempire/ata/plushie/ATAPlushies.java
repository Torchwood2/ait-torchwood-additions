package uk.jacobempire.ata.plushie;

import dev.amble.lib.block.ABlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import uk.jacobempire.ata.ATA;

import java.util.ArrayList;
import java.util.List;

/** The ATA crew plushies. Each name has a matching model and texture in assets/ata. */
public final class ATAPlushies {
    public static final List<String> NAMES = List.of("diceline", "diceline_purple", "torchwood_one", "bluenbygamer", "samtiz");
    public static final List<Block> BLOCKS = new ArrayList<>();
    public static final List<Item> ITEMS = new ArrayList<>();
    public static BlockEntityType<ATAPlushieBlockEntity> BLOCK_ENTITY;

    private ATAPlushies() {
    }

    public static void register() {
        for (String name : NAMES) {
            Block block = Registry.register(Registries.BLOCK, ATA.id(name + "_plushie"),
                    new ATAPlushieBlock(new ABlockSettings(), name));
            Item item = Registry.register(Registries.ITEM, ATA.id(name + "_plushie"),
                    new BlockItem(block, new Item.Settings()));
            BLOCKS.add(block);
            ITEMS.add(item);
        }
        BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, ATA.id("plushie"),
                FabricBlockEntityTypeBuilder.create(ATAPlushieBlockEntity::new, BLOCKS.toArray(Block[]::new)).build());
    }
}
