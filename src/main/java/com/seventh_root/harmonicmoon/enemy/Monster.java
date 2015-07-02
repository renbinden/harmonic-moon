package com.seventh_root.harmonicmoon.enemy;

import com.seventh_root.harmonicmoon.HarmonicMoon;
import com.seventh_root.harmonicmoon.sprite.Sprite;

public abstract class Monster extends Enemy {

    public Monster(HarmonicMoon harmonicMoon, String name, Sprite waitingSprite, Sprite attackingSprite, Sprite damagedSprite, Sprite injuredSprite) {
        super(harmonicMoon, name, waitingSprite, attackingSprite, damagedSprite, injuredSprite);
    }

}
