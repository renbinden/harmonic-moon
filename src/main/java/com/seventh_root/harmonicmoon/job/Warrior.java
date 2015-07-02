package com.seventh_root.harmonicmoon.job;

import static com.seventh_root.harmonicmoon.stat.Stat.*;

public class Warrior extends Job {

    public Warrior() {
        super("Warrior");
        setBaseHealth(100);
        setBaseMana(50);
        setBaseStat(PHYSICAL_ATTACK, 100);
        setBaseStat(PHYSICAL_DEFENCE, 80);
        setBaseStat(MAGIC_ATTACK, 30);
        setBaseStat(MAGIC_DEFENCE, 80);
        setBaseStat(AGILITY, 90);
        setBaseStat(ACCURACY, 80);
        setBaseStat(EVASION, 50);

        setHealthBonus(11900);
        setManaBonus(9950);
        setStatBonus(PHYSICAL_ATTACK, 11900);
        setStatBonus(PHYSICAL_DEFENCE, 11120);
        setStatBonus(MAGIC_ATTACK, 9170);
        setStatBonus(MAGIC_DEFENCE, 11120);
        setStatBonus(AGILITY, 11510);
        setStatBonus(ACCURACY, 11120);
        setStatBonus(EVASION, 9950);
    }

}
