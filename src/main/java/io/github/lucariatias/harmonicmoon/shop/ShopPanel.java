package io.github.lucariatias.harmonicmoon.shop;

import io.github.lucariatias.harmonicmoon.inventory.item.Item;

import javax.swing.*;
import java.awt.*;

public class ShopPanel extends JPanel {

    private Shop shop;

    public ShopPanel(Shop shop) {
        this.shop = shop;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRoundRect(16, 16, getWidth() - 32, getHeight() - 32, 16, 16);
        int y = 32;
        for (Item item : getShop().getItems()) {
            graphics.setColor(Color.GRAY);
            graphics.fillRoundRect(32, y, getWidth() - 64, 32, 8, 8);
            graphics.setColor(Color.WHITE);
            graphics.drawString(item.getName(), 40, y + 8);
            y += 48;
        }
    }

}
