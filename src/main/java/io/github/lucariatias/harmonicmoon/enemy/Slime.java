package io.github.lucariatias.harmonicmoon.enemy;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;

public class Slime extends Monster {

    public Slime(HarmonicMoon harmonicMoon) {
        super(harmonicMoon, "Slime", harmonicMoon.getEnemyManager().getWaitSprite(Slime.class), harmonicMoon.getEnemyManager().getAttackSprite(Slime.class), harmonicMoon.getEnemyManager().getInjuredSprite(Slime.class));
    }

    @Override
    public int getMaxHealth() {
        return 15;
    }
}
