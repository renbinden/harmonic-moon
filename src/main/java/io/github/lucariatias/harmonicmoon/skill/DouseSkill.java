package io.github.lucariatias.harmonicmoon.skill;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.fight.Combatant;
import io.github.lucariatias.harmonicmoon.fight.Fight;
import io.github.lucariatias.harmonicmoon.inventory.item.weapon.Weapon;
import io.github.lucariatias.harmonicmoon.particle.SplashEffect;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DouseSkill extends SkillBase {

    private HarmonicMoon harmonicMoon;

    public DouseSkill(HarmonicMoon harmonicMoon) {
        this.harmonicMoon = harmonicMoon;
    }

    @Override
    public String getName() {
        return "Douse";
    }

    @Override
    public boolean doSkill(Fight fight, Combatant attacking, Combatant defending, Weapon weapon) {
        BufferedImage blue = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
        Graphics blueGraphics = blue.createGraphics();
        blueGraphics.setColor(Color.BLUE);
        blueGraphics.fillOval(0, 0, 64, 64);
        blueGraphics.dispose();
        BufferedImage[] blues = new BufferedImage[] {blue};
        BufferedImage cyan = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
        Graphics cyanGraphics = cyan.createGraphics();
        cyanGraphics.setColor(Color.CYAN);
        cyanGraphics.fillOval(0, 0, 64, 64);
        cyanGraphics.dispose();
        BufferedImage[] cyans = new BufferedImage[] {cyan};
        harmonicMoon.getParticleManager().addEffect(new SplashEffect(harmonicMoon.getParticleManager(), defending.getLocation().getX() + 32, defending.getLocation().getY() + 32, Color.BLUE, blues, cyans));
        attacking.playSpriteOnce(attacking.getAttackingSprite());
        defending.setHealth(defending.getHealth() - 10);
        return true;
    }

    @Override
    public boolean canUse(Combatant combatant) {
        return true;
    }

}
