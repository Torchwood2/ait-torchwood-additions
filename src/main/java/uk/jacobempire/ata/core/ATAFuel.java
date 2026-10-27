package uk.jacobempire.ata.core;

import dev.amble.ait.core.blockentities.ConsoleBlockEntity;
import dev.amble.ait.core.tardis.Tardis;
import dev.amble.ait.core.tardis.handler.FuelHandler;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;

import java.util.Map;

/**
 * Atrium as TARDIS fuel: use Atrium Fuel, Energized Atrium or an Atrium Core on a console to top up
 * its artron. A TARDIS holds {@value FuelHandler#TARDIS_MAX_FUEL} artron.
 */
public final class ATAFuel {
    /** How much artron each item gives. */
    private static final Map<Item, Double> FUEL = Map.of(
            ATAItems.ATRIUM_FUEL, 5_000.0,
            ATAItems.ENERGIZED_ATRIUM, 15_000.0,
            ATAItems.ATRIUM_CORE, FuelHandler.TARDIS_MAX_FUEL);

    private ATAFuel() {
    }

    public static void register() {
        UseBlockCallback.EVENT.register((player, world, hand, hit) -> {
            ItemStack stack = player.getStackInHand(hand);
            Double amount = FUEL.get(stack.getItem());
            if (amount == null || player.isSpectator()) return ActionResult.PASS;
            if (!(world.getBlockEntity(hit.getBlockPos()) instanceof ConsoleBlockEntity console)) return ActionResult.PASS;
            if (console.tardis() == null || console.tardis().isEmpty()) return ActionResult.PASS;
            if (world.isClient) return ActionResult.SUCCESS;

            Tardis tardis = console.tardis().get();
            FuelHandler fuel = tardis.fuel();
            if (fuel.getCurrentFuel() >= fuel.getMaxFuel()) {
                player.sendMessage(Text.translatable("message.ata.fuel_full").formatted(Formatting.GOLD), true);
                return ActionResult.SUCCESS;
            }

            double before = fuel.getCurrentFuel();
            fuel.addFuel(amount);
            double added = fuel.getCurrentFuel() - before;
            if (!player.getAbilities().creativeMode) stack.decrement(1);

            BlockPos pos = hit.getBlockPos();
            world.playSound(null, pos, ATASounds.BLOOP, SoundCategory.BLOCKS, 1.0f, 1.0f);
            if (world instanceof ServerWorld server) {
                server.spawnParticles(ParticleTypes.ELECTRIC_SPARK, pos.getX() + 0.5, pos.getY() + 1.2, pos.getZ() + 0.5,
                        20, 0.4, 0.3, 0.4, 0.05);
            }
            int percent = (int) Math.round(fuel.getCurrentFuel() / fuel.getMaxFuel() * 100);
            player.sendMessage(Text.translatable("message.ata.fuel_added", Math.round(added), percent)
                    .formatted(Formatting.YELLOW), true);
            return ActionResult.SUCCESS;
        });
    }
}
