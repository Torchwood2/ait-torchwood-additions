package uk.jacobempire.ata.tardis.variant;

import uk.jacobempire.ata.ATA;
import mdteam.ait.tardis.exterior.variant.box.PoliceBoxVariant;
import net.minecraft.util.Identifier;

public class MysticalVariant extends PoliceBoxVariant {
    public static final Identifier REFERENCE = new Identifier(ATA.MOD_ID, "exterior/police_box/" + "mystical");

    public MysticalVariant() {
        super("mystical", ATA.MOD_ID);
    }
}
