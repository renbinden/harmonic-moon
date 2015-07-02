package com.seventh_root.harmonicmoon.enemy;

import com.seventh_root.harmonicmoon.HarmonicMoon;
import com.seventh_root.harmonicmoon.fight.Fight;
import com.seventh_root.harmonicmoon.fight.TurnAction;
import com.seventh_root.harmonicmoon.stat.Stat;

public class Soldier extends Enemy {

    public Soldier(HarmonicMoon harmonicMoon) {
        super(harmonicMoon, "Soldier", harmonicMoon.getEnemyManager().getWaitSprite(Soldier.class), harmonicMoon.getEnemyManager().getAttackSprite(Soldier.class), harmonicMoon.getEnemyManager().getDamagedSprite(Soldier.class), harmonicMoon.getEnemyManager().getInjuredSprite(Soldier.class));
    }

    @Override
    public int getMaxHealth() {
        return 10;
    }

    @Override
    public int getMaxMana() {
        return 10;
    }

    @Override
    public int getStatValue(Stat stat) {
        return 1;
    }

    @Override
    public TurnAction chooseTurnAction(final Fight fight) {
        return attack(fight.getCharacterParty().getMembers().get(0));
    }

}
