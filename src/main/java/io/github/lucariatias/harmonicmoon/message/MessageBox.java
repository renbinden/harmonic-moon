package io.github.lucariatias.harmonicmoon.message;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageBox {

    private HarmonicMoon harmonicMoon;
    private BufferedImage image;
    private String message;
    private Queue<String> queuedMessages = new ConcurrentLinkedQueue<>();
    private int charactersShown;
    private int y;
    private boolean hidden;

    public MessageBox(HarmonicMoon harmonicMoon) {
        this.harmonicMoon = harmonicMoon;
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/message.png"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        this.message = "";
        this.y = 480;
        this.hidden = true;
    }

    public void queueMessage(String message) {
        if (hidden) {
            this.message = message + " \u21b2";
        } else {
            queuedMessages.add(message + " \u21b2");
        }
    }

    public void nextMessage() {
        if (!queuedMessages.isEmpty()) {
            this.message = queuedMessages.poll();
        } else {
            setHidden(true);
        }
    }

    public void onTick() {
        if (hidden) y++; else y--;
        if (y == harmonicMoon.getHeight() - image.getHeight()) {
            if (message.length() > charactersShown) {
                charactersShown++;
            }
        }
    }

    public void render(Graphics graphics) {
        graphics.drawImage(image, 0, y, null);
        graphics.setColor(Color.WHITE);
        graphics.setFont(harmonicMoon.getMessageFont());
        graphics.drawString(message.substring(0, charactersShown), 32, y + 32);
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isHidden() {
        return hidden;
    }
}
