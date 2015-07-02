package com.seventh_root.harmonicmoon.fight;

import com.seventh_root.harmonicmoon.util.sort.Sortable;
import com.seventh_root.harmonicmoon.stat.Stat;

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
