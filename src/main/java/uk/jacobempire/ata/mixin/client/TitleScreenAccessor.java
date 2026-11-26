package uk.jacobempire.ata.mixin.client;

import net.minecraft.client.gui.LogoDrawer;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(TitleScreen.class)
public interface TitleScreenAccessor {
    @Accessor("logoDrawer")
    LogoDrawer ata$getLogoDrawer();

    @Mutable
    @Accessor("logoDrawer")
    void ata$setLogoDrawer(LogoDrawer drawer);
}
