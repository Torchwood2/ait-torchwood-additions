package uk.jacobempire.ata.gun;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * The Bullet Magazine for the Torchwood Pistol. Crafted empty: right-click Bullets onto it to fill it, then
 * right-click it onto the pistol to load. The one in the creative tab comes full.
 */
public class BulletMagazineItem extends Item {
    public static final String ROUNDS_KEY = "rounds";
    private final int capacity;

    public BulletMagazineItem(Settings settings, int capacity) {
        super(settings);
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRounds(ItemStack stack) {
        return stack.hasNbt() ? stack.getNbt().getInt(ROUNDS_KEY) : 0;
    }

    public void setRounds(ItemStack stack, int rounds) {
        stack.getOrCreateNbt().putInt(ROUNDS_KEY, MathHelper.clamp(rounds, 0, capacity));
    }

    @Override
    public ItemStack getDefaultStack() {
        ItemStack stack = new ItemStack(this);
        setRounds(stack, capacity);
        return stack;
    }

    /** Right-click Bullets onto the magazine to load them. */
    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player,
                             StackReference cursorStackReference) {
        if (clickType == ClickType.RIGHT && otherStack.isOf(ATAGuns.BULLET)) {
            int moved = Math.min(capacity - getRounds(stack), otherStack.getCount());
            if (moved > 0) {
                setRounds(stack, getRounds(stack) + moved);
                otherStack.decrement(moved);
                player.playSound(SoundEvents.BLOCK_CHAIN_PLACE, 0.6f, 1.5f);
            }
            return true;
        }
        return super.onClicked(stack, otherStack, slot, clickType, player, cursorStackReference);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return getRounds(stack) < capacity;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        return Math.round(13.0f * getRounds(stack) / capacity);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return 0xD9A441;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        int rounds = getRounds(stack);
        tooltip.add(Text.translatable("tooltip.ata.bullet_magazine.rounds", rounds, capacity)
                .formatted(rounds > capacity / 4 ? Formatting.GOLD : Formatting.RED));
        tooltip.add(Text.translatable("tooltip.ata.bullet_magazine.help").formatted(Formatting.DARK_GRAY));
    }
}
