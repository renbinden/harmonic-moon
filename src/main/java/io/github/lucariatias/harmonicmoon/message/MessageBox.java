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
            long startTime = System.currentTimeMillis();
            this.image = ImageIO.read(getClass().getResourceAsStream("/message.png"));
            harmonicMoon.getLogger().info("Loaded message box image (" + (System.currentTimeMillis() - startTime) + "ms)");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        this.message = "";
        this.y = 480;
        this.hidden = true;
    }

    public void queueMessage(String message) {
        if (hidden) {
            queuedMessages.add(message + " \u21b2");
            nextMessage();
            setHidden(false);
        } else {
            queuedMessages.add(message + " \u21b2");
        }
    }

    public void nextMessage() {
        if (!queuedMessages.isEmpty()) {
            charactersShown = 0;
            this.message = queuedMessages.poll();
        } else {
            setHidden(true);
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCharactersShown() {
        return charactersShown;
    }

    public void setCharactersShown(int charactersShown) {
        this.charactersShown = charactersShown;
    }

    public void showAllCharacters() {
        setCharactersShown(message.length());
    }

    public void onTick() {
        if (hidden) y = y < harmonicMoon.getHeight() ? y + 8 : harmonicMoon.getHeight(); else y = y > harmonicMoon.getHeight() - image.getHeight() ? y - 8 : harmonicMoon.getHeight() - image.getHeight();
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
