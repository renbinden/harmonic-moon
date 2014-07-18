package io.github.lucariatias.harmonicmoon.npc.path;

import io.github.lucariatias.harmonicmoon.character.Character;
import io.github.lucariatias.harmonicmoon.npc.NPC;
import io.github.lucariatias.harmonicmoon.player.Player;
import io.github.lucariatias.harmonicmoon.world.Direction;
import io.github.lucariatias.harmonicmoon.world.WorldLocation;

public class FollowingPath extends Path {

    private Player player;
    private int sightDistance;

    public FollowingPath(NPC npc, Player player, int sightDistance) {
        super(npc);
        this.player = player;
        this.sightDistance = sightDistance;
    }

    @Override
    public void step() {
        if (!isFrozen()) {
            Character.World character = player.getCharacter().world();
            WorldLocation characterLocation = character.getLocation();
            if (getNPC().getLocation().distanceSquared(characterLocation) <= sightDistance * sightDistance) {
                if (getNPC().getLocation().getX() < characterLocation.getX()) {
                    getNPC().move(Direction.RIGHT);
                } else if (getNPC().getLocation().getX() > characterLocation.getX()) {
                    getNPC().move(Direction.LEFT);
                } else if (getNPC().getLocation().getY() < characterLocation.getY()) {
                    getNPC().move(Direction.DOWN);
                } else if (getNPC().getLocation().getY() > characterLocation.getY()) {
                    getNPC().move(Direction.UP);
                }
            }
        }
    }
}
