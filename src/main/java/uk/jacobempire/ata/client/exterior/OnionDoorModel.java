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

/** The Onion's door from inside: a pad on the floor, with the beam (and its arrow) standing on it while open. */
@Environment(EnvType.CLIENT)
public class OnionDoorModel extends DoorModel {
    private final ModelPart root;
    /** The beam's own box; the pad and the arrow hang off it. */
    private final ModelPart beam;
    private final ModelPart arrow;

    public OnionDoorModel() {
        this.root = ATAExteriorModels.onionDoor().createModel();
        this.beam = root.getChild("Root");
        this.arrow = beam.getChild("GetInLoserWereGoingShopping");
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
        boolean open = tardis.door().isOpen();
        // "hidden" skips the beam's own box but still draws the pad under it.
        beam.hidden = !open;
        arrow.visible = open;

        matrices.push();
        matrices.translate(0, -1.5f, 0);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180f));
        super.renderWithAnimations(tardis, linkable, root, matrices, vertices, light, overlay, red, green, blue, alpha, tickDelta);
        matrices.pop();
    }
}
