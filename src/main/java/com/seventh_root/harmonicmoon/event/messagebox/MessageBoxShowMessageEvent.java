package com.seventh_root.harmonicmoon.event.messagebox;

import com.seventh_root.harmonicmoon.event.Cancellable;
import com.seventh_root.harmonicmoon.event.Event;
import com.seventh_root.harmonicmoon.message.Message;
import com.seventh_root.harmonicmoon.message.MessageBox;

public class MessageBoxShowMessageEvent extends Event implements Cancellable {

    private MessageBox messageBox;
    private Message message;
    private boolean cancelled;

    public MessageBoxShowMessageEvent(MessageBox messageBox, Message message) {
        this.messageBox = messageBox;
        this.message = message;
    }

    public MessageBox getMessageBox() {
        return messageBox;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
