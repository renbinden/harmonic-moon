package com.seventh_root.harmonicmoon.event.messagebox;

import com.seventh_root.harmonicmoon.event.Event;
import com.seventh_root.harmonicmoon.message.MessageBox;

public class MessageBoxCloseEvent extends Event {

    private MessageBox messageBox;

    public MessageBoxCloseEvent(MessageBox messageBox) {
        this.messageBox = messageBox;
    }

    public MessageBox getMessageBox() {
        return messageBox;
    }

}
