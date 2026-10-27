package uk.jacobempire.ata.mixin;

import dev.amble.ait.core.tardis.Tardis;
import dev.amble.ait.core.tardis.animation.v2.AnimationHolder;
import dev.amble.ait.core.tardis.animation.v2.TardisAnimation;
import dev.amble.ait.core.tardis.animation.v2.TardisAnimationMap;
import dev.amble.ait.core.tardis.animation.v2.datapack.TardisAnimationRegistry;
import dev.amble.ait.core.tardis.handler.travel.TravelHandlerBase;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * AIT works out a TARDIS's takeoff and landing animations once and keeps them. Swapping to or from the Onion
 * afterwards would leave the old ones in place, so check them again each time the TARDIS starts to travel.
 */
@Mixin(value = AnimationHolder.class, remap = false)
public abstract class AnimationHolderMixin {
    @Shadow @Final protected TardisAnimationMap map;

    @Inject(method = "onStateChange", at = @At("HEAD"))
    private void ata$refreshAnimation(TravelHandlerBase.State state, CallbackInfo ci) {
        if (state != TravelHandlerBase.State.DEMAT && state != TravelHandlerBase.State.MAT) return;
        AnimationHolder self = (AnimationHolder) (Object) this;
        if (!self.isLinked()) return;
        Tardis tardis = self.tardis().get();
        if (tardis == null) return;
        Identifier wanted = tardis.travel().getAnimationIdFor(state);
        if (wanted == null) return;
        TardisAnimation have = map.get(state);
        if (have == null || !have.id().equals(wanted)) {
            map.put(state, TardisAnimationRegistry.getInstance().instantiate(wanted));
        }
    }
}
