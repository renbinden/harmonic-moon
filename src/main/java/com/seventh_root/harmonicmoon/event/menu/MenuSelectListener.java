package com.seventh_root.harmonicmoon.event.menu;

import com.seventh_root.harmonicmoon.event.Listener;

public abstract class MenuSelectListener extends Listener<MenuSelectEvent> {

    public MenuSelectListener() {
        super(MenuSelectEvent.class);
    }

    @Override
    public void onEvent(MenuSelectEvent event) {
        onMenuSelect(event);
    }

    public abstract void onMenuSelect(MenuSelectEvent event);

}
