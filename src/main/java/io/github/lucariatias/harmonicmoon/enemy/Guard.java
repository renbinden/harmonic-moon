package io.github.lucariatias.harmonicmoon.enemy;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;

public class Guard extends Enemy {

    public Guard(HarmonicMoon harmonicMoon) {
        super(harmonicMoon, "Guard", harmonicMoon.getEnemyManager().getWaitSprite(Guard.class), harmonicMoon.getEnemyManager().getAttackSprite(Guard.class), harmonicMoon.getEnemyManager().getInjuredSprite(Guard.class));
    }

    @Override
    public int getMaxHealth() {
        return 10;
    }
}
