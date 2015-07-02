package com.seventh_root.harmonicmoon.event.collision;

import com.seventh_root.harmonicmoon.world.WorldObject;
import com.seventh_root.harmonicmoon.event.Event;

public class CollisionEvent extends Event {

    private WorldObject[] objects;

    public CollisionEvent(WorldObject... objects) {
        this.objects = objects;
    }

    public WorldObject[] getObjects() {
        return objects;
    }

}
