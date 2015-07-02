package com.seventh_root.harmonicmoon.fight;

import com.seventh_root.harmonicmoon.sprite.Sprite;
import com.seventh_root.harmonicmoon.stat.Stat;

public abstract class Combatant extends FightObject {

    public abstract String getName();

    public abstract void setName(String name);

    public abstract int getHealth();

    public abstract void setHealth(int health);

    public abstract int getMaxHealth();

    public abstract int getMana();

    public abstract void setMana(int mana);

    public abstract int getMaxMana();

    public abstract Sprite getSprite();

    public abstract void setSprite(Sprite sprite);

    public abstract Sprite getAttackingSprite();

    public abstract Sprite getWaitingSprite();

    public abstract Sprite getDamagedSprite();

    public abstract Sprite getInjuredSprite();

    public abstract int getStatValue(Stat stat);

    public abstract TurnAction attack(Combatant combatant);

    public abstract TurnAction defend();
}
