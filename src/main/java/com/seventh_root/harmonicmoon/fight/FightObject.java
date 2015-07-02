package com.seventh_root.harmonicmoon.fight;

import java.awt.*;

public abstract class FightObject {

    private FightLocation location;

    public abstract void onTick();
    public abstract void render(Graphics graphics);

    public FightLocation getLocation() {
        return location;
    }

    public void setLocation(FightLocation location) {
        this.location = location;
    }
}
