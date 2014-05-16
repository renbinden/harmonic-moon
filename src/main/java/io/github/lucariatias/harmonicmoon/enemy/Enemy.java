package io.github.lucariatias.harmonicmoon.enemy;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.fight.Combatant;
import io.github.lucariatias.harmonicmoon.sprite.Sprite;
import io.github.lucariatias.harmonicmoon.sprite.SpriteSheet;

import java.awt.*;

public abstract class Enemy extends Combatant {

    private HarmonicMoon harmonicMoon;

    private String name;

    private SpriteSheet spriteSheet;
    private Sprite sprite;
    private Sprite waitingSprite;
    private Sprite attackingSprite;
    private Sprite injuredSprite;

    private int health;

    public Enemy(HarmonicMoon harmonicMoon, String name, Sprite waitingSprite, Sprite attackingSprite, Sprite injuredSprite) {
        this.harmonicMoon = harmonicMoon;
        this.name = name;
        this.waitingSprite = waitingSprite;
        this.attackingSprite = attackingSprite;
        this.injuredSprite = injuredSprite;
        this.sprite = waitingSprite;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(sprite.getImage(), getLocation().getX(), getLocation().getY(), null);
    }

    @Override
    public void onTick() {
        sprite.onTick();
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }
}
