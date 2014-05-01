package io.github.lucariatias.harmonicmoon.job;

import static io.github.lucariatias.harmonicmoon.stat.Stat.*;

public class FireMage extends Job {

    public FireMage() {
        super("Fire Mage");
        setBaseHealth(50);
        setBaseMana(100);
        setBaseStat(PHYSICAL_ATTACK, 30);
        setBaseStat(PHYSICAL_DEFENCE, 50);
        setBaseStat(MAGIC_ATTACK, 100);
        setBaseStat(MAGIC_DEFENCE, 90);
        setBaseStat(AGILITY, 80);
        setBaseStat(ACCURACY, 80);
        setBaseStat(EVASION, 80);

        setHealthBonus(9950);
        setManaBonus(11900);
        setStatBonus(PHYSICAL_ATTACK, 9170);
        setStatBonus(PHYSICAL_DEFENCE, 9950);
        setStatBonus(MAGIC_ATTACK, 11900);
        setStatBonus(MAGIC_DEFENCE, 11510);
        setStatBonus(AGILITY, 11120);
        setStatBonus(ACCURACY, 11120);
        setStatBonus(EVASION, 11120);
    }

}
