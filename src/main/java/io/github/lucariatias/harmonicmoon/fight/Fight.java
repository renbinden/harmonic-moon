package io.github.lucariatias.harmonicmoon.fight;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.character.Character;
import io.github.lucariatias.harmonicmoon.enemy.Enemy;
import io.github.lucariatias.harmonicmoon.fight.party.CharacterFightParty;
import io.github.lucariatias.harmonicmoon.fight.party.EnemyFightParty;
import io.github.lucariatias.harmonicmoon.util.sort.Sorter;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Fight {

    private HarmonicMoon harmonicMoon;

    private FightArea area;
    private CharacterFightParty characterParty;
    private EnemyFightParty enemyParty;

    private List<TurnAction> turnActions = new ArrayList<>();
    private Stack<TurnAction> pendingTurnActions = new Stack<>();
    private TurnAction turnAction;
    private boolean turnReady;

    public Fight(HarmonicMoon harmonicMoon, FightArea area, CharacterFightParty characterParty, EnemyFightParty enemyParty) {
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

    public CharacterFightParty getCharacterParty() {
        return characterParty;
    }

    public void setCharacterParty(CharacterFightParty characterParty) {
        this.characterParty = characterParty;
    }

    public EnemyFightParty getEnemyParty() {
        return enemyParty;
    }

    public void setEnemyParty(EnemyFightParty enemyParty) {
        this.enemyParty = enemyParty;
    }

    public void onTick() {
        for (Character.Fight character : characterParty.getMembers()) {
            character.onTick();
        }
        for (Enemy enemy : enemyParty.getMembers()) {
            enemy.onTick();
        }
        if (!pendingTurnActions.isEmpty()) {
            if (turnAction == null || turnAction.isFinished()) {
                turnAction = pendingTurnActions.pop();
                turnAction.doTurn();
            }
        } else if (turnReady && (turnAction == null || turnAction.isFinished())) {
            harmonicMoon.getFightPanel().getOptionBox().resetOptions();
            turnReady = false;
        }
        if (enemyParty.getMembers().isEmpty()) {
            harmonicMoon.getFightPanel().endFight();
            harmonicMoon.getFightPanel().setActive(false);
            harmonicMoon.showWorld(harmonicMoon.getWorldPanel().getWorld().getName());
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
