package com.seventh_root.harmonicmoon.particle;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Emitter implements Cloneable {

    private ParticleManager particleManager;

    private static Random rnd = new Random();

    private Vector3D loc;
    private int numEmits;
    private int emitNum = 1;

    private Color color = Color.red;

    private boolean randomV = false;
    private boolean continuous;
    private boolean isAlive = true;
    private boolean blink = false;
    private BufferedImage[] images;

    //particle spawn vars
    private int angle1 = 360;
    private int angle2 = 0;
    private double maxAge = 1;
    private double ageRate = 0.01;
    private double size = 1;
    private double maxSize = 256;
    private double growthRate = 0;
    private double fadeRate = 0.01;
    private double fade = 1;
    private double spreadX = 0;
    private double spreadY = 0;
    private double xSpeed = 0;
    private double ySpeed = 0;
    private double maxYSpeed = 8;
    private double maxXSpeed = 8;
    private double xAcc = 0;
    private double yAcc = 0;
    private double scale = 1;


    //Update vars
    private int update = 0;
    private int currUpdate = 0;

    public Emitter(ParticleManager particleManager) {
        this.particleManager = particleManager;
        loc = new Vector3D(0, 0, 0);
    }

    public Emitter(ParticleManager particleManager, double x, double y, double z, boolean cont, int num, int r, BufferedImage[] im) {
        this.particleManager = particleManager;
        loc = new Vector3D(x, y, z);
        continuous = cont;
        numEmits = num;
        images = im;
        update = r;

    }

    public void jump(double x, double y) {
        loc.x = x;
        loc.y = y;
    }

    public void update() {
        if (currUpdate == update) {
            if (numEmits > 0) {
                emit();
                numEmits--;
            } else if (!continuous) {
                isAlive = false;
            } else {
                emit();
            }
            currUpdate = 0;
        } else {
            currUpdate++;
        }
    }

    public void emit() {
        for (int i = 1; i <= emitNum; i++) {
            Particle x = new Particle(loc.x, loc.y, 0, null);
            if (images != null) {
                x.setImage(images[(int) randomP(images.length)]);
            } else {
                x.setImage(null);
            }
            double temp = angle2 - angle1;
            double newAngle = (((rnd.nextDouble() * temp) + angle1) * Math.PI / 180);
            if (randomV) {
                x.setVel(randomP(xSpeed) * FastMath.cos(newAngle), randomP(ySpeed) * FastMath.sin(newAngle), 0);
            } else {
                x.setVel(xSpeed * FastMath.cos(newAngle), ySpeed * FastMath.sin(newAngle), 0);
            }


            x.setFade(fade);
            x.setFadeRate(fadeRate);
            x.setLoc(loc.x + random(spreadX), loc.y + random(spreadY), 0);
            x.setAcc(xAcc, yAcc, 0);
            x.setMaxAge(maxAge);
            x.setMaxSize(maxSize);
            x.setSize(size);
            x.setAgeRate(ageRate);
            x.setGrowthRate(growthRate);
            x.setBlink(blink);
            x.setColor(color);
            x.setMaxSpeed(new Vector3D(maxXSpeed, maxYSpeed, 0));
            particleManager.addParticle(x);

        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    public double randomP(double num) {
        double temp = ((num * 2) * rnd.nextDouble()) - num;
        if (temp < 0)
            return temp * -1;
        else
            return temp;
    }

    public double randomN(double num) {
        double temp = ((num * 2) * rnd.nextDouble()) - num;
        if (temp > 0)
            return temp * -1;
        else
            return temp;
    }

    public double random(double num) {
        return ((num * 2) * rnd.nextDouble()) - num;
    }


    //-------------------------------------------------\\
    //--------------------SET METHODS--------------------\\
    //-----------------------------------------------------\\
    public void setColor(Color c) {
        color = c;
    }

    public void setAcc(double x, double y) {
        xAcc = x;
        yAcc = y;
    }

    public void setSize(double num) {
        size = num;
    }

    public void setRandomV(boolean b) {
        randomV = b;
    }

    public void setBlink(boolean b) {
        blink = b;
    }

    public void setMaxSpeed(double xnum, double ynum) {
        maxXSpeed = xnum;
        maxYSpeed = ynum;
    }

    public void setAgeRate(double num) {
        ageRate = num;
    }

    public void setMaxAge(double num) {
        maxAge = num;
    }

    public void setFade(double num) {
        fade = num;
    }

    public void setScale(double num) {
        size *= num;
        maxSize *= num;
        growthRate *= num;
        spreadX *= num;
        spreadY *= num;
        xSpeed *= num;
        ySpeed *= num;
        xAcc *= num;
        yAcc *= num;
    }

    public void setFadeRate(double num) {
        if (num < 1 && num > 0) {
            fadeRate = num;
        }
    }

    public void setMaxSize(double num) {
        this.maxSize = num;
    }

    public void setGrowthRate(double num) {
        growthRate = num;
    }

    public void setEmitNum(int num) {
        emitNum = num;
    }

    public void setImages(BufferedImage[] b) {
        images = b;
    }

    public void setVelocity(double speedX, double speedY) {
        xSpeed = speedX;
        ySpeed = speedY;
    }

    public void setSpread(double numx, double numy) {
        spreadX = numx;
        spreadY = numy;
    }

    public void setAngle(int num1, int num2) {
        angle1 = num1;
        angle2 = num2;
    }

    public void scaleSpeed(double num) {
        xSpeed *= num;
        ySpeed *= num;
        growthRate *= num;
        fadeRate *= num;
        scale = num;
        ageRate *= num;
        xAcc *= num;
        yAcc *= num;
    }

    public void setUpdateRate(int num) {
        update = num;
    }

    public void setNumEmits(int num) {
        numEmits = num;
    }

    public void setContinuous(boolean c) {
        continuous = c;
    }

    //-----------------Get Methods------------------------\\
    public BufferedImage[] getImage() {
        return images;
    }

    public Vector3D getLoc() {
        return loc;
    }

    public int getNumEmits() {
        return numEmits;
    }

    public int getEmitNum() {
        return emitNum;
    }

    public Color getColor() {
        return color;
    }

    public boolean getRandomV() {
        return randomV;
    }

    public boolean getBlink() {
        return blink;
    }

    public boolean getCont() {
        return continuous;
    }

    public int getAngle1() {
        return angle1;
    }

    public int getAngle2() {
        return angle2;
    }

    public double getMaxAge() {
        return maxAge;
    }

    public double getAgeRate() {
        return ageRate;
    }

    public double getSize() {
        return size;
    }

    public double getMaxsize() {
        return maxSize;
    }

    public double getGrowthRate() {
        return growthRate;
    }

    public double getFadeRate() {
        return fadeRate;
    }

    public double getFade() {
        return fade;
    }

    public double getSpreadX() {
        return spreadX;
    }

    public double getSpreadY() {
        return spreadY;
    }

    public Vector3D getSpeed() {
        return new Vector3D(xSpeed, ySpeed, 0);
    }

    public Vector3D getMaxSpeed() {
        return new Vector3D(maxXSpeed, maxYSpeed, 0);
    }

    public Vector3D getAcc() {
        return new Vector3D(xAcc, yAcc, 0);
    }

    public int getUpdate() {
        return update;
    }

    public double getScale() {
        return scale;
    }

    public Emitter clone() throws CloneNotSupportedException {
        try {
            return (Emitter) super.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public static double randomNum(double num) {
        return ((num * 2) * rnd.nextDouble()) - num;
    }

}
