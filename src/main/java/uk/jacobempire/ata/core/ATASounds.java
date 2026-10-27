package uk.jacobempire.ata.core;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import uk.jacobempire.ata.ATA;

/**
 * Sound events, so they can be used anywhere a SoundEvent is needed (and with /playsound). The TARDIS
 * travel sounds are hooked up through data/ata/fx; AIT finds those by id.
 */
public final class ATASounds {
    public static final SoundEvent TYPE70_DEMAT = register("tardis/type70_demat");
    public static final SoundEvent TYPE70_MAT = register("tardis/type70_mat");
    public static final SoundEvent TYPE70_FLIGHT = register("tardis/type70_flight");
    public static final SoundEvent PARADOX_MAT = register("tardis/paradox_mat");
    public static final SoundEvent EMERGENCY_LAND = register("tardis/emergency_land");
    public static final SoundEvent POLICE_BOX_DOOR_OPEN = register("tardis/police_box_door_open");
    public static final SoundEvent POLICE_BOX_DOOR_CLOSE = register("tardis/police_box_door_close");
    public static final SoundEvent BLOOP = register("bloop");

    private ATASounds() {
    }

    public static void register() {
    }

    private static SoundEvent register(String name) {
        return Registry.register(Registries.SOUND_EVENT, ATA.id(name), SoundEvent.of(ATA.id(name)));
    }
}
