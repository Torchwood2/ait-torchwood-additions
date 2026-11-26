package uk.jacobempire.ata.client;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.amble.ait.client.screens.widget.AITLogoDrawer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.LogoDrawer;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.util.Identifier;
import uk.jacobempire.ata.ATA;
import uk.jacobempire.ata.compat.GallifreyCompat;
import uk.jacobempire.ata.mixin.client.TitleScreenAccessor;

/**
 * Swaps AIT's title screen logo for the AIT: Torchwood Additions one. AIT only uses its own logo while its
 * "ait:menu" resource pack is on, so turning that off still gives the vanilla logo. With Gallifrey installed,
 * Gallifrey's logo takes priority and this stays out of the way.
 */
@Environment(EnvType.CLIENT)
public class ATALogoDrawer extends LogoDrawer {
    private static final Identifier ATA_LOGO = ATA.id("textures/gui/title/logo.png");
    private static final int WIDTH = 300;
    private static final int HEIGHT = 48;

    public ATALogoDrawer() {
        super(false);
    }

    public static void register() {
        ScreenEvents.AFTER_INIT.register((client, screen, width, height) -> {
            if (!(screen instanceof TitleScreen) || GallifreyCompat.isLoaded()) return;
            TitleScreenAccessor title = (TitleScreenAccessor) screen;
            if (title.ata$getLogoDrawer() instanceof AITLogoDrawer) title.ata$setLogoDrawer(new ATALogoDrawer());
        });
    }

    @Override
    public void draw(DrawContext context, int screenWidth, float alpha) {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        context.setShaderColor(1f, 1f, 1f, alpha);
        context.drawTexture(ATA_LOGO, (screenWidth - WIDTH) / 2, 18, 0, 0, WIDTH, HEIGHT, WIDTH, HEIGHT);
        context.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.disableBlend();
    }
}
