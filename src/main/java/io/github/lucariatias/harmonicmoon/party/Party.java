package io.github.lucariatias.harmonicmoon.party;

import io.github.lucariatias.harmonicmoon.fight.Combatant;

import java.util.Arrays;
import java.util.List;

public class Party {

    private List<Combatant> members;

    public Party(Combatant... members) {
        this.members = Arrays.asList(members);
    }

    public void addMember(Combatant combatant) {
        members.add(combatant);
    }

    public void removeMember(Combatant combatant) {
        members.remove(combatant);
    }

    public List<? extends Combatant> getMembers() {
        return members;
    }

}
