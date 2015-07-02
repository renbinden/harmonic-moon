package com.seventh_root.harmonicmoon.job;

import static com.seventh_root.harmonicmoon.stat.Stat.*;

public class Druid extends Job {

    public Druid() {
        super("Druid");
        setBaseHealth(100);
        setBaseMana(90);
        setBaseStat(PHYSICAL_ATTACK, 30);
        setBaseStat(PHYSICAL_DEFENCE, 80);
        setBaseStat(MAGIC_ATTACK, 50);
        setBaseStat(MAGIC_DEFENCE, 100);
        setBaseStat(AGILITY, 50);
        setBaseStat(ACCURACY, 80);
        setBaseStat(EVASION, 80);

        setHealthBonus(11900);
        setManaBonus(11510);
        setStatBonus(PHYSICAL_ATTACK, 9170);
        setStatBonus(PHYSICAL_DEFENCE, 11120);
        setStatBonus(MAGIC_ATTACK, 9950);
        setStatBonus(MAGIC_DEFENCE, 11900);
        setStatBonus(AGILITY, 9950);
        setStatBonus(ACCURACY, 11120);
        setStatBonus(EVASION, 11120);
    }

}
