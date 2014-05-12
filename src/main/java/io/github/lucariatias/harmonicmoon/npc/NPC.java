package io.github.lucariatias.harmonicmoon.npc;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.event.npc.NPCMoveEvent;
import io.github.lucariatias.harmonicmoon.sprite.Sprite;
import io.github.lucariatias.harmonicmoon.sprite.SpriteSheet;
import io.github.lucariatias.harmonicmoon.world.Direction;
import io.github.lucariatias.harmonicmoon.world.WorldLocation;
import io.github.lucariatias.harmonicmoon.world.WorldObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.Map;

public abstract class NPC extends WorldObject {

    enum MovementState {
        TRANSITIONING_UP, TRANSITIONING_DOWN, TRANSITIONING_LEFT, TRANSITIONING_RIGHT, WAITING
    }

    private HarmonicMoon harmonicMoon;

    private Sprite sprite;
    private Map<Direction, Sprite> sprites = new EnumMap<>(Direction.class);

    private MovementState movementState;

    private Path path;

    public NPC(HarmonicMoon harmonicMoon, SpriteSheet spriteSheet) {
        this.harmonicMoon = harmonicMoon;
        this.movementState = MovementState.WAITING;
        sprites.put(Direction.DOWN, spriteSheet.getSprite(0, 0, 3));
        sprites.put(Direction.RIGHT, spriteSheet.getSprite(3, 0, 3));
        sprites.put(Direction.LEFT, spriteSheet.getSprite(6, 0, 3));
        sprites.put(Direction.UP, spriteSheet.getSprite(9, 0, 3));
        this.sprite = sprites.get(Direction.DOWN);
    }

    public BufferedImage getImage() {
        return sprite.getImage();
    }

    public void move(Direction direction) {
        if (movementState == MovementState.WAITING) {
            if (!isCollision(direction)) {
                NPCMoveEvent event = new NPCMoveEvent(this, getLocation(), getLocation().getRelative(direction, 16));
                harmonicMoon.getEventManager().dispatchEvent(event);
                if (!event.isCancelled()) {
                    switch (direction) {
                        case UP:
                            movementState = MovementState.TRANSITIONING_UP;
                            setLocation(getLocation().getRelative(direction, 2));
                            break;
                        case DOWN:
                            movementState = MovementState.TRANSITIONING_DOWN;
                            setLocation(getLocation().getRelative(direction, 2));
                            break;
                        case LEFT:
                            movementState = MovementState.TRANSITIONING_LEFT;
                            setLocation(getLocation().getRelative(direction, 2));
                            break;
                        case RIGHT:
                            movementState = MovementState.TRANSITIONING_RIGHT;
                            setLocation(getLocation().getRelative(direction, 2));
                            break;
                    }
                }
            }
            sprite = sprites.get(direction);
        }
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    @Override
    public void onTick() {
        switch (movementState) {
            case WAITING: getPath().step(this); break;
            case TRANSITIONING_UP: setLocation(getLocation().getRelative(Direction.UP, 2)); sprite.onTick(); break;
            case TRANSITIONING_DOWN: setLocation(getLocation().getRelative(Direction.DOWN, 2)); sprite.onTick(); break;
            case TRANSITIONING_LEFT: setLocation(getLocation().getRelative(Direction.LEFT, 2)); sprite.onTick(); break;
            case TRANSITIONING_RIGHT: setLocation(getLocation().getRelative(Direction.RIGHT, 2)); sprite.onTick(); break;
        }
        if (getLocation().getX() % 16 == 0 && getLocation().getY() % 16 == 0) movementState = MovementState.WAITING;
    }

    @Override
    public void render(Graphics graphics) {
        if (harmonicMoon.getWorldPanel().getCamera().getLocation().distanceSquared(getLocation()) < 713728
                && getLocation().getX() >= harmonicMoon.getWorldPanel().getCamera().getLocation().getX() - 32 && getLocation().getY() >= harmonicMoon.getWorldPanel().getCamera().getLocation().getY() - 32)
            graphics.drawImage(getImage(), getLocation().getX(), getLocation().getY() - (getImage().getHeight() / 2), null);
    }

    @Override
    public Rectangle getBoundsAtPosition(WorldLocation location) {
        return new Rectangle(location.getX(), location.getY(), getImage().getWidth(), getImage().getHeight() / 2);
    }

    private boolean isCollision(Direction direction) {
        for (WorldObject object : getLocation().getWorld().getObjects()) {
            WorldLocation relativeLocation = getLocation().getRelative(direction);
            Rectangle relativeBounds = getBoundsAtPosition(relativeLocation);
            if (relativeBounds.intersects(object.getBounds()) && object.isSolid()) {
                return true;
            }
        }
        return false;
    }

}
