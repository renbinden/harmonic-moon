package com.seventh_root.harmonicmoon.particle;

import java.awt.*;
import java.awt.image.BufferedImage;


public class SplashEffect implements Effect {

    private ParticleManager particleManager;

    public SplashEffect(ParticleManager particleManager, double x, double y, Color c, BufferedImage[] buff, BufferedImage[] buffy) {
        this.particleManager = particleManager;
        Emitter t;
        //int water1;
        t = new Emitter(particleManager, x, y, 0, false, 4, 1, buffy);
        t.setFadeRate(.005);
        t.setEmitNum(2);
        t.setMaxAge(290);
        t.setSize(32);
        t.setGrowthRate(2.5);
        t.setAngle(200, 340);
        t.setVelocity(.5, 2);
        // t.setAcc(0, .05);
        t.setMaxSize(258);
        //t.setRandomV(true);
        t.setSpread(4, 4);
        //t.setBlink(true);
        t.setFade(1);
        //t.setColor(new Color(25,(int) (Math.random()*100)+105,(int) ((Math.random()*100)+120)));
        t.setColor(c);
        t.setScale(.25);
        t.scaleSpeed(4.3);
        //water1 =
        particleManager.addEmitter(t);

        t = new Emitter(particleManager, x, y, 0, false, 6, 1, buffy);
        t.setFadeRate(.007);
        t.setEmitNum(1);
        t.setMaxAge(2850);
        t.setSize(32);
        t.setGrowthRate(1.5);
        t.setAngle(240, 300);
        t.setVelocity(2, 2);
        //t.setAcc(0, -.02);
        t.setMaxSize(256);
        t.setRandomV(true);
        t.setSpread(4, 4);
        //t.setBlink(true);
        t.setFade(.8);
        //t.setColor(new Color(25,(int) (Math.random()*100)+105,(int) ((Math.random()*100)+120)));
        t.setColor(c);
        t.setScale(.25);
        t.scaleSpeed(4.3);
        //water1 =
        particleManager.addEmitter(t);

        t = new Emitter(particleManager, x, y, 0, false, 4, 1, buff);
        t.setFadeRate(.008);
        t.setEmitNum(3);
        t.setMaxAge(2850);
        t.setSize(5);
        t.setGrowthRate(2);
        t.setAngle(180, 360);
        t.setVelocity(.5, 2);
        // t.setAcc(0, .05);
        t.setMaxSize(256);
        //t.setRandomV(true);
        t.setSpread(6, 6);
        //t.setBlink(true);
        t.setFade(1);
        //t.setColor(new Color(25,(int) (Math.random()*100)+105,(int) ((Math.random()*100)+120)));
        t.setColor(c);
        t.setScale(.25);
        t.scaleSpeed(4.3);
        //water1 =
        particleManager.addEmitter(t);


    }

    @Override
    public void done() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean update() {
        // TODO Auto-generated method stub
        return false;
    }

}
