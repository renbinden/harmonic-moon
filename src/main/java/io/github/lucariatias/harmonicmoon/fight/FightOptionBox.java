package io.github.lucariatias.harmonicmoon.fight;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class FightOptionBox {

    private HarmonicMoon harmonicMoon;
    private BufferedImage image;
    private String[] options;

    public FightOptionBox(HarmonicMoon harmonicMoon) {
        this.harmonicMoon = harmonicMoon;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/message.png"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        this.options = new String[] {"Attack", "Defend", "Use item", "Run"};
    }

    public void setOptions(String... options) {
        this.options = options;
    }

    public String[] getOptions() {
        return options;
    }

    public void render(Graphics graphics) {
        graphics.drawImage(image, 0, 480 - image.getHeight(), null);
        if (options != null) {
            graphics.setColor(Color.WHITE);
            int x = 16, y = 496 - image.getHeight();
            for (String option : options) {
                graphics.drawRect(x, y, image.getWidth() / 2 - 32, image.getHeight() / 2 - 32);
                graphics.drawString(option, x + 8, y + 16);
                y += 48;
                if ((y - (496 - image.getHeight())) / 48 == 2) {
                    y = 496 - image.getHeight();
                    x += image.getWidth() / 2;
                }
            }
        }
    }

}
