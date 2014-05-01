package io.github.lucariatias.harmonicmoon.particle;


import java.awt.*;
import java.awt.image.BufferedImage;


public class SparksEffect implements Effect {

    private ParticleManager particleManager;

    public int sparks;

    public SparksEffect(ParticleManager particleManager, int x, int y, BufferedImage[] sparksB) {
        this.particleManager = particleManager;
        Emitter t = new Emitter(particleManager);
        t.jump(x + 0.0, y + 0.0);
        t.setSpread(0.0, 0.0);
        t.setAcc(0.0, 0.08);
        t.setMaxSpeed(6.0, 6.0);
        t.setAngle(315, 315);
        t.setVelocity(4.0, 6.0);
        t.setMaxSpeed(6.0, 6.0);
        t.setSize(1.0);
        t.setFade(1.0);
        t.setFadeRate(0.02);
        t.setGrowthRate(0.0);
        t.setMaxSize(128.0);
        t.setMaxAge(1.0);
        t.setAgeRate(0.01);
        t.setBlink(false);
        t.setRandomV(true);
        t.setContinuous(false);
        t.setEmitNum(5);
        t.setNumEmits(4);
        t.setImages(sparksB);
        t.setUpdateRate(3);
        t.setColor(new Color(255, 200, 15));

        sparks = particleManager.addEmitter(t);


    }

    public void done() {
        //Clean up emitters from ParticleManager if they are CONTINUOUS.
    }

    public boolean update() {
        return false;
        //Any special things for updating go here such as creating other effects in this effect.
    }
}