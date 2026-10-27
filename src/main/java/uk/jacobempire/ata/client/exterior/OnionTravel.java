package uk.jacobempire.ata.client.exterior;

import dev.amble.ait.client.tardis.manager.ClientTardisManager;
import dev.amble.ait.core.tardis.Tardis;
import dev.amble.ait.core.tardis.handler.travel.TravelHandlerBase.State;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Keeps time for the Onion's takeoff and landing: how long each TARDIS has been in its current travel state,
 * counted in client ticks from when the state changed.
 */
@Environment(EnvType.CLIENT)
public final class OnionTravel {
    private record Phase(State state, int ticks) {
    }

    private static final Map<UUID, Phase> PHASES = new HashMap<>();

    private OnionTravel() {
    }

    static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.world == null) {
                PHASES.clear();
                return;
            }
            if (client.isPaused()) return;
            Set<UUID> seen = new HashSet<>();
            ClientTardisManager.getInstance().forEach(tardis -> {
                if (tardis == null) return;
                UUID id = tardis.getUuid();
                seen.add(id);
                State state = tardis.travel().getState();
                Phase phase = PHASES.get(id);
                PHASES.put(id, phase != null && phase.state() == state ? new Phase(state, phase.ticks() + 1) : new Phase(state, 0));
            });
            PHASES.keySet().retainAll(seen);
        });
    }

    /** Seconds since this TARDIS entered its current travel state. */
    static float seconds(Tardis tardis, float tickDelta) {
        Phase phase = PHASES.get(tardis.getUuid());
        if (phase == null || phase.state() != tardis.travel().getState()) return 0;
        return (phase.ticks() + tickDelta) / 20f;
    }
}
