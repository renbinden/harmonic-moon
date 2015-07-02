package com.seventh_root.harmonicmoon.event;

import com.seventh_root.harmonicmoon.HarmonicMoon;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EventManager {

    private HarmonicMoon harmonicMoon;

    private Map<Class<? extends Event>, Set<Listener>> listeners = new HashMap<>();

    public EventManager(HarmonicMoon harmonicMoon) {
        this.harmonicMoon = harmonicMoon;
    }

    public boolean registerListener(Listener listener) {
        if (!listeners.containsKey(listener.getEvent())) {
            listeners.put(listener.getEvent(), new HashSet<Listener>());
        }
        return listeners.get(listener.getEvent()).add(listener);
    }

    public void dispatchEvent(Event event) {
        if (!listeners.containsKey(event.getClass())) return;
        for (Listener listener : listeners.get(event.getClass())) {
            listener.onEvent(event);
        }
    }

}
