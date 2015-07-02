package com.seventh_root.harmonicmoon.fight;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public enum FightArea {

    PALACE("/fight-backgrounds/palace.png"),
    GRASS("/fight-backgrounds/grass.png");

    private String backgroundPath;

    private FightArea(String backgroundPath) {
        this.backgroundPath = backgroundPath;
    }

    public String getBackgroundPath() {
        return backgroundPath;
    }

    public BufferedImage getBackground() {
        try {
            return ImageIO.read(getClass().getResourceAsStream(backgroundPath));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
