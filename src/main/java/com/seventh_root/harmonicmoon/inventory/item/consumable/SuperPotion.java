package com.seventh_root.harmonicmoon.inventory.item.consumable;

import com.seventh_root.harmonicmoon.fight.Combatant;

public class SuperPotion extends Consumable {

    public SuperPotion() {
        super("Super Potion", "A magical potion that regenerates health.\nHeals 500HP.");
    }

    @Override
    public void use(Combatant user, Combatant target) {
        target.setHealth(target.getHealth() + 500);
    }

}