package io.github.lucariatias.harmonicmoon.character;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.job.Job;
import io.github.lucariatias.harmonicmoon.sprite.SpriteSheet;

public class Lonyre extends Character {

    public Lonyre(HarmonicMoon harmonicMoon) {
        super(
                harmonicMoon,
                "Lonyre",
                17,
                Gender.FEMALE,
                new Personality()
                        .setOpenness(10)
                        .setConscientiousness(90)
                        .setExtraversion(10)
                        .setAgreeableness(10)
                        .setNeuroticism(40)
                        .setAchievement(10)
                        .setBenevolence(80)
                        .setConformity(80)
                        .setHedonism(10)
                        .setPower(90)
                        .setSecurity(90)
                        .setSelfDirection(20)
                        .setStimulation(10)
                        .setTradition(100)
                        .setUniversalism(80),
                Job.WATER_MAGE,
                new SpriteSheet(harmonicMoon, "/characters/lonyre.png", 32, 16),
                new SpriteSheet(harmonicMoon, "/characters/lonyre-fight.png", 64, 64)
        );
    }

}
