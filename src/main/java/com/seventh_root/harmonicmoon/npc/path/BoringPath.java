package com.seventh_root.harmonicmoon.npc.path;

import com.seventh_root.harmonicmoon.npc.NPC;
import com.seventh_root.harmonicmoon.world.Direction;
import com.seventh_root.harmonicmoon.world.WorldLocation;

import java.util.Random;

public class BoringPath extends Path {

    private WorldLocation neutralPosition;
    private int tickDelay;

    public BoringPath(NPC npc) {
        super(npc);
        this.neutralPosition = npc.getLocation();
    }

    public void setNeutralPosition() {
        this.neutralPosition = getNPC().getLocation();
    }

    @Override
    public void step() {
        if (!isFrozen()) {
            if (getNPC().getLocation().distanceSquared(neutralPosition) <= 8) {
                if (tickDelay >= 40) {
                    Random random = new Random();
                    switch (random.nextInt(4)) {
                        case 0:
                            getNPC().move(Direction.UP);
                            break;
                        case 1:
                            getNPC().move(Direction.DOWN);
                            break;
                        case 2:
                            getNPC().move(Direction.LEFT);
                            break;
                        case 3:
                            getNPC().move(Direction.RIGHT);
                            break;
                    }
                    tickDelay = 0;
                } else {
                    tickDelay++;
                }
            } else if (getNPC().getLocation().getRelative(Direction.UP, 16).distanceSquared(neutralPosition) <= 8) {
                if (tickDelay >= 40) {
                    getNPC().move(Direction.UP);
                    tickDelay = 0;
                } else {
                    tickDelay++;
                }
            } else if (getNPC().getLocation().getRelative(Direction.DOWN, 16).distanceSquared(neutralPosition) <= 8) {
                if (tickDelay >= 40) {
                    getNPC().move(Direction.DOWN);
                    tickDelay = 0;
                } else {
                    tickDelay++;
                }
            } else if (getNPC().getLocation().getRelative(Direction.RIGHT, 16).distanceSquared(neutralPosition) <= 8) {
                if (tickDelay >= 40) {
                    getNPC().move(Direction.RIGHT);
                    tickDelay = 0;
                } else {
                    tickDelay++;
                }
            } else if (getNPC().getLocation().getRelative(Direction.LEFT, 16).distanceSquared(neutralPosition) <= 8) {
                if (tickDelay >= 40) {
                    getNPC().move(Direction.LEFT);
                    tickDelay = 0;
                } else {
                    tickDelay++;
                }
            }
        }
    }

}
