package io.github.lucariatias.harmonicmoon.character;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;

import java.util.HashMap;
import java.util.Map;

public class CharacterManager {

    private Map<String, Character> characters;

    public CharacterManager(HarmonicMoon harmonicMoon) {
        characters = new HashMap<>();
        //addCharacter(new Anaria(harmonicMoon));
        //addCharacter(new Idain(harmonicMoon));
        addCharacter(new Lonyre(harmonicMoon));
        //addCharacter(new Namapo(harmonicMoon));
        //addCharacter(new Seuri(harmonicMoon));
        //addCharacter(new Syalae(harmonicMoon));
        //addCharacter(new Tesowa(harmonicMoon));
        //addCharacter(new Tivor(harmonicMoon));
    }

    public void addCharacter(Character character) {
        characters.put(character.getName().toLowerCase(), character);
    }

    public void removeCharacter(Character character) {
        characters.remove(character.getName().toLowerCase());
    }

    public Character getCharacter(String name) {
        return characters.get(name.toLowerCase());
    }

}
