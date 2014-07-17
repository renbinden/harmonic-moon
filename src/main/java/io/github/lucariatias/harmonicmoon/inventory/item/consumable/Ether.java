package io.github.lucariatias.harmonicmoon.inventory.item.consumable;

import io.github.lucariatias.harmonicmoon.fight.Combatant;

public class Ether extends Consumable {

    public Ether() {
        super("Ether", "A tonic that regenerates mana.\nReplenishes 100 mana.");
    }

    @Override
    public void use(Combatant user, Combatant target) {
        target.setMana(target.getMana() + 100);
    }

}
