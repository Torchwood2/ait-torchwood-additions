package uk.jacobempire.ata.chair;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/** The invisible thing you sit on in a chair. It goes away when you get up or the chair is broken. */
public class SeatEntity extends Entity {
    public SeatEntity(EntityType<? extends SeatEntity> type, World world) {
        super(type, world);
        this.noClip = true;
    }

    public SeatEntity(World world, BlockPos pos, Direction facing) {
        this(ATAChairs.SEAT, world);
        this.setPosition(pos.getX() + 0.5, pos.getY() + 0.2, pos.getZ() + 0.5);
        this.setYaw(facing.asRotation());
    }

    @Override
    public void tick() {
        super.tick();
        if (getWorld().isClient) return;
        BlockPos pos = getBlockPos();
        if (!hasPassengers() || !(getWorld().getBlockState(pos).getBlock() instanceof ChairBlock)) {
            discard();
        }
    }

    @Override
    public double getMountedHeightOffset() {
        return 0.0;
    }

    @Override
    public Vec3d updatePassengerForDismount(LivingEntity passenger) {
        // Stand up on top of the chair rather than inside it.
        return new Vec3d(getX(), getBlockPos().getY() + 1.0, getZ());
    }

    @Override
    protected void initDataTracker() {
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }
}
