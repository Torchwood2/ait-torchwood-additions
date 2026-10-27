package uk.jacobempire.ata.core;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Rarity;
import uk.jacobempire.ata.ATA;

import java.util.ArrayList;
import java.util.List;

/** The atrium materials. Sonics aren't items of their own: they're AIT sonic designs (data/ata/sonic). */
public final class ATAItems {
    public static final List<Item> ALL = new ArrayList<>();

    public static final Item ATRIUM = register("atrium", new Item(new Item.Settings()));
    public static final Item ATRIUM_FUEL = register("atrium_fuel", new Item(new Item.Settings()));
    public static final Item ENERGIZED_ATRIUM = register("energized_atrium", new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item ATRIUM_CORE = register("atrium_core", new Item(new Item.Settings().maxCount(16).rarity(Rarity.RARE)));

    private ATAItems() {
    }

    public static void register() {
        // Loading the class registers the items.
    }

    private static Item register(String name, Item item) {
        ALL.add(Registry.register(Registries.ITEM, ATA.id(name), item));
        return item;
    }
}
