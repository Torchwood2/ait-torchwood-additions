package uk.jacobempire.ata.plushie;

import dev.amble.lib.block.ABlockSettings;
import dev.amble.lib.client.bedrock.BedrockModelReference;
import dev.amble.plushies.MarketablePlushieBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import uk.jacobempire.ata.ATA;

/**
 * An AIT-style plushie of one of the ATA crew. It is AmbleKit's own plushie block (so it can be placed, turned,
 * stacked and worn just like AIT's), pointed at ATA's model and texture: assets/ata/bedrock/&lt;name&gt;.geo.json and
 * assets/ata/textures/block/&lt;name&gt;.png.
 */
public class ATAPlushieBlock extends MarketablePlushieBlock {
    private final BedrockModelReference model;

    public ATAPlushieBlock(ABlockSettings settings, String name) {
        super(settings, name);
        this.model = new BedrockModelReference(ATA.MOD_ID, name);
    }

    @Override
    public BedrockModelReference getModel() {
        return model;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ATAPlushieBlockEntity(pos, state);
    }
}
