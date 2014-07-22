package io.github.lucariatias.harmonicmoon.fight;

public interface TurnActionProcess {

    public void onStart();

    public void onTick();

    public boolean isFinished();

}
