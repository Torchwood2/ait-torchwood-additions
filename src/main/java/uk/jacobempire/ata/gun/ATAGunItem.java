package uk.jacobempire.ata.gun;

import dev.amble.ait.module.gun.core.item.BaseGunItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * An ATA gun on top of AIT's gun system: it fires AIT's staser bolts, loads from AIT's Staser Bolt Magazine
 * (click the magazine onto the gun in your inventory) and aims down sights with use, like AIT's guns, but with
 * its own magazine size, fire rate, accuracy and damage.
 *
 * @param damageMultiplier how hard each bolt hits, compared with AIT's Cult Staser
 */
public class ATAGunItem extends BaseGunItem {
    private final int maxAmmo;
    private final int cooldown;
    private final float hipSpread;
    private final float aimSpread;
    private final float damageMultiplier;

    public ATAGunItem(Settings settings, int maxAmmo, int cooldown, float hipSpread, float aimSpread, float damageMultiplier) {
        super(settings);
        this.maxAmmo = maxAmmo;
        this.cooldown = cooldown;
        this.hipSpread = hipSpread;
        this.aimSpread = aimSpread;
        this.damageMultiplier = damageMultiplier;
    }

    public float getDamageMultiplier() {
        return damageMultiplier;
    }

    @Override
    public double getMaxAmmo() {
        return maxAmmo;
    }

    @Override
    public int getCooldown() {
        return cooldown;
    }

    @Override
    public float getAimDeviation(boolean isAds) {
        return isAds ? aimSpread : hipSpread;
    }

    /** Repaired in an anvil with Rift Shards. */
    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(uk.jacobempire.ata.core.ATARift.RIFT_SHARD);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(Text.translatable("tooltip.ata.gun.stats",
                        String.format("%.1f", 20.0 / cooldown), String.format("×%.1f", damageMultiplier))
                .formatted(Formatting.DARK_GRAY));
    }
}
