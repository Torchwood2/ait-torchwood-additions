package uk.jacobempire.ata;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.jacobempire.ata.exterior.ATAExteriors;
import uk.jacobempire.ata.compat.GallifreyCompat;
import uk.jacobempire.ata.core.ATABlocks;
import uk.jacobempire.ata.core.ATAWood;
import uk.jacobempire.ata.core.ATARift;
import uk.jacobempire.ata.chair.ATAChairs;
import uk.jacobempire.ata.gun.ATAGuns;
import uk.jacobempire.ata.core.ATAFuel;
import uk.jacobempire.ata.core.ATARewards;
import uk.jacobempire.ata.core.ATAItems;
import uk.jacobempire.ata.core.ATASounds;
import uk.jacobempire.ata.world.ATAWorldGen;

/**
 * ATA, an Adventures in Time addon.
 * <p>
 * Blocks, items, sounds and ore generation are registered here. The sonics, desktops (interiors) and
 * exteriors need no code: AIT loads them from data/ata/sonic, data/ata/desktop and data/ata/exterior,
 * and the TARDIS sounds from data/ata/fx.
 */
public class ATA implements ModInitializer {
    public static final String MOD_ID = "ata";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static ItemGroup BLOCKS_GROUP;
    public static ItemGroup ITEM_GROUP;

    @Override
    public void onInitialize() {
        ATABlocks.register();
        ATAWood.register();
        ATARift.register();
        ATAChairs.register();
        ATAGuns.register();
        ATAItems.register();
        ATASounds.register();
        ATAWorldGen.register();
        ATAFuel.register();
        ATARewards.register();
        ATAExteriors.register();
        GallifreyCompat.register();

        // Two tabs: building blocks, and everything you hold (atrium, Rift gear, guns).
        BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP, id("blocks"), FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup.ata.blocks"))
                .icon(() -> new ItemStack(ATABlocks.ALL.isEmpty() ? ATAItems.ATRIUM : ATABlocks.ALL.get(0).asItem()))
                .entries((context, entries) -> ATABlocks.ALL.forEach(entries::add))
                .build());
        ITEM_GROUP = Registry.register(Registries.ITEM_GROUP, id("item_group"), FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup.ata.item_group"))
                .icon(() -> new ItemStack(ATAItems.ENERGIZED_ATRIUM))
                .entries((context, entries) -> ATAItems.ALL.forEach(item -> entries.add(item.getDefaultStack())))
                .build());

        LOGGER.info("ATA loaded");
    }

    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }
}
