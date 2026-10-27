package uk.jacobempire.ata.client.exterior;

import dev.amble.ait.api.tardis.link.v2.block.AbstractLinkableBlockEntity;
import dev.amble.ait.client.models.doors.DoorModel;
import dev.amble.ait.client.tardis.ClientTardis;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;

/** The inside of an exterior's door, as seen from inside the TARDIS. */
@Environment(EnvType.CLIENT)
public class ShapeDoorModel extends DoorModel {
    private final ShapeRender shape;
    private final ModelPart root;

    public ShapeDoorModel(ShapeRender shape) {
        this.shape = shape;
        this.root = ShapeRender.build(shape.door(), shape.hidden());
    }

    @Override
    public ModelPart getPart() {
        return root;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        root.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }

    @Override
    public void renderWithAnimations(ClientTardis tardis, AbstractLinkableBlockEntity linkable, ModelPart root, MatrixStack matrices,
                                     VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha, float tickDelta) {
        matrices.push();
        ShapeRender.pose(this.root, tardis.door(), shape.doorDoors());
        matrices.scale(shape.scale(), shape.scale(), shape.scale());
        matrices.translate(0, -1.5f, 0);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180f));
        super.renderWithAnimations(tardis, linkable, root, matrices, vertices, light, overlay, red, green, blue, alpha, tickDelta);
        matrices.pop();
    }
}
