package io.github.lucariatias.harmonicmoon.particle;


import io.github.lucariatias.harmonicmoon.HarmonicMoon;

import java.awt.*;

public class ParticleManager {

    private HarmonicMoon harmonicMoon;

    private int maxParticles = 11500;
    private int maxEmitters = 500;
    private int maxEffects = 200;
    public double windGlobal = 0;
    public double gravityGlobal = 0;
    public double friction = 0;
    public int boundBoxX1 = -25;
    public int boundBoxX2 = 825;
    public int boundBoxY1 = -25;
    public int boundBoxY2 = 625;

    public int currentParticles = 0;
    public int currentEmitters = 0;
    public int currentEffects = 0;

    private Particle[] particles = new Particle[maxParticles];
    private Emitter[] emitters = new Emitter[maxEmitters];
    private Effect[] effects = new Effect[maxEffects];

    private int pIterator = 0;
    private int eIterator = 0;
    private int fxIterator = 0;

    public ParticleManager(HarmonicMoon harmonicMoon) {
        this.harmonicMoon = harmonicMoon;
    }

    public synchronized void render(Graphics graphics) {

        Graphics2D graphics2D = (Graphics2D) graphics.create();

        for (int i = particles.length - 1; i >= 0; i--) {
            if (particles[i] != null) {
                particles[i].render(graphics2D);
            }
        }
        graphics2D.dispose();
    }

    public void renderHUD(Graphics graphics) {
        if (harmonicMoon.isDebug()) {
            graphics.drawString("Particles: " + currentParticles, 16, 16);
        }
    }

    /**
     * Updates the particle system
     */
    public void update() {
        updateParticles();
        updateEmitters();
        updateEffects();
    }

    public synchronized void addParticle(Particle p) {

        if (currentParticles == maxParticles) {
            return;
        }
        for (int i = particles.length - 1; i >= 0; i--) {
            if (pIterator > particles.length - 1)
                pIterator = 0;
            if (particles[pIterator] == null) {
                particles[pIterator] = p;
                pIterator++;
                return;
            }
            pIterator++;
        }
    }

    private synchronized void updateParticles() {
        currentParticles = 0;
        for (int i = 0; i <= particles.length - 1; i++) {
            if (particles[i] != null) {
                particles[i].update();
                particles[i].applyForce(new Vector3D(windGlobal, gravityGlobal, 0));

                if (!particles[i].isAlive())
                    particles[i] = null;
                else if (particles[i].inside(boundBoxX1, boundBoxX2, boundBoxY1, boundBoxY2)) {
                    particles[i] = null;
                } else
                    currentParticles++;
            }
        }
    }

    /**
     * Removes the emitter at the given index.
     *
     * @param num index of the emitter to be removed
     */
    public void removeEmitter(int num) {
        if (num >= 0)
            emitters[num] = null;
    }

    /**
     * Adds the emitter to ParticleManager and returns its index in the backing array.
     *
     * @param e the emitter being added
     * @return the index of the emitter
     */
    public int addEmitter(Emitter e) {
        if (currentEmitters == maxEmitters) {
            return -1;
        }
        for (int i = emitters.length - 1; i >= 0; i--) {
            if (eIterator > emitters.length - 1)
                eIterator = 0;
            if (emitters[eIterator] == null) {
                emitters[eIterator] = e;
                eIterator++;
                return eIterator - 1;
            }
            eIterator++;
        }
        return -1;
    }

    private void updateEmitters() {
        currentEmitters = 0;
        for (int i = 0; i <= emitters.length - 1; i++) {
            if (emitters[i] != null) {
                emitters[i].update();
                if (!emitters[i].isAlive()) {
                    emitters[i] = null;
                } else
                    currentEmitters++;
            }
        }
    }

    /**
     * Returns the emitter at the given index. If there is no emitter it will return null.
     *
     * @param i the index of the emitter
     * @return the emitter at the given index
     */
    public Emitter getEmitter(int i) {
        if (i <= emitters.length - 1)
            return emitters[i];
        else
            return null;
    }

    /**
     * Same as the add emitter method but instead with the effect
     * Warning! You can add effects and emitters that die without having to worry about managing them but
     * if the effect has an emitter that is "continuous," it will never end until it is removed or ParticleManager.clearEmitters()
     * is called.
     *
     * @param e the effect being added
     * @return the index of this effect
     */
    public int addEffect(Effect e) {
        if (currentEffects == maxEffects)
            return -1;
        for (int i = effects.length - 1; i >= 0; i--) {
            if (fxIterator > effects.length - 1)
                fxIterator = 0;
            if (effects[fxIterator] == null) {
                effects[fxIterator] = e;
                fxIterator++;
                return fxIterator - 1;
            }
            fxIterator++;
        }
        return -1;
    }

    private void updateEffects() {
        currentEffects = 0;
        for (int i = 0; i <= effects.length - 1; i++) {
            if (effects[i] != null)
                if (effects[i].update())
                    effects[i] = null;
                else
                    currentEffects++;
        }

    }

    /**
     * Same as the emitter version. Removeds the effect at the given index
     *
     * @param num the index of the effect
     */
    public void removeEffect(int num) {
        if (num >= 0)
            effects[num] = null;
    }

    /**
     * Same as the emitter version. Returns the effect at the given index. Will return null if there is no effect.
     *
     * @param num the index of the effect
     * @return the effect at the given index
     */
    public Effect getEffect(int num) {
        return effects[num];
    }

    /**
     * Clears emitters. Good for scene transitions.
     */
    public void clearEmitters() {
        emitters = new Emitter[maxEmitters];
    }

    /**
     * Is use mainly to change the number of max particles.
     */
    public void clearParticles() {
        particles = new Particle[maxParticles];
    }

    /**
     * Should be called when a scene is changed to kill and continuous emitters.
     */
    public void clearEffects() {
        effects = new Effect[maxEffects];
    }

    /**
     * Set the bounding box for particles to be update/rendered. This is useful for side scrollers as we don't
     * want to update and draw particles/emitters/effects now on the screen.
     *
     * @param x1 lowest x cord allowed
     * @param y1 lowest y cord allowed
     * @param x2 largest x cord allowed
     * @param y2 largest y cord allowed
     */
    public void setBoundingBox(int x1, int y1, int x2, int y2) {
        boundBoxX1 = x1;
        boundBoxX2 = x2;
        boundBoxY1 = y1;
        boundBoxY2 = y2;
    }

    /**
     * Sets the maximum number of particles. May kill all particles on the screen if the size shrinks.
     *
     * @param num the amount of particles to set
     */
    public void setMaxParticles(int num) {
        Particle[] temp = new Particle[num];
        if (num > maxParticles) {
            System.arraycopy(particles, 0, temp, 0, particles.length - 1 + 1);
        } else {
            System.arraycopy(particles, 0, temp, 0, temp.length - 1 + 1);
        }
        maxParticles = num;
        particles = temp;
    }

    /**
     * Set the max emitters. Emitters may be lost if the size is decreased
     *
     * @param num the amount of emitters to set
     */
    public void setMaxEmitters(int num) {
        Emitter[] temp = new Emitter[num];
        if (num > maxEmitters) {
            System.arraycopy(emitters, 0, temp, 0, emitters.length - 1 + 1);
        } else {
            System.arraycopy(emitters, 0, temp, 0, temp.length - 1 + 1);
        }
        maxEmitters = num;
        emitters = temp;
    }

    /**
     * Set the max number of Effects. May lose some effects if the size shrinks.
     *
     * @param num the amount of effects to set
     */
    public void setMaxEffects(int num) {
        Effect[] temp = new Effect[num];
        if (num > maxEffects) {
            System.arraycopy(effects, 0, temp, 0, effects.length - 1 + 1);
        } else {
            System.arraycopy(effects, 0, temp, 0, temp.length - 1 + 1);
        }
        maxEffects = num;
        effects = temp;
    }


}
