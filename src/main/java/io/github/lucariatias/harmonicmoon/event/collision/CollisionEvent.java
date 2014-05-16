package io.github.lucariatias.harmonicmoon.event.collision;

import io.github.lucariatias.harmonicmoon.event.Event;
import io.github.lucariatias.harmonicmoon.world.WorldObject;

public class CollisionEvent extends Event {

    private WorldObject[] objects;

    public CollisionEvent(WorldObject... objects) {
        this.objects = objects;
    }

    public WorldObject[] getObjects() {
        return objects;
    }

}
