package io.github.lucariatias.harmonicmoon.fight;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TurnState {

    private List<TurnAction> turnActions;
    private final Stack<TurnAction> pendingTurnActions;
    private TurnAction turnAction;
    private boolean turnReady;

    public TurnState(List<TurnAction> turnActions, Stack<TurnAction> pendingTurnActions, TurnAction turnAction, boolean turnReady) {
        this.turnActions = turnActions;
        this.pendingTurnActions = pendingTurnActions;
        this.turnAction = turnAction;
        this.turnReady = turnReady;
    }

    public TurnState(TurnState toCopy) {
        this.turnActions = new ArrayList<>();
        this.turnActions.addAll(toCopy.turnActions);
        this.pendingTurnActions = new Stack<>();
        this.pendingTurnActions.addAll(toCopy.pendingTurnActions);
        this.turnAction = toCopy.turnAction;
        this.turnReady = toCopy.turnReady;
    }

    public List<TurnAction> getTurnActions() {
        return turnActions;
    }

    public Stack<TurnAction> getPendingTurnActions() {
        return pendingTurnActions;
    }

    public TurnAction getTurnAction() {
        return turnAction;
    }

    public void setTurnAction(TurnAction turnAction) {
        this.turnAction = turnAction;
    }

    public boolean isTurnReady() {
        return turnReady;
    }

    public void setTurnReady(boolean turnReady) {
        this.turnReady = turnReady;
    }

}
