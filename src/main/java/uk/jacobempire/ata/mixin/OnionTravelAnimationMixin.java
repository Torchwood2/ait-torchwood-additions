package uk.jacobempire.ata.mixin;

import dev.amble.ait.api.tardis.TardisComponent;
import dev.amble.ait.core.tardis.Tardis;
import dev.amble.ait.core.tardis.handler.travel.AnimatedTravelHandler;
import dev.amble.ait.core.tardis.handler.travel.TravelHandlerBase;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.jacobempire.ata.awt.OnionTravelAnimations;

/** An Onion always takes off and lands with its own animations, whatever the TARDIS has picked. */
@Mixin(value = AnimatedTravelHandler.class, remap = false)
public abstract class OnionTravelAnimationMixin {
    @Inject(method = "getAnimationIdFor", at = @At("RETURN"), cancellable = true)
    private void ata$onionAnimation(TravelHandlerBase.State state, CallbackInfoReturnable<Identifier> cir) {
        Tardis tardis = ((TardisComponent) (Object) this).tardis();
        Identifier forced = OnionTravelAnimations.forced(tardis, state);
        if (forced != null) cir.setReturnValue(forced);
    }
}
