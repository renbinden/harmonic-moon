package io.github.lucariatias.harmonicmoon.character;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.job.Job;
import io.github.lucariatias.harmonicmoon.sprite.SpriteSheet;

public class Seuri extends Character {

    public Seuri(HarmonicMoon harmonicMoon) {
        super(harmonicMoon, "Seuri", 14, Gender.FEMALE, Job.ASSASSIN, new SpriteSheet(harmonicMoon, "/characters/seuri.png", 32, 16), new SpriteSheet(harmonicMoon, "/characters/seuri-fight.png", 64, 64));
    }

}
