package io.github.lucariatias.harmonicmoon.enemy;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.fight.Fight;
import io.github.lucariatias.harmonicmoon.fight.TurnAction;
import io.github.lucariatias.harmonicmoon.stat.Stat;

public class Slime extends Monster {

    public Slime(HarmonicMoon harmonicMoon) {
        super(harmonicMoon, "Slime", harmonicMoon.getEnemyManager().getWaitSprite(Slime.class), harmonicMoon.getEnemyManager().getAttackSprite(Slime.class), harmonicMoon.getEnemyManager().getInjuredSprite(Slime.class));
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
        return new TurnAction(this, new Runnable() {
            @Override
            public void run() {
                attack(fight.getCharacterParty().getMembers().get(0));
            }
        }, 2500L);
    }
}
