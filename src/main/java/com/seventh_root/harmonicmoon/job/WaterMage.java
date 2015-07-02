package com.seventh_root.harmonicmoon.job;

import com.seventh_root.harmonicmoon.stat.Stat;

public class WaterMage extends Job {

    public WaterMage() {
        super("Water Mage");
        setBaseHealth(80);
        setBaseMana(100);
        setBaseStat(Stat.PHYSICAL_ATTACK, 30);
        setBaseStat(Stat.PHYSICAL_DEFENCE, 80);
        setBaseStat(Stat.MAGIC_ATTACK, 80);
        setBaseStat(Stat.MAGIC_DEFENCE, 100);
        setBaseStat(Stat.AGILITY, 90);
        setBaseStat(Stat.ACCURACY, 50);
        setBaseStat(Stat.EVASION, 50);

        setHealthBonus(11120);
        setManaBonus(11900);
        setStatBonus(Stat.PHYSICAL_ATTACK, 9170);
        setStatBonus(Stat.PHYSICAL_DEFENCE, 11120);
        setStatBonus(Stat.MAGIC_ATTACK, 11120);
        setStatBonus(Stat.MAGIC_DEFENCE, 11900);
        setStatBonus(Stat.AGILITY, 11510);
        setStatBonus(Stat.ACCURACY, 9950);
        setStatBonus(Stat.EVASION, 9950);
    }

}
