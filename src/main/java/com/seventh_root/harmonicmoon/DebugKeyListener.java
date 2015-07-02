package com.seventh_root.harmonicmoon;

import com.seventh_root.harmonicmoon.enemy.Slime;
import com.seventh_root.harmonicmoon.fight.Fight;
import com.seventh_root.harmonicmoon.fight.FightArea;
import com.seventh_root.harmonicmoon.fight.party.EnemyFightParty;
import com.seventh_root.harmonicmoon.particle.ExplosionEffect;
import com.seventh_root.harmonicmoon.particle.SparksEffect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class DebugKeyListener extends KeyAdapter {

    private HarmonicMoon harmonicMoon;

    public DebugKeyListener(HarmonicMoon harmonicMoon) {
        this.harmonicMoon = harmonicMoon;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_SLASH) {
            harmonicMoon.setDebug(!harmonicMoon.isDebug());
        }
        if (harmonicMoon.isDebug()) {
            if (event.getKeyCode() == KeyEvent.VK_M) {
                BufferedImage yellow = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
                Graphics graphics = yellow.getGraphics();
                graphics.setColor(Color.YELLOW);
                graphics.fillRect(0, 0, 64, 64);
                graphics.dispose();
                harmonicMoon.getParticleManager().addEffect(new SparksEffect(harmonicMoon.getParticleManager(), (int) Math.round(MouseInfo.getPointerInfo().getLocation().getX() - harmonicMoon.getLocationOnScreen().getX()), (int) Math.round(MouseInfo.getPointerInfo().getLocation().getY() - harmonicMoon.getLocationOnScreen().getY()), new BufferedImage[] {yellow, yellow, yellow, yellow, yellow, yellow, yellow, yellow, yellow, yellow}));
            }
            if (event.getKeyCode() == KeyEvent.VK_N) {
                BufferedImage red = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
                Graphics redGraphics = red.getGraphics();
                redGraphics.setColor(Color.RED);
                redGraphics.fillOval(0, 0, 64, 64);
                redGraphics.dispose();
                BufferedImage[] reds = new BufferedImage[] {red, red, red, red, red, red, red, red, red, red};
                BufferedImage orange = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
                Graphics orangeGraphics = orange.getGraphics();
                orangeGraphics.setColor(Color.ORANGE);
                orangeGraphics.fillOval(0, 0, 64, 64);
                orangeGraphics.dispose();
                BufferedImage[] oranges = new BufferedImage[] {orange, orange, orange, orange, orange, orange, orange, orange, orange, orange};
                harmonicMoon.getParticleManager().addEffect(new ExplosionEffect(harmonicMoon.getParticleManager(), (int) Math.round(MouseInfo.getPointerInfo().getLocation().getX() - harmonicMoon.getLocationOnScreen().getX()), (int) Math.round(MouseInfo.getPointerInfo().getLocation().getY() - harmonicMoon.getLocationOnScreen().getY()), reds, oranges, 8));
            }
            if (event.getKeyCode() == KeyEvent.VK_C) {
                harmonicMoon.getWorldPanel().getPlayer().setCharacter(harmonicMoon.getCharacterManager().getCharacter(JOptionPane.showInputDialog("Character: ")));
            }
            if (event.getKeyCode() == KeyEvent.VK_B) {
                Fight fight = new Fight(harmonicMoon, FightArea.GRASS, harmonicMoon.getCharacterManager().getParty(harmonicMoon.getCharacterManager().getCharacter("lonyre")).asFightParty(), new EnemyFightParty(new Slime(harmonicMoon)));
                harmonicMoon.getFightPanel().prepareFight(fight);
                harmonicMoon.getFightPanel().startFight();
                harmonicMoon.setPanel("fight");
            }
            if (event.getKeyCode() == KeyEvent.VK_P) {
                harmonicMoon.getMessageBox().queueMessage("This is a test. This is a test. This is a test. This is a test.");
                harmonicMoon.getMessageBox().queueMessage("This is another test. This is another test.");
            }
            if (event.getKeyCode() == KeyEvent.VK_O) {
                harmonicMoon.getMessageBox().queueMessage("This is a question.", "Yes", "No");
            }
        }
    }

}