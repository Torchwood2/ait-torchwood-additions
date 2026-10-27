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

/**
 * Client side of the AWT exteriors: which model each shape uses, how its doors move, and its textures
 * (textures/exterior/awt/{shape}/{variant}.png, with {variant}_emission.png for the parts that glow).
 */
@Environment(EnvType.CLIENT)
public final class AwtClient {
    /** For shapes with nothing that glows. */
    private static final Identifier NO_EMISSION = ATA.id("textures/exterior/awt/no_emission.png");

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
            // Phone booth doors open outwards.
            AwtExteriors.PHONE_BOOTH, AwtShapeRender.of(AwtModels::phoneBooth,
                    List.of(DoorPart.swing(true, Motion.SWING_POSITIVE, "root", "door")), 1.0f),
            AwtExteriors.BLUEBERRY_BOX, AwtShapeRender.of(AwtModels::blueberryBox, List.of(
                            DoorPart.swing(true, Motion.SWING_POSITIVE, "parent", "right_door"),
                            DoorPart.swing(false, Motion.SWING_NEGATIVE, "parent", "left_door")),
                    0.64f, "portal", "fade_value"),
            AwtExteriors.BLOCKTIS, AwtShapeRender.of(AwtModels::blocktis,
                    List.of(DoorPart.swing(true, Motion.SWING_POSITIVE, "bone", "door")), 0.5f),
            // Type 70 doors slide aside rather than swing.
            AwtExteriors.TYPE_70, new AwtShapeRender(
                    AwtModels::type70, List.of(DoorPart.slide(15, "bone", "door")),
                    AwtModels::type70Door, List.of(DoorPart.slide(14, "bone", "door")),
                    1.0f, List.of()),
            AwtExteriors.ONION, AwtShapeRender.of(AwtModels::onion, List.of(), 1.0f));

    /** The Blocktis has a second model for its v2 variant. */
    private static final AwtShapeRender BLOCKTIS_V2 = AwtShapeRender.of(AwtModels::blocktisV2,
            List.of(DoorPart.swing(true, Motion.SWING_POSITIVE, "bone", "door")), 0.5f);

    private AwtClient() {
    }

    public static void register() {
        for (Shape shape : AwtExteriors.SHAPES) {
            AwtShapeRender render = RENDER.get(shape);
            ClientDoorRegistry.getInstance().register(new Door(shape, render));
            for (String variant : shape.variants()) {
                AwtShapeRender variantRender = shape == AwtExteriors.BLOCKTIS && variant.equals("v2") ? BLOCKTIS_V2 : render;
                boolean glows = shape != AwtExteriors.ONION;
                ClientExteriorVariantRegistry.getInstance().register(new Variant(shape, variant, variantRender, glows));
            }
        }
    }

    private static final class Variant extends ClientExteriorVariantSchema {
        private final Shape shape;
        private final String variant;
        private final AwtShapeRender render;
        private final boolean glows;

        Variant(Shape shape, String variant, AwtShapeRender render, boolean glows) {
            super(shape.variantId(variant));
            this.shape = shape;
            this.variant = variant;
            this.render = render;
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
            return new AwtExteriorModel(render);
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
        private final AwtShapeRender render;

        Door(Shape shape, AwtShapeRender render) {
            super(shape.doorId());
            this.render = render;
        }

        @Override
        public AnimatedModel<DoorBlockEntity> model() {
            return new AwtDoorModel(render);
        }
    }
}
