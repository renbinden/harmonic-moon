package io.github.lucariatias.harmonicmoon.event.messagebox;

import io.github.lucariatias.harmonicmoon.event.Event;
import io.github.lucariatias.harmonicmoon.message.MessageBox;

public class MessageBoxCloseEvent extends Event {

    private MessageBox messageBox;

    public MessageBoxCloseEvent(MessageBox messageBox) {
        this.messageBox = messageBox;
    }

    public MessageBox getMessageBox() {
        return messageBox;
    }

}
