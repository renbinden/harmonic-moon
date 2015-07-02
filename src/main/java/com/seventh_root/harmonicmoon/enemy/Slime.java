package com.seventh_root.harmonicmoon.enemy;

import com.seventh_root.harmonicmoon.HarmonicMoon;
import com.seventh_root.harmonicmoon.fight.Fight;
import com.seventh_root.harmonicmoon.fight.TurnAction;
import com.seventh_root.harmonicmoon.stat.Stat;

public class Slime extends Monster {

    public Slime(HarmonicMoon harmonicMoon) {
        super(harmonicMoon, "Slime", harmonicMoon.getEnemyManager().getWaitSprite(Slime.class), harmonicMoon.getEnemyManager().getAttackSprite(Slime.class), harmonicMoon.getEnemyManager().getDamagedSprite(Slime.class), harmonicMoon.getEnemyManager().getInjuredSprite(Slime.class));
    }

    @Override
    public int getMaxHealth() {
        return 15;
    }

    @Override
    public int getMaxMana() {
        return 15;
    }

    public int getStatValue(Stat stat) {
        return 1;
    }

    @Override
    public TurnAction chooseTurnAction(final Fight fight) {
        return attack(fight.getCharacterParty().getMembers().get(0));
    }
}
