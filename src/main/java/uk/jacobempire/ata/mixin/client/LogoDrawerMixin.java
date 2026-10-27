package uk.jacobempire.ata.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.amble.ait.client.AITModClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.LogoDrawer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.jacobempire.ata.ATA;

/**
 * Swaps AIT's title screen logo for the AIT: Torchwood Additions one. Only while AIT's custom menu is on
 * (so turning it off still gives the vanilla logo).
 * <p>
 * Priority 150: when several mods replace the logo this way, the lowest number runs first and cancels the
 * rest. Gallifrey uses 100, so with Gallifrey installed its logo shows instead.
 */
@Mixin(value = LogoDrawer.class, priority = 150)
public class LogoDrawerMixin {
    @Unique
    private static final Identifier ATA_LOGO = ATA.id("textures/gui/title/logo.png");
    @Unique
    private static final int WIDTH = 300;
    @Unique
    private static final int HEIGHT = 48;

    @Inject(method = "draw(Lnet/minecraft/client/gui/DrawContext;IF)V", at = @At("HEAD"), cancellable = true)
    private void ata$logo(DrawContext context, int screenWidth, float alpha, CallbackInfo ci) {
        if (!AITModClient.CONFIG.customMenu) return;
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        context.setShaderColor(1f, 1f, 1f, alpha);
        context.drawTexture(ATA_LOGO, (screenWidth - WIDTH) / 2, 18, 0, 0, WIDTH, HEIGHT, WIDTH, HEIGHT);
        context.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.disableBlend();
        ci.cancel();
    }
}
