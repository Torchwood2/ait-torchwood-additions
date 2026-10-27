package uk.jacobempire.ata.world;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import uk.jacobempire.ata.ATA;

/**
 * Atrium ore in every Overworld biome. The ore itself (vein size, how many per chunk, how deep) is in
 * data/ata/worldgen/configured_feature and placed_feature, so it can be tuned without touching code.
 */
public final class ATAWorldGen {
    public static final RegistryKey<PlacedFeature> ATRIUM_ORE = RegistryKey.of(RegistryKeys.PLACED_FEATURE, ATA.id("ore_atrium"));
    public static final RegistryKey<PlacedFeature> ATRIUM_ORE_BURIED = RegistryKey.of(RegistryKeys.PLACED_FEATURE, ATA.id("ore_atrium_buried"));

    private ATAWorldGen() {
    }

    public static void register() {
        // Gallifrey generates its own atrium ore (which works in ATA's recipes), so don't add a second lot.
        if (uk.jacobempire.ata.compat.GallifreyCompat.isLoaded()) return;
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ATRIUM_ORE);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ATRIUM_ORE_BURIED);
    }
}
