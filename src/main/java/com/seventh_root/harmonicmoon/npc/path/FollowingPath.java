package com.seventh_root.harmonicmoon.npc.path;

import com.seventh_root.harmonicmoon.character.Character;
import com.seventh_root.harmonicmoon.npc.NPC;
import com.seventh_root.harmonicmoon.player.Player;
import com.seventh_root.harmonicmoon.world.Direction;
import com.seventh_root.harmonicmoon.world.WorldLocation;

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
