package com.seventh_root.harmonicmoon.inventory.item.consumable;

import com.seventh_root.harmonicmoon.fight.Combatant;

public class Ether extends Consumable {

    public Ether() {
        super("Ether", "A tonic that regenerates mana.\nReplenishes 100 mana.");
    }

    @Override
    public void use(Combatant user, Combatant target) {
        target.setMana(target.getMana() + 100);
    }

}
