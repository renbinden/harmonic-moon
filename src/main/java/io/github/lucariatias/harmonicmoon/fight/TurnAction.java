package io.github.lucariatias.harmonicmoon.fight;

public class TurnAction {

    private Combatant combatant;
    private Runnable runnable;
    private long duration;
    private long startTime;
    private boolean hasStarted;

    public TurnAction(Combatant combatant, Runnable runnable, long duration) {
        this.combatant = combatant;
        this.runnable = runnable;
        this.duration = duration;
    }

    public Combatant getCombatant() {
        return combatant;
    }

    public void doTurn() {
        startTime = System.currentTimeMillis();
        hasStarted = true;
        runnable.run();
    }

    public boolean isFinished() {
        return hasStarted && startTime + duration <= System.currentTimeMillis();
    }

}
