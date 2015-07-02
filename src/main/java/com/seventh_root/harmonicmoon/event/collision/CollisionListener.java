package com.seventh_root.harmonicmoon.event.collision;

import com.seventh_root.harmonicmoon.event.Listener;

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
