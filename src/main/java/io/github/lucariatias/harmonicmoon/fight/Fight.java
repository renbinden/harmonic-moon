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
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Fight {

    private HarmonicMoon harmonicMoon;

    private FightArea area;
    private CharacterFightParty characterParty;
    private EnemyFightParty enemyParty;

    private AtomicReference<TurnState> turnState = new AtomicReference<>(new TurnState(new ArrayList<>(), new Stack<>(), null, false));

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
        TurnState newTurnState = new TurnState(getTurnState());
        if (!newTurnState.getPendingTurnActions().isEmpty()) {
            if (newTurnState.getTurnAction() == null || newTurnState.getTurnAction().isFinished()) {
                if (newTurnState.getTurnAction() != null) {
                    harmonicMoon.getLogger().info("Completed " + newTurnState.getTurnAction().getCombatant().getName() + "'s turn action.");
                }
                TurnAction turnAction = newTurnState.getPendingTurnActions().pop();
                if (turnAction != null) {
                    turnAction.onStart();
                    turnAction.onTick();
                }
                newTurnState.setTurnAction(turnAction);
            } else {
                newTurnState.getTurnAction().onTick();
            }
        } else if (newTurnState.isTurnReady() && newTurnState.getTurnAction() != null && !newTurnState.getTurnAction().isFinished()) {
            newTurnState.getTurnAction().onTick();
        } else if (newTurnState.isTurnReady()) {
            if (newTurnState.getTurnAction() != null) {
                harmonicMoon.getLogger().info("Completed " + newTurnState.getTurnAction().getCombatant().getName() + "'s turn action.");
            }
            newTurnState.setTurnAction(null);
            harmonicMoon.getFightPanel().getOptionBox().resetOptions();
            newTurnState.setTurnReady(false);
        }
        if (enemyParty.getMembers().isEmpty()) {
            harmonicMoon.getFightPanel().endFight();
            harmonicMoon.getFightPanel().setActive(false);
            harmonicMoon.showWorld(harmonicMoon.getWorldPanel().getWorld().getName());
        }
        setTurnState(newTurnState);
    }

    public void addTurnAction(TurnAction turnAction) {
        getTurnState().getTurnActions().add(turnAction);
    }

    public void doTurn() {
        TurnState newTurnState = new TurnState(getTurnState());
        for (Enemy enemy : getEnemyParty().getMembers()) {
            newTurnState.getTurnActions().add(enemy.chooseTurnAction(this));
        }
        List<TurnSortableWrapper> sortableTurns = newTurnState.getTurnActions().stream().map(TurnSortableWrapper::new).collect(Collectors.toList());
        newTurnState.getTurnActions().clear();
        long startTime = System.currentTimeMillis();
        Sorter<TurnSortableWrapper> sorter = new Sorter<>(sortableTurns);
        List<TurnSortableWrapper> sortedTurns = sorter.sortAscending();
        for (TurnSortableWrapper wrapper : sortedTurns) {
            newTurnState.getPendingTurnActions().push(wrapper.getTurnAction());
        }
        harmonicMoon.getLogger().info("Sorted " + newTurnState.getPendingTurnActions().size() + " turn actions (" + (System.currentTimeMillis() - startTime) + "ms)");
        newTurnState.setTurnReady(true);
        setTurnState(newTurnState);
    }

    private synchronized TurnState getTurnState() {
        return turnState.get();
    }

    private synchronized void setTurnState(TurnState turnState) {
        this.turnState.set(turnState);
    }
}
