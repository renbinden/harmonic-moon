package io.github.lucariatias.harmonicmoon.player;

import io.github.lucariatias.harmonicmoon.world.WorldLocation;
import io.github.lucariatias.harmonicmoon.world.WorldPanel;

public class Camera {

    private WorldLocation location;
    private Player player;

    public Camera(Player player) {
        this.player = player;
        this.location = player.getCharacter().getWorldInfo().getLocation();
    }

    public void onTick() {
        //int targetX = - character.getLocation().getX() + WorldPanel.WIDTH / 2;
        //int targetY = - character.getLocation().getY() + WorldPanel.HEIGHT / 2;
        //location = location.getRelative(new Vector((int) Math.round((double) (targetX - location.getX()) * 0.01D), (int) Math.round((double) (targetY - location.getY()) * 0.01D)));
        location = new WorldLocation(player.getCharacter().getWorldInfo().getLocation().getWorld(), player.getCharacter().getWorldInfo().getLocation().getX() - (WorldPanel.WIDTH / 2), player.getCharacter().getWorldInfo().getLocation().getY() - (WorldPanel.HEIGHT / 2));
    }

    public WorldLocation getLocation() {
        return location;
    }

    public void setLocation(WorldLocation location) {
        this.location = location;
    }

}
