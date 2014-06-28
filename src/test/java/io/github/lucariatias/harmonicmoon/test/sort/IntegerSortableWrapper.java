package io.github.lucariatias.harmonicmoon.test.sort;

import io.github.lucariatias.harmonicmoon.util.sort.Sortable;

public class IntegerSortableWrapper implements Sortable {

    private int value;

    public IntegerSortableWrapper(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

}
