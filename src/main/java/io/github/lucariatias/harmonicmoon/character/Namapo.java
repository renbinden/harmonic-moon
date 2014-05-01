package io.github.lucariatias.harmonicmoon.character;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.job.Job;
import io.github.lucariatias.harmonicmoon.sprite.SpriteSheet;

public class Namapo extends Character {

    public Namapo(HarmonicMoon harmonicMoon) {
        super(harmonicMoon, "Namapo", 14, Gender.MALE, Job.DRUID, new SpriteSheet("/characters/namapo.png", 32, 16), new SpriteSheet("/characters/namapo-fight.png", 64, 64));
    }

}
