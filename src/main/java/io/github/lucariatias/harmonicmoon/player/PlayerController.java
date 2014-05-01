package io.github.lucariatias.harmonicmoon.player;

import io.github.lucariatias.harmonicmoon.world.Direction;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

public  abstract class PlayerController {
	
    private Player player;
    private Set<PlayerAction> activeActions = Collections.synchronizedSet(EnumSet.noneOf(PlayerAction.class));
    private boolean active;

    public PlayerController(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isActionEnabled(PlayerAction action) {
        return active && activeActions.contains(action);
    }

    public void setActionEnabled(PlayerAction action, boolean enabled) {
        if (enabled) activeActions.add(action); else activeActions.remove(action);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void onTick() {
        if (active) {
            if (isActionEnabled(PlayerAction.MOVE_UP)) player.move(Direction.UP);
            if (isActionEnabled(PlayerAction.MOVE_LEFT)) player.move(Direction.LEFT);
            if (isActionEnabled(PlayerAction.MOVE_RIGHT)) player.move(Direction.RIGHT);
            if (isActionEnabled(PlayerAction.MOVE_DOWN)) player.move(Direction.DOWN);
        }
    }

}
