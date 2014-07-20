package io.github.lucariatias.harmonicmoon.inventory;

import io.github.lucariatias.harmonicmoon.currency.Currency;
import io.github.lucariatias.harmonicmoon.inventory.item.Item;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class PartyInventory {

    private Map<Currency, Integer> money;

    private Map<Item, Integer> items;

    public PartyInventory() {
        money = new EnumMap<>(Currency.class);
        items = new HashMap<>();
    }

    public int getMoney(Currency currency) {
        return money.containsKey(currency) ? money.get(currency) : 0;
    }

    public void setMoney(Currency currency, int amount) {
        money.put(currency, amount);
    }

    public Map<Item, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

    public void addItems(Map<Item, Integer> items) {
        for (Map.Entry<Item, Integer> item : items.entrySet()) {
            addItem(item.getKey(), item.getValue());
        }
    }

    public void addItem(Item item, int amount) {
        items.put(item, (items.containsKey(item) ? items.get(item) : 0) + amount);
    }

}
