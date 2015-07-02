package com.seventh_root.harmonicmoon.event.npc;

import com.seventh_root.harmonicmoon.event.Listener;

public abstract class NPCMoveListener extends Listener<NPCMoveEvent> {

    public NPCMoveListener() {
        super(NPCMoveEvent.class);
    }

    public abstract void onNPCMove(NPCMoveEvent event);

    @Override
    public void onEvent(NPCMoveEvent event) {
        onNPCMove(event);
    }

}
