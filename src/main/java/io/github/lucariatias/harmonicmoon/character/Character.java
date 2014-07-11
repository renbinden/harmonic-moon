package io.github.lucariatias.harmonicmoon.character;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.job.Job;
import io.github.lucariatias.harmonicmoon.sprite.SpriteSheet;
import io.github.lucariatias.harmonicmoon.stat.Stat;

import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

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

    //Money stuff
    private Map<Currency, Integer> money;

    private WorldCharacter worldInfo;
    private FightCharacter fightInfo;

    private SpriteSheet spriteSheet;
    private SpriteSheet fightSpriteSheet;

    public Character(HarmonicMoon harmonicMoon, String name, int age, Gender gender, Personality personality, Job job, SpriteSheet spriteSheet, SpriteSheet fightSpriteSheet) {
        this.harmonicMoon = harmonicMoon;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.personality = personality;
        this.job = job;
        this.experience = new HashMap<>();
        this.health = getMaxHealth();
        this.mana = getMaxMana();
        this.money = new HashMap<>();
        this.spriteSheet = spriteSheet;
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

    public WorldCharacter world() {
        if (worldInfo == null) worldInfo = new WorldCharacter(harmonicMoon, this, spriteSheet);
        return worldInfo;
    }

    public FightCharacter fight() {
        if (fightInfo == null) fightInfo = new FightCharacter(harmonicMoon, this, fightSpriteSheet);
        return fightInfo;
    }
}
