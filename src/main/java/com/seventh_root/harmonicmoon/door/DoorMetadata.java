package com.seventh_root.harmonicmoon.door;

import com.seventh_root.harmonicmoon.HarmonicMoon;
import com.seventh_root.harmonicmoon.world.WorldLocation;
import com.seventh_root.harmonicmoon.world.WorldObjectMetadata;

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
