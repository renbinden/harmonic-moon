package io.github.lucariatias.harmonicmoon.job;

import static io.github.lucariatias.harmonicmoon.stat.Stat.*;

public class DarkMage extends Job {

    public DarkMage() {
        super("Dark Mage");
        setBaseHealth(50);
        setBaseMana(90);
        setBaseStat(PHYSICAL_ATTACK, 30);
        setBaseStat(PHYSICAL_DEFENCE, 80);
        setBaseStat(MAGIC_ATTACK, 80);
        setBaseStat(MAGIC_DEFENCE, 80);
        setBaseStat(AGILITY, 100);
        setBaseStat(ACCURACY, 50);
        setBaseStat(EVASION, 100);

        setHealthBonus(9950);
        setManaBonus(11510);
        setStatBonus(PHYSICAL_ATTACK, 9170);
        setStatBonus(PHYSICAL_DEFENCE, 11120);
        setStatBonus(MAGIC_ATTACK, 11120);
        setStatBonus(MAGIC_DEFENCE, 11120);
        setStatBonus(AGILITY, 11900);
        setStatBonus(ACCURACY, 9950);
        setStatBonus(EVASION, 11900);
    }
}
