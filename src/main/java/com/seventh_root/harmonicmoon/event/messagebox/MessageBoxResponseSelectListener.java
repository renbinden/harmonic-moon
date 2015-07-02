package com.seventh_root.harmonicmoon.event.messagebox;

import com.seventh_root.harmonicmoon.event.Listener;

public abstract class MessageBoxResponseSelectListener extends Listener<MessageBoxResponseSelectEvent> {

    public MessageBoxResponseSelectListener() {
        super(MessageBoxResponseSelectEvent.class);
    }

    public abstract void onMessageBoxResponseSelect(MessageBoxResponseSelectEvent event);

    @Override
    public void onEvent(MessageBoxResponseSelectEvent event) {
        onMessageBoxResponseSelect(event);
    }

}
