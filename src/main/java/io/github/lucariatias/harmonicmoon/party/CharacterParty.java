package io.github.lucariatias.harmonicmoon.party;

import io.github.lucariatias.harmonicmoon.character.Character;
import io.github.lucariatias.harmonicmoon.fight.Combatant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CharacterParty extends Party {

    private List<Character> members;

    public CharacterParty(Character... members) {
        this.members = new CopyOnWriteArrayList<>(Arrays.asList(members));
    }

    @Override
    public void addMember(Combatant combatant) {
        if (combatant instanceof Character.Fight) members.add(((Character.Fight) combatant).getCharacter());
    }

    public void addMember(Character character) {
        members.add(character);
    }

    public void removeMember(Combatant combatant) {
        if (combatant instanceof Character.Fight) members.remove(((Character.Fight) combatant).getCharacter());
    }

    public void removeMember(Character character) {
        members.remove(character);
    }

    public List<? extends Character.Fight> getMembers() {
        List<Character.Fight> members = new ArrayList<>();
        for (Character member : this.members) {
            members.add(member.fight());
        }
        return members;
    }
}
