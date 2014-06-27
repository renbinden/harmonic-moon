package io.github.lucariatias.harmonicmoon.fight;

import io.github.lucariatias.harmonicmoon.sprite.Sprite;

public abstract class Combatant extends FightObject {

    public abstract String getName();

    public abstract void setName(String name);

    public abstract int getHealth();

    public abstract void setHealth(int health);

    public abstract int getMaxHealth();

    public abstract Sprite getSprite();

    public abstract void setSprite(Sprite sprite);

    public abstract void playSpriteOnce(Sprite sprite);

    public abstract Sprite getAttackingSprite();

    public abstract Sprite getWaitingSprite();

    public abstract Sprite getInjuredSprite();

}
