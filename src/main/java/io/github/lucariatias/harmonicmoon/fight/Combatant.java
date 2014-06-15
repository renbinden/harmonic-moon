package io.github.lucariatias.harmonicmoon.fight;

public abstract class Combatant extends FightObject {

    public abstract String getName();

    public abstract void setName(String name);

    public abstract int getHealth();

    public abstract void setHealth(int health);

    public abstract int getMaxHealth();

}
