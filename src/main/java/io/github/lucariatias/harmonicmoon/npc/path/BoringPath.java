package io.github.lucariatias.harmonicmoon.npc.path;

import io.github.lucariatias.harmonicmoon.npc.NPC;
import io.github.lucariatias.harmonicmoon.world.Direction;
import io.github.lucariatias.harmonicmoon.world.WorldLocation;

import java.util.Random;

public class BoringPath extends Path {

    private WorldLocation neutralPosition;
    private int tickDelay;

    public BoringPath(NPC npc) {
        super(npc);
        this.neutralPosition = npc.getLocation();
    }

    public void setNeutralPosition() {
        this.neutralPosition = getNpc().getLocation();
    }

    @Override
    public void step() {
        if (!isFrozen()) {
            if (getNpc().getLocation().distanceSquared(neutralPosition) <= 8) {
                if (tickDelay >= 40) {
                    Random random = new Random();
                    switch (random.nextInt(4)) {
                        case 0:
                            getNpc().move(Direction.UP);
                            break;
                        case 1:
                            getNpc().move(Direction.DOWN);
                            break;
                        case 2:
                            getNpc().move(Direction.LEFT);
                            break;
                        case 3:
                            getNpc().move(Direction.RIGHT);
                            break;
                    }
                    tickDelay = 0;
                } else {
                    tickDelay++;
                }
            } else if (getNpc().getLocation().getRelative(Direction.UP, 16).distanceSquared(neutralPosition) <= 8) {
                if (tickDelay >= 40) {
                    getNpc().move(Direction.UP);
                    tickDelay = 0;
                } else {
                    tickDelay++;
                }
            } else if (getNpc().getLocation().getRelative(Direction.DOWN, 16).distanceSquared(neutralPosition) <= 8) {
                if (tickDelay >= 40) {
                    getNpc().move(Direction.DOWN);
                    tickDelay = 0;
                } else {
                    tickDelay++;
                }
            } else if (getNpc().getLocation().getRelative(Direction.RIGHT, 16).distanceSquared(neutralPosition) <= 8) {
                if (tickDelay >= 40) {
                    getNpc().move(Direction.RIGHT);
                    tickDelay = 0;
                } else {
                    tickDelay++;
                }
            } else if (getNpc().getLocation().getRelative(Direction.LEFT, 16).distanceSquared(neutralPosition) <= 8) {
                if (tickDelay >= 40) {
                    getNpc().move(Direction.LEFT);
                    tickDelay = 0;
                } else {
                    tickDelay++;
                }
            }
        }
    }

}
