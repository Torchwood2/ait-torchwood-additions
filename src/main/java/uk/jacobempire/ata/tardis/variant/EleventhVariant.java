package uk.jacobempire.ata.tardis.variant;

import uk.jacobempire.ata.ATA;
import mdteam.ait.registry.DoorRegistry;
import mdteam.ait.tardis.exterior.variant.box.PoliceBoxVariant;
import mdteam.ait.tardis.variant.door.DoorSchema;
import mdteam.ait.tardis.variant.door.PoliceBoxCoralDoorVariant;
import net.minecraft.util.Identifier;

public class EleventhVariant extends PoliceBoxVariant {
    public static final Identifier REFERENCE = new Identifier(ATA.MOD_ID, "exterior/police_box/" + "eleventh");

    public EleventhVariant() {
        super("eleventh", ATA.MOD_ID);
    }

    @Override
    public DoorSchema door() {
        return DoorRegistry.REGISTRY.get(PoliceBoxCoralDoorVariant.REFERENCE);
    }
}
