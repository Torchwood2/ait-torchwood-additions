package uk.jacobempire.ata.client.variant;

import uk.jacobempire.ata.ATA;
import uk.jacobempire.ata.tardis.variant.EleventhVariant;
import uk.jacobempire.ata.tardis.variant.MysticalVariant;
import mdteam.ait.client.models.exteriors.ExteriorModel;
import mdteam.ait.client.models.exteriors.PoliceBoxModel;
import mdteam.ait.client.registry.exterior.ClientExteriorVariantSchema;
import net.minecraft.util.Identifier;
import org.joml.Vector3f;

public class ClientMysticalVariant extends ClientExteriorVariantSchema {
    public static final Identifier TEXTURE = new Identifier(ATA.MOD_ID, "textures/exterior/" + "mystical" + ".png");
    public static final Identifier EMISSION = new Identifier(ATA.MOD_ID, "textures/exterior/" + "mystical" + "_emission" + ".png");

    public ClientMysticalVariant() {
        super(MysticalVariant.REFERENCE);
    }

    @Override
    public Identifier texture() {
        return TEXTURE;
    }

    @Override
    public Identifier emission() {
        return EMISSION;
    }

    @Override
    public ExteriorModel model() {
        return new PoliceBoxModel(PoliceBoxModel.getTexturedModelData().createModel());
    }
    @Override
    public Vector3f sonicItemTranslations() {
        return new Vector3f(0.56F, 1.2F, 1.2F);
    }
}
