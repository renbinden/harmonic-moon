package io.github.lucariatias.harmonicmoon.character;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.job.Job;
import io.github.lucariatias.harmonicmoon.sprite.SpriteSheet;

public class Lonyre extends Character {

    public Lonyre(HarmonicMoon harmonicMoon) {
        super(harmonicMoon, "Lonyre", 17, Gender.FEMALE, Job.WATER_MAGE, new SpriteSheet(harmonicMoon, "/characters/lonyre.png", 32, 16), new SpriteSheet(harmonicMoon, "/characters/lonyre-fight.png", 64, 64));
    }

}
