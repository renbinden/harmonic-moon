package io.github.lucariatias.harmonicmoon.inventory.item.consumable;

import io.github.lucariatias.harmonicmoon.fight.Combatant;

public class GigaPotion extends Consumable {

    public GigaPotion() {
        super("Giga Potion", "A magical potion that regenerates health.\nHeals 10000HP.");
    }

    @Override
    public void use(Combatant user, Combatant target) {
        target.setHealth(target.getHealth() + 10000);
    }

}
