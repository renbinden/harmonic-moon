package com.seventh_root.harmonicmoon.enemy;

import com.seventh_root.harmonicmoon.fight.TurnActionProcess;
import com.seventh_root.harmonicmoon.HarmonicMoon;
import com.seventh_root.harmonicmoon.character.Character;
import com.seventh_root.harmonicmoon.fight.Combatant;
import com.seventh_root.harmonicmoon.fight.Fight;
import com.seventh_root.harmonicmoon.fight.TurnAction;
import com.seventh_root.harmonicmoon.sprite.Sprite;
import com.seventh_root.harmonicmoon.sprite.SpriteSheet;

import java.awt.*;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class Enemy extends Combatant {

    private HarmonicMoon harmonicMoon;

    private String name;

    private SpriteSheet spriteSheet;
    private Sprite sprite;
    private Sprite waitingSprite;
    private Sprite attackingSprite;
    private Sprite damagedSprite;
    private Sprite injuredSprite;

    private int health;
    private int mana;

    public Enemy(HarmonicMoon harmonicMoon, String name, Sprite waitingSprite, Sprite attackingSprite, Sprite damagedSprite, Sprite injuredSprite) {
        this.harmonicMoon = harmonicMoon;
        this.name = name;
        this.waitingSprite = waitingSprite;
        this.attackingSprite = attackingSprite;
        this.damagedSprite = damagedSprite;
        this.injuredSprite = injuredSprite;
        this.sprite = waitingSprite;
        setHealth(getMaxHealth());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.GREEN);
        graphics.fillRect(getLocation().getX(), getLocation().getY() - 16, (int) Math.round(64D * ((double) getHealth() / (double) getMaxHealth())), 8);
        graphics.setColor(Color.RED);
        graphics.fillRect(getLocation().getX() + (int) Math.round(64 * ((double) getHealth() / (double) getMaxHealth())), getLocation().getY() - 16, (int) Math.round(64D * (((double) getMaxHealth() - (double) getHealth()) / (double) getMaxHealth())), 8);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(getLocation().getX(), getLocation().getY() - 16, 64, 8);
        graphics.drawImage(sprite.getImage(), getLocation().getX(), getLocation().getY(), null);
    }

    @Override
    public void onTick() {
        sprite.onTick();
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = Math.min(Math.max(health, 0), getMaxHealth());
    }

    @Override
    public int getMana() {
        return mana;
    }

    @Override
    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public void setSprite(Sprite sprite) {
        this.sprite.reset();
        this.sprite = sprite;
    }

    @Override
    public Sprite getWaitingSprite() {
        return waitingSprite;
    }

    @Override
    public Sprite getAttackingSprite() {
        return attackingSprite;
    }

    @Override
    public Sprite getDamagedSprite() {
        return damagedSprite;
    }

    @Override
    public Sprite getInjuredSprite() {
        return injuredSprite;
    }

    public abstract TurnAction chooseTurnAction(Fight fight);

    @Override
    public TurnAction attack(final Combatant combatant) {
        Queue<TurnActionProcess> turnActionProcesses = new LinkedBlockingQueue<>();
        turnActionProcesses.add(new TurnActionProcess() {
            private boolean finished;

            @Override
            public void onStart() {
                setSprite(getAttackingSprite());
            }

            @Override
            public void onTick() {
                if (getSprite().isFinished()) {
                    setSprite(getWaitingSprite());
                    finished = true;
                }
            }

            @Override
            public boolean isFinished() {
                return finished;
            }
        });
        turnActionProcesses.add(new TurnActionProcess() {
            private boolean finished;

            @Override
            public void onStart() {
                combatant.setSprite(combatant.getDamagedSprite());
            }

            @Override
            public void onTick() {
                if (combatant.getSprite().isFinished()) {
                    combatant.setSprite(combatant.getWaitingSprite());
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
                combatant.setHealth(combatant.getHealth() - 5);
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
                if (combatant.getHealth() <= 0) {
                    combatant.setSprite(combatant.getInjuredSprite());
                } else {
                    finished = true;
                }
            }

            @Override
            public void onTick() {
                if (combatant.getSprite() == combatant.getInjuredSprite() && combatant.getSprite().isFinished()) {
                    if (combatant instanceof Enemy) {
                        harmonicMoon.getFightPanel().getFight().getEnemyParty().removeMember(combatant);
                    } else if (combatant instanceof Character.Fight) {
                        harmonicMoon.getFightPanel().getFight().getCharacterParty().removeMember(combatant);
                    }
                    finished = true;
                }
            }

            @Override
            public boolean isFinished() {
                return finished;
            }
        });
        return new TurnAction(this, turnActionProcesses);
    }

    @Override
    public TurnAction defend() {
        return new TurnAction(this, new LinkedBlockingQueue<TurnActionProcess>());
    }

}
