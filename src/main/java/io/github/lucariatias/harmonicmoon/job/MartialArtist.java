package io.github.lucariatias.harmonicmoon.job;

import static io.github.lucariatias.harmonicmoon.stat.Stat.*;
import static io.github.lucariatias.harmonicmoon.stat.Stat.EVASION;

public class MartialArtist extends Job {

    public MartialArtist() {
        super("Martial Artist");
        setBaseHealth(90);
        setBaseMana(30);
        setBaseStat(PHYSICAL_ATTACK, 80);
        setBaseStat(PHYSICAL_DEFENCE, 80);
        setBaseStat(MAGIC_ATTACK, 50);
        setBaseStat(MAGIC_DEFENCE, 50);
        setBaseStat(AGILITY, 100);
        setBaseStat(ACCURACY, 80);
        setBaseStat(EVASION, 100);

        setHealthBonus(11510);
        setManaBonus(9170);
        setStatBonus(PHYSICAL_ATTACK, 11120);
        setStatBonus(PHYSICAL_DEFENCE, 11120);
        setStatBonus(MAGIC_ATTACK, 9950);
        setStatBonus(MAGIC_DEFENCE, 9950);
        setStatBonus(AGILITY, 11900);
        setStatBonus(ACCURACY, 11120);
        setStatBonus(EVASION, 11900);
    }

}
