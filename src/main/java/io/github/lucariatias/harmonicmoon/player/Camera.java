package io.github.lucariatias.harmonicmoon.player;

import io.github.lucariatias.harmonicmoon.world.Vector;
import io.github.lucariatias.harmonicmoon.world.WorldLocation;

public class Camera {

    private WorldLocation location;
    private Player player;
    private static final double SPEED = 0.05D;

    public Camera(Player player) {
        this.player = player;
        this.location = player.getCharacter().getWorldInfo().getLocation().getRelative(new Vector(-320, -240));
    }

    public void onTick() {
        int targetX = player.getCharacter().getWorldInfo().getLocation().getX() - 320;
        int targetY = player.getCharacter().getWorldInfo().getLocation().getY() - 240;
        location = location.getRelative(new Vector((int) Math.round((double) (targetX - location.getX()) * SPEED), (int) Math.round((double) (targetY - location.getY()) * SPEED)));
        //location = new WorldLocation(player.getCharacter().getWorldInfo().getLocation().getWorld(), player.getCharacter().getWorldInfo().getLocation().getX() - (WorldPanel.WIDTH / 2), player.getCharacter().getWorldInfo().getLocation().getY() - (WorldPanel.HEIGHT / 2));
    }

    public WorldLocation getLocation() {
        return location;
    }

    public void setLocation(WorldLocation location) {
        this.location = location;
    }

}
