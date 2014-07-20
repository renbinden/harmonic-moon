package io.github.lucariatias.harmonicmoon.enemy;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.event.sprite.SpriteAnimationCompleteEvent;
import io.github.lucariatias.harmonicmoon.event.sprite.SpriteAnimationCompleteListener;
import io.github.lucariatias.harmonicmoon.fight.Combatant;
import io.github.lucariatias.harmonicmoon.fight.Fight;
import io.github.lucariatias.harmonicmoon.fight.TurnAction;
import io.github.lucariatias.harmonicmoon.fight.party.EnemyFightParty;
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
    private boolean spriteTemporary;

    private int health;
    private int mana;

    public Enemy(HarmonicMoon harmonicMoon, String name, Sprite waitingSprite, Sprite attackingSprite, Sprite injuredSprite) {
        this.harmonicMoon = harmonicMoon;
        this.name = name;
        this.waitingSprite = waitingSprite;
        this.attackingSprite = attackingSprite;
        this.injuredSprite = injuredSprite;
        this.sprite = waitingSprite;
        setHealth(getMaxHealth());
        harmonicMoon.getEventManager().registerListener(new SpriteAnimationCompleteListener() {
            @Override
            public void onSpriteAnimationComplete(SpriteAnimationCompleteEvent event) {
                HarmonicMoon harmonicMoon = Enemy.this.harmonicMoon;
                if (spriteTemporary && event.getSprite() == sprite) {
                    if (sprite == getAttackingSprite()) {
                        setSprite(getWaitingSprite());
                        spriteTemporary = false;
                    } else if (sprite == getInjuredSprite()) {
                        EnemyFightParty party = harmonicMoon.getFightPanel().getFight().getEnemyParty();
                        synchronized (party.getMembers()) {
                            party.removeMember(Enemy.this);
                        }
                    }
                }
            }
        });
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
        graphics.fillRect(getLocation().getX(), getLocation().getY() - 16, (int) Math.round(64D * ((double) getHealth() / (double) getMaxHealth())), 8);
        graphics.setColor(Color.RED);
        graphics.fillRect(getLocation().getX() + (int) Math.round(64 * ((double) getHealth() / (double) getMaxHealth())), getLocation().getY() - 16, (int) Math.round(64D * (((double) getMaxHealth() - (double) getHealth()) / (double) getMaxHealth())), 8);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(getLocation().getX(), getLocation().getY() - 16, 64, 8);
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
        if (health <= 0) die();
        this.health = Math.min(Math.max(health, 0), getMaxHealth());
    }

    @Override
    public int getMana() {
        return mana;
    }

    @Override
    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public void setSprite(Sprite sprite) {
        this.sprite.reset();
        this.sprite = sprite;
    }

    @Override
    public void playSpriteOnce(Sprite sprite) {
        spriteTemporary = true;
        setSprite(sprite);
    }

    @Override
    public Sprite getWaitingSprite() {
        return waitingSprite;
    }

    @Override
    public Sprite getAttackingSprite() {
        return attackingSprite;
    }

    @Override
    public Sprite getInjuredSprite() {
        return injuredSprite;
    }

    public void die() {
        playSpriteOnce(getInjuredSprite());
    }

    public abstract TurnAction chooseTurnAction(Fight fight);

    @Override
    public void attack(Combatant combatant) {
        if (harmonicMoon.getFightPanel().isActive()) {
            combatant.setHealth(combatant.getHealth() - 5);
            playSpriteOnce(getAttackingSprite());
        }
    }

    @Override
    public void defend() {

    }

}
