package uk.jacobempire.ata.client.render;

import dev.amble.ait.client.models.entities.projectiles.StaserBoltEntityModel;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import uk.jacobempire.ata.ATA;
import uk.jacobempire.ata.gun.BulletEntity;

/** Draws a bullet as AIT's staser bolt, in bronzey gold and a bit smaller, and not glowing. */
public class BulletEntityRenderer extends EntityRenderer<BulletEntity> {
    public static final Identifier TEXTURE = ATA.id("textures/entity/projectiles/bullet.png");
    private final StaserBoltEntityModel model = new StaserBoltEntityModel(StaserBoltEntityModel.getTexturedModelData().createModel());

    public BulletEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public void render(BulletEntity entity, float yaw, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(entity.getYaw()));
        matrices.multiply(RotationAxis.NEGATIVE_X.rotationDegrees(entity.getPitch()));
        matrices.translate(0, -1.125f, 0);
        model.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(TEXTURE)), light,
                OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f);
        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(BulletEntity entity) {
        return TEXTURE;
    }
}
