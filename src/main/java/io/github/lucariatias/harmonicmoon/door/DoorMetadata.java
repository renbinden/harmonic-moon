package io.github.lucariatias.harmonicmoon.door;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.world.WorldLocation;
import io.github.lucariatias.harmonicmoon.world.WorldObjectMetadata;

public class DoorMetadata extends WorldObjectMetadata {

    private DoorEntryLocation entryLocation;

    public DoorMetadata(HarmonicMoon harmonicMoon, DoorMetadata original) {
        entryLocation = new DoorEntryLocation(original.entryLocation);
    }

    public DoorMetadata() {}

    public WorldLocation getEntryLocation(HarmonicMoon harmonicMoon) {
        return entryLocation.toWorldLocation(harmonicMoon);
    }

}
