package io.github.lucariatias.harmonicmoon.fight.party;

import io.github.lucariatias.harmonicmoon.character.Character;
import io.github.lucariatias.harmonicmoon.fight.Combatant;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CharacterFightParty extends FightParty {

    private List<Character.Fight> members;

    public CharacterFightParty(Character.Fight... members) {
        this.members = new CopyOnWriteArrayList<>(Arrays.asList(members));
    }

    @Override
    public void addMember(Combatant combatant) {
        if (combatant instanceof Character.Fight) members.add((Character.Fight) combatant);
    }

    public void addMember(Character.Fight character) {
        members.add(character);
    }

    public void removeMember(Combatant combatant) {
        if (combatant instanceof Character.Fight) members.remove(combatant);
    }

    public void removeMember(Character.Fight character) {
        members.remove(character);
    }

    public List<? extends Character.Fight> getMembers() {
        return members;
    }
}
