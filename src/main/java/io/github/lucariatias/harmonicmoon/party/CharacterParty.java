package io.github.lucariatias.harmonicmoon.party;

import io.github.lucariatias.harmonicmoon.character.Character;
import io.github.lucariatias.harmonicmoon.character.CharacterFightInfo;
import io.github.lucariatias.harmonicmoon.fight.Combatant;

import java.util.Arrays;
import java.util.List;

public class CharacterParty extends Party {

    private List<CharacterFightInfo> members;

    public CharacterParty(CharacterFightInfo... members) {
        this.members = Arrays.asList(members);
    }

    @Override
    public void addMember(Combatant combatant) {
        if (combatant instanceof CharacterFightInfo) members.add((CharacterFightInfo) combatant);
    }

    public void addMember(Character character) {
        members.add(character.fight());
    }

    public void removeMember(Combatant combatant) {
        if (combatant instanceof CharacterFightInfo) members.remove(combatant);
    }

    public void removeMember(Character character) {
        members.remove(character.fight());
    }

    public List<? extends CharacterFightInfo> getMembers() {
        return members;
    }
}
