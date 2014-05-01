package io.github.lucariatias.harmonicmoon.character;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.job.Job;
import io.github.lucariatias.harmonicmoon.sprite.SpriteSheet;

public class Seuri extends Character {

    public Seuri(HarmonicMoon harmonicMoon) {
        super(harmonicMoon, "Seuri", 14, Gender.FEMALE, Job.ASSASSIN, new SpriteSheet("/characters/seuri.png", 32, 16), new SpriteSheet("/characters/seuri-fight.png", 64, 64));
    }

}
