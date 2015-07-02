package com.seventh_root.harmonicmoon.character;

import com.seventh_root.harmonicmoon.HarmonicMoon;
import com.seventh_root.harmonicmoon.job.Job;
import com.seventh_root.harmonicmoon.sprite.SpriteSheet;

public class Kesowa extends Character {

    public Kesowa(HarmonicMoon harmonicMoon) {
        super(
                harmonicMoon,
                "Kesowa",
                21,
                Gender.FEMALE,
                new Personality()
                        .setOpenness(50)
                        .setConscientiousness(60)
                        .setExtraversion(75)
                        .setAgreeableness(80)
                        .setNeuroticism(40)
                        .setAchievement(30)
                        .setBenevolence(90)
                        .setConformity(60)
                        .setHedonism(60)
                        .setPower(20)
                        .setSecurity(80)
                        .setSelfDirection(90)
                        .setStimulation(85)
                        .setTradition(90)
                        .setUniversalism(60),
                Job.SPEARMAN,
                new SpriteSheet(harmonicMoon, "/characters/kesowa.png", 32, 16),
                new SpriteSheet(harmonicMoon, "/characters/kesowa-fight.png", 64, 64)
        );
    }

}
