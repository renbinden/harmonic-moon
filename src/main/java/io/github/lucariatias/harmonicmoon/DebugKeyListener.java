package io.github.lucariatias.harmonicmoon;

import io.github.lucariatias.harmonicmoon.fight.Fight;
import io.github.lucariatias.harmonicmoon.fight.FightArea;
import io.github.lucariatias.harmonicmoon.particle.ExplosionEffect;
import io.github.lucariatias.harmonicmoon.particle.SparksEffect;
import io.github.lucariatias.harmonicmoon.party.CharacterParty;
import io.github.lucariatias.harmonicmoon.party.EnemyParty;

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
                harmonicMoon.getParticleManager().addEffect(new SparksEffect(harmonicMoon.getParticleManager(), (int) MouseInfo.getPointerInfo().getLocation().getX(), (int) MouseInfo.getPointerInfo().getLocation().getY(), new BufferedImage[] {yellow, yellow, yellow, yellow, yellow, yellow, yellow, yellow, yellow, yellow}));
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
                harmonicMoon.getParticleManager().addEffect(new ExplosionEffect(harmonicMoon.getParticleManager(), (int) MouseInfo.getPointerInfo().getLocation().getX(), (int) MouseInfo.getPointerInfo().getLocation().getY(), reds, oranges, 8));
            }
            if (event.getKeyCode() == KeyEvent.VK_C) {
                harmonicMoon.getWorldPanel().getPlayer().setCharacter(harmonicMoon.getCharacterManager().getCharacter(JOptionPane.showInputDialog("Character: ")));
            }
            if (event.getKeyCode() == KeyEvent.VK_B) {
                Fight fight = new Fight(FightArea.GRASS, new CharacterParty(harmonicMoon.getCharacterManager().getCharacter("lonyre").getFightInfo()), new EnemyParty());
                harmonicMoon.getFightPanel().prepareFight(fight);
                harmonicMoon.getFightPanel().startFight();
                harmonicMoon.setPanel("fight");
            }
            if (event.getKeyCode() == KeyEvent.VK_P) {
                harmonicMoon.getMessageBox().queueMessage("Test");
            }
        }
    }

}
