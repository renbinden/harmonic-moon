package com.seventh_root.harmonicmoon.particle;

import java.awt.image.BufferedImage;

public class SmokeEffect implements Effect {

    private ParticleManager particleManager;

    private int smoke;
    private int smoke1;
    private int smoke2;

    public SmokeEffect(ParticleManager particleManager, int x, int y, BufferedImage[] smokes) {
        this.particleManager = particleManager;

        Emitter t = new Emitter(particleManager, x, y, 6, false, 3, 1, smokes);
        t.setFadeRate(.003);
        t.setEmitNum(6);
        t.setMaxAge(500);
        t.setSize(32);
        t.setGrowthRate(1.9);
        t.setAngle(240, 300);
        t.setVelocity(2.8, 1.9f);
        t.setMaxSize(128);
        //t.setRandomV(true);
        t.setSpread(16, 16);
        //t.setBlink(true);
        t.setFade(.25);
        t.setScale(1);
        smoke = particleManager.addEmitter(t);

        t = new Emitter(particleManager, x, y, 6, false, 3, 2, smokes);
        t.setFadeRate(.002);
        t.setEmitNum(8);
        t.setMaxAge(500);
        t.setSize(32);
        t.setGrowthRate(1.5);
        t.setAngle(250, 290);
        t.setVelocity(2.5, 1);
        t.setMaxSize(64);
        //t.setRandomV(true);
        //t.setSpread(32,32);
        //t.setBlink(true);
        t.setFade(.25);
        t.setScale(1);
        smoke1 = particleManager.addEmitter(t);

        t = new Emitter(particleManager, x, y, 6, false, 2, 1, smokes);
        t.setFadeRate(.0028);
        t.setEmitNum(3);
        t.setMaxAge(500);
        t.setSize(32);
        t.setGrowthRate(1.9);
        t.setAngle(260, 280);
        t.setVelocity(2.8, 2.5);
        t.setMaxSize(128);
        //t.setRandomV(true);
        //t.setSpread(32,32);
        //t.setBlink(true);
        t.setFade(.25);
        t.setScale(1);
        // t.scaleSpeed(2);
        smoke2 = particleManager.addEmitter(t);

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

    public void jump(int x, int y) {
        particleManager.getEmitter(smoke).jump(x, y);
        particleManager.getEmitter(smoke1).jump(x, y);
    }

}
