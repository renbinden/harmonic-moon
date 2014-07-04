package io.github.lucariatias.harmonicmoon.menu;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;

import java.awt.image.BufferedImage;

public class MainMenu extends Menu {


    public MainMenu(final HarmonicMoon harmonicMoon, BufferedImage logo) {
        super(harmonicMoon, logo);
        addMenuItem("New game", new Runnable() {
            @Override
            public void run() {
                harmonicMoon.setPanel(/*"map_palace_1_2f"*/"map_world_test");
                harmonicMoon.getWorldPanel().reset();
                harmonicMoon.getWorldPanel().setActive(true);
                harmonicMoon.getMusicPlayer().loop("/music/avirnyr.ogg");
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
            public void run() {
                System.exit(0);
            }
        });
    }
}
