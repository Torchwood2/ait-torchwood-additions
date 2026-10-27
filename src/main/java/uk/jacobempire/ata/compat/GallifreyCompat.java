package uk.jacobempire.ata.compat;

import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.Text;
import uk.jacobempire.ata.ATA;

/**
 * ATA × Gallifrey. Only switches on when Gallifrey is installed, and needs none of Gallifrey's code.
 * <ul>
 *   <li>Atrium is shared: ATA's atrium recipes and console refuelling take either mod's atrium (the c:atrium,
 *   c:atrium_fuels, c:energized_atrium and c:atrium_cores tags), and the built-in "ATA × Gallifrey" pack
 *   (resourcepacks/gallifrey_compat) adds 1:1 crafting swaps between the two mods' atrium items, so ATA's atrium
 *   works in Gallifrey's recipes (sonic screwdriver, sonic workshop, circuits) too.</li>
 *   <li>Only Gallifrey's atrium ore generates, so there aren't two lots of it in the world.</li>
 *   <li>The same pack adds all of Gallifrey's sonic casings to AIT's sonic, using Gallifrey's own models.</li>
 *   <li>Gallifrey's own sonic already links to AIT (so ATA) TARDISes at the sonic port, from Gallifrey's side.</li>
 * </ul>
 */
public final class GallifreyCompat {
    public static final String GALLIFREY = "gallifrey";

    private GallifreyCompat() {
    }

    public static boolean isLoaded() {
        return FabricLoader.getInstance().isModLoaded(GALLIFREY);
    }

    public static void register() {
        if (!isLoaded()) return;
        FabricLoader.getInstance().getModContainer(ATA.MOD_ID).ifPresent(mod ->
                ResourceManagerHelper.registerBuiltinResourcePack(ATA.id("gallifrey_compat"), mod,
                        Text.translatable("pack.ata.gallifrey_compat"), ResourcePackActivationType.ALWAYS_ENABLED));
        ATA.LOGGER.info("Gallifrey found: sharing atrium and adding Gallifrey's sonic casings");
    }
}
