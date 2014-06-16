package io.github.lucariatias.harmonicmoon.fight;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.character.FightCharacter;
import io.github.lucariatias.harmonicmoon.enemy.Enemy;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class FightPanel extends JPanel {

    private HarmonicMoon harmonicMoon;
    private BufferedImage background;
    private FightOptionBox optionBox;

    private Fight fight;

    private boolean active;
    private boolean prepared;

    public FightPanel(HarmonicMoon harmonicMoon) {
        this.harmonicMoon = harmonicMoon;
        //harmonicMoon.getEventManager().registerListener(new FightCharacterMoveListener(harmonicMoon));
        optionBox = new FightOptionBox(harmonicMoon, this);
    }

    public void prepareFight(Fight fight) {
        this.fight = fight;
        this.background = fight.getArea().getBackground();
        int x = 16;
        for (FightCharacter character : fight.getCharacterParty().getMembers()) {
            character.setLocation(new FightLocation(x, 224));
            x += 144;
        }
        x = 16;
        for (Enemy enemy : fight.getEnemyParty().getMembers()) {
            enemy.setLocation(new FightLocation(x, 96));
            x += 144;
        }
        prepared = true;
    }

    public void startFight() {
        if (prepared) {
            active = true;
            harmonicMoon.getMusicPlayer().loop("/music/battle_theme.ogg");
            repaint();
        }
    }

    public void endFight() {
        prepared = false;
        harmonicMoon.setPanel("world");
    }

    public Fight getFight() {
        return fight;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        if (prepared) {
            render(graphics);
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void onTick() {
        if (active) {
            fight.onTick();
            repaint();
        }
    }

    public void render(Graphics graphics) {
        graphics.drawImage(background, 0, 0, null);
        for (FightCharacter character : fight.getCharacterParty().getMembers()) {
            character.render(graphics);
        }
        for (Enemy enemy : fight.getEnemyParty().getMembers()) {
            enemy.render(graphics);
        }
        optionBox.render(graphics);
    }

}
