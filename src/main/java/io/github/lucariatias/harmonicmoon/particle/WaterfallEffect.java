package io.github.lucariatias.harmonicmoon.particle;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WaterfallEffect implements Effect {

    private ParticleManager particleManager;

    private int water1, water2, water3;


    public WaterfallEffect(ParticleManager particleManager, int x, int y, BufferedImage[] waters) {
        this.particleManager = particleManager;
        Emitter t = new Emitter(particleManager, x, y, 6, true, 1, 6, waters);
        t.setFadeRate(.015);
        t.setEmitNum(2);
        t.setMaxAge(280);
        t.setSize(5);
        t.setGrowthRate(-.035);
        t.setAngle(200, 340);
        t.setVelocity(-.5, -4.5);
        //t.setAcc(0, -.05);
        t.setMaxSize(7.4);
        //t.setRandomV(true);
        t.setSpread(800, 500);
        t.setBlink(true);
        t.setFade(0);
        t.setColor(new Color(25, (int) (Math.random() * 100) + 105, (int) ((Math.random() * 100) + 120)));
        t.setColor(Color.pink);
        water1 = particleManager.addEmitter(t);

        t = new Emitter(particleManager, x, y, 6, true, 1, 10, waters);
        t.setFadeRate(.019);
        t.setEmitNum(1);
        t.setMaxAge(110);
        t.setSize(1);
        t.setGrowthRate(1);
        t.setAngle(120, 150);
        t.setVelocity(2, 4.5);
        t.setAcc(0, .07);
        t.setMaxSize(10);
        t.setRandomV(true);
        t.setSpread(128, 16);
        t.setBlink(true);
        t.setFade(0);
        t.setColor(new Color(55, 5, 200));
        //water1 = ParticleManager.addEmitter(t);


    }

    public void done() {

    }

    @Override
    public boolean update() {
        // TODO Auto-generated method stub
        return false;
    }

}
