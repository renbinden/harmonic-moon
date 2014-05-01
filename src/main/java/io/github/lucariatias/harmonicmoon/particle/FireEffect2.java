package io.github.lucariatias.harmonicmoon.particle;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FireEffect2 implements Effect {

    private ParticleManager particleManager;

    public int fire;
    public int smoke;
    public int embers;

    public FireEffect2(ParticleManager particleManager, int x, int y, BufferedImage[] smoke1, BufferedImage[] smoke2) {
        this.particleManager = particleManager;

        Emitter t = new Emitter(particleManager, x, y, 6, true, 1, 2, smoke1);
        t.setFadeRate(.02);
        t.setEmitNum(4);
        t.setMaxAge(500);
        t.setSize(32);
        t.setGrowthRate(-1.4);
        t.setAngle(260, 280);
        t.setVelocity(2.5, 2.5);

        t.setSpread(16, 16);
        t.setBlink(true);
        t.setFade(0);
        t.setColor(new Color(99, 2, 255));
        fire = particleManager.addEmitter(t);

        t = new Emitter(particleManager, x, y - 24, 6, true, 1, 25, smoke2);
        t.setFadeRate(.004);
        t.setEmitNum(1);
        t.setMaxAge(500);
        t.setSize(64);
        t.setGrowthRate(1);
        t.setAngle(260, 280);
        t.setVelocity(1.2, 1.2f);
        t.setMaxSize(512);
        t.setSpread(128, 128);
        t.setBlink(true);
        t.setFade(0);

        //smoke = ParticleManager.addEmitter(t);


        t = new Emitter(particleManager, x, y, 6, true, 1, 2, smoke1);
        t.setFadeRate(.02);
        t.setEmitNum(2);
        t.setMaxAge(500);
        t.setSize(4);
        t.setGrowthRate(-.05);
        t.setAngle(170, 360);
        t.setVelocity(1.5, 1.5);
        t.setBlink(true);
        t.setFade(0);
        t.setColor(new Color(99, 2, 255));
        t.setSpread(16, 16);

        embers = particleManager.addEmitter(t);
    }


    public void done() {
        particleManager.removeEmitter(smoke);
        particleManager.removeEmitter(fire);
        particleManager.removeEmitter(embers);
    }


    @Override
    public boolean update() {
        return false;
    }


}
