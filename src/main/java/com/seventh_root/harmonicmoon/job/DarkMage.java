package com.seventh_root.harmonicmoon.job;

import com.seventh_root.harmonicmoon.stat.Stat;

public class DarkMage extends Job {

    public DarkMage() {
        super("Dark Mage");
        setBaseHealth(50);
        setBaseMana(90);
        setBaseStat(Stat.PHYSICAL_ATTACK, 30);
        setBaseStat(Stat.PHYSICAL_DEFENCE, 80);
        setBaseStat(Stat.MAGIC_ATTACK, 80);
        setBaseStat(Stat.MAGIC_DEFENCE, 80);
        setBaseStat(Stat.AGILITY, 100);
        setBaseStat(Stat.ACCURACY, 50);
        setBaseStat(Stat.EVASION, 100);

        setHealthBonus(9950);
        setManaBonus(11510);
        setStatBonus(Stat.PHYSICAL_ATTACK, 9170);
        setStatBonus(Stat.PHYSICAL_DEFENCE, 11120);
        setStatBonus(Stat.MAGIC_ATTACK, 11120);
        setStatBonus(Stat.MAGIC_DEFENCE, 11120);
        setStatBonus(Stat.AGILITY, 11900);
        setStatBonus(Stat.ACCURACY, 9950);
        setStatBonus(Stat.EVASION, 11900);
    }
}
