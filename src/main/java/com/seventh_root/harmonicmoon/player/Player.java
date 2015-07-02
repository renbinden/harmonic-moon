package com.seventh_root.harmonicmoon.player;

import com.seventh_root.harmonicmoon.HarmonicMoon;
import com.seventh_root.harmonicmoon.character.Character;
import com.seventh_root.harmonicmoon.world.Direction;

public class Player {

    private HarmonicMoon harmonicMoon;

    private com.seventh_root.harmonicmoon.character.Character character;

    public Player(HarmonicMoon harmonicMoon) {
        this.harmonicMoon = harmonicMoon;
        this.character = harmonicMoon.getCharacterManager().getCharacter("lonyre");
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public void move(Direction direction) {
        if (character != null) {
            character.world().move(direction);
        }
    }

    public void interact(Direction direction) {
        if (character != null) {
            character.world().interact(direction);
        }
    }

}
