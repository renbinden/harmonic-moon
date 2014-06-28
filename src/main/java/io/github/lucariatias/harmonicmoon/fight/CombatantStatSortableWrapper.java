package io.github.lucariatias.harmonicmoon.fight;

import io.github.lucariatias.harmonicmoon.fight.Combatant;
import io.github.lucariatias.harmonicmoon.stat.Stat;
import io.github.lucariatias.harmonicmoon.util.sort.Sortable;

public class CombatantStatSortableWrapper implements Sortable {

    private Combatant combatant;
    private Stat stat;

    public CombatantStatSortableWrapper(Combatant combatant, Stat stat) {
        this.combatant = combatant;
        this.stat = stat;
    }

    @Override
    public int getValue() {
        return combatant.getStatValue(stat);
    }

    public Combatant getCombatant() {
        return combatant;
    }

}
