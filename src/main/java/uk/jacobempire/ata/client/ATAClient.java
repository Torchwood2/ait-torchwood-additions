package uk.jacobempire.ata.client;

import uk.jacobempire.ata.client.variant.ClientDimensionalVariant;
import uk.jacobempire.ata.client.variant.ClientEleventhVariant;
import uk.jacobempire.ata.client.variant.ClientMysticalVariant;
import mdteam.ait.client.registry.ClientExteriorVariantRegistry;
import mdteam.ait.client.registry.exterior.ClientExteriorVariantSchema;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(value = EnvType.CLIENT)
public class ATAClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientExteriorVariantRegistry.getInstance().register(new ClientDimensionalVariant());
        ClientExteriorVariantRegistry.getInstance().register(new ClientEleventhVariant());
        ClientExteriorVariantRegistry.getInstance().register(new ClientMysticalVariant());
    }
}
