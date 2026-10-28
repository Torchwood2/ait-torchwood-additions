package uk.jacobempire.ata.mixin;

import dev.amble.ait.module.gun.core.entity.StaserBoltEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.hit.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.jacobempire.ata.gun.ATAGunItem;

/**
 * AIT's staser bolt sets its own damage just before it hits something. For bolts fired from an ATA gun, scale
 * that by the gun's damage multiplier.
 */
@Mixin(StaserBoltEntity.class)
public abstract class StaserBoltDamageMixin {
    @Inject(method = "onCollision", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/entity/projectile/PersistentProjectileEntity;onCollision(Lnet/minecraft/util/hit/HitResult;)V"))
    private void ata$gunDamage(HitResult hitResult, CallbackInfo ci) {
        PersistentProjectileEntity self = (PersistentProjectileEntity) (Object) this;
        Entity owner = self.getOwner();
        if (owner instanceof PlayerEntity player && player.getMainHandStack().getItem() instanceof ATAGunItem gun) {
            self.setDamage(self.getDamage() * gun.getDamageMultiplier());
        }
    }
}
