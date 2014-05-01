package io.github.lucariatias.harmonicmoon.event.tick;

import io.github.lucariatias.harmonicmoon.event.Listener;

public abstract class TickListener extends Listener<TickEvent> {

    public TickListener() {
        super(TickEvent.class);
    }

    @Override
    public void onEvent(TickEvent event) {
        onTick(event);
    }

    public abstract void onTick(TickEvent event);

}
