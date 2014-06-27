package io.github.lucariatias.harmonicmoon.character;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.job.Job;
import io.github.lucariatias.harmonicmoon.sprite.SpriteSheet;

public class Idain extends Character {

    public Idain(HarmonicMoon harmonicMoon) {
        super(harmonicMoon, "Idain", 48, Gender.MALE, Job.BARBARIAN, new SpriteSheet(harmonicMoon, "/characters/idain.png", 32, 16), new SpriteSheet(harmonicMoon, "/characters/idain-fight.png", 64, 64));
    }

}
