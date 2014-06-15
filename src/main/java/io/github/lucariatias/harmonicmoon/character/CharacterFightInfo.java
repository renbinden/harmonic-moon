package io.github.lucariatias.harmonicmoon.character;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.fight.Combatant;
import io.github.lucariatias.harmonicmoon.skill.Skill;
import io.github.lucariatias.harmonicmoon.sprite.Sprite;
import io.github.lucariatias.harmonicmoon.sprite.SpriteSheet;

import java.awt.*;

import java.util.ArrayList;
import java.util.List;

public class CharacterFightInfo extends Combatant {

    private HarmonicMoon harmonicMoon;

    private Character character;
    private int health;

    private SpriteSheet spriteSheet;
    private Sprite sprite;
    private Sprite waitingSprite;
    private Sprite attackingSprite;
    private Sprite injuredSprite;

    public CharacterFightInfo(HarmonicMoon harmonicMoon, Character character, SpriteSheet spriteSheet) {
        this.harmonicMoon = harmonicMoon;
        this.character = character;
        this.spriteSheet = spriteSheet;
        this.waitingSprite = spriteSheet.getSprite(0, 0, 8);
        this.attackingSprite = spriteSheet.getSprite(0, 1, 8);
        this.injuredSprite = spriteSheet.getSprite(0, 2, 8);
        this.sprite = waitingSprite;
    }

    public String getName() {
        return character.getName();
    }

    public void setName(String name) {
        character.setName(name);
    }

    @Override
    public void onTick() {
        sprite.onTick();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.GREEN);
        graphics.fillRect(getLocation().getX(), getLocation().getY() - 16, health, 8);
        graphics.setColor(Color.RED);
        graphics.fillRect(getLocation().getX() + health, getLocation().getY() - 16, getMaxHealth() - health, 8);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(getLocation().getX(), getLocation().getY() - 16, getMaxHealth(), 8);
        graphics.drawImage(sprite.getImage(), getLocation().getX(), getLocation().getY(), null);
    }

    public Character getCharacter() {
        return character;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    public List<Skill> getSkills() {
        List<Skill> skills = new ArrayList<>();
        for (Skill skill : harmonicMoon.getSkillManager().getSkills()) {
            if (skill.canUse(this)) {
                skills.add(skill);
            }
        }
        return skills;
    }

    public void useSkill(Skill skill) {

    }

    @Override
    public int getMaxHealth() {
        return character.getMaxHealth();
    }

    public void attack(Combatant combatant) {
        if (harmonicMoon.getFightPanel().isActive()) {
            combatant.setHealth(combatant.getHealth() - 5);
        }
    }

    public void defend() {
    }
}
