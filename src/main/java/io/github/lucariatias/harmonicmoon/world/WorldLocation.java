package io.github.lucariatias.harmonicmoon.world;

public class WorldLocation {

    private final World world;
    private final int x;
    private final int y;

    public WorldLocation(World world, int x, int y) {
        this.world = world;
        this.x = x;
        this.y = y;
    }

    public WorldLocation(WorldLocation original) {
        this.world = original.world;
        this.x = original.x;
        this.y = original.y;
    }

    public World getWorld() {
        return world;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public WorldLocation getRelative(Direction direction) {
        return getRelative(direction, 1);
    }

    public WorldLocation getRelative(Direction direction, int amount) {
        return getRelative(direction.asVector(), amount);
    }

    public WorldLocation getRelative(Vector vector) {
        return getRelative(vector, 1);
    }

    public WorldLocation getRelative(Vector vector, int amount) {
        return new WorldLocation(world, x + (vector.getHorizontalComponent() * amount), y + (vector.getVerticalComponent() * amount));
    }

    public int distanceSquared(WorldLocation location) {
        int xDiff = location.getX() - x;
        int yDiff = location.getY() - y;
        return (xDiff * xDiff) + (yDiff * yDiff);
    }

    public int distance(WorldLocation location) {
        return (int) Math.round(Math.sqrt(distanceSquared(location)));
    }

}
