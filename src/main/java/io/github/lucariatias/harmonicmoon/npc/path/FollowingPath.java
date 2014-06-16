package io.github.lucariatias.harmonicmoon.npc.path;

import io.github.lucariatias.harmonicmoon.character.WorldCharacter;
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
            WorldCharacter character = player.getCharacter().world();
            WorldLocation characterLocation = character.getLocation();
            if (getNpc().getLocation().distanceSquared(characterLocation) <= sightDistance * sightDistance) {
                if (getNpc().getLocation().getX() < characterLocation.getX()) {
                    getNpc().move(Direction.RIGHT);
                } else if (getNpc().getLocation().getX() > characterLocation.getX()) {
                    getNpc().move(Direction.LEFT);
                } else if (getNpc().getLocation().getY() < characterLocation.getY()) {
                    getNpc().move(Direction.DOWN);
                } else if (getNpc().getLocation().getY() > characterLocation.getY()) {
                    getNpc().move(Direction.UP);
                }
            }
        }
    }
}
