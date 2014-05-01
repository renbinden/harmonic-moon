package io.github.lucariatias.harmonicmoon.block;

import io.github.lucariatias.harmonicmoon.world.WorldLocation;
import io.github.lucariatias.harmonicmoon.world.WorldObject;

import java.awt.*;

public class Block extends WorldObject {

    public Block() {
        setSolid(true);
    }

    @Override
    public void onTick() {

    }

    @Override
    public void render(Graphics graphics) {
        /*graphics.setColor(Color.GRAY);
        graphics.fillRect(getLocation().getX(), getLocation().getY(), 16, 16);
        graphics.setColor(Color.WHITE);
        graphics.drawRect(getLocation().getX(), getLocation().getY(), 16, 16);*/
    }

    @Override
    public Rectangle getBoundsAtPosition(WorldLocation location) {
        return new Rectangle(location.getX(), location.getY(), 16, 16);
    }

}
