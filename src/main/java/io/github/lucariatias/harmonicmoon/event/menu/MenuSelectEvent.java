package io.github.lucariatias.harmonicmoon.event.menu;

import io.github.lucariatias.harmonicmoon.event.Cancellable;
import io.github.lucariatias.harmonicmoon.event.Event;
import io.github.lucariatias.harmonicmoon.menu.Menu;
import io.github.lucariatias.harmonicmoon.menu.MenuItem;

public class MenuSelectEvent extends Event implements Cancellable {

    private Menu menu;
    private MenuItem menuItem;
    private boolean cancelled;

    public MenuSelectEvent(Menu menu, MenuItem menuItem) {
        this.menu = menu;
        this.menuItem = menuItem;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
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
