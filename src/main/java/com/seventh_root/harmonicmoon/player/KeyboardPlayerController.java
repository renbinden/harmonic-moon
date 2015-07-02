package com.seventh_root.harmonicmoon.player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;

public class KeyboardPlayerController extends PlayerController implements KeyListener {

    public KeyboardPlayerController(Player player) {
        super(player);
    }

    @Override
    public void keyTyped(KeyEvent event) {}

    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case VK_W:case VK_UP: setActionEnabled(MOVE_UP, true); break;
            case VK_A:case VK_LEFT: setActionEnabled(MOVE_LEFT, true); break;
            case VK_S:case VK_DOWN: setActionEnabled(MOVE_DOWN, true); break;
            case VK_D:case VK_RIGHT: setActionEnabled(MOVE_RIGHT, true); break;
            case VK_Z: setActionEnabled(INTERACT, true); break;
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        switch (event.getKeyCode()) {
            case VK_W:case VK_UP: setActionEnabled(MOVE_UP, false); break;
            case VK_A:case VK_LEFT: setActionEnabled(MOVE_LEFT, false); break;
            case VK_S:case VK_DOWN: setActionEnabled(MOVE_DOWN, false); break;
            case VK_D:case VK_RIGHT: setActionEnabled(MOVE_RIGHT, false); break;
            case VK_Z: setActionEnabled(INTERACT, false); break;
        }
    }
}
