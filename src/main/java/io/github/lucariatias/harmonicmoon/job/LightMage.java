package io.github.lucariatias.harmonicmoon.job;

import static io.github.lucariatias.harmonicmoon.stat.Stat.*;
import static io.github.lucariatias.harmonicmoon.stat.Stat.ACCURACY;
import static io.github.lucariatias.harmonicmoon.stat.Stat.EVASION;

public class LightMage extends Job {

    public LightMage() {
        super("Light Mage");
        setBaseHealth(50);
        setBaseMana(90);
        setBaseStat(PHYSICAL_ATTACK, 30);
        setBaseStat(PHYSICAL_DEFENCE, 50);
        setBaseStat(MAGIC_ATTACK, 80);
        setBaseStat(MAGIC_DEFENCE, 80);
        setBaseStat(AGILITY, 100);
        setBaseStat(ACCURACY, 100);
        setBaseStat(EVASION, 80);

        setHealthBonus(9950);
        setManaBonus(11510);
        setStatBonus(PHYSICAL_ATTACK, 9170);
        setStatBonus(PHYSICAL_DEFENCE, 9950);
        setStatBonus(MAGIC_ATTACK, 11120);
        setStatBonus(MAGIC_DEFENCE, 11120);
        setStatBonus(AGILITY, 11900);
        setStatBonus(ACCURACY, 11900);
        setStatBonus(EVASION, 11120);
    }
}
