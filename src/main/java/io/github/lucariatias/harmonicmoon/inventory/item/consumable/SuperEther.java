package io.github.lucariatias.harmonicmoon.inventory.item.consumable;

import io.github.lucariatias.harmonicmoon.fight.Combatant;

public class SuperEther extends Consumable {

    public SuperEther() {
        super("Super Ether", "A tonic that regenerates mana.\nReplenishes 500 mana.");
    }

    @Override
    public void use(Combatant user, Combatant target) {
        target.setMana(target.getMana() + 500);
    }

}
