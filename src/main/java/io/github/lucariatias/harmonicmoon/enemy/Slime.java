package io.github.lucariatias.harmonicmoon.enemy;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.sprite.Sprite;
import io.github.lucariatias.harmonicmoon.sprite.SpriteSheet;

public class Slime extends Monster {

    public Slime(HarmonicMoon harmonicMoon, SpriteSheet spriteSheet, Sprite waitingSprite, Sprite attackingSprite, Sprite injuredSprite) {
        super(harmonicMoon, "Slime", spriteSheet, waitingSprite, attackingSprite, injuredSprite);
    }

}
