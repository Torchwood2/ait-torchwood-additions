package uk.jacobempire.ata.gun;

import dev.amble.ait.module.gun.core.item.StaserBoltMagazine;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ClickType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import uk.jacobempire.ata.ATA;

/**
 * The Torchwood Pistol: a real gun. It aims down sights like AIT's guns, but fires {@link BulletEntity bullets}
 * instead of staser bolts and loads from a {@link BulletMagazineItem Bullet Magazine} (right-click the magazine onto
 * the pistol in your inventory). AIT's staser magazines don't fit it.
 */
public class TorchwoodPistolItem extends ATAGunItem {
    public static final Identifier SHOOT = ATA.id("shoot_bullet");
    /** Bullet speed (an arrow from a fully drawn bow is 3, a staser bolt 4). */
    private static final float SPEED = 4.5f;
    /** Arrow-style damage, multiplied by speed: about 2.2 x 4.5 = 10 (5 hearts). */
    private static final double DAMAGE = 2.2;

    public TorchwoodPistolItem(Settings settings, int maxAmmo, int cooldown, float hipSpread, float aimSpread) {
        super(settings, maxAmmo, cooldown, hipSpread, aimSpread, 1.0f);
    }

    /** Fires on the server, when the client says the attack key is down (sent from {@link #tryShoot}). */
    public static void registerServer() {
        net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.registerGlobalReceiver(SHOOT,
                (server, player, handler, buf, responseSender) -> {
                    boolean aiming = buf.readBoolean();
                    server.execute(() -> {
                        if (player.getMainHandStack().getItem() instanceof TorchwoodPistolItem pistol) {
                            pistol.fire(player, player.getMainHandStack(), aiming);
                        }
                    });
                });
    }

    private void fire(ServerPlayerEntity player, ItemStack stack, boolean aiming) {
        if (player.getItemCooldownManager().isCoolingDown(this)) {
            return;
        }
        World world = player.getWorld();
        player.getItemCooldownManager().set(this, this.getCooldown());
        if (this.getCurrentAmmo(stack) <= 0) {
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF,
                    SoundCategory.PLAYERS, 1.0f, 1.4f);
            return;
        }

        BulletEntity bullet = new BulletEntity(world, player);
        bullet.setVelocity(player, player.getPitch(), player.getYaw(), 0.0f, SPEED, this.getAimDeviation(aiming));
        bullet.setDamage(DAMAGE);
        world.spawnEntity(bullet);

        this.setCurrentAmmo(this.getCurrentAmmo(stack) - 1, stack);
        stack.damage(1, player, p -> p.sendToolBreakStatus(net.minecraft.util.Hand.MAIN_HAND));
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_GENERIC_EXPLODE,
                SoundCategory.PLAYERS, 0.35f, 1.9f + world.random.nextFloat() * 0.2f);
    }

    /** Replaces AIT's shooting (which always fires staser bolts) with ours. */
    @Override
    @Environment(EnvType.CLIENT)
    public void tryShoot(World world, Entity entity, boolean selected) {
        if (!world.isClient() || !(entity instanceof PlayerEntity) || !selected) {
            return;
        }
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.options.attackKey.isPressed()) {
            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeBoolean(client.options.useKey.isPressed());
            ClientPlayNetworking.send(SHOOT, buf);
        }
        client.options.attackKey.setPressed(false);
    }

    /** Right-click a Bullet Magazine onto the pistol to load it. Staser magazines don't fit. */
    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player,
                             StackReference cursorStackReference) {
        if (otherStack.getItem() instanceof BulletMagazineItem magazine) {
            if (clickType != ClickType.RIGHT) {
                return false;
            }
            int space = (int) (this.getMaxAmmo() - this.getCurrentAmmo(stack));
            int moved = Math.min(space, magazine.getRounds(otherStack));
            if (moved > 0) {
                this.setCurrentAmmo(this.getCurrentAmmo(stack) + moved, stack);
                magazine.setRounds(otherStack, magazine.getRounds(otherStack) - moved);
                player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.8f, 1.6f);
            }
            return true;
        }
        if (otherStack.getItem() instanceof StaserBoltMagazine) {
            return false;
        }
        return super.onClicked(stack, otherStack, slot, clickType, player, cursorStackReference);
    }
}
