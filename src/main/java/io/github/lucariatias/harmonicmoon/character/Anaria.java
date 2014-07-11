package io.github.lucariatias.harmonicmoon.character;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.job.Job;
import io.github.lucariatias.harmonicmoon.sprite.SpriteSheet;

public class Anaria extends Character {

    public Anaria(HarmonicMoon harmonicMoon) {
        super(
                harmonicMoon,
                "Anaria",
                128,
                Gender.FEMALE,
                new Personality()
                    .setOpenness(30)
                    .setConscientiousness(90)
                    .setExtraversion(20)
                    .setAgreeableness(20)
                    .setNeuroticism(40)
                    .setAchievement(50)
                    .setBenevolence(60)
                    .setConformity(10)
                    .setHedonism(60)
                    .setPower(70)
                    .setSecurity(90)
                    .setSelfDirection(90)
                    .setStimulation(80)
                    .setTradition(85)
                    .setUniversalism(50),
                Job.WARRIOR,
                new SpriteSheet(harmonicMoon, "/characters/anaria.png", 32, 16),
                new SpriteSheet(harmonicMoon, "/characters/anaria-fight.png", 64, 64)
        );
    }

}
