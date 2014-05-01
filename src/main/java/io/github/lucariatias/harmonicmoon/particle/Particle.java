package io.github.lucariatias.harmonicmoon.particle;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;


public class Particle {

    private BufferedImage image;
    private Vector3D loc;
    private Vector3D velocity;
    private Vector3D acceleration;
    private Vector3D maxSpeed = new Vector3D(1, 1, 0);
    private Vector3D rotation = new Vector3D(0, 0, 0);
    private double age = 0;
    private double maxAge = 120;
    private double ageRate = 1;
    private double size = 1;
    private double maxSize = 256;
    private double growthRate = 0;
    private double fade = 1;
    private double fadeRate = 0.000001;
    private boolean alive = true;
    private boolean blink = false;
    private Color color = Color.red;

    public Particle(double x, double y, double z, BufferedImage i) {
        loc = new Vector3D(x, y, z);
        velocity = new Vector3D(0, 0, 0);
        acceleration = new Vector3D(0, 0, 0);
        age = 0;
        image = i;
    }

    public Particle(double x, double y, double z, double vx, double vy, double vz, BufferedImage i) {
        loc = new Vector3D(x, y, z);
        velocity = new Vector3D(vx, vy, vz);
        acceleration = new Vector3D(0f, 0f, 0f);
        age = 0;
        image = i;
    }

    public Particle(double x, double y, double z, double vx, double vy, double vz, double ax, double ay, double az, BufferedImage i) {
        loc = new Vector3D(x, y, z);
        velocity = new Vector3D(vx, vy, vz);
        acceleration = new Vector3D(ax, ay, az);
        age = 0;
        image = i;
    }


    //-------------------------------------------------\\
    //--------------------SET METHODS--------------------\\
    //-----------------------------------------------------\\

    public void setColor(Color c) {
        color = c;
    }

    public void setSize(double num) {
        size = num;
    }

    public void setImage(BufferedImage i) {
        image = i;
    }

    public void setLoc(double x, double y, double z) {
        loc.setNum(x, y, z);
    }

    public void setMaxSpeed(Vector3D v) {
        maxSpeed = v;
    }

    public void setVel(double vx, double vy, double vz) {
        velocity.setNum(vx, vy, vz);
    }

    public void setAcc(double ax, double ay, double az) {
        acceleration.setNum(ax, ay, az);
    }

    public void setAge(double a) {
        age = a;
    }

    public void setAgeRate(double num) {
        ageRate = num;
    }

    public void setMaxAge(double num) {
        maxAge = num;
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

    public void setFade(double num) {
        fade = num;
    }

    public void setBlink(boolean b) {
        blink = b;
    }

    public void setRotation(double rate) {
        rotation.y = rate;
    }

    public void reset() {
        this.age = 0;
        this.fade = 1f;
        this.alive = true;
        this.velocity.x = 0;
        this.velocity.y = 0;
        this.velocity.z = 0;
    }
    //------------------------------------------------------||
    //--------------------END METHODS-----------------------||
    //------------------------------------------------------||
    //------------------------------------------------------||

    //-------------------------------------------------\\
    //--------------------GET METHODS--------------------\\
    //-----------------------------------------------------\\

    public Vector3D getLoc() {
        return loc;
    }

    public Vector3D getVel() {
        return velocity;
    }

    public Vector3D getAcc() {
        return acceleration;
    }

    public double getAge() {
        return age;
    }

    public double getMaxAge() {
        return maxAge;
    }

    public double getAgeRate() {
        return ageRate;
    }

    public Color getColor() {
        return color;
    }

    public double getMaxSize() {
        return maxSize;
    }

    public double getFade() {
        return fade;
    }

    public double getSize() {
        return size;
    }

    public double getGrowth() {
        return growthRate;
    }

    public BufferedImage getImage() {
        return image;
    }

    public double getFadeRate() {
        return fadeRate;
    }

    public boolean isAlive() {
        return alive;
    }

    public Vector3D getMaxSpeed() {
        return maxSpeed;
    }
    //------------------------------------------------------||
    //--------------------END METHODS-----------------------||
    //------------------------------------------------------||
    //------------------------------------------------------||


    //---------------------------------------------------\\
    //--------------------FORCE METHODS--------------------\\
    //-------------------------------------------------------\\

    public void applyForce(Vector3D force) {
        acceleration.add(force);
    }

    public void applyDissipativeForce(double friction) {
        Vector3D f = Vector3D.mult(velocity, -friction);
        applyForce(f);
        //acceleration.mult(0);
    }

    //------------------------------------------------------||
    //--------------------END METHODS-----------------------||
    //------------------------------------------------------||
    //------------------------------------------------------||

    //---------------------------------------------------\\
    //--------------UPDATE AND RENDER METHODS--------------\\
    //-------------------------------------------------------\\
    public void update() {
        if (maxSpeed.x > velocity.x || maxSpeed.x * -1 > (velocity.x)) {
            velocity.x += acceleration.x;
        } else
            velocity.x = maxSpeed.x;
        if (maxSpeed.y > velocity.y || maxSpeed.y * -1 > (velocity.y)) {
            velocity.y += acceleration.y;
        } else
            velocity.y = maxSpeed.y;
        loc.add(velocity);
        //acceleration.mult(0);
        age += ageRate;
        fade -= fadeRate;
        rotation.x += rotation.y;
        if (rotation.x > 360)
            rotation.x = 0;
        if (rotation.x < 0)
            rotation.x = 360;
        if (blink) {
            if (fade <= 0) {
                fade = 0;
                fadeRate *= -1;
            } else if (fade >= 1) {
                fade = 1;
                fadeRate *= -1;
            }

        }
        size += growthRate;
        if (size > maxSize) {
            size = maxSize;
        }

        if (dead()) {
            alive = false;
        }

    }

    public boolean dead() {
        if ((fade <= 0 || fade >= 1) && !blink) {
            fade = 0;
            return true;
        } else return age > maxAge || size <= 0;

    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) (fade)));
        g2d.setTransform(AffineTransform.getRotateInstance(rotation.x, loc.x, loc.y));
        if (image != null) {
            if (alive) {
                int x1 = (int) (loc.x - (size / 2));
                int y1 = (int) (loc.y - (size / 2));
                g2d.drawImage(image, x1, y1, (int) size, (int) size, null);
            }
        } else if (alive) {
            int x1 = (int) size >> 1;
            int y1 = (int) size >> 1;
            g2d.setColor(color);
            g2d.fillRect((int) (loc.x - x1), (int) (loc.y - y1), (int) size, (int) size);
        }
        g2d.dispose();
    }

    public boolean inside(int x1, int x2, int y1, int y2) {
        return loc.x > x2 + size || loc.x < x1 - size || loc.y < y1 - size || loc.y > y2 + size;
    }


}
