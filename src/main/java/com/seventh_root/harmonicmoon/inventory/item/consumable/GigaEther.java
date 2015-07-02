package com.seventh_root.harmonicmoon.inventory.item.consumable;

import com.seventh_root.harmonicmoon.fight.Combatant;

public class GigaEther extends Consumable {

    public GigaEther() {
        super("Giga Ether", "A tonic that regenerates mana.\nReplenishes 10000 mana.");
    }

    @Override
    public void use(Combatant user, Combatant target) {
        target.setMana(target.getMana() + 10000);
    }

}
