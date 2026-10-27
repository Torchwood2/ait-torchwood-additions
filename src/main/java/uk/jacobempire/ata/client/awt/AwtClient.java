package uk.jacobempire.ata.client.awt;

import dev.amble.ait.client.models.AnimatedModel;
import dev.amble.ait.client.models.exteriors.ExteriorModel;
import dev.amble.ait.core.blockentities.DoorBlockEntity;
import dev.amble.ait.data.datapack.exterior.BiomeOverrides;
import dev.amble.ait.data.schema.door.ClientDoorSchema;
import dev.amble.ait.data.schema.exterior.ClientExteriorVariantSchema;
import dev.amble.ait.registry.impl.door.ClientDoorRegistry;
import dev.amble.ait.registry.impl.exterior.ClientExteriorVariantRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;
import org.joml.Vector3f;
import uk.jacobempire.ata.ATA;
import uk.jacobempire.ata.awt.AwtExteriors;
import uk.jacobempire.ata.awt.AwtExteriors.Shape;
import uk.jacobempire.ata.client.awt.AwtShapeRender.DoorPart;
import uk.jacobempire.ata.client.awt.AwtShapeRender.Motion;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Client side of the AWT exteriors: which model each shape uses, how its doors move, and its textures
 * (textures/exterior/awt/{shape}/{variant}.png, with {variant}_emission.png for the parts that glow).
 */
@Environment(EnvType.CLIENT)
public final class AwtClient {
    /** For shapes with nothing that glows. */
    private static final Identifier NO_EMISSION = ATA.id("textures/exterior/awt/no_emission.png");

    /** Drawn at 7/8 size, to match AIT's own booth. */
    private static final float PHONE_BOOTH_SCALE = 0.875f;

    /** Phone booth doors open outwards. */
    private static final AwtShapeRender PHONE_BOOTH = new AwtShapeRender(
            AwtModels::phoneBooth, List.of(DoorPart.swing(true, Motion.SWING_POSITIVE, "root", "door")),
            AwtModels::phoneBoothDoor, List.of(DoorPart.swing(true, Motion.SWING_POSITIVE, "root", "door")),
            PHONE_BOOTH_SCALE, List.of());

    private static final Map<Shape, AwtShapeRender> RENDER = Map.of(
            // Police box doors open inwards, like the TARDIS's: the +x-hinged door is the one that opens first.
            AwtExteriors.POLICE_BOX, new AwtShapeRender(
                    AwtModels::policeBox, List.of(
                            DoorPart.swing(true, Motion.SWING_POSITIVE, "bone", "front", "doors", "leftdoor"),
                            DoorPart.swing(false, Motion.SWING_NEGATIVE, "bone", "front", "doors", "rightdoor")),
                    AwtModels::policeBoxDoor, List.of(
                            DoorPart.swing(true, Motion.SWING_POSITIVE, "bone", "front", "doors", "leftdoor"),
                            DoorPart.swing(false, Motion.SWING_NEGATIVE, "bone", "front", "doors", "rightdoor")),
                    1.0f, List.of()),
            AwtExteriors.K2_PHONE_BOOTH, PHONE_BOOTH,
            AwtExteriors.K6_PHONE_BOOTH, PHONE_BOOTH);

    private AwtClient() {
    }

    public static void register() {
        for (Shape shape : AwtExteriors.SHAPES) {
            if (shape == AwtExteriors.ONION) {
                // The Onion has its own models: a beam for a door, and its takeoff and landing animations.
                ClientDoorRegistry.getInstance().register(new Door(shape, OnionDoorModel::new));
                for (String variant : shape.variants()) {
                    ClientExteriorVariantRegistry.getInstance().register(new Variant(shape, variant, OnionExteriorModel::new, false));
                }
                continue;
            }
            AwtShapeRender render = RENDER.get(shape);
            ClientDoorRegistry.getInstance().register(new Door(shape, () -> new AwtDoorModel(render)));
            for (String variant : shape.variants()) {
                ClientExteriorVariantRegistry.getInstance().register(
                        new Variant(shape, variant, () -> new AwtExteriorModel(render), true));
            }
        }
        OnionTravel.register();
    }

    private static final class Variant extends ClientExteriorVariantSchema {
        private final Shape shape;
        private final String variant;
        private final Supplier<ExteriorModel> model;
        private final boolean glows;

        Variant(Shape shape, String variant, Supplier<ExteriorModel> model, boolean glows) {
            super(shape.variantId(variant));
            this.shape = shape;
            this.variant = variant;
            this.model = model;
            this.glows = glows;
        }

        @Override
        public Identifier texture() {
            return ATA.id("textures/exterior/awt/" + shape.id() + "/" + variant + ".png");
        }

        @Override
        public Identifier emission() {
            return glows ? ATA.id("textures/exterior/awt/" + shape.id() + "/" + variant + "_emission.png") : NO_EMISSION;
        }

        @Override
        public ExteriorModel model() {
            return model.get();
        }

        @Override
        public Vector3f sonicItemTranslations() {
            return new Vector3f(0.56f, 1.2f, 1.2f);
        }

        @Override
        public BiomeOverrides overrides() {
            return BiomeOverrides.EMPTY;
        }
    }

    private static final class Door extends ClientDoorSchema {
        private final Supplier<AnimatedModel<DoorBlockEntity>> model;

        Door(Shape shape, Supplier<AnimatedModel<DoorBlockEntity>> model) {
            super(shape.doorId());
            this.model = model;
        }

        @Override
        public AnimatedModel<DoorBlockEntity> model() {
            return model.get();
        }
    }
}
