package io.github.lucariatias.harmonicmoon.character;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.job.Job;
import io.github.lucariatias.harmonicmoon.sprite.SpriteSheet;

public class Syalae extends Character {

    public Syalae(HarmonicMoon harmonicMoon) {
        super(harmonicMoon, "Syalae", 24, Gender.AGENDER, Job.FIRE_MAGE, new SpriteSheet(harmonicMoon, "/characters/syalae.png", 32, 16), new SpriteSheet(harmonicMoon, "/characters/syalae-fight.png", 64, 64));
    }

}
