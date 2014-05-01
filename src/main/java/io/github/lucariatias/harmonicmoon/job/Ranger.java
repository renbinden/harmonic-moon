package io.github.lucariatias.harmonicmoon.job;

import static io.github.lucariatias.harmonicmoon.stat.Stat.*;
import static io.github.lucariatias.harmonicmoon.stat.Stat.EVASION;

public class Ranger extends Job {

    public Ranger() {
        super("Ranger");
        setBaseHealth(80);
        setBaseMana(80);
        setBaseStat(PHYSICAL_ATTACK, 90);
        setBaseStat(PHYSICAL_DEFENCE, 50);
        setBaseStat(MAGIC_ATTACK, 50);
        setBaseStat(MAGIC_DEFENCE, 30);
        setBaseStat(AGILITY, 100);
        setBaseStat(ACCURACY, 80);
        setBaseStat(EVASION, 100);

        setHealthBonus(11120);
        setManaBonus(11120);
        setStatBonus(PHYSICAL_ATTACK, 11510);
        setStatBonus(PHYSICAL_DEFENCE, 9950);
        setStatBonus(MAGIC_ATTACK, 9950);
        setStatBonus(MAGIC_DEFENCE, 9170);
        setStatBonus(AGILITY, 11900);
        setStatBonus(ACCURACY, 11120);
        setStatBonus(EVASION, 11900);
    }

}
