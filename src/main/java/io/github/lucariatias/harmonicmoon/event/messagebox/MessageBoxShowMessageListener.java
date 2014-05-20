package io.github.lucariatias.harmonicmoon.event.messagebox;

import io.github.lucariatias.harmonicmoon.event.Listener;

public abstract class MessageBoxShowMessageListener extends Listener<MessageBoxShowMessageEvent> {

    public MessageBoxShowMessageListener() {
        super(MessageBoxShowMessageEvent.class);
    }

    public abstract void onMessageBoxShowMessage(MessageBoxShowMessageEvent event);

    @Override
    public void onEvent(MessageBoxShowMessageEvent event) {
        onMessageBoxShowMessage(event);
    }

}
