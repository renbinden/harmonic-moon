package com.seventh_root.harmonicmoon.job;

import com.seventh_root.harmonicmoon.stat.Stat;

public class Assassin extends Job {

    public Assassin() {
        super("Assassin");
        setBaseHealth(50);
        setBaseMana(80);
        setBaseStat(Stat.PHYSICAL_ATTACK, 90);
        setBaseStat(Stat.PHYSICAL_DEFENCE, 50);
        setBaseStat(Stat.MAGIC_ATTACK, 80);
        setBaseStat(Stat.MAGIC_DEFENCE, 30);
        setBaseStat(Stat.AGILITY, 100);
        setBaseStat(Stat.ACCURACY, 80);
        setBaseStat(Stat.EVASION, 100);

        setHealthBonus(9950);
        setManaBonus(11120);
        setStatBonus(Stat.PHYSICAL_ATTACK, 11510);
        setStatBonus(Stat.PHYSICAL_DEFENCE, 9950);
        setStatBonus(Stat.MAGIC_ATTACK, 11120);
        setStatBonus(Stat.MAGIC_DEFENCE, 9170);
        setStatBonus(Stat.AGILITY, 11900);
        setStatBonus(Stat.ACCURACY, 11120);
        setStatBonus(Stat.EVASION, 11900);
    }

}
