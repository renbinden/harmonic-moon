package io.github.lucariatias.harmonicmoon.enemy;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.fight.Fight;
import io.github.lucariatias.harmonicmoon.fight.TurnAction;
import io.github.lucariatias.harmonicmoon.stat.Stat;

public class Soldier extends Enemy {

    public Soldier(HarmonicMoon harmonicMoon) {
        super(harmonicMoon, "Soldier", harmonicMoon.getEnemyManager().getWaitSprite(Soldier.class), harmonicMoon.getEnemyManager().getAttackSprite(Soldier.class), harmonicMoon.getEnemyManager().getInjuredSprite(Soldier.class));
    }

    @Override
    public int getMaxHealth() {
        return 10;
    }

    @Override
    public int getStatValue(Stat stat) {
        return 1;
    }

    @Override
    public TurnAction chooseTurnAction(final Fight fight) {
        return new TurnAction(this, new Runnable() {
            @Override
            public void run() {
                attack(fight.getCharacterParty().getMembers().get(0));
            }
        }, 2500L);
    }

}
