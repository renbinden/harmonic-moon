package com.seventh_root.harmonicmoon.job;

import com.seventh_root.harmonicmoon.stat.Stat;

import java.util.EnumMap;
import java.util.Map;

public abstract class Job {

    public static final Job ASSASSIN = new Assassin();
    public static final Job BARBARIAN = new Barbarian();
    public static final Job DARK_MAGE = new DarkMage();
    public static final Job DRUID = new Druid();
    public static final Job FIRE_MAGE = new FireMage();
    public static final Job GUARDIAN = new Guardian();
    public static final Job LIGHT_MAGE = new LightMage();
    public static final Job MARTIAL_ARTIST = new MartialArtist();
    public static final Job RANGER = new Ranger();
    public static final Job SPEARMAN = new Spearman();
    public static final Job WARRIOR = new Warrior();
    public static final Job WATER_MAGE = new WaterMage();

    private String name;
    private int maxLevel;
    private int baseHealth;
    private int baseMana;
    private Map<Stat, Integer> baseStats;
    private int healthBonus;
    private int manaBonus;
    private Map<Stat, Integer> statBonuses;

    public Job() {
        this("");
    }

    public Job(String name) {
        this(name, 100);
    }

    public Job(String name, int maxLevel) {
        this.name = name;
        this.maxLevel = maxLevel;
        this.baseStats = new EnumMap<>(Stat.class);
        this.statBonuses = new EnumMap<>(Stat.class);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public void setBaseHealth(int baseHealth) {
        this.baseHealth = baseHealth;
    }

    public int getBaseMana() {
        return baseMana;
    }

    public void setBaseMana(int baseMana) {
        this.baseMana = baseMana;
    }

    public int getBaseStat(Stat stat) {
        return baseStats.get(stat);
    }

    public void setBaseStat(Stat stat, int value) {
        baseStats.put(stat, value);
    }

    public int getHealthBonus() {
        return healthBonus;
    }

    public void setHealthBonus(int healthBonus) {
        this.healthBonus = healthBonus;
    }

    public int getManaBonus() {
        return manaBonus;
    }

    public void setManaBonus(int manaBonus) {
        this.manaBonus = manaBonus;
    }

    public int getStatBonus(Stat stat) {
        return statBonuses.get(stat);
    }

    public void setStatBonus(Stat stat, int value) {
        statBonuses.put(stat, value);
    }

    public int getHealthAtLevel(int level) {
        return (int) Math.round((double) getBaseHealth() + ((double) getHealthBonus() / (double) getMaxLevel()));
    }

    public int getManaAtLevel(int level) {
        return (int) Math.round((double) getBaseMana() + ((double) getManaBonus() / (double) getMaxLevel()));
    }

    public int getStatValueAtLevel(Stat stat, int level) {
        return (int) Math.round((double) getBaseStat(stat) + ((double) getStatBonus(stat) / (double) getMaxLevel()));
    }

}
