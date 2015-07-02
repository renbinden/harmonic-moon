package com.seventh_root.harmonicmoon.skill;

import com.seventh_root.harmonicmoon.enemy.Enemy;
import com.seventh_root.harmonicmoon.fight.Fight;
import com.seventh_root.harmonicmoon.fight.TurnAction;
import com.seventh_root.harmonicmoon.fight.TurnActionProcess;
import com.seventh_root.harmonicmoon.HarmonicMoon;
import com.seventh_root.harmonicmoon.character.Character;
import com.seventh_root.harmonicmoon.fight.Combatant;
import com.seventh_root.harmonicmoon.inventory.item.weapon.Weapon;
import com.seventh_root.harmonicmoon.particle.SplashEffect;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

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
    public TurnAction doSkill(Fight fight, final Combatant attacking, final Combatant defending, Weapon weapon) {
        Queue<TurnActionProcess> turnActionProcesses = new LinkedBlockingQueue<>();
        turnActionProcesses.add(new TurnActionProcess() {
            private boolean finished;

            @Override
            public void onStart() {
                attacking.setSprite(attacking.getAttackingSprite());
            }

            @Override
            public void onTick() {
                if (attacking.getSprite().isFinished()) {
                    attacking.setSprite(attacking.getWaitingSprite());
                    finished = true;
                }
            }

            @Override
            public boolean isFinished() {
                return finished;
            }
        });
        turnActionProcesses.add(new TurnActionProcess() {
            @Override
            public void onStart() {
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
            }

            @Override
            public void onTick() {

            }

            @Override
            public boolean isFinished() {
                return harmonicMoon.getParticleManager().getCurrentParticles() <= 0;
            }
        });
        turnActionProcesses.add(new TurnActionProcess() {
            private boolean finished;

            @Override
            public void onStart() {
                defending.setSprite(defending.getDamagedSprite());
            }

            @Override
            public void onTick() {
                if (defending.getSprite().isFinished()) {
                    defending.setSprite(defending.getWaitingSprite());
                    finished = true;
                }
            }

            @Override
            public boolean isFinished() {
                return finished;
            }
        });
        turnActionProcesses.add(new TurnActionProcess() {
            @Override
            public void onStart() {
                defending.setHealth(defending.getHealth() - 10);
            }

            @Override
            public void onTick() {

            }

            @Override
            public boolean isFinished() {
                return true;
            }
        });
        turnActionProcesses.add(new TurnActionProcess() {
            private boolean finished;

            @Override
            public void onStart() {
                if (defending.getHealth() <= 0) {
                    defending.setSprite(defending.getInjuredSprite());
                } else {
                    finished = true;
                }
            }

            @Override
            public void onTick() {
                if (defending.getSprite() == defending.getInjuredSprite() && defending.getSprite().isFinished()) {
                    if (defending instanceof Enemy) {
                        harmonicMoon.getFightPanel().getFight().getEnemyParty().removeMember(defending);
                    } else if (defending instanceof Character.Fight) {
                        harmonicMoon.getFightPanel().getFight().getCharacterParty().removeMember(defending);
                    }
                    finished = true;
                }
            }

            @Override
            public boolean isFinished() {
                return finished;
            }
        });
        return new TurnAction(attacking, turnActionProcesses);
    }

    @Override
    public boolean canUse(Combatant combatant) {
        return true;
    }

}
