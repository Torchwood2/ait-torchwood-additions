package uk.jacobempire.ata.awt;

import dev.amble.ait.api.AITRegistryEvents;
import dev.amble.ait.data.schema.door.DoorSchema;
import dev.amble.ait.data.schema.exterior.ExteriorCategorySchema;
import dev.amble.ait.data.schema.exterior.ExteriorVariantSchema;
import dev.amble.ait.registry.impl.CategoryRegistry;
import dev.amble.ait.registry.impl.door.DoorRegistry;
import dev.amble.ait.registry.impl.exterior.ExteriorVariantRegistry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import uk.jacobempire.ata.ATA;
import uk.jacobempire.ata.core.ATASounds;

import java.util.List;
import java.util.Optional;

/**
 * The AWT exteriors: new TARDIS shapes, each its own category in the exterior menu, with their own
 * interior door. The models and how they move are client-side (client/awt); this is the part both
 * sides need: categories, variants, doors and where the BOTI portal sits.
 */
public final class AwtExteriors {
    /**
     * One TARDIS shape.
     *
     * @param id           category and door name, e.g. "awt_police_box"
     * @param variants     the variant names; each has textures/exterior/awt/{id}/{variant}.png (and _emission)
     * @param portalDist   how far in front of the block centre the doorway is, in blocks
     * @param portalWidth  doorway width, in blocks (0 for no portal)
     * @param portalHeight doorway height, in blocks
     */
    public record Shape(String id, List<String> variants, boolean doubleDoor, boolean policeBoxSounds,
                        double portalDist, double portalWidth, double portalHeight) {
        public Identifier categoryId() {
            return ATA.id("exterior/" + id);
        }

        public Identifier variantId(String variant) {
            return ATA.id("exterior/" + id + "/" + variant);
        }

        public Identifier doorId() {
            return ATA.id("door/" + id);
        }

        public boolean hasPortals() {
            return portalWidth > 0;
        }
    }

    public static final Shape POLICE_BOX = new Shape("awt_police_box",
            List.of("default", "alt", "alt2", "badwolf", "coral", "dino", "gamblebox", "nathan", "purple", "rhamnous", "tokomak"),
            true, true, 0.70, 1.3, 2.2);
    public static final Shape PHONE_BOOTH = new Shape("awt_phone_booth",
            List.of("default", "battered", "gloom", "futuristic", "bee", "blue", "grey", "pink", "ghost"),
            false, false, 0.64, 0.94, 2.35);
    public static final Shape BLUEBERRY_BOX = new Shape("awt_blueberry_box", List.of("default"),
            true, true, 0.62, 1.0, 2.08);
    public static final Shape BLOCKTIS = new Shape("awt_blocktis", List.of("default", "v2"),
            false, false, 0.75, 0.5, 1.0);
    public static final Shape TYPE_70 = new Shape("awt_type_70", List.of("default", "alt", "war_games"),
            false, false, 0.5, 0.9, 2.2);
    public static final Shape ONION = new Shape("awt_onion",
            List.of("red", "blue", "yellow", "purple", "pink", "lime", "grey", "white", "light_blue"),
            false, false, 0, 0, 0);

    public static final List<Shape> SHAPES = List.of(POLICE_BOX, PHONE_BOOTH, BLUEBERRY_BOX, BLOCKTIS, TYPE_70, ONION);

    private AwtExteriors() {
    }

    /**
     * AIT asks addons for their exteriors whenever it (re)loads its own. They're also added straight away,
     * in case AIT set its registries up before this mod started.
     */
    public static void register() {
        AITRegistryEvents.EXTERIOR_DEFAULTS.register(AwtExteriors::addToRegistries);
        addToRegistries();
    }

    private static void addToRegistries() {
        for (Shape shape : SHAPES) {
            CategoryRegistry.getInstance().register(new Category(shape));
            DoorRegistry.getInstance().register(new Door(shape));
            for (String variant : shape.variants()) {
                ExteriorVariantRegistry.getInstance().register(new Variant(shape, variant));
            }
        }
    }

    // ---- The AIT schemas ----

    static final class Category extends ExteriorCategorySchema {
        Category(Shape shape) {
            super(shape.categoryId(), shape.id());
        }
    }

    static final class Door extends DoorSchema {
        private final Shape shape;

        Door(Shape shape) {
            super(shape.doorId());
            this.shape = shape;
        }

        @Override
        public boolean isDouble() {
            return shape.doubleDoor();
        }

        @Override
        public SoundEvent openSound() {
            return shape.policeBoxSounds() ? ATASounds.POLICE_BOX_DOOR_OPEN : super.openSound();
        }

        @Override
        public SoundEvent closeSound() {
            return shape.policeBoxSounds() ? ATASounds.POLICE_BOX_DOOR_CLOSE : super.closeSound();
        }

        @Override
        public Vec3d adjustPortalPos(Vec3d pos, Direction direction) {
            return switch (direction) {
                case DOWN, UP -> pos;
                case NORTH -> pos.add(0, 0.05, -0.45);
                case SOUTH -> pos.add(0, 0.05, 0.45);
                case WEST -> pos.add(-0.45, 0.05, 0);
                case EAST -> pos.add(0.45, 0.05, 0);
            };
        }
    }

    static final class Variant extends ExteriorVariantSchema {
        private final Shape shape;

        Variant(Shape shape, String variant) {
            // No loyalty requirement: available from the start, like the other ATA exteriors.
            super(shape.categoryId(), shape.variantId(variant), Optional.empty());
            this.shape = shape;
        }

        @Override
        public DoorSchema door() {
            return DoorRegistry.getInstance().get(shape.doorId());
        }

        @Override
        public boolean hasPortals() {
            return shape.hasPortals();
        }

        @Override
        public Vec3d adjustPortalPos(Vec3d pos, byte direction) {
            double d = shape.portalDist();
            double diag = d * 0.707;
            return switch (direction) {
                case 0 -> pos.add(0, -0.05, -d); // north
                case 1, 2, 3 -> pos.add(diag, -0.05, -diag);
                case 4 -> pos.add(d, -0.05, 0); // east
                case 5, 6, 7 -> pos.add(diag, -0.05, diag);
                case 8 -> pos.add(0, -0.05, d); // south
                case 9, 10, 11 -> pos.add(-diag, -0.05, diag);
                case 12 -> pos.add(-d, -0.05, 0); // west
                case 13, 14, 15 -> pos.add(-diag, -0.05, -diag);
                default -> pos;
            };
        }

        @Override
        public Vec3d seatTranslations() {
            return new Vec3d(0.5, 1, 0.5);
        }

        @Override
        public double portalWidth() {
            return shape.portalWidth();
        }

        @Override
        public double portalHeight() {
            return shape.portalHeight();
        }
    }
}
