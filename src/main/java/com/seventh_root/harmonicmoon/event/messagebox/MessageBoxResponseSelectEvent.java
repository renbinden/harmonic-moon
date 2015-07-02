package com.seventh_root.harmonicmoon.event.messagebox;

import com.seventh_root.harmonicmoon.event.Cancellable;
import com.seventh_root.harmonicmoon.event.Event;
import com.seventh_root.harmonicmoon.message.Message;
import com.seventh_root.harmonicmoon.message.MessageBox;

public class MessageBoxResponseSelectEvent extends Event implements Cancellable {

    private MessageBox messageBox;
    private Message message;
    private String selectedResponse;
    private boolean cancelled;

    public MessageBoxResponseSelectEvent(MessageBox messageBox, Message message, String selectedResponse) {
        this.messageBox = messageBox;
        this.message = message;
        this.selectedResponse = selectedResponse;
    }

    public MessageBox getMessageBox() {
        return messageBox;
    }

    public Message getMessage() {
        return message;
    }

    public String getSelectedResponse() {
        return selectedResponse;
    }

    public void setSelectedResponse(String selectedResponse) {
        this.selectedResponse = selectedResponse;
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
