package io.github.lucariatias.harmonicmoon.character;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.job.Job;
import io.github.lucariatias.harmonicmoon.sprite.SpriteSheet;

public class Syalae extends Character {

    public Syalae(HarmonicMoon harmonicMoon) {
        super(
                harmonicMoon,
                "Syalae",
                24,
                Gender.FEMALE,
                new Personality()
                        .setOpenness(70)
                        .setConscientiousness(60)
                        .setExtraversion(10)
                        .setAgreeableness(10)
                        .setNeuroticism(80)
                        .setAchievement(90)
                        .setBenevolence(10)
                        .setConformity(10)
                        .setHedonism(10)
                        .setPower(10)
                        .setSecurity(60)
                        .setSelfDirection(80)
                        .setStimulation(10)
                        .setTradition(10)
                        .setUniversalism(10),
                Job.FIRE_MAGE,
                new SpriteSheet(harmonicMoon, "/characters/syalae.png", 32, 16),
                new SpriteSheet(harmonicMoon, "/characters/syalae-fight.png", 64, 64)
        );
    }

}
