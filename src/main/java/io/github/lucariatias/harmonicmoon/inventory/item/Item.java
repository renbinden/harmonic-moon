package io.github.lucariatias.harmonicmoon.inventory.item;

import io.github.lucariatias.harmonicmoon.fight.Combatant;

public abstract class Item {

    private String name;
    private String description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract void use(Combatant user, Combatant target);

}
