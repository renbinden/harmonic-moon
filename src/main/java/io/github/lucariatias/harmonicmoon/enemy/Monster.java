package io.github.lucariatias.harmonicmoon.enemy;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.sprite.Sprite;

public abstract class Monster extends Enemy {

    public Monster(HarmonicMoon harmonicMoon, String name, Sprite waitingSprite, Sprite attackingSprite, Sprite damagedSprite, Sprite injuredSprite) {
        super(harmonicMoon, name, waitingSprite, attackingSprite, damagedSprite, injuredSprite);
    }

}
