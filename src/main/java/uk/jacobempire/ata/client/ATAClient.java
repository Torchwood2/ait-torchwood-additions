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
import uk.jacobempire.ata.client.exterior.ATAExteriorsClient;
import net.minecraft.client.render.RenderLayer;
import uk.jacobempire.ata.core.ATABlocks;

@Environment(EnvType.CLIENT)
public class ATAClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // The grates have holes you can see through.
        ATABlocks.CUTOUT.forEach(block -> BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout()));

        // Chairs: the seat you sit on is invisible.
        net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry.register(uk.jacobempire.ata.chair.ATAChairs.SEAT,
                net.minecraft.client.render.entity.EmptyEntityRenderer::new);
        uk.jacobempire.ata.chair.ATAChairs.CHAIRS.forEach(block -> BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout()));

        // Guns switch to their aiming model while you hold use, like AIT's.
        for (net.minecraft.item.Item gun : new net.minecraft.item.Item[]{uk.jacobempire.ata.gun.ATAGuns.SERVICE_PISTOL,
                uk.jacobempire.ata.gun.ATAGuns.RIFT_CARBINE, uk.jacobempire.ata.gun.ATAGuns.RIFT_LANCE}) {
            net.minecraft.client.item.ModelPredicateProviderRegistry.register(gun, new net.minecraft.util.Identifier("ads"),
                    (stack, world, entity, seed) -> entity instanceof net.minecraft.entity.player.PlayerEntity
                            && entity.getMainHandStack() == stack
                            && net.minecraft.client.MinecraftClient.getInstance().options.useKey.isPressed() ? 1.0f : 0.0f);
        }

        // ATA's Java-model exteriors: models and textures.
        ATAExteriorsClient.register();

        // Optional GUI recolours for AIT's monitor screens, switched on in Options > Resource Packs.
        ModContainer mod = FabricLoader.getInstance().getModContainer(ATA.MOD_ID).orElseThrow();
        for (String[] pack : new String[][]{{"blueshiftgui", "Blue Shift GUI"}, {"redshiftgui", "Red Shift GUI"},
                {"greyedgui", "Greyed GUI"}, {"lebronjamesgui", "LeBron James GUI"}}) {
            ResourceManagerHelper.registerBuiltinResourcePack(ATA.id(pack[0]), mod, Text.literal(pack[1]), ResourcePackActivationType.NORMAL);
        }
    }
}
