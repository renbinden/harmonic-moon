package com.seventh_root.harmonicmoon.npc.path;

import com.seventh_root.harmonicmoon.npc.NPC;
import com.seventh_root.harmonicmoon.world.Direction;

public class UnmovingPath extends Path {

    public UnmovingPath(NPC npc) {
        super(npc);
    }

    @Override
    public void step() {
        if (!isFrozen()) getNPC().face(Direction.DOWN);
    }

}
