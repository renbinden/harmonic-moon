package io.github.lucariatias.harmonicmoon.npc.path;

import io.github.lucariatias.harmonicmoon.npc.NPC;
import io.github.lucariatias.harmonicmoon.world.Direction;

public class UnmovingPath extends Path {

    public UnmovingPath(NPC npc) {
        super(npc);
    }

    @Override
    public void step() {
        if (!isFrozen()) getNPC().face(Direction.DOWN);
    }

}
