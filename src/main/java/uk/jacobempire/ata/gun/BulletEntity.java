package uk.jacobempire.ata.gun;

import net.minecraft.block.BlockState;
import net.minecraft.block.GlassBlock;
import net.minecraft.block.PaneBlock;
import net.minecraft.block.StainedGlassBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

/**
 * A bullet from the Torchwood Pistol. Flies straight, breaks glass, can't be picked up, and is gone when it hits
 * something. Drawn as a gold version of AIT's staser bolt.
 */
public class BulletEntity extends PersistentProjectileEntity {
    /** How long a bullet can fly before it's removed, in ticks. */
    private static final int MAX_AGE = 80;

    public BulletEntity(EntityType<? extends BulletEntity> type, World world) {
        super(type, world);
        this.pickupType = PickupPermission.DISALLOWED;
    }

    public BulletEntity(World world, LivingEntity owner) {
        super(ATAGuns.BULLET_ENTITY, owner, world);
        this.pickupType = PickupPermission.DISALLOWED;
        this.setPosition(owner.getX(), owner.getEyeY() - 0.15, owner.getZ());
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.getWorld().isClient && this.age > MAX_AGE) {
            this.discard();
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult hit) {
        World world = this.getWorld();
        BlockState state = world.getBlockState(hit.getBlockPos());
        if (!world.isClient) {
            if (state.getBlock() instanceof GlassBlock || state.getBlock() instanceof StainedGlassBlock
                    || state.getBlock() instanceof PaneBlock) {
                world.breakBlock(hit.getBlockPos(), false);
            } else {
                world.playSound(null, hit.getBlockPos(), state.getSoundGroup().getHitSound(), SoundCategory.BLOCKS,
                        0.6f, 1.4f);
            }
            this.discard();
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult hit) {
        // Arrows stay stuck in whatever they hit; bullets shouldn't.
        int stuck = hit.getEntity() instanceof LivingEntity living ? living.getStuckArrowCount() : 0;
        super.onEntityHit(hit);
        if (hit.getEntity() instanceof LivingEntity living) {
            living.setStuckArrowCount(stuck);
        }
    }

    @Override
    protected SoundEvent getHitSound() {
        return SoundEvents.ENTITY_ARROW_HIT;
    }

    @Override
    protected ItemStack asItemStack() {
        return ItemStack.EMPTY;
    }
}
