package com.seventh_root.harmonicmoon.test.sort;

import com.seventh_root.harmonicmoon.util.sort.Sortable;

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
