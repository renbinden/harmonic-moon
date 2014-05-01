package io.github.lucariatias.harmonicmoon.job;

import static io.github.lucariatias.harmonicmoon.stat.Stat.*;
import static io.github.lucariatias.harmonicmoon.stat.Stat.EVASION;

public class Barbarian extends Job {

    public Barbarian() {
        super("Barbarian");
        setBaseHealth(80);
        setBaseMana(50);
        setBaseStat(PHYSICAL_ATTACK, 100);
        setBaseStat(PHYSICAL_DEFENCE, 50);
        setBaseStat(MAGIC_ATTACK, 30);
        setBaseStat(MAGIC_DEFENCE, 80);
        setBaseStat(AGILITY, 100);
        setBaseStat(ACCURACY, 80);
        setBaseStat(EVASION, 90);

        setHealthBonus(11120);
        setManaBonus(9950);
        setStatBonus(PHYSICAL_ATTACK, 11900);
        setStatBonus(PHYSICAL_DEFENCE, 9950);
        setStatBonus(MAGIC_ATTACK, 9170);
        setStatBonus(MAGIC_DEFENCE, 11120);
        setStatBonus(AGILITY, 11900);
        setStatBonus(ACCURACY, 11120);
        setStatBonus(EVASION, 11510);
    }

}
