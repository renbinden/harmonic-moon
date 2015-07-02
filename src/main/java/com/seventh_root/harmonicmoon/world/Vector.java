package com.seventh_root.harmonicmoon.world;

public class Vector {

    private final int i;
    private final int j;

    public Vector(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getHorizontalComponent() {
        return i;
    }

    public int getVerticalComponent() {
        return j;
    }

    public Vector multiply(int amount) {
        return new Vector(i * amount, j * amount);
    }

}
