package io.github.lucariatias.harmonicmoon.character;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.enemy.Enemy;
import io.github.lucariatias.harmonicmoon.event.character.CharacterMoveEvent;
import io.github.lucariatias.harmonicmoon.event.collision.CollisionEvent;
import io.github.lucariatias.harmonicmoon.event.sprite.SpriteAnimationCompleteEvent;
import io.github.lucariatias.harmonicmoon.event.sprite.SpriteAnimationCompleteListener;
import io.github.lucariatias.harmonicmoon.fight.Combatant;
import io.github.lucariatias.harmonicmoon.fight.TurnAction;
import io.github.lucariatias.harmonicmoon.fight.TurnActionProcess;
import io.github.lucariatias.harmonicmoon.inventory.CharacterInventory;
import io.github.lucariatias.harmonicmoon.inventory.item.weapon.Weapon;
import io.github.lucariatias.harmonicmoon.job.Job;
import io.github.lucariatias.harmonicmoon.skill.Skill;
import io.github.lucariatias.harmonicmoon.sprite.Sprite;
import io.github.lucariatias.harmonicmoon.sprite.SpriteSheet;
import io.github.lucariatias.harmonicmoon.stat.Stat;
import io.github.lucariatias.harmonicmoon.world.Direction;
import io.github.lucariatias.harmonicmoon.world.MovementState;
import io.github.lucariatias.harmonicmoon.world.WorldLocation;
import io.github.lucariatias.harmonicmoon.world.WorldObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class Character {

    private HarmonicMoon harmonicMoon;

    // Personal stuff
    private String name;
    private int age;
    private Gender gender;
    private Personality personality;

    // Job stuff
    private Job job;
    private Map<Job, Integer> experience;
    private int health;
    private int mana;

    // Inventory
    private CharacterInventory inventory;

    private World worldInstance;
    private Character.Fight fightInstance;

    private SpriteSheet worldSpriteSheet;
    private SpriteSheet fightSpriteSheet;

    public class Fight extends Combatant {

        private HarmonicMoon harmonicMoon;

        private Character character;

        private SpriteSheet spriteSheet;
        private Sprite sprite;
        private Sprite waitingSprite;
        private Sprite attackingSprite;
        private Sprite damagedSprite;
        private Sprite injuredSprite;
        private boolean spriteTemporary;

        public Fight(HarmonicMoon harmonicMoon, Character character, final SpriteSheet spriteSheet) {
            this.harmonicMoon = harmonicMoon;
            this.character = character;
            this.spriteSheet = spriteSheet;
            this.waitingSprite = spriteSheet.getSprite(0, 0, 8);
            this.attackingSprite = spriteSheet.getSprite(0, 1, 8, 5);
            this.damagedSprite = spriteSheet.getSprite(0, 0, 8, 5);
            this.injuredSprite = spriteSheet.getSprite(0, 2, 8, 5);
            this.sprite = waitingSprite;
            setHealth(getMaxHealth());
            harmonicMoon.getEventManager().registerListener(new SpriteAnimationCompleteListener() {
                @Override
                public void onSpriteAnimationComplete(SpriteAnimationCompleteEvent event) {
                    HarmonicMoon harmonicMoon = Fight.this.harmonicMoon;
                    if (spriteTemporary && event.getSprite() == getSprite()) {
                        if (getSprite() == getAttackingSprite()) {
                            setSprite(getWaitingSprite());
                            spriteTemporary = false;
                        } else if (getSprite() == getInjuredSprite()) {
                            harmonicMoon.getFightPanel().getFight().getCharacterParty().removeMember(Fight.this);
                        }
                    }
                }
            });
        }

        public String getName() {
            return getCharacter().getName();
        }

        public void setName(String name) {
            getCharacter().setName(name);
        }

        @Override
        public void onTick() {
            getSprite().onTick();
        }

        @Override
        public void render(Graphics graphics) {
            graphics.setColor(Color.GREEN);
            graphics.fillRect(getLocation().getX(), getLocation().getY() - 16, (int) Math.round(64D * ((double) getHealth() / (double) getMaxHealth())), 8);
            graphics.setColor(Color.RED);
            graphics.fillRect(getLocation().getX() + (int) Math.round(64 * ((double) getHealth() / (double) getMaxHealth())), getLocation().getY() - 16, (int) Math.round(64D * (((double) getMaxHealth() - (double) getHealth()) / (double) getMaxHealth())), 8);
            graphics.setColor(Color.BLACK);
            graphics.drawRect(getLocation().getX(), getLocation().getY() - 16, 64, 8);
            graphics.drawImage(getSprite().getImage(), getLocation().getX(), getLocation().getY(), null);
        }

        public Character getCharacter() {
            return character;
        }

        @Override
        public int getHealth() {
            return getCharacter().getHealth();
        }

        @Override
        public void setHealth(int health) {
            getCharacter().setHealth(health);
        }

        @Override
        public int getMaxHealth() {
            return getCharacter().getMaxHealth();
        }

        public java.util.List<Skill> getSkills() {
            java.util.List<Skill> skills = new ArrayList<>();
            for (Skill skill : harmonicMoon.getSkillManager().getSkills()) {
                if (skill.canUse(this)) {
                    skills.add(skill);
                }
            }
            return skills;
        }

        public TurnAction useSkill(io.github.lucariatias.harmonicmoon.fight.Fight fight, Skill skill, Combatant target, Weapon weapon) {
            return skill.doSkill(fight, this, target, weapon);
        }

        @Override
        public int getMana() {
            return getCharacter().getMana();
        }

        @Override
        public void setMana(int mana) {
            getCharacter().setMana(mana);
        }

        @Override
        public int getMaxMana() {
            return getCharacter().getMaxMana();
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
        public Sprite getAttackingSprite() {
            return attackingSprite;
        }

        @Override
        public Sprite getWaitingSprite() {
            return waitingSprite;
        }

        @Override
        public Sprite getDamagedSprite() {
            return damagedSprite;
        }

        @Override
        public Sprite getInjuredSprite() {
            return injuredSprite;
        }

        @Override
        public int getStatValue(Stat stat) {
            return character.getStatValue(stat);
        }

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

    public class World extends WorldObject {

        private HarmonicMoon harmonicMoon;

        private Character character;

        private int interactDelay;

        // Graphics
        private SpriteSheet spriteSheet;
        private Sprite sprite;
        private Map<Direction, Sprite> sprites = new EnumMap<>(Direction.class);

        private MovementState movementState;
        private Direction direction = Direction.DOWN;

        public World(HarmonicMoon harmonicMoon, Character character, SpriteSheet spriteSheet) {
            this.harmonicMoon = harmonicMoon;
            this.character = character;
            this.movementState = MovementState.WAITING;
            this.spriteSheet = spriteSheet;
            sprites.put(Direction.DOWN, spriteSheet.getSprite(0, 0, 4));
            sprites.put(Direction.RIGHT, spriteSheet.getSprite(4, 0, 4));
            sprites.put(Direction.LEFT, spriteSheet.getSprite(8, 0, 4));
            sprites.put(Direction.UP, spriteSheet.getSprite(12, 0, 4));
            this.sprite = sprites.get(Direction.DOWN);
            setSolid(true);
        }

        public Character getCharacter() {
            return character;
        }

        public BufferedImage getImage() {
            return sprite.getImage();
        }

        public void move(Direction direction) {
            if (harmonicMoon.getMessageBox().isHidden()) {
                if (movementState == MovementState.WAITING) {
                    this.direction = direction;
                    WorldObject colliding = getCollision(direction);
                    if (colliding == null) {
                        CharacterMoveEvent event = new CharacterMoveEvent(character, getLocation(), getLocation().getRelative(direction, 16));
                        harmonicMoon.getEventManager().dispatchEvent(event);
                        if (!event.isCancelled()) {
                            switch (direction) {
                                case UP:
                                    movementState = MovementState.TRANSITIONING_UP;
                                    break;
                                case DOWN:
                                    movementState = MovementState.TRANSITIONING_DOWN;
                                    break;
                                case LEFT:
                                    movementState = MovementState.TRANSITIONING_LEFT;
                                    break;
                                case RIGHT:
                                    movementState = MovementState.TRANSITIONING_RIGHT;
                                    break;
                            }
                        }
                    } else {
                        CollisionEvent collisionEvent = new CollisionEvent(this, colliding);
                        harmonicMoon.getEventManager().dispatchEvent(collisionEvent);
                    }
                    sprite = sprites.get(direction);
                }
            }
        }

        @Override
        public void onTick() {
            interactDelay = interactDelay > 0 ? interactDelay - 1 : 0;
            switch (movementState) {
                case WAITING: break;
                case TRANSITIONING_UP: setLocation(getLocation().getRelative(Direction.UP, 2)); sprite.onTick(); break;
                case TRANSITIONING_DOWN: setLocation(getLocation().getRelative(Direction.DOWN, 2)); sprite.onTick(); break;
                case TRANSITIONING_LEFT: setLocation(getLocation().getRelative(Direction.LEFT, 2)); sprite.onTick(); break;
                case TRANSITIONING_RIGHT: setLocation(getLocation().getRelative(Direction.RIGHT, 2)); sprite.onTick(); break;
            }
            if (getLocation().getX() % 16 == 0 && getLocation().getY() % 16 == 0) movementState = MovementState.WAITING;
        }

        @Override
        public void render(Graphics graphics) {
            graphics.drawImage(getImage(), getLocation().getX(), getLocation().getY() - (getImage().getHeight() / 2), null);
            if (harmonicMoon.isDebug()) {
                Rectangle bounds = getBounds();
                graphics.setColor(Color.RED);
                graphics.drawRect((int) bounds.getX(), (int) bounds.getY(), (int) bounds.getWidth(), (int) bounds.getHeight());
            }
        }

        @Override
        public Rectangle getBoundsAtPosition(WorldLocation location, MovementState movementState) {
            switch (movementState) {
                case TRANSITIONING_UP:
                    return new Rectangle((location.getX() / 16) * 16, (location.getY() / 16) * 16, 16, 32);
                case TRANSITIONING_DOWN:
                    return new Rectangle((location.getX() / 16) * 16, (location.getY() / 16) * 16, 16, 32);
                case TRANSITIONING_LEFT:
                    return new Rectangle((location.getX() / 16) * 16, (location.getY() / 16) * 16, 32, 16);
                case TRANSITIONING_RIGHT:
                    return new Rectangle((location.getX() / 16) * 16, (location.getY() / 16) * 16, 32, 16);
                default:
                    return new Rectangle(location.getX(), location.getY(), 16, 16);
            }
        }

        public MovementState getMovementState() {
            return movementState;
        }

        public Direction getDirection() {
            return direction;
        }

        public void interact(Direction direction) {
            if (interactDelay == 0) {
                WorldObject object = getCollision(direction);
                if (object != null) {
                    interactDelay = 10;
                    object.interact();
                }
            }
        }

    }

    public Character(HarmonicMoon harmonicMoon, String name, int age, Gender gender, Personality personality, Job job, SpriteSheet worldSpriteSheet, SpriteSheet fightSpriteSheet) {
        this.harmonicMoon = harmonicMoon;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.personality = personality;
        this.job = job;
        this.experience = new HashMap<>();
        this.health = getMaxHealth();
        this.mana = getMaxMana();
        this.inventory = new CharacterInventory();
        this.worldSpriteSheet = worldSpriteSheet;
        this.fightSpriteSheet = fightSpriteSheet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Personality getPersonality() {
        return personality;
    }

    public void setPersonality(Personality personality) {
        this.personality = personality;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public int getExperience(Job job) {
        if (!experience.containsKey(job)) return 0;
        return experience.get(job);
    }

    public void setExperience(Job job, int experience) {
        this.experience.put(job, experience);
    }

    public int getLevel() {
        return getLevel(job);
    }

    public int getLevel(Job job) {
        int level = 1;
        while (getExperienceForLevel(level + 1) <= getExperience(job)) {
            level += 1;
        }
        return level;
    }

    public int getTotalExperienceForLevel(int level) {
        return level * (level - 1) * 500;
    }

    public int getExperienceForLevel(int level) {
        return (level - 1) * 1000;
    }

    public int getMaxHealth() {
        return job.getHealthAtLevel(getLevel());
    }

    public int getHealth() {
        return Math.max(0, Math.min(getMaxHealth(), health));
    }

    public void setHealth(int health) {
        this.health = Math.max(0, Math.min(getMaxHealth(), health));
    }

    public int getMaxMana() {
        return job.getManaAtLevel(getLevel());
    }

    public int getMana() {
        return Math.max(0, Math.min(getMaxMana(), mana));
    }

    public void setMana(int mana) {
        this.mana = Math.max(0, Math.min(getMaxMana(), mana));
    }

    public int getStatValue(Stat stat) {
        return job.getStatValueAtLevel(stat, getLevel());
    }

    public CharacterInventory getInventory() {
        return inventory;
    }

    public World world() {
        if (worldInstance == null) worldInstance = new World(harmonicMoon, this, worldSpriteSheet);
        return worldInstance;
    }

    public Fight fight() {
        if (fightInstance == null) fightInstance = new Fight(harmonicMoon, this, fightSpriteSheet);
        return fightInstance;
    }
}
