package io.github.lucariatias.harmonicmoon.event.collision;

import io.github.lucariatias.harmonicmoon.event.Listener;

public abstract class CollisionListener extends Listener<CollisionEvent> {

    public CollisionListener() {
        super(CollisionEvent.class);
    }

    public abstract void onCollision(CollisionEvent event);

    @Override
    public void onEvent(CollisionEvent event) {
        onCollision(event);
    }

}
