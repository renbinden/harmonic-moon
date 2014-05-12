package io.github.lucariatias.harmonicmoon.character;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.event.character.CharacterMoveEvent;
import io.github.lucariatias.harmonicmoon.sprite.Sprite;
import io.github.lucariatias.harmonicmoon.sprite.SpriteSheet;
import io.github.lucariatias.harmonicmoon.world.Direction;
import io.github.lucariatias.harmonicmoon.world.WorldLocation;
import io.github.lucariatias.harmonicmoon.world.WorldObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.Map;

public class CharacterWorldInfo extends WorldObject {

    enum MovementState {
        TRANSITIONING_UP, TRANSITIONING_DOWN, TRANSITIONING_LEFT, TRANSITIONING_RIGHT, WAITING
    }

    private HarmonicMoon harmonicMoon;

    private Character character;

    // Graphics
    private SpriteSheet spriteSheet;
    private Sprite sprite;
    private Map<Direction, Sprite> sprites = new EnumMap<>(Direction.class);

    private MovementState movementState;

    public CharacterWorldInfo(HarmonicMoon harmonicMoon, Character character, SpriteSheet spriteSheet) {
        this.harmonicMoon = harmonicMoon;
        this.character = character;
        this.movementState = MovementState.WAITING;
        this.spriteSheet = spriteSheet;
        sprites.put(Direction.DOWN, spriteSheet.getSprite(0, 0, 3));
        sprites.put(Direction.RIGHT, spriteSheet.getSprite(3, 0, 3));
        sprites.put(Direction.LEFT, spriteSheet.getSprite(6, 0, 3));
        sprites.put(Direction.UP, spriteSheet.getSprite(9, 0, 3));
        this.sprite = sprites.get(Direction.DOWN);
        setSolid(true);
    }

    public BufferedImage getImage() {
        return sprite.getImage();
    }

    public void move(Direction direction) {
        if (movementState == MovementState.WAITING) {
            if (!isCollision(direction)) {
                CharacterMoveEvent event = new CharacterMoveEvent(character, getLocation(), getLocation().getRelative(direction, 16));
                harmonicMoon.getEventManager().dispatchEvent(event);
                if (!event.isCancelled()) {
                    switch (direction) {
                        case UP:
                            movementState = MovementState.TRANSITIONING_UP;
                            break;
                        case DOWN:
                            movementState = MovementState.TRANSITIONING_DOWN;
                            break;
                        case LEFT:
                            movementState = MovementState.TRANSITIONING_LEFT;
                            break;
                        case RIGHT:
                            movementState = MovementState.TRANSITIONING_RIGHT;
                            break;
                    }
                }
            }
            sprite = sprites.get(direction);
        }
    }

    @Override
    public void onTick() {
        switch (movementState) {
            case WAITING: break;
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
            if (relativeBounds.intersects(object.getBounds()) && object.isSolid() && object != this) {
                return true;
            }
        }
        return false;
    }

}
