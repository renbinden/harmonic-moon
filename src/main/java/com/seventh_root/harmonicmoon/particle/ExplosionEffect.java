package com.seventh_root.harmonicmoon.particle;

import java.awt.image.BufferedImage;
import java.util.Random;

public class ExplosionEffect implements Effect {

    private ParticleManager particleManager;

    private int update = 6;
    private static Random rnd = new Random();
    private int x, y;
    private int num;
    private int spreadx = 62;
    private int spready = 8;
    private BufferedImage[] im1;
    private BufferedImage[] im2;


    public ExplosionEffect(ParticleManager particleManager, int xl, int yl, BufferedImage[] buff, BufferedImage[] buffy, int emit) {
        this.particleManager = particleManager;
        num = emit;
        im1 = buff;
        im2 = buffy;
        x = xl;
        y = yl;
    }

    public void done() {
        im1 = null;
        im2 = null;

    }

    @Override
    public boolean update() {
        if (update == 6) {
            update = 0;
            num--;
            //SplashEffect effect =
            new SplashEffect(particleManager, x + random(spreadx), y + random(spready), null, im1, im2);

        } else
            update++;
        return num <= 0;
    }

    public double random(double num) {
        return ((num * 2) * rnd.nextDouble()) - num;
    }


}
