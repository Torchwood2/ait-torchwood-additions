package uk.jacobempire.ata.core;

import dev.amble.ait.core.AITItems;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import uk.jacobempire.ata.ATA;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * The devs get a Skeleton Key, a TARDIS and a Sonic Screwdriver the first time they join each world (or server).
 * A tag on the player remembers it, so they're only given once per world.
 */
public final class ATARewards {
    private static final Set<UUID> DEVS = Set.of(
            UUID.fromString("c36e27d8-1658-4465-b8ed-29ff2bc622ec"),   // DiceLine1
            UUID.fromString("d21bc86a-14ce-4d4b-bc15-62f6a4c58fe3"));  // BluEnbyGamer

    private static final String GIVEN_TAG = "ata_dev_kit";

    private ATARewards() {
    }

    public static void register() {
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayerEntity player = handler.getPlayer();
            server.execute(() -> giveKit(player));
        });
    }

    private static void giveKit(ServerPlayerEntity player) {
        if (!DEVS.contains(player.getUuid()) || player.getCommandTags().contains(GIVEN_TAG)) return;
        for (Item item : List.of(AITItems.SKELETON_KEY, AITItems.TARDIS_ITEM, AITItems.SONIC_SCREWDRIVER)) {
            ItemStack stack = item.getDefaultStack();
            if (!player.getInventory().insertStack(stack)) player.dropItem(stack, false);
        }
        player.addCommandTag(GIVEN_TAG);
        player.sendMessage(Text.literal("Welcome back! Here's your TARDIS kit.").formatted(Formatting.AQUA), false);
        ATA.LOGGER.info("Gave {} the dev TARDIS kit", player.getName().getString());
    }
}
