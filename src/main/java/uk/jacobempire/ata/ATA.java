package uk.jacobempire.ata;


//import uk.jacobempire.ata.blocks.ExtrasBlocks;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ATA implements ModInitializer {
    public static final String MOD_ID = "ata";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
      //  FieldRegistrationHandler.register(ExtrasBlocks.class, MOD_ID, false);

        // TODO - These can be removed now as datapack support for exterior variants is available and is recommended.
       // ExteriorVariantRegistry.getInstance().register(new DimensionalVariant());
      //  ExteriorVariantRegistry.getInstance().register(new EleventhVariant());
      //  ExteriorVariantRegistry.getInstance().register(new MysticalVariant());

      //  EXTRAS_ITEM_GROUP.initialize();
    }
}
