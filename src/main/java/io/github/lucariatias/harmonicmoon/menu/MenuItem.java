package io.github.lucariatias.harmonicmoon.menu;

public class MenuItem {

    private String title;
    private Runnable runnable;

    public MenuItem(String title, Runnable runnable) {
        this.title = title;
        this.runnable = runnable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void doSelect() {
        runnable.run();
    }

}
