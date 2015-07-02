package com.seventh_root.harmonicmoon.character;

import com.seventh_root.harmonicmoon.HarmonicMoon;
import com.seventh_root.harmonicmoon.job.Job;
import com.seventh_root.harmonicmoon.sprite.SpriteSheet;

public class Idain extends Character {

    public Idain(HarmonicMoon harmonicMoon) {
        super(
                harmonicMoon,
                "Idain",
                48,
                Gender.MALE,
                new Personality()
                        .setOpenness(80)
                        .setConscientiousness(10)
                        .setExtraversion(90)
                        .setAgreeableness(20)
                        .setNeuroticism(60)
                        .setAchievement(30)
                        .setBenevolence(20)
                        .setConformity(70)
                        .setHedonism(70)
                        .setPower(70)
                        .setSecurity(90)
                        .setSelfDirection(70)
                        .setStimulation(90)
                        .setTradition(90)
                        .setUniversalism(30),
                Job.BARBARIAN,
                new SpriteSheet(harmonicMoon, "/characters/idain.png", 32, 16),
                new SpriteSheet(harmonicMoon, "/characters/idain-fight.png", 64, 64)
        );
    }

}
