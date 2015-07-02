package com.seventh_root.harmonicmoon.event.character;

import com.seventh_root.harmonicmoon.character.Character;
import com.seventh_root.harmonicmoon.event.Cancellable;
import com.seventh_root.harmonicmoon.event.Event;
import com.seventh_root.harmonicmoon.world.WorldLocation;

public class CharacterMoveEvent extends Event implements Cancellable {

    private Character character;
    private WorldLocation from;
    private WorldLocation to;
    private boolean cancelled;

    public CharacterMoveEvent(Character character, WorldLocation from, WorldLocation to) {
        this.character = character;
        this.from = from;
        this.to = to;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public WorldLocation getFrom() {
        return from;
    }

    public void setFrom(WorldLocation from) {
        this.from = from;
    }

    public WorldLocation getTo() {
        return to;
    }

    public void setTo(WorldLocation to) {
        this.to = to;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

}
