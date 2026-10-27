package uk.jacobempire.ata.client.awt;

import dev.amble.ait.client.AITModClient;
import dev.amble.ait.core.tardis.handler.DoorHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.TexturedModelData;

import java.util.List;
import java.util.function.Supplier;

/**
 * How one AWT shape is drawn: its models, size, which parts are doors and how they move.
 *
 * @param exterior      the outside
 * @param exteriorDoors its doors
 * @param door          the interior door (the inside of the doorway); the exterior itself where there's no
 *                      separate model
 * @param doorDoors     the interior door model's doors
 * @param scale         how big to draw the Blockbench model (1 = as modelled, 16 pixels to a block)
 * @param hidden        parts that exist in the Blockbench file but shouldn't be drawn (like a portal plane)
 */
@Environment(EnvType.CLIENT)
public record AwtShapeRender(Supplier<TexturedModelData> exterior, List<DoorPart> exteriorDoors,
                             Supplier<TexturedModelData> door, List<DoorPart> doorDoors,
                             float scale, List<String> hidden) {

    /** A shape with no separate interior door model: the inside of the door is the exterior, turned round. */
    public static AwtShapeRender of(Supplier<TexturedModelData> exterior, List<DoorPart> doors, float scale, String... hidden) {
        return new AwtShapeRender(exterior, doors, exterior, doors, scale, List.of(hidden));
    }

    public enum Motion {
        /** Swings with a positive yaw (a door hinged on the +x side opening inwards, or on -x opening outwards). */
        SWING_POSITIVE,
        SWING_NEGATIVE,
        /** Slides sideways along +x, like Type 70 doors. */
        SLIDE
    }

    /**
     * A door in the model.
     *
     * @param path  part names from the model root down to the door
     * @param left  true for the door that opens first (AIT's left door); single doors use this
     * @param travel for a slide, how far it moves, in model pixels
     */
    public record DoorPart(List<String> path, boolean left, Motion motion, float travel) {
        public static DoorPart swing(boolean left, Motion motion, String... path) {
            return new DoorPart(List.of(path), left, motion, 0);
        }

        public static DoorPart slide(float travel, String... path) {
            return new DoorPart(List.of(path), true, Motion.SLIDE, travel);
        }

        ModelPart find(ModelPart root) {
            ModelPart part = root;
            for (String name : path) part = part.getChild(name);
            return part;
        }
    }

    /** Builds the model and hides the parts that shouldn't show. */
    static ModelPart build(Supplier<TexturedModelData> data, List<String> hidden) {
        ModelPart root = data.get().createModel();
        for (String name : hidden) {
            if (root.hasChild(name)) root.getChild(name).visible = false;
        }
        return root;
    }

    /** Moves the doors to match the TARDIS's door state. */
    static void pose(ModelPart root, DoorHandler door, List<DoorPart> doors) {
        boolean animate = AITModClient.CONFIG.animateDoors;
        for (DoorPart part : doors) {
            float open;
            if (animate) {
                open = part.left() ? door.getLeftRot() : door.getRightRot();
            } else {
                boolean isOpen = part.left() ? (door.isLeftOpen() || door.isOpen()) : (door.isRightOpen() || door.areBothOpen());
                open = isOpen ? 1.0f : 0.0f;
            }
            ModelPart model = part.find(root);
            model.resetTransform();
            switch (part.motion()) {
                case SWING_POSITIVE -> model.yaw = (float) Math.toRadians(90 * open);
                case SWING_NEGATIVE -> model.yaw = (float) -Math.toRadians(90 * open);
                case SLIDE -> model.pivotX += part.travel() * open;
            }
        }
    }
}
