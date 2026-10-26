package uk.jacobempire.ata.blocks;

import io.wispforest.owo.Owo;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import io.wispforest.owo.registration.reflect.BlockRegistryContainer;
import uk.jacobempire.ata.ATA;
import mdteam.ait.AITMod;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class ExtrasBlocks implements BlockRegistryContainer {
    public static final Block HARTNELL_A = new RoundelBlock(FabricBlockSettings.create());
    public static final Block HARTNELL_B = new RoundelBlock(FabricBlockSettings.create());
    public static final Block HARTNELL_C = new RoundelBlock(FabricBlockSettings.create());


    @Override
    public BlockItem createBlockItem(Block block, String identifier) {
        return new BlockItem(block, new OwoItemSettings().group(ATA.EXTRAS_ITEM_GROUP));
    }
}
