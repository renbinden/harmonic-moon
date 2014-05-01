package io.github.lucariatias.harmonicmoon.job;

import static io.github.lucariatias.harmonicmoon.stat.Stat.*;

public class WaterMage extends Job {

    public WaterMage() {
        super("Water Mage");
        setBaseHealth(80);
        setBaseMana(100);
        setBaseStat(PHYSICAL_ATTACK, 30);
        setBaseStat(PHYSICAL_DEFENCE, 80);
        setBaseStat(MAGIC_ATTACK, 80);
        setBaseStat(MAGIC_DEFENCE, 100);
        setBaseStat(AGILITY, 90);
        setBaseStat(ACCURACY, 50);
        setBaseStat(EVASION, 50);

        setHealthBonus(11120);
        setManaBonus(11900);
        setStatBonus(PHYSICAL_ATTACK, 9170);
        setStatBonus(PHYSICAL_DEFENCE, 11120);
        setStatBonus(MAGIC_ATTACK, 11120);
        setStatBonus(MAGIC_DEFENCE, 11900);
        setStatBonus(AGILITY, 11510);
        setStatBonus(ACCURACY, 9950);
        setStatBonus(EVASION, 9950);
    }

}
