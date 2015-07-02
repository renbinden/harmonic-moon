package com.seventh_root.harmonicmoon.fight;

import com.seventh_root.harmonicmoon.stat.Stat;
import com.seventh_root.harmonicmoon.util.sort.Sortable;

import java.util.Random;

public class TurnSortableWrapper implements Sortable {

    private TurnAction turnAction;
    private int value;

    public TurnSortableWrapper(TurnAction turnAction) {
        this.turnAction = turnAction;
        Random random = new Random();
        value = random.nextInt(turnAction.getCombatant().getStatValue(Stat.AGILITY));
    }

    @Override
    public int getValue() {
        return value;
    }

    public TurnAction getTurnAction() {
        return turnAction;
    }
}
