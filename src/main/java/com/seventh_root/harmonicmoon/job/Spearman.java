package com.seventh_root.harmonicmoon.job;

import com.seventh_root.harmonicmoon.stat.Stat;

public class Spearman extends Job {

    public Spearman() {
        super("Spearman");
        setBaseHealth(80);
        setBaseMana(50);
        setBaseStat(Stat.PHYSICAL_ATTACK, 80);
        setBaseStat(Stat.PHYSICAL_DEFENCE, 50);
        setBaseStat(Stat.MAGIC_ATTACK, 80);
        setBaseStat(Stat.MAGIC_DEFENCE, 30);
        setBaseStat(Stat.AGILITY, 100);
        setBaseStat(Stat.ACCURACY, 100);
        setBaseStat(Stat.EVASION, 90);

        setHealthBonus(11120);
        setManaBonus(9950);
        setStatBonus(Stat.PHYSICAL_ATTACK, 11120);
        setStatBonus(Stat.PHYSICAL_DEFENCE, 9950);
        setStatBonus(Stat.MAGIC_ATTACK, 11120);
        setStatBonus(Stat.MAGIC_DEFENCE, 9170);
        setStatBonus(Stat.AGILITY, 11900);
        setStatBonus(Stat.ACCURACY, 11900);
        setStatBonus(Stat.EVASION, 11510);
    }

}
