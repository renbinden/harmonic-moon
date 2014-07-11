package io.github.lucariatias.harmonicmoon.character;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.job.Job;
import io.github.lucariatias.harmonicmoon.sprite.SpriteSheet;

public class Seuri extends Character {

    public Seuri(HarmonicMoon harmonicMoon) {
        super(
                harmonicMoon,
                "Seuri",
                14,
                Gender.FEMALE,
                new Personality()
                        .setOpenness(30)
                        .setConscientiousness(100)
                        .setExtraversion(10)
                        .setAgreeableness(10)
                        .setNeuroticism(10)
                        .setAchievement(80)
                        .setBenevolence(40)
                        .setConformity(90)
                        .setHedonism(10)
                        .setPower(10)
                        .setSecurity(10)
                        .setSelfDirection(60)
                        .setStimulation(80)
                        .setTradition(100)
                        .setUniversalism(10),
                Job.ASSASSIN,
                new SpriteSheet(harmonicMoon, "/characters/seuri.png", 32, 16),
                new SpriteSheet(harmonicMoon, "/characters/seuri-fight.png", 64, 64)
        );
    }

}
