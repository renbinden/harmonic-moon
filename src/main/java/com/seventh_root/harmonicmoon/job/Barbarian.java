package com.seventh_root.harmonicmoon.job;

import com.seventh_root.harmonicmoon.stat.Stat;

public class Barbarian extends Job {

    public Barbarian() {
        super("Barbarian");
        setBaseHealth(80);
        setBaseMana(50);
        setBaseStat(Stat.PHYSICAL_ATTACK, 100);
        setBaseStat(Stat.PHYSICAL_DEFENCE, 50);
        setBaseStat(Stat.MAGIC_ATTACK, 30);
        setBaseStat(Stat.MAGIC_DEFENCE, 80);
        setBaseStat(Stat.AGILITY, 100);
        setBaseStat(Stat.ACCURACY, 80);
        setBaseStat(Stat.EVASION, 90);

        setHealthBonus(11120);
        setManaBonus(9950);
        setStatBonus(Stat.PHYSICAL_ATTACK, 11900);
        setStatBonus(Stat.PHYSICAL_DEFENCE, 9950);
        setStatBonus(Stat.MAGIC_ATTACK, 9170);
        setStatBonus(Stat.MAGIC_DEFENCE, 11120);
        setStatBonus(Stat.AGILITY, 11900);
        setStatBonus(Stat.ACCURACY, 11120);
        setStatBonus(Stat.EVASION, 11510);
    }

}
