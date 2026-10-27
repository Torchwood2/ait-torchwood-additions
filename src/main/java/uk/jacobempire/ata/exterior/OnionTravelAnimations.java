package uk.jacobempire.ata.exterior;

import dev.amble.ait.core.tardis.Tardis;
import dev.amble.ait.core.tardis.handler.travel.TravelHandlerBase.State;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import uk.jacobempire.ata.ATA;

/**
 * The Onion's travel animations (data/ata/fx/animation). They keep the Onion fully there for exactly as long as
 * its takeoff (10.5 s) and landing (12 s) last; the model plays the actual movement.
 */
public final class OnionTravelAnimations {
    public static final Identifier TAKEOFF = ATA.id("onion_takeoff");
    public static final Identifier LANDING = ATA.id("onion_landing");

    private OnionTravelAnimations() {
    }

    /** The animation an Onion must use for this state, or null if the TARDIS isn't an Onion. */
    @Nullable
    public static Identifier forced(@Nullable Tardis tardis, State state) {
        if (tardis == null || tardis.getExterior() == null) return null;
        if (!ATAExteriors.ONION.categoryId().equals(tardis.getExterior().getCategory().id())) return null;
        return switch (state) {
            case DEMAT -> TAKEOFF;
            case MAT -> LANDING;
            default -> null;
        };
    }
}
