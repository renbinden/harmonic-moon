package com.seventh_root.harmonicmoon.fight.party;

import com.seventh_root.harmonicmoon.fight.Combatant;

import java.util.Arrays;
import java.util.List;

public abstract class FightParty {

    private List<Combatant> members;

    public FightParty(Combatant... members) {
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
