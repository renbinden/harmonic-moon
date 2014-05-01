package io.github.lucariatias.harmonicmoon.event;

public abstract class Listener<T extends Event> {

    private Class<T> event;

    public Listener(Class<T> event) {
        this.event = event;
    }

    public abstract void onEvent(T event);

    public Class<T> getEvent() {
        return event;
    }

}
