package com.seventh_root.harmonicmoon.shop;

import com.seventh_root.harmonicmoon.HarmonicMoon;
import com.seventh_root.harmonicmoon.character.Party;
import com.seventh_root.harmonicmoon.inventory.PartyInventory;
import com.seventh_root.harmonicmoon.inventory.item.Item;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ShopPanel extends JPanel {

    private HarmonicMoon harmonicMoon;

    private Shop shop;

    private boolean active;

    public ShopPanel(HarmonicMoon harmonicMoon, Shop shop) {
        this.harmonicMoon = harmonicMoon;
        this.shop = shop;
        addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                HarmonicMoon harmonicMoon = ShopPanel.this.harmonicMoon;
                Font font = harmonicMoon.getTitleFont().deriveFont(16.0F);
                if (SwingUtilities.isLeftMouseButton(event)) {
                    int x = 32;
                    int y = 32;
                    for (Item item : getShop().getItems()) {
                        Point mouse = MouseInfo.getPointerInfo().getLocation();
                        if (mouse.getX() - getLocationOnScreen().getX() >= x && mouse.getY() - getLocationOnScreen().getY() >= y && mouse.getX() - getLocationOnScreen().getX() <= getWidth() - x && mouse.getY() - getLocationOnScreen().getY() <= y + 32 + getFontMetrics(font).getMaxAscent()) {
                            Party party = harmonicMoon.getCharacterManager().getParty(harmonicMoon.getPlayer().getCharacter());
                            PartyInventory inventory = party.getInventory();
                            inventory.setMoney(getShop().getCurrency(), inventory.getMoney(getShop().getCurrency()) - getShop().getPrice(item));
                            harmonicMoon.getCharacterManager().getParty(harmonicMoon.getPlayer().getCharacter()).getInventory().addItem(item, 1);
                            return;
                        }
                        y += 48;
                    }
                }
            }
        });
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void onTick() {
        if (isActive()) {
            repaint();
        }
    }

    @Override
    public void paintComponent(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRoundRect(16, 16, getWidth() - 32, getHeight() - 32, 16, 16);
        int x = 32;
        int y = 32;
        Point mousePoint = MouseInfo.getPointerInfo().getLocation();
        int mouseX = (int) Math.round(mousePoint.getX() - harmonicMoon.getLocationOnScreen().getX());
        int mouseY = (int) Math.round(mousePoint.getY() - harmonicMoon.getLocationOnScreen().getY());
        for (Item item : getShop().getItems()) {
            graphics.setColor(Color.GRAY);
            graphics.fillRoundRect(x, y, getWidth() - 64, 32, 8, 8);
            graphics.setColor(Color.WHITE);
            if (mouseX > x && mouseY > y && mouseX < getWidth() - 64 && mouseY < y + 32) {
                graphics.drawRoundRect(x, y, getWidth() - 64, 32, 8, 8);
            }
            graphics.setFont(harmonicMoon.getMessageFont().deriveFont(16F));
            graphics.drawString(item.getName(), 40, y + graphics.getFontMetrics().getLeading() + graphics.getFontMetrics().getMaxAscent() + 8);
            y += 48;
        }
    }

}
