package io.github.lucariatias.harmonicmoon.world;

import java.awt.*;

public abstract class WorldObject {

    private boolean solid;

    private WorldLocation location;

    public abstract void onTick();
    public abstract void render(Graphics graphics);

    public Rectangle getBounds() {
        return getBoundsAtPosition(getLocation());
    }

    public abstract Rectangle getBoundsAtPosition(WorldLocation location);

    public boolean isSolid() {
        return solid;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }

    public WorldLocation getLocation() {
        return location;
    }

    public void setLocation(WorldLocation location) {
        this.location = location;
    }
}
