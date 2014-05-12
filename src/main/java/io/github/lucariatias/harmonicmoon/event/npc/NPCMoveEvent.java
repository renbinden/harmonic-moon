package io.github.lucariatias.harmonicmoon.event.npc;

import io.github.lucariatias.harmonicmoon.event.Cancellable;
import io.github.lucariatias.harmonicmoon.event.Event;
import io.github.lucariatias.harmonicmoon.npc.NPC;
import io.github.lucariatias.harmonicmoon.world.WorldLocation;

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
