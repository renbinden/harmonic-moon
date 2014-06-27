package io.github.lucariatias.harmonicmoon.character;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.job.Job;
import io.github.lucariatias.harmonicmoon.sprite.SpriteSheet;

public class Tivor extends Character {

    public Tivor(HarmonicMoon harmonicMoon) {
        super(harmonicMoon, "Tivor", 18, Gender.MALE, Job.RANGER, new SpriteSheet(harmonicMoon, "/characters/tivor.png", 32, 16), new SpriteSheet(harmonicMoon, "/characters/tivor-fight.png", 64, 64));
    }

}
