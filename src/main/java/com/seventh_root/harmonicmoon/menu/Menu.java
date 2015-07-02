package com.seventh_root.harmonicmoon.menu;

import com.seventh_root.harmonicmoon.HarmonicMoon;
import com.seventh_root.harmonicmoon.event.menu.MenuSelectEvent;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Menu extends JPanel {

    private HarmonicMoon harmonicMoon;

    private BufferedImage logo;
    private String title;
    private List<com.seventh_root.harmonicmoon.menu.MenuItem> menuItems = new ArrayList<>();

    private boolean mousePressed;

    private static final int MENU_ITEM_WIDTH = 384;

    public Menu(final HarmonicMoon harmonicMoon, BufferedImage logo) {
        this.harmonicMoon = harmonicMoon;
        this.logo = logo;
        addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                Font font = harmonicMoon.getTitleFont().deriveFont(16.0F);
                if (SwingUtilities.isLeftMouseButton(event)) {
                    int x = (getWidth() - MENU_ITEM_WIDTH) / 2;
                    int y = 128;
                    for (com.seventh_root.harmonicmoon.menu.MenuItem menuItem : getMenuItems()) {
                        Point mouse = MouseInfo.getPointerInfo().getLocation();
                        if (mouse.getX() - getLocationOnScreen().getX() >= x && mouse.getY() - getLocationOnScreen().getY() >= y && mouse.getX() - getLocationOnScreen().getX() <= getWidth() - x && mouse.getY() - getLocationOnScreen().getY() <= y + 32 + getFontMetrics(font).getMaxAscent()) {
                            MenuSelectEvent menuSelectEvent = new MenuSelectEvent(Menu.this, menuItem);
                            harmonicMoon.getEventManager().dispatchEvent(menuSelectEvent);
                            if (!menuSelectEvent.isCancelled()) {
                                menuSelectEvent.getMenuItem().doSelect();
                            }
                            return;
                        }
                        y += 1.5 * (32 + getFontMetrics(font).getMaxAscent());
                    }
                }
            }
        });
        addMouseListener(new MouseInputAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                Menu.this.mousePressed = true;
            }

            @Override
            public void mouseReleased(MouseEvent event) {
                Menu.this.mousePressed = false;
            }
        });
    }

    @Override
    public void paintComponent(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        if (getTitle() == null || getTitle().isEmpty()) {
            graphics.drawImage(logo, 0, 0, null);
        } else {
            graphics.setFont(harmonicMoon.getTitleFont().deriveFont(48.0F));
            graphics.setColor(Color.WHITE);
            graphics.drawString(title, (getWidth() - graphics.getFontMetrics().charsWidth(title.toCharArray(), 0, title.length())) / 2, (graphics.getFontMetrics().getLeading() + graphics.getFontMetrics().getMaxAscent()) * 2);
        }
        graphics.setFont(harmonicMoon.getTitleFont().deriveFont(16.0F));
        int x = (getWidth() - MENU_ITEM_WIDTH) / 2;
        int y = 128;
        for (com.seventh_root.harmonicmoon.menu.MenuItem menuItem : getMenuItems()) {
            Point mouse = MouseInfo.getPointerInfo().getLocation();
            if (mouse.getX() - getLocationOnScreen().getX() >= x && mouse.getY() - getLocationOnScreen().getY() >= y && mouse.getX() - getLocationOnScreen().getX() <= getWidth() - x && mouse.getY() - getLocationOnScreen().getY() <= y + 32 + graphics.getFontMetrics().getMaxAscent()) {
                if (mousePressed) {
                    graphics.setColor(new Color(0, 0, 40));
                    graphics.fillRoundRect(x - 8, y - 8, MENU_ITEM_WIDTH + 16, 32 + graphics.getFontMetrics().getMaxAscent() + 16, 16, 16);
                    graphics.setColor(Color.BLACK);
                    graphics.fillRoundRect(x + 8, y + 8, MENU_ITEM_WIDTH - 16, 32 + graphics.getFontMetrics().getMaxAscent() - 16, 16, 16);
                    graphics.setColor(Color.BLUE);
                } else {
                    graphics.setColor(Color.BLUE);
                    graphics.fillRoundRect(x - 8, y - 8, MENU_ITEM_WIDTH + 16, 32 + graphics.getFontMetrics().getMaxAscent() + 16, 16, 16);
                    graphics.setColor(Color.BLACK);
                    graphics.fillRoundRect(x + 8, y + 8, MENU_ITEM_WIDTH - 16, 32 + graphics.getFontMetrics().getMaxAscent() - 16, 16, 16);
                    graphics.setColor(Color.CYAN);
                }
            } else {
                graphics.setColor(Color.WHITE);
            }
            graphics.drawRect(x, y, MENU_ITEM_WIDTH, 32 + graphics.getFontMetrics().getMaxAscent());
            graphics.drawString(menuItem.getTitle(), (getWidth() - graphics.getFontMetrics().charsWidth(menuItem.getTitle().toCharArray(), 0, menuItem.getTitle().length())) / 2, 16 + graphics.getFontMetrics().getAscent() + y);
            y += 1.5 * (32 + graphics.getFontMetrics().getMaxAscent());
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<com.seventh_root.harmonicmoon.menu.MenuItem> getMenuItems() {
        return menuItems;
    }

    public void addMenuItem(com.seventh_root.harmonicmoon.menu.MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public void addMenuItem(String title, Runnable runnable) {
        addMenuItem(new com.seventh_root.harmonicmoon.menu.MenuItem(title, runnable));
    }

}
