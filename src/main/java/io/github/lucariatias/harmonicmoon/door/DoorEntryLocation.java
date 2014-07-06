package io.github.lucariatias.harmonicmoon.door;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.world.World;
import io.github.lucariatias.harmonicmoon.world.WorldLocation;

public class DoorEntryLocation {

    private String worldName;
    private int x;
    private int y;

    public DoorEntryLocation(DoorEntryLocation original) {
        this.worldName = original.worldName;
        this.x = original.x;
        this.y = original.y;
    }

    public WorldLocation toWorldLocation(HarmonicMoon harmonicMoon) {
        World world = harmonicMoon.getWorld(worldName);
        return new WorldLocation(world, x * world.getTileSheets().get(0).getTileWidth(), y * world.getTileSheets().get(0).getTileHeight());
    }

}
