package com.seventh_root.harmonicmoon.event.character;

import com.seventh_root.harmonicmoon.event.Listener;

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
