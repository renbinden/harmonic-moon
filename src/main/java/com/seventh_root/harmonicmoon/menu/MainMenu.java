package com.seventh_root.harmonicmoon.menu;

import com.seventh_root.harmonicmoon.HarmonicMoon;

import java.awt.image.BufferedImage;

public class MainMenu extends Menu {


    public MainMenu(final HarmonicMoon harmonicMoon, BufferedImage logo) {
        super(harmonicMoon, logo);
        addMenuItem("New game", new Runnable() {
            @Override
            public void run() {
                harmonicMoon.showWorld("palace_1_2f");
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
