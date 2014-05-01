package io.github.lucariatias.harmonicmoon.event.character;

import io.github.lucariatias.harmonicmoon.event.Listener;

public abstract class CharacterMoveListener extends Listener<CharacterMoveEvent> {

    public CharacterMoveListener() {
        super(CharacterMoveEvent.class);
    }

    public abstract void onPlayerMove(CharacterMoveEvent event);

    @Override
    public void onEvent(CharacterMoveEvent event) {
        onPlayerMove(event);
    }

}
