package io.github.lucariatias.harmonicmoon.message;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageBox {

    private HarmonicMoon harmonicMoon;
    private BufferedImage image;
    private BufferedImage responseBoxImage;
    private String message;
    private String[] responses;
    private Queue<String> queuedMessages = new ConcurrentLinkedQueue<>();
    private Map<String, String[]> queuedResponses = new HashMap<>();
    private int charactersShown;
    private int y;
    private int responseBoxY;
    private boolean hidden;

    public MessageBox(HarmonicMoon harmonicMoon) {
        this.harmonicMoon = harmonicMoon;
        try {
            long startTime = System.currentTimeMillis();
            this.image = ImageIO.read(getClass().getResourceAsStream("/message.png"));
            this.responseBoxImage = ImageIO.read(getClass().getResourceAsStream("/question.png"));
            harmonicMoon.getLogger().info("Loaded message box images (" + (System.currentTimeMillis() - startTime) + "ms)");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        this.message = "";
        this.y = 480;
        this.hidden = true;
    }

    public void queueMessage(String message) {
        if (hidden) {
            queuedMessages.add(message);
            nextMessage();
            setHidden(false);
        } else {
            queuedMessages.add(message);
        }
    }

    public void queueMesssage(String message, String... responses) {
        queuedResponses.put(message, responses);
        queueMessage(message);
    }

    public void nextMessage() {
        if (!queuedMessages.isEmpty()) {
            charactersShown = 0;
            this.message = queuedMessages.poll();
            if (queuedResponses.get(message) != null) {
                this.responses = queuedResponses.get(message);
                queuedResponses.remove(message);
            } else {
                this.responses = null;
            }
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
        if (hidden) {
            y = y < harmonicMoon.getHeight() ? y + 8 : harmonicMoon.getHeight();
            responseBoxY = responseBoxY < harmonicMoon.getHeight() ? responseBoxY + 8 : harmonicMoon.getHeight();
        } else {
            y = y > harmonicMoon.getHeight() - image.getHeight() ? y - 8 : harmonicMoon.getHeight() - image.getHeight();
            responseBoxY = responseBoxY > harmonicMoon.getHeight() - (image.getHeight() + responseBoxImage.getHeight() - 16) ? responseBoxY - 8 : harmonicMoon.getHeight() - (image.getHeight() + responseBoxImage.getHeight() - 16);
        }
        if (y == harmonicMoon.getHeight() - image.getHeight()) {
            if (message.length() > charactersShown) {
                charactersShown++;
            }
        }
    }

    public void render(Graphics graphics) {
        if (y >= harmonicMoon.getHeight()) return;
        graphics.setColor(Color.WHITE);
        graphics.setFont(harmonicMoon.getMessageFont());
        if (responses != null) {
            renderResponseBox(graphics);
        }
        graphics.drawImage(image, 0, y, null);
        graphics.drawString(message.substring(0, charactersShown), 32, y + 32);
    }

    private void renderResponseBox(Graphics graphics) {
        graphics.drawImage(responseBoxImage, harmonicMoon.getWidth() - responseBoxImage.getWidth(), responseBoxY, null);
        int responseX = harmonicMoon.getWidth() - responseBoxImage.getWidth() + 16;
        int responseY = responseBoxY + 16;
        for (String response : responses) {
            Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
            int mouseXInWindow = (int) (mouseLocation.getX() - harmonicMoon.getLocationOnScreen().getX());
            int mouseYInWindow = (int) (mouseLocation.getY() - harmonicMoon.getLocationOnScreen().getY());
            if (mouseXInWindow >= responseX - 16
                    && mouseXInWindow <= responseX - 16 + responseBoxImage.getWidth()
                    && mouseYInWindow >= responseY
                    && mouseYInWindow <= responseY + 16) {
                graphics.setColor(Color.GRAY);
                graphics.drawRect(responseX - 16, responseY, responseBoxImage.getWidth(), 16);
            }
            graphics.drawString(response, responseX, responseY + graphics.getFontMetrics().getLeading() + graphics.getFontMetrics().getMaxAscent());
            responseY += 16;
            graphics.setColor(Color.WHITE);
        }
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isHidden() {
        return hidden;
    }
}
