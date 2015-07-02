package com.seventh_root.harmonicmoon.fight;

public interface TurnActionProcess {

    public void onStart();

    public void onTick();

    public boolean isFinished();

}
