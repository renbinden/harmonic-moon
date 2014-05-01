package io.github.lucariatias.harmonicmoon.character;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.job.Job;
import io.github.lucariatias.harmonicmoon.sprite.SpriteSheet;

public class Tesowa extends Character {

    public Tesowa(HarmonicMoon harmonicMoon) {
        super(harmonicMoon, "Tesowa", 21, Gender.FEMALE, Job.SPEARMAN, new SpriteSheet("/characters/tesowa.png", 32, 16), new SpriteSheet("/characters/tesowa-fight.png", 64, 64));
    }

}
