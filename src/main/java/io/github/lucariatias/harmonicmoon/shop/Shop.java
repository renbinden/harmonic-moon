package io.github.lucariatias.harmonicmoon.shop;

import io.github.lucariatias.harmonicmoon.currency.Currency;
import io.github.lucariatias.harmonicmoon.inventory.item.Item;

import java.util.LinkedHashMap;

public class Shop {

    private Currency currency;
    private LinkedHashMap<Item, Integer> items;

    public Shop(Currency currency, LinkedHashMap<Item, Integer> items) {
        this.currency = currency;
        this.items = items;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Item[] getItems() {
        return items.keySet().toArray(new Item[items.size()]);
    }

    public int getPrice(Item item) {
        return items.get(item);
    }

}
