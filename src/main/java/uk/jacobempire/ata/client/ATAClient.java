package uk.jacobempire.ata.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.text.Text;
import uk.jacobempire.ata.ATA;
import uk.jacobempire.ata.client.awt.AwtClient;
import net.minecraft.client.render.RenderLayer;
import uk.jacobempire.ata.core.ATABlocks;

@Environment(EnvType.CLIENT)
public class ATAClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // The grates have holes you can see through.
        ATABlocks.CUTOUT.forEach(block -> BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout()));

        // The AWT exteriors' models and textures.
        AwtClient.register();

        // Optional GUI recolours for AIT's monitor screens, switched on in Options > Resource Packs.
        ModContainer mod = FabricLoader.getInstance().getModContainer(ATA.MOD_ID).orElseThrow();
        for (String[] pack : new String[][]{{"blueshiftgui", "Blue Shift GUI"}, {"redshiftgui", "Red Shift GUI"},
                {"greyedgui", "Greyed GUI"}, {"lebronjamesgui", "LeBron James GUI"}}) {
            ResourceManagerHelper.registerBuiltinResourcePack(ATA.id(pack[0]), mod, Text.literal(pack[1]), ResourcePackActivationType.NORMAL);
        }
    }
}
