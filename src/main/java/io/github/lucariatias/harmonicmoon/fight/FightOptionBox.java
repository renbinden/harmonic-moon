package io.github.lucariatias.harmonicmoon.fight;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FightOptionBox {

    private HarmonicMoon harmonicMoon;
    private BufferedImage image;

    public FightOptionBox(HarmonicMoon harmonicMoon) {
        this.harmonicMoon = harmonicMoon;
    }

    public void render(Graphics graphics) {
        graphics.drawImage(image, 0, 480 - image.getHeight(), null);
    }

}
