package io.github.lucariatias.harmonicmoon.event.character;

import io.github.lucariatias.harmonicmoon.character.Character;
import io.github.lucariatias.harmonicmoon.event.Cancellable;
import io.github.lucariatias.harmonicmoon.event.Event;
import io.github.lucariatias.harmonicmoon.world.WorldLocation;

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

    public void setCharacter(io.github.lucariatias.harmonicmoon.character.Character character) {
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
