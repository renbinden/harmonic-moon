package io.github.lucariatias.harmonicmoon.menu;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;

public class MainMenu extends Menu {


    public MainMenu(final HarmonicMoon harmonicMoon) {
        super(harmonicMoon);
        setTitle("HARMONIC MOON");
        addMenuItem("New game", new Runnable() {
            @Override
            public void run() {
                harmonicMoon.setPanel("map_world"); harmonicMoon.getWorldPanel().reset(); harmonicMoon.getWorldPanel().setActive(true);
            }
        });
        addMenuItem("Continue", new Runnable() {
            @Override
            public void run() {}
        });
        addMenuItem("Options", new Runnable() {
            @Override
            public void run() {}
        });
        addMenuItem("Exit", new Runnable() {
            @Override
            public void run() {}
        });
    }
}
