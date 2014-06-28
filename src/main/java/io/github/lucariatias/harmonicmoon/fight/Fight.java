package io.github.lucariatias.harmonicmoon.fight;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.character.FightCharacter;
import io.github.lucariatias.harmonicmoon.enemy.Enemy;
import io.github.lucariatias.harmonicmoon.party.CharacterParty;
import io.github.lucariatias.harmonicmoon.party.EnemyParty;
import io.github.lucariatias.harmonicmoon.util.sort.Sorter;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Fight {

    private HarmonicMoon harmonicMoon;

    private FightArea area;
    private CharacterParty characterParty;
    private EnemyParty enemyParty;

    private List<TurnAction> turnActions = new ArrayList<>();
    private Stack<TurnAction> pendingTurnActions = new Stack<>();
    private TurnAction turnAction;
    private boolean turnReady;

    public Fight(HarmonicMoon harmonicMoon, FightArea area, CharacterParty characterParty, EnemyParty enemyParty) {
        this.harmonicMoon = harmonicMoon;
        this.area = area;
        this.characterParty = characterParty;
        this.enemyParty = enemyParty;
    }

    public FightArea getArea() {
        return area;
    }

    public void setArea(FightArea area) {
        this.area = area;
    }

    public CharacterParty getCharacterParty() {
        return characterParty;
    }

    public void setCharacterParty(CharacterParty characterParty) {
        this.characterParty = characterParty;
    }

    public EnemyParty getEnemyParty() {
        return enemyParty;
    }

    public void setEnemyParty(EnemyParty enemyParty) {
        this.enemyParty = enemyParty;
    }

    public void onTick() {
        for (FightCharacter character : characterParty.getMembers()) {
            character.onTick();
        }
        for (Enemy enemy : enemyParty.getMembers()) {
            enemy.onTick();
        }
        if (!pendingTurnActions.isEmpty()) {
            if (turnAction == null || turnAction.isFinished()) {
                turnAction = pendingTurnActions.pop();
                harmonicMoon.getLogger().info("Performing turn action: " + turnAction.getCombatant().getName());
                turnAction.doTurn();
            }
        } else if (turnReady && (turnAction == null || turnAction.isFinished())) {
            harmonicMoon.getFightPanel().getOptionBox().resetOptions();
            turnReady = false;
        }
        if (enemyParty.getMembers().isEmpty()) {
            harmonicMoon.getFightPanel().endFight();
            harmonicMoon.getFightPanel().setActive(false);
            harmonicMoon.setPanel("map_" + harmonicMoon.getWorldPanel().getWorld().getName());
            harmonicMoon.getWorldPanel().setActive(true);
        }
    }

    public void addTurnAction(TurnAction turnAction) {
        turnActions.add(turnAction);
    }

    public void doTurn() {
        for (Enemy enemy : getEnemyParty().getMembers()) {
            turnActions.add(enemy.chooseTurnAction(this));
        }
        List<TurnSortableWrapper> sortableTurns = new ArrayList<>();
        for (TurnAction turnAction : turnActions) {
            sortableTurns.add(new TurnSortableWrapper(turnAction));
        }
        turnActions.clear();
        Sorter<TurnSortableWrapper> sorter = new Sorter<>(sortableTurns);
        List<TurnSortableWrapper> sortedTurns = sorter.sortAscending();
        for (TurnSortableWrapper wrapper : sortedTurns) {
            pendingTurnActions.push(wrapper.getTurnAction());
        }
        turnReady = true;
    }

}
