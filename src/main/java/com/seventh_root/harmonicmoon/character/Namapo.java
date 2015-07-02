package com.seventh_root.harmonicmoon.character;

import com.seventh_root.harmonicmoon.HarmonicMoon;
import com.seventh_root.harmonicmoon.job.Job;
import com.seventh_root.harmonicmoon.sprite.SpriteSheet;

public class Namapo extends Character {

    public Namapo(HarmonicMoon harmonicMoon) {
        super(
                harmonicMoon,
                "Namapo",
                14,
                Gender.MALE,
                new Personality()
                        .setOpenness(80)
                        .setConscientiousness(50)
                        .setExtraversion(20)
                        .setAgreeableness(10)
                        .setNeuroticism(10)
                        .setAchievement(10)
                        .setBenevolence(90)
                        .setConformity(10)
                        .setHedonism(10)
                        .setPower(10)
                        .setSecurity(10)
                        .setSelfDirection(90)
                        .setStimulation(40)
                        .setTradition(10)
                        .setUniversalism(10),
                Job.DRUID,
                new SpriteSheet(harmonicMoon, "/characters/namapo.png", 32, 16),
                new SpriteSheet(harmonicMoon, "/characters/namapo-fight.png", 64, 64)
        );
    }

}
