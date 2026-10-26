package uk.jacobempire.ata.client.variant;

import uk.jacobempire.ata.ATA;
import uk.jacobempire.ata.tardis.variant.DimensionalVariant;
import mdteam.ait.client.models.exteriors.ExteriorModel;
import mdteam.ait.client.models.exteriors.PoliceBoxModel;
import mdteam.ait.client.registry.exterior.ClientExteriorVariantSchema;
import net.minecraft.util.Identifier;
import org.joml.Vector3f;

public class ClientDimensionalVariant extends ClientExteriorVariantSchema {
    public static final Identifier TEXTURE = new Identifier(ATA.MOD_ID, "textures/exterior/" + "dimensional" + ".png");
    public static final Identifier EMISSION = new Identifier(ATA.MOD_ID, "textures/exterior/" + "dimensional" + "_emission" + ".png");

    public ClientDimensionalVariant() {
        super(DimensionalVariant.REFERENCE);
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
