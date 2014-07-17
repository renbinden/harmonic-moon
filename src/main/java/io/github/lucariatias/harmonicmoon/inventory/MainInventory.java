package io.github.lucariatias.harmonicmoon.inventory;

import io.github.lucariatias.harmonicmoon.currency.Currency;
import io.github.lucariatias.harmonicmoon.inventory.item.ItemStack;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class MainInventory {

    private Map<Currency, Integer> money;

    private List<ItemStack> items;

    public MainInventory() {
        money = new EnumMap<>(Currency.class);
        items = new ArrayList<>();
    }

    public int getMoney(Currency currency) {
        return money.containsKey(currency) ? money.get(currency) : 0;
    }

    public void setMoney(Currency currency, int amount) {
        money.put(currency, amount);
    }

    public List<ItemStack> getItems() {
        return items;
    }
}
