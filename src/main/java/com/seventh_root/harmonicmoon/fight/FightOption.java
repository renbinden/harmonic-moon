package com.seventh_root.harmonicmoon.fight;

public class FightOption {

    private String name;
    private Runnable runnable;

    public FightOption(String name, Runnable runnable) {
        this.name = name;
        this.runnable = runnable;
    }

    public String getName() {
        return name;
    }

    public void doSelect() {
        runnable.run();
    }
}
