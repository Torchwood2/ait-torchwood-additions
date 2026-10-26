package uk.jacobempire.ata.client;

import dev.amble.ait.registry.impl.exterior.ClientExteriorVariantRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(value = EnvType.CLIENT)
public class ATAClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
    }
}
