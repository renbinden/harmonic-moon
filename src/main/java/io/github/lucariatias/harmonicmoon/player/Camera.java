package io.github.lucariatias.harmonicmoon.player;

import io.github.lucariatias.harmonicmoon.world.Vector;
import io.github.lucariatias.harmonicmoon.world.WorldLocation;

public class Camera {

    private WorldLocation location;
    private Player player;

    private boolean smoothMovementEnabled;
    private double speed = 0.05D;

    public Camera(Player player) {
        this.player = player;
        this.location = player.getCharacter().world().getLocation().getRelative(new Vector(-320, -240));
    }

    public void onTick() {
        if (smoothMovementEnabled) {
            int targetX = player.getCharacter().world().getLocation().getX() - 320;
            int targetY = player.getCharacter().world().getLocation().getY() - 240;
            location = location.getRelative(new Vector((int) Math.round((double) (targetX - location.getX()) * speed), (int) Math.round((double) (targetY - location.getY()) * speed)));
        } else {
            location = new WorldLocation(player.getCharacter().world().getLocation().getWorld(), player.getCharacter().world().getLocation().getX() - 320, player.getCharacter().world().getLocation().getY() - 240);
        }
    }

    public WorldLocation getLocation() {
        return location;
    }

    public void setLocation(WorldLocation location) {
        this.location = location;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
