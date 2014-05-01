package io.github.lucariatias.harmonicmoon.party;

import io.github.lucariatias.harmonicmoon.enemy.Enemy;
import io.github.lucariatias.harmonicmoon.fight.Combatant;

import java.util.Arrays;
import java.util.List;

public class EnemyParty extends Party {

    private List<Enemy> members;

    public EnemyParty(Enemy... members) {
        this.members = Arrays.asList(members);
    }

    @Override
    public void addMember(Combatant combatant) {
        if (combatant instanceof Enemy) members.add((Enemy) combatant);
    }

    public void removeMember(Combatant combatant) {
        if (combatant instanceof Enemy) members.remove(combatant);
    }

    public List<? extends Enemy> getMembers() {
        return members;
    }

}
