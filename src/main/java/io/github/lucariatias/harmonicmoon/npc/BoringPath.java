package io.github.lucariatias.harmonicmoon.npc;

import io.github.lucariatias.harmonicmoon.world.Direction;
import io.github.lucariatias.harmonicmoon.world.WorldLocation;

import java.util.Random;

public class BoringPath extends Path {

    private WorldLocation neutralPosition;
    private int tickDelay;

    public BoringPath(WorldLocation neutralPosition) {
        this.neutralPosition = neutralPosition;
    }

    @Override
    public void step(NPC npc) {
        if (npc.getLocation().distanceSquared(neutralPosition) <= 8) {
            if (tickDelay >= 40) {
                Random random = new Random();
                switch (random.nextInt(4)) {
                    case 0: npc.move(Direction.UP); break;
                    case 1: npc.move(Direction.DOWN); break;
                    case 2: npc.move(Direction.LEFT); break;
                    case 3: npc.move(Direction.RIGHT); break;
                }
                tickDelay = 0;
            } else {
                tickDelay++;
            }
        } else if (npc.getLocation().getRelative(Direction.UP, 16).distanceSquared(neutralPosition) <= 8) {
            if (tickDelay >= 40) {
                npc.move(Direction.UP);
                tickDelay = 0;
            } else {
                tickDelay++;
            }
        } else if (npc.getLocation().getRelative(Direction.DOWN, 16).distanceSquared(neutralPosition) <= 8) {
            if (tickDelay >= 40) {
                npc.move(Direction.DOWN);
                tickDelay = 0;
            } else {
                tickDelay++;
            }
        } else if (npc.getLocation().getRelative(Direction.RIGHT, 16).distanceSquared(neutralPosition) <= 8) {
            if (tickDelay >= 40) {
                npc.move(Direction.RIGHT);
                tickDelay = 0;
            } else {
                tickDelay++;
            }
        } else if (npc.getLocation().getRelative(Direction.LEFT, 16).distanceSquared(neutralPosition) <= 8) {
            if (tickDelay >= 40) {
                npc.move(Direction.LEFT);
                tickDelay = 0;
            } else {
                tickDelay++;
            }
        }
    }

}
