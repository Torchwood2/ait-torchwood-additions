package uk.jacobempire.ata.plushie;

import dev.amble.plushies.MarketablePlushieBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public class ATAPlushieBlockEntity extends MarketablePlushieBlockEntity {
    public ATAPlushieBlockEntity(BlockPos pos, BlockState state) {
        super(ATAPlushies.BLOCK_ENTITY, pos, state);
    }
}
