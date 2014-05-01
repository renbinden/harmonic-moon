package io.github.lucariatias.harmonicmoon.job;

import static io.github.lucariatias.harmonicmoon.stat.Stat.*;
import static io.github.lucariatias.harmonicmoon.stat.Stat.EVASION;

public class Spearman extends Job {

    public Spearman() {
        super("Spearman");
        setBaseHealth(80);
        setBaseMana(50);
        setBaseStat(PHYSICAL_ATTACK, 80);
        setBaseStat(PHYSICAL_DEFENCE, 50);
        setBaseStat(MAGIC_ATTACK, 80);
        setBaseStat(MAGIC_DEFENCE, 30);
        setBaseStat(AGILITY, 100);
        setBaseStat(ACCURACY, 100);
        setBaseStat(EVASION, 90);

        setHealthBonus(11120);
        setManaBonus(9950);
        setStatBonus(PHYSICAL_ATTACK, 11120);
        setStatBonus(PHYSICAL_DEFENCE, 9950);
        setStatBonus(MAGIC_ATTACK, 11120);
        setStatBonus(MAGIC_DEFENCE, 9170);
        setStatBonus(AGILITY, 11900);
        setStatBonus(ACCURACY, 11900);
        setStatBonus(EVASION, 11510);
    }

}
