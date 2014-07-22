package io.github.lucariatias.harmonicmoon.fight;

import java.util.Queue;

public class TurnAction {

    private Combatant combatant;
    private TurnActionProcess activeProcess;
    private Queue<TurnActionProcess> processes;
    private boolean hasStarted;

    public TurnAction(Combatant combatant, Queue<TurnActionProcess> processes) {
        this.combatant = combatant;
        this.processes = processes;
    }

    public Combatant getCombatant() {
        return combatant;
    }

    public void onStart() {
        hasStarted = true;
    }

    public void onTick() {
        if (hasStarted) {
            if (activeProcess == null || activeProcess.isFinished()) {
                activeProcess = processes.poll();
                if (activeProcess != null) {
                    activeProcess.onStart();
                    activeProcess.onTick();
                }
            } else {
                activeProcess.onTick();
            }
        }
    }

    public boolean isFinished() {
        return hasStarted && processes.isEmpty() && (activeProcess == null || activeProcess.isFinished());
    }

}
