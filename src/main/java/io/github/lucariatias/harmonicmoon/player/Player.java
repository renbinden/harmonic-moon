package io.github.lucariatias.harmonicmoon.player;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.character.Character;
import io.github.lucariatias.harmonicmoon.world.Direction;

public class Player {

    private HarmonicMoon harmonicMoon;

    private Character character;

    public Player(HarmonicMoon harmonicMoon) {
        this.harmonicMoon = harmonicMoon;
        this.character = harmonicMoon.getCharacterManager().getCharacter("lonyre");
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(io.github.lucariatias.harmonicmoon.character.Character character) {
        this.character = character;
    }

    public void move(Direction direction) {
        if (character != null) {
            character.getWorldInfo().move(direction);
        }
    }



}
