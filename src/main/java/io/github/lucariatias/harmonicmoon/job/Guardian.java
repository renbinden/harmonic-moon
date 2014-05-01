package io.github.lucariatias.harmonicmoon.job;

import static io.github.lucariatias.harmonicmoon.stat.Stat.*;
import static io.github.lucariatias.harmonicmoon.stat.Stat.EVASION;

public class Guardian extends Job {

    public Guardian() {
        super("Guardian");
        setBaseHealth(100);
        setBaseMana(50);
        setBaseStat(PHYSICAL_ATTACK, 80);
        setBaseStat(PHYSICAL_DEFENCE, 100);
        setBaseStat(MAGIC_ATTACK, 30);
        setBaseStat(MAGIC_DEFENCE, 90);
        setBaseStat(AGILITY, 50);
        setBaseStat(ACCURACY, 80);
        setBaseStat(EVASION, 80);

        setHealthBonus(11900);
        setManaBonus(9950);
        setStatBonus(PHYSICAL_ATTACK, 11120);
        setStatBonus(PHYSICAL_DEFENCE, 11900);
        setStatBonus(MAGIC_ATTACK, 9170);
        setStatBonus(MAGIC_DEFENCE, 11510);
        setStatBonus(AGILITY, 9950);
        setStatBonus(ACCURACY, 11120);
        setStatBonus(EVASION, 11120);
    }

}
