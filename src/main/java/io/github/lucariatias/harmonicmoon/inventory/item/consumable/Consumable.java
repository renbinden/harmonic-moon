package io.github.lucariatias.harmonicmoon.inventory.item.consumable;

import io.github.lucariatias.harmonicmoon.inventory.item.Item;

public abstract class Consumable extends Item {

    // Healing items
    public static final Item POTION = new Potion();
    public static final Item SUPER_POTION = new SuperPotion();
    public static final Item MEGA_POTION = new MegaPotion();
    public static final Item GIGA_POTION = new GigaPotion();

    // Mana replenishment items
    public static final Item ETHER = new Ether();
    public static final Item SUPER_ETHER = new SuperEther();
    public static final Item MEGA_ETHER = new MegaEther();
    public static final Item GIGA_ETHER = new GigaEther();
    
    public Consumable(String name, String description) {
        super(name, description);
    }

}
