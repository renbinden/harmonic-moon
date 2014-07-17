package io.github.lucariatias.harmonicmoon.inventory.item.consumable;

import io.github.lucariatias.harmonicmoon.fight.Combatant;

public class Potion extends Consumable {

    public Potion() {
        super("Potion", "A magical potion that regenerates health.\nHeals 100HP.");
    }

    @Override
    public void use(Combatant user, Combatant target) {
        target.setHealth(target.getHealth() + 100);
    }

}
