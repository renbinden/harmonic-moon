package com.seventh_root.harmonicmoon.door;

import com.seventh_root.harmonicmoon.world.World;
import com.seventh_root.harmonicmoon.HarmonicMoon;
import com.seventh_root.harmonicmoon.world.WorldLocation;

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
