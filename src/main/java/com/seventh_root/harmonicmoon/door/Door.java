package com.seventh_root.harmonicmoon.door;

import com.seventh_root.harmonicmoon.world.MovementState;
import com.seventh_root.harmonicmoon.world.WorldLocation;
import com.seventh_root.harmonicmoon.world.WorldObject;
import com.seventh_root.harmonicmoon.HarmonicMoon;

import java.awt.*;

public class Door extends WorldObject {

    private HarmonicMoon harmonicMoon;

    private DoorMetadata metadata;

    public Door(HarmonicMoon harmonicMoon, DoorMetadata metadata) {
        this.harmonicMoon = harmonicMoon;
        this.metadata = metadata;
        setSolid(true);
    }

    @Override
    public void onTick() {}

    @Override
    public void render(Graphics graphics) {}

    @Override
    public Rectangle getBoundsAtPosition(WorldLocation location, MovementState movementState) {
        return new Rectangle(location.getX(), location.getY(), 16, 16);
    }

    @Override
    public void interact() {
        if (metadata != null) {
            WorldLocation entryLocation = metadata.getEntryLocation(harmonicMoon);
            harmonicMoon.getWorldPanel().getWorld().removeObject(harmonicMoon.getWorldPanel().getPlayer().getCharacter().world());
            harmonicMoon.showWorld(entryLocation.getWorld().getName());
            harmonicMoon.getWorldPanel().getPlayer().getCharacter().world().setLocation(entryLocation);
            entryLocation.getWorld().addObject(harmonicMoon.getPlayer().getCharacter().world());
        }
    }

}
