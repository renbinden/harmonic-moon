package io.github.lucariatias.harmonicmoon.party;

import io.github.lucariatias.harmonicmoon.character.Character;
import io.github.lucariatias.harmonicmoon.character.FightCharacter;
import io.github.lucariatias.harmonicmoon.fight.Combatant;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CharacterParty extends Party {

    private List<FightCharacter> members;

    public CharacterParty(FightCharacter... members) {
        this.members = new CopyOnWriteArrayList<>(Arrays.asList(members));
    }

    @Override
    public void addMember(Combatant combatant) {
        if (combatant instanceof FightCharacter) members.add((FightCharacter) combatant);
    }

    public void addMember(Character character) {
        members.add(character.fight());
    }

    public void removeMember(Combatant combatant) {
        if (combatant instanceof FightCharacter) members.remove(combatant);
    }

    public void removeMember(Character character) {
        members.remove(character.fight());
    }

    public List<? extends FightCharacter> getMembers() {
        return members;
    }
}
