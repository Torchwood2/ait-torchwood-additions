package uk.jacobempire.ata.gun;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Rarity;
import uk.jacobempire.ata.ATA;
import uk.jacobempire.ata.core.ATAItems;

/**
 * ATA's guns. All use AIT's Staser Bolt Magazine for ammo. Damage is relative to AIT's Cult Staser.
 * <ul>
 *   <li><b>Torchwood Service Pistol</b>: quick to fire, accurate when aimed, a bit weaker.</li>
 *   <li><b>Rift Carbine</b>: big magazine, fires very fast, sprays when not aimed.</li>
 *   <li><b>Rift Lance</b>: slow, small magazine, perfectly accurate when aimed and hits very hard.</li>
 * </ul>
 * The <b>Torchwood Pistol</b> is different: it fires bullets from its own Bullet Magazine.
 */
public final class ATAGuns {
    public static final Item SERVICE_PISTOL = register("torchwood_service_pistol",
            new ATAGunItem(new Item.Settings().maxCount(1).maxDamage(600).rarity(Rarity.UNCOMMON), 24, 8, 1.2f, 0.1f, 0.85f));
    public static final Item RIFT_CARBINE = register("rift_carbine",
            new ATAGunItem(new Item.Settings().maxCount(1).maxDamage(900).rarity(Rarity.RARE), 48, 4, 2.6f, 0.7f, 0.6f));
    public static final Item RIFT_LANCE = register("rift_lance",
            new ATAGunItem(new Item.Settings().maxCount(1).maxDamage(400).rarity(Rarity.EPIC), 12, 45, 3.0f, 0.0f, 2.2f));

    // Torchwood Pistol: bullets, not staser bolts
    public static final Item TORCHWOOD_PISTOL = register("torchwood_pistol",
            new TorchwoodPistolItem(new Item.Settings().maxCount(1).maxDamage(500).rarity(Rarity.UNCOMMON), 12, 6, 1.0f, 0.05f));
    public static final Item BULLET_MAGAZINE = register("bullet_magazine",
            new BulletMagazineItem(new Item.Settings().maxCount(1), 12));
    public static final Item BULLET = register("bullet", new Item(new Item.Settings()));

    public static final EntityType<BulletEntity> BULLET_ENTITY = Registry.register(Registries.ENTITY_TYPE, ATA.id("bullet"),
            FabricEntityTypeBuilder.<BulletEntity>create(SpawnGroup.MISC, BulletEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackRangeChunks(4).trackedUpdateRate(20).build());

    private ATAGuns() {
    }

    public static void register() {
        // Loading the class registers the guns.
        TorchwoodPistolItem.registerServer();
    }

    private static Item register(String name, Item item) {
        ATAItems.ALL.add(Registry.register(Registries.ITEM, ATA.id(name), item));
        return item;
    }
}
