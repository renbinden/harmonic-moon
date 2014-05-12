package io.github.lucariatias.harmonicmoon.event.messagebox;

import io.github.lucariatias.harmonicmoon.event.Cancellable;
import io.github.lucariatias.harmonicmoon.event.Event;
import io.github.lucariatias.harmonicmoon.message.MessageBox;

public class MessageBoxResponseSelectEvent extends Event implements Cancellable {

    private MessageBox messageBox;
    private String message;
    private String[] responses;
    private String selectedResponse;
    private boolean cancelled;

    public MessageBoxResponseSelectEvent(MessageBox messageBox, String message, String[] responses, String selectedResponse) {
        this.messageBox = messageBox;
        this.message = message;
        this.responses = responses;
        this.selectedResponse = selectedResponse;
    }

    public MessageBox getMessageBox() {
        return messageBox;
    }

    public String getMessage() {
        return message;
    }

    public String[] getResponses() {
        return responses;
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
