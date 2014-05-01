package io.github.lucariatias.harmonicmoon.enemy;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.sprite.Sprite;
import io.github.lucariatias.harmonicmoon.sprite.SpriteSheet;

public abstract class Monster extends Enemy {

    public Monster(HarmonicMoon harmonicMoon, String name, SpriteSheet spriteSheet, Sprite waitingSprite, Sprite attackingSprite, Sprite injuredSprite) {
        super(harmonicMoon, name, spriteSheet, waitingSprite, attackingSprite, injuredSprite);
    }

}
