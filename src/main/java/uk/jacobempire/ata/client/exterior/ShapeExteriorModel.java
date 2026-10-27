package uk.jacobempire.ata.client.exterior;

import dev.amble.ait.api.tardis.link.v2.Linkable;
import dev.amble.ait.client.models.exteriors.SimpleExteriorModel;
import dev.amble.ait.client.tardis.ClientTardis;
import dev.amble.ait.core.blockentities.ExteriorBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

/** A Java-model exterior, drawn from its converted Blockbench model. */
@Environment(EnvType.CLIENT)
public class ShapeExteriorModel extends SimpleExteriorModel {
    private final ShapeRender shape;
    private final ModelPart root;

    public ShapeExteriorModel(ShapeRender shape) {
        this.shape = shape;
        this.root = ShapeRender.build(shape.exterior(), shape.hidden());
    }

    @Override
    public ModelPart getPart() {
        return root;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        root.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }

    /** Scaled about the ground; the model's ground is 1.5 blocks below its origin, as in AIT's own exteriors. */
    private void place(MatrixStack matrices) {
        matrices.scale(shape.scale(), shape.scale(), shape.scale());
        matrices.translate(0, -1.5f, 0);
    }

    @Override
    public void renderWithAnimations(ClientTardis tardis, ExteriorBlockEntity exterior, ModelPart root, MatrixStack matrices,
                                     VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        matrices.push();
        place(matrices);
        ShapeRender.pose(this.root, tardis.door(), shape.exteriorDoors());
        super.renderWithAnimations(tardis, exterior, root, matrices, vertices, light, overlay, red, green, blue, alpha);
        matrices.pop();
    }

    @Override
    public <T extends Entity & Linkable> void renderEntity(T falling, ModelPart root, MatrixStack matrices, VertexConsumer vertices,
                                                           int light, int overlay, float red, float green, float blue, float alpha) {
        if (falling.tardis().isEmpty()) return;
        matrices.push();
        place(matrices);
        ShapeRender.pose(this.root, falling.tardis().get().door(), shape.exteriorDoors());
        super.renderEntity(falling, root, matrices, vertices, light, overlay, red, green, blue, alpha);
        matrices.pop();
    }

    @Override
    public void renderDoors(ClientTardis tardis, ExteriorBlockEntity exterior, ModelPart root, MatrixStack matrices, VertexConsumer vertices,
                            int light, int overlay, float red, float green, float blue, float alpha, boolean isBOTI) {
        ShapeRender.pose(this.root, tardis.door(), shape.exteriorDoors());
        if (!isBOTI) return;
        matrices.push();
        place(matrices);
        for (ShapeRender.DoorPart door : shape.exteriorDoors()) {
            door.find(this.root).render(matrices, vertices, light, overlay, red, green, blue, alpha);
        }
        matrices.pop();
    }
}
