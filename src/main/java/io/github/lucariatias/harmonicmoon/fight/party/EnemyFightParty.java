package io.github.lucariatias.harmonicmoon.fight.party;

import io.github.lucariatias.harmonicmoon.enemy.Enemy;
import io.github.lucariatias.harmonicmoon.fight.Combatant;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class EnemyFightParty extends FightParty {

    private List<Enemy> members;

    public EnemyFightParty(Enemy... members) {
        this.members = new CopyOnWriteArrayList<>(Arrays.asList(members));
    }

    @Override
    public void addMember(Combatant combatant) {
        if (combatant instanceof Enemy) members.add((Enemy) combatant);
    }

    public void addMember(Enemy enemy) {
        members.add(enemy);
    }

    @Override
    public void removeMember(Combatant combatant) {
        if (combatant instanceof Enemy) members.remove(combatant);
    }

    public void removeMember(Enemy enemy) {
        members.remove(enemy);
    }

    public List<? extends Enemy> getMembers() {
        return members;
    }

}
