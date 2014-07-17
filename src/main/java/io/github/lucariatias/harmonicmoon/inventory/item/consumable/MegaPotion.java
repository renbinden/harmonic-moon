package io.github.lucariatias.harmonicmoon.inventory.item.consumable;

import io.github.lucariatias.harmonicmoon.fight.Combatant;

public class MegaPotion extends Consumable {

    public MegaPotion() {
        super("Mega Potion", "A magical potion that regenerates health.\nHeals 1000HP.");
    }

    @Override
    public void use(Combatant user, Combatant target) {
        target.setHealth(target.getHealth() + 1000);
    }

}
