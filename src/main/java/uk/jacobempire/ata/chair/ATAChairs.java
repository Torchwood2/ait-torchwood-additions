package uk.jacobempire.ata.chair;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import uk.jacobempire.ata.ATA;
import uk.jacobempire.ata.core.ATABlocks;
import uk.jacobempire.ata.core.ATAWood;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** A chair in every wood. */
public final class ATAChairs {
    public static final EntityType<SeatEntity> SEAT = Registry.register(Registries.ENTITY_TYPE, ATA.id("seat"),
            FabricEntityTypeBuilder.<SeatEntity>create(SpawnGroup.MISC, SeatEntity::new)
                    .dimensions(EntityDimensions.fixed(0.001f, 0.001f)).disableSummon().disableSaving()
                    .trackRangeBlocks(10).build());

    /** Wood name to its planks, for every chair. */
    public static final Map<String, Block> WOODS = new LinkedHashMap<>();
    public static final List<Block> CHAIRS = new ArrayList<>();

    private ATAChairs() {
    }

    public static void register() {
        for (String wood : List.of("oak", "spruce", "birch", "jungle", "acacia", "dark_oak", "mangrove", "cherry",
                "bamboo", "crimson", "warped")) {
            WOODS.put(wood, Registries.BLOCK.get(new Identifier(wood + "_planks")));
        }
        WOODS.put("torchwood", ATAWood.PLANKS);
        for (Map.Entry<String, Block> wood : WOODS.entrySet()) {
            CHAIRS.add(ATABlocks.register(wood.getKey() + "_chair",
                    new ChairBlock(FabricBlockSettings.copyOf(wood.getValue()).nonOpaque())));
        }
    }
}
