package com.seventh_root.harmonicmoon.job;

import com.seventh_root.harmonicmoon.stat.Stat;

public class MartialArtist extends Job {

    public MartialArtist() {
        super("Martial Artist");
        setBaseHealth(90);
        setBaseMana(30);
        setBaseStat(Stat.PHYSICAL_ATTACK, 80);
        setBaseStat(Stat.PHYSICAL_DEFENCE, 80);
        setBaseStat(Stat.MAGIC_ATTACK, 50);
        setBaseStat(Stat.MAGIC_DEFENCE, 50);
        setBaseStat(Stat.AGILITY, 100);
        setBaseStat(Stat.ACCURACY, 80);
        setBaseStat(Stat.EVASION, 100);

        setHealthBonus(11510);
        setManaBonus(9170);
        setStatBonus(Stat.PHYSICAL_ATTACK, 11120);
        setStatBonus(Stat.PHYSICAL_DEFENCE, 11120);
        setStatBonus(Stat.MAGIC_ATTACK, 9950);
        setStatBonus(Stat.MAGIC_DEFENCE, 9950);
        setStatBonus(Stat.AGILITY, 11900);
        setStatBonus(Stat.ACCURACY, 11120);
        setStatBonus(Stat.EVASION, 11900);
    }

}
