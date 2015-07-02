package com.seventh_root.harmonicmoon.event.npc;

import com.seventh_root.harmonicmoon.event.Cancellable;
import com.seventh_root.harmonicmoon.event.Event;
import com.seventh_root.harmonicmoon.npc.NPC;
import com.seventh_root.harmonicmoon.world.WorldLocation;

public class NPCMoveEvent extends Event implements Cancellable {

    private NPC npc;
    private WorldLocation from;
    private WorldLocation to;
    private boolean cancelled;

    public NPCMoveEvent(NPC npc, WorldLocation from, WorldLocation to) {
        this.npc = npc;
        this.from = from;
        this.to = to;
    }

    public NPC getNpc() {
        return npc;
    }

    public WorldLocation getFrom() {
        return from;
    }

    public WorldLocation getTo() {
        return to;
    }

    public void setTo(WorldLocation to) {
        this.to = to;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
