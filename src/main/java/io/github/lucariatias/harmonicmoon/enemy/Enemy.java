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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.GREEN);
        graphics.fillRect(getLocation().getX(), getLocation().getY() - 16, health, 8);
        graphics.setColor(Color.RED);
        graphics.fillRect(getLocation().getX() + health, getLocation().getY() - 16, getMaxHealth() - health, 8);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(getLocation().getX(), getLocation().getY() - 16, getMaxHealth(), 8);
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
