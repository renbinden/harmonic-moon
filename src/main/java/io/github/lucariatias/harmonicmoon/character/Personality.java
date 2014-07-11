package io.github.lucariatias.harmonicmoon.character;

import static java.lang.Math.min;
import static java.lang.Math.max;

public class Personality {

    // All personality traits are between 0 and 100

    private int openness; // Curious, creative, open to new ideas
    private int conscientiousness; // Organised, systematic, punctual
    private int extraversion; // Outgoing, talkative
    private int agreeableness; // Tolerant, trusting, sensitive
    private int neuroticism; // Anxious, irritable, tempremental, moody

    private int achievement; // Desire for personal success
    private int benevolence; // Desire to protect well-being of others close to the character
    private int conformity; // Self-disciplined, obedient
    private int hedonism; // Desire for pleasure
    private int power; // Desire for control over others
    private int security; // Valuing safety & stability
    private int selfDirection; // Desire to be free & independent
    private int stimulation; // Desire for excitement
    private int tradition; // Acceptance of social customs
    private int universalism; // Desire to protect well-being of all people

    public int getOpenness() {
        return openness;
    }

    public Personality setOpenness(int openness) {
        this.openness = openness;
        return this;
    }

    public int getConscientiousness() {
        return conscientiousness;
    }

    public Personality setConscientiousness(int conscientiousness) {
        this.conscientiousness = min(max(conscientiousness, 0), 100);
        return this;
    }

    public int getExtraversion() {
        return extraversion;
    }

    public Personality setExtraversion(int extraversion) {
        this.extraversion = min(max(extraversion, 0), 100);
        return this;
    }

    public int getAgreeableness() {
        return agreeableness;
    }

    public Personality setAgreeableness(int agreeableness) {
        this.agreeableness = min(max(agreeableness, 0), 100);
        return this;
    }

    public int getNeuroticism() {
        return neuroticism;
    }

    public Personality setNeuroticism(int neuroticism) {
        this.neuroticism = min(max(neuroticism, 0), 100);
        return this;
    }

    public int getAchievement() {
        return achievement;
    }

    public Personality setAchievement(int achievement) {
        this.achievement = min(max(achievement, 0), 100);
        return this;
    }

    public int getBenevolence() {
        return benevolence;
    }

    public Personality setBenevolence(int benevolence) {
        this.benevolence = min(max(benevolence, 0), 100);
        return this;
    }

    public int getConformity() {
        return conformity;
    }

    public Personality setConformity(int conformity) {
        this.conformity = min(max(conformity, 0), 100);
        return this;
    }

    public int getHedonism() {
        return hedonism;
    }

    public Personality setHedonism(int hedonism) {
        this.hedonism = min(max(hedonism, 0), 100);
        return this;
    }

    public int getPower() {
        return power;
    }

    public Personality setPower(int power) {
        this.power = min(max(power, 0), 100);
        return this;
    }

    public int getSecurity() {
        return security;
    }

    public Personality setSecurity(int security) {
        this.security = min(max(security, 0), 100);
        return this;
    }

    public int getSelfDirection() {
        return selfDirection;
    }

    public Personality setSelfDirection(int selfDirection) {
        this.selfDirection = min(max(selfDirection, 0), 100);
        return this;
    }

    public int getStimulation() {
        return stimulation;
    }

    public Personality setStimulation(int stimulation) {
        this.stimulation = min(max(stimulation, 0), 100);
        return this;
    }

    public int getTradition() {
        return tradition;
    }

    public Personality setTradition(int tradition) {
        this.tradition = min(max(tradition, 0), 100);
        return this;
    }

    public int getUniversalism() {
        return universalism;
    }

    public Personality setUniversalism(int universalism) {
        this.universalism = min(max(universalism, 0), 100);
        return this;
    }

}
