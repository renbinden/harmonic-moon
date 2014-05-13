package io.github.lucariatias.harmonicmoon.event.messagebox;

import io.github.lucariatias.harmonicmoon.event.Listener;

public abstract class MessageBoxCloseListener extends Listener<MessageBoxCloseEvent> {

    public MessageBoxCloseListener() {
        super(MessageBoxCloseEvent.class);
    }

    public abstract void onMessageBoxClose(MessageBoxCloseEvent event);

    @Override
    public void onEvent(MessageBoxCloseEvent event) {
        onMessageBoxClose(event);
    }

}
