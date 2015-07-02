package com.seventh_root.harmonicmoon.npc;

import com.seventh_root.harmonicmoon.HarmonicMoon;
import com.seventh_root.harmonicmoon.character.Character;
import com.seventh_root.harmonicmoon.event.collision.CollisionEvent;
import com.seventh_root.harmonicmoon.event.messagebox.MessageBoxCloseEvent;
import com.seventh_root.harmonicmoon.event.messagebox.MessageBoxCloseListener;
import com.seventh_root.harmonicmoon.event.npc.NPCMoveEvent;
import com.seventh_root.harmonicmoon.message.Message;
import com.seventh_root.harmonicmoon.sprite.Sprite;
import com.seventh_root.harmonicmoon.sprite.SpriteSheet;
import com.seventh_root.harmonicmoon.world.Direction;
import com.seventh_root.harmonicmoon.world.MovementState;
import com.seventh_root.harmonicmoon.world.WorldLocation;
import com.seventh_root.harmonicmoon.world.WorldObject;
import com.seventh_root.harmonicmoon.npc.path.Path;

import javax.script.Bindings;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public abstract class NPC extends WorldObject {

    private HarmonicMoon harmonicMoon;

    private Sprite sprite;
    private Map<Direction, Sprite> sprites = new EnumMap<>(Direction.class);

    private NPCMetadata metadata;

    private MovementState movementState;

    private Path path;

    private boolean hasInteracted;

    public NPC(HarmonicMoon harmonicMoon, SpriteSheet spriteSheet, NPCMetadata metadata) {
        this.harmonicMoon = harmonicMoon;
        this.movementState = MovementState.WAITING;
        sprites.put(Direction.DOWN, spriteSheet.getSprite(0, 0, 4));
        sprites.put(Direction.RIGHT, spriteSheet.getSprite(4, 0, 4));
        sprites.put(Direction.LEFT, spriteSheet.getSprite(8, 0, 4));
        sprites.put(Direction.UP, spriteSheet.getSprite(12, 0, 4));
        this.sprite = sprites.get(Direction.DOWN);
        this.metadata = metadata;
        setSolid(true);
        harmonicMoon.getEventManager().registerListener(new MessageBoxCloseListener() {
            @Override
            public void onMessageBoxClose(MessageBoxCloseEvent event) {
                getPath().setFrozen(false);
                hasInteracted = false;
            }
        });
        if (getMetadata() != null && getMetadata().getInitialiseScript() != null) {
            Bindings bindings = new SimpleBindings();
            bindings.put("npc", this);
            bindings.put("game", harmonicMoon);
            try {
                harmonicMoon.getScriptEngine().eval(getMetadata().getInitialiseScript(), bindings);
            } catch (ScriptException exception) {
                exception.printStackTrace();
            }
        }
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
        if (harmonicMoon.isDebug()) {
            Rectangle bounds = getBounds();
            graphics.setColor(Color.RED);
            graphics.drawRect((int) bounds.getX(), (int) bounds.getY(), (int) bounds.getWidth(), (int) bounds.getHeight());
        }
    }

    @Override
    public Rectangle getBoundsAtPosition(WorldLocation location, MovementState movementState) {
        switch (movementState) {
            case TRANSITIONING_UP:
                return new Rectangle((location.getX() / 16) * 16, (location.getY() / 16) * 16, 16, 32);
            case TRANSITIONING_DOWN:
                return new Rectangle((location.getX() / 16) * 16, (location.getY() / 16) * 16, 16, 32);
            case TRANSITIONING_LEFT:
                return new Rectangle((location.getX() / 16) * 16, (location.getY() / 16) * 16, 32, 16);
            case TRANSITIONING_RIGHT:
                return new Rectangle((location.getX() / 16) * 16, (location.getY() / 16) * 16, 32, 16);
            default:
                return new Rectangle(location.getX(), location.getY(), 16, 16);
        }
    }

    @Override
    public MovementState getMovementState() {
        return movementState;
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
            if (getCollision(direction) instanceof Character.World) {
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

    public NPCMetadata getMetadata() {
        return metadata;
    }

    @Override
    public void interact() {
        if (!hasInteracted) {
            hasInteracted = true;
            if (getMetadata() != null && getMetadata().getInteractScript() != null) {
                Bindings bindings = new SimpleBindings();
                bindings.put("npc", this);
                bindings.put("game", harmonicMoon);
                try {
                    harmonicMoon.getScriptEngine().eval(getMetadata().getInteractScript(), bindings);
                } catch (ScriptException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

}
