package com.seventh_root.harmonicmoon.character;

import com.seventh_root.harmonicmoon.HarmonicMoon;
import com.seventh_root.harmonicmoon.job.Job;
import com.seventh_root.harmonicmoon.sprite.SpriteSheet;

public class Tivor extends Character {

    public Tivor(HarmonicMoon harmonicMoon) {
        super(
                harmonicMoon,
                "Tivor",
                18,
                Gender.MALE,
                new Personality()
                        .setOpenness(20)
                        .setConscientiousness(20)
                        .setExtraversion(20)
                        .setAgreeableness(80)
                        .setNeuroticism(60)
                        .setAchievement(70)
                        .setBenevolence(60)
                        .setConformity(30)
                        .setHedonism(10)
                        .setPower(10)
                        .setSecurity(20)
                        .setSelfDirection(80)
                        .setStimulation(50)
                        .setTradition(30)
                        .setUniversalism(80),
                Job.RANGER,
                new SpriteSheet(harmonicMoon, "/characters/tivor.png", 32, 16),
                new SpriteSheet(harmonicMoon, "/characters/tivor-fight.png", 64, 64)
        );
    }

}
