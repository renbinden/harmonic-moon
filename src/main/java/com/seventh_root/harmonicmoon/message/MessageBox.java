package com.seventh_root.harmonicmoon.message;

import com.seventh_root.harmonicmoon.HarmonicMoon;
import com.seventh_root.harmonicmoon.event.messagebox.MessageBoxCloseEvent;
import com.seventh_root.harmonicmoon.event.messagebox.MessageBoxResponseSelectEvent;
import com.seventh_root.harmonicmoon.event.messagebox.MessageBoxShowMessageEvent;

import javax.imageio.ImageIO;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageBox {

    private HarmonicMoon harmonicMoon;
    private BufferedImage image;
    private BufferedImage responseBoxImage;
    private Message message;
    private Queue<Message> queuedMessages = new ConcurrentLinkedQueue<>();
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
        this.message = new Message("");
        this.y = 480;
        this.hidden = true;
        harmonicMoon.getFrame().addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (message instanceof Question) {
                    HarmonicMoon harmonicMoon = MessageBox.this.harmonicMoon;
                    int responseX = harmonicMoon.getWidth() - responseBoxImage.getWidth() + 35;
                    int responseY = responseBoxY + 27;
                    Question question = (Question) message;
                    for (String response : question.getResponses()) {
                        Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
                        int mouseXInWindow = (int) (mouseLocation.getX() - harmonicMoon.getLocationOnScreen().getX());
                        int mouseYInWindow = (int) (mouseLocation.getY() - harmonicMoon.getLocationOnScreen().getY());
                        if (mouseXInWindow >= responseX - 8
                                && mouseXInWindow <= responseX - 62 + responseBoxImage.getWidth()
                                && mouseYInWindow >= responseY
                                && mouseYInWindow <= responseY + 16) {
                            MessageBoxResponseSelectEvent messageBoxResponseSelectEvent = new MessageBoxResponseSelectEvent(MessageBox.this, message, response);
                            MessageBox.this.harmonicMoon.getEventManager().dispatchEvent(messageBoxResponseSelectEvent);
                        }
                        responseY += 16;
                    }
                }
            }
        });
    }

    public void queueMessage(Message message) {
        if (hidden) {
            queuedMessages.add(message);
            nextMessage();
            setHidden(false);
        } else {
            queuedMessages.add(message);
        }
    }

    public void queueMessage(String message) {
        queueMessage(new Message(message));
    }

    public void queueMessage(String message, String... responses) {
        queueMessage(new Question(message, responses));
    }

    public void nextMessage() {
        if (!queuedMessages.isEmpty()) {
            charactersShown = 0;
            MessageBoxShowMessageEvent messageBoxShowMessageEvent = new MessageBoxShowMessageEvent(this, queuedMessages.poll());
            harmonicMoon.getEventManager().dispatchEvent(messageBoxShowMessageEvent);
            if (!messageBoxShowMessageEvent.isCancelled()) {
                message = messageBoxShowMessageEvent.getMessage();
            } else {
                queuedMessages.remove(messageBoxShowMessageEvent.getMessage());
            }
        } else if (!isHidden()) {
            MessageBoxCloseEvent event = new MessageBoxCloseEvent(this);
            harmonicMoon.getEventManager().dispatchEvent(event);
            setHidden(true);
        }
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public int getCharactersShown() {
        return charactersShown;
    }

    public void setCharactersShown(int charactersShown) {
        this.charactersShown = charactersShown;
    }

    public void showAllCharacters() {
        setCharactersShown(message.getText().length());
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
            if (message.getText().length() > charactersShown) {
                charactersShown++;
            }
        }
    }

    public void render(Graphics graphics) {
        if (responseBoxY >= harmonicMoon.getHeight() && y >= harmonicMoon.getHeight()) return;
        graphics.setColor(Color.WHITE);
        graphics.setFont(harmonicMoon.getMessageFont());
        if (message instanceof Question) {
            renderResponseBox(graphics);
        }
        graphics.drawImage(image, 0, y, null);
        graphics.drawString(message.getText().substring(0, charactersShown), 32, y + 32);
    }

    private void renderResponseBox(Graphics graphics) {
        graphics.drawImage(responseBoxImage, harmonicMoon.getWidth() - responseBoxImage.getWidth(), responseBoxY, null);
        int responseX = harmonicMoon.getWidth() - responseBoxImage.getWidth() + 35;
        int responseY = responseBoxY + 27;
        Question question = (Question) message;
        for (String response : question.getResponses()) {
            Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
            int mouseXInWindow = (int) (mouseLocation.getX() - harmonicMoon.getLocationOnScreen().getX());
            int mouseYInWindow = (int) (mouseLocation.getY() - harmonicMoon.getLocationOnScreen().getY());
            if (mouseXInWindow >= responseX - 8
                    && mouseXInWindow <= responseX - 62 + responseBoxImage.getWidth()
                    && mouseYInWindow >= responseY
                    && mouseYInWindow <= responseY + 16) {
                graphics.setColor(Color.GRAY);
                graphics.drawRect(responseX - 8, responseY, responseBoxImage.getWidth() - 54, 16);
            }
            graphics.drawString(response, responseX, responseY + 2 + graphics.getFontMetrics().getLeading() + graphics.getFontMetrics().getMaxAscent());
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
