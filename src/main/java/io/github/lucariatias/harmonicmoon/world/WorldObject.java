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

    public boolean isCollision(Direction direction) {
        return isCollision(direction, true);
    }

    public boolean isCollision(Direction direction, boolean solidOnly) {
        return getCollision(direction, solidOnly) != null;
    }

    public WorldObject getCollision(Direction direction) {
        return getCollision(direction, true);
    }

    public WorldObject getCollision(Direction direction, boolean solidOnly) {
        WorldLocation relativeLocation = getLocation().getRelative(direction, 8);
        Rectangle relativeBounds = getBoundsAtPosition(relativeLocation);
        for (WorldObject object : getLocation().getWorld().getObjects()) {
            if (object.getLocation().distanceSquared(getLocation()) > 800) continue;
            if (relativeBounds.intersects(object.getBounds()) && (!solidOnly || object.isSolid()) && object != this) return object;
        }
        return null;
    }

    public void interact() {}

}
