package io.github.lucariatias.harmonicmoon.world;

public class DoorMetadata extends WorldObjectMetadata {

    private WorldLocation entryLocation;

    public DoorMetadata(DoorMetadata original) {
        entryLocation = new WorldLocation(original.entryLocation);
    }

    public DoorMetadata() {}

}
