package uk.jacobempire.ata.client.exterior;

import dev.amble.ait.api.tardis.link.v2.Linkable;
import dev.amble.ait.client.models.exteriors.SimpleExteriorModel;
import dev.amble.ait.client.tardis.ClientTardis;
import dev.amble.ait.core.blockentities.ExteriorBlockEntity;
import dev.amble.ait.core.tardis.Tardis;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import org.joml.Vector3f;

/**
 * The Onion's outside. Its door is the beam ("OmgitsaUFO"), which only shows while the door is open. While
 * dematerialising it plays its takeoff, and while materialising its landing.
 */
@Environment(EnvType.CLIENT)
public class OnionExteriorModel extends SimpleExteriorModel {
    private static final Vector3f TEMP = new Vector3f();

    private final ModelPart root;
    private final ModelPart beam;

    public OnionExteriorModel() {
        this.root = ATAExteriorModels.onion().createModel();
        this.beam = root.getChild("Root").getChild("Body").getChild("OmgitsaUFO");
    }

    @Override
    public ModelPart getPart() {
        return root;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        root.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }

    /** Poses the model for this moment: the travel animation, then the beam. */
    private void pose(Tardis tardis) {
        root.traverse().forEach(ModelPart::resetTransform);

        Animation animation = switch (tardis.travel().getState()) {
            case DEMAT -> OnionAnimations.takeoff;
            case MAT -> OnionAnimations.landing;
            default -> null;
        };
        if (animation != null) {
            float seconds = OnionTravel.seconds(tardis, MinecraftClient.getInstance().getTickDelta());
            AnimationHelper.animate(this, animation, (long) (seconds * 1000), 1.0f, TEMP);
        }

        beam.visible = tardis.door().isOpen();
    }

    private static void place(MatrixStack matrices) {
        matrices.translate(0, -1.5f, 0);
    }

    @Override
    public void renderWithAnimations(ClientTardis tardis, ExteriorBlockEntity exterior, ModelPart root, MatrixStack matrices,
                                     VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        matrices.push();
        place(matrices);
        pose(tardis);
        super.renderWithAnimations(tardis, exterior, root, matrices, vertices, light, overlay, red, green, blue, alpha);
        matrices.pop();
    }

    @Override
    public <T extends Entity & Linkable> void renderEntity(T falling, ModelPart root, MatrixStack matrices, VertexConsumer vertices,
                                                           int light, int overlay, float red, float green, float blue, float alpha) {
        if (falling.tardis().isEmpty()) return;
        matrices.push();
        place(matrices);
        pose(falling.tardis().get());
        super.renderEntity(falling, root, matrices, vertices, light, overlay, red, green, blue, alpha);
        matrices.pop();
    }

    @Override
    public void renderDoors(ClientTardis tardis, ExteriorBlockEntity exterior, ModelPart root, MatrixStack matrices, VertexConsumer vertices,
                            int light, int overlay, float red, float green, float blue, float alpha, boolean isBOTI) {
        // No doorway to look through: the beam is drawn with the rest of the model.
    }
}
