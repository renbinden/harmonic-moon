package io.github.lucariatias.harmonicmoon.npc;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.character.CharacterWorldInfo;
import io.github.lucariatias.harmonicmoon.event.collision.CollisionEvent;
import io.github.lucariatias.harmonicmoon.event.messagebox.MessageBoxCloseEvent;
import io.github.lucariatias.harmonicmoon.event.messagebox.MessageBoxCloseListener;
import io.github.lucariatias.harmonicmoon.event.npc.NPCMoveEvent;
import io.github.lucariatias.harmonicmoon.message.Message;
import io.github.lucariatias.harmonicmoon.npc.path.Path;
import io.github.lucariatias.harmonicmoon.sprite.Sprite;
import io.github.lucariatias.harmonicmoon.sprite.SpriteSheet;
import io.github.lucariatias.harmonicmoon.world.Direction;
import io.github.lucariatias.harmonicmoon.world.WorldLocation;
import io.github.lucariatias.harmonicmoon.world.WorldObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
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
        sprites.put(Direction.DOWN, spriteSheet.getSprite(0, 0, 4));
        sprites.put(Direction.RIGHT, spriteSheet.getSprite(4, 0, 4));
        sprites.put(Direction.LEFT, spriteSheet.getSprite(8, 0, 4));
        sprites.put(Direction.UP, spriteSheet.getSprite(12, 0, 4));
        this.sprite = sprites.get(Direction.DOWN);
        setSolid(true);
        harmonicMoon.getEventManager().registerListener(new MessageBoxCloseListener() {
            @Override
            public void onMessageBoxClose(MessageBoxCloseEvent event) {
                getPath().setFrozen(false);
            }
        });
    }

    public BufferedImage getImage() {
        return sprite.getImage();
    }

    public void face(Direction direction) {
        sprite = sprites.get(direction);
    }

    public void move(Direction direction) {
        if (movementState == MovementState.WAITING) {
            WorldObject colliding = getCollision(direction);
            if (colliding == null) {
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
            } else {
                CollisionEvent collisionEvent = new CollisionEvent(this, colliding);
                harmonicMoon.getEventManager().dispatchEvent(collisionEvent);
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
            case WAITING: getPath().step(); break;
            case TRANSITIONING_UP: setLocation(getLocation().getRelative(Direction.UP, 1)); sprite.onTick(); break;
            case TRANSITIONING_DOWN: setLocation(getLocation().getRelative(Direction.DOWN, 1)); sprite.onTick(); break;
            case TRANSITIONING_LEFT: setLocation(getLocation().getRelative(Direction.LEFT, 1)); sprite.onTick(); break;
            case TRANSITIONING_RIGHT: setLocation(getLocation().getRelative(Direction.RIGHT, 1)); sprite.onTick(); break;
        }
        if (getLocation().getX() % 16 == 0 && getLocation().getY() % 16 == 0) movementState = MovementState.WAITING;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(getImage(), getLocation().getX(), getLocation().getY() - (getImage().getHeight() / 2), null);
    }

    @Override
    public Rectangle getBoundsAtPosition(WorldLocation location) {
        return new Rectangle(location.getX(), location.getY(), getImage().getWidth(), getImage().getHeight() / 2);
    }

    public void say(String... messages) {
        List<Message> messagesToSend = new ArrayList<>();
        for (String message : messages) {
            messagesToSend.add(new Message(this, message));
        }
        say(messagesToSend.toArray(new Message[messagesToSend.size()]));
    }

    public void say(Message... messages) {
        for (Direction direction : Direction.values()) {
            if (getCollision(direction) instanceof CharacterWorldInfo) {
                face(direction);
            }
        }
        getPath().setFrozen(true);
        if (harmonicMoon.getMessageBox().isHidden()) {
            for (Message message : messages) {
                harmonicMoon.getMessageBox().queueMessage(message);
            }
        }
    }

}
