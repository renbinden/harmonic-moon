package io.github.lucariatias.harmonicmoon.message;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MessageKeyListener extends KeyAdapter {

    private MessageBox messageBox;

    public MessageKeyListener(MessageBox messageBox) {
        this.messageBox = messageBox;
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_ENTER) {
            if (messageBox.getCharactersShown() < messageBox.getMessage().length()) messageBox.showAllCharacters(); else messageBox.nextMessage();
        }
    }
}
