package com.seventh_root.harmonicmoon.npc.path;

import com.seventh_root.harmonicmoon.npc.NPC;

public abstract class Path {

    private NPC npc;
    private boolean frozen;

    public Path(NPC npc) {
        this.npc = npc;
    }

    public abstract void step();

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public NPC getNPC() {
        return npc;
    }
}
