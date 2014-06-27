package io.github.lucariatias.harmonicmoon.sprite;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.event.sprite.SpriteAnimationCompleteEvent;

import java.awt.image.BufferedImage;

public class Sprite {

    private HarmonicMoon harmonicMoon;

    private BufferedImage[] frames;
    private int currentFrame;

    private final int frameDelay;
    private int frameTicks;

    public Sprite(Sprite sprite) {
        this.harmonicMoon = sprite.harmonicMoon;
        this.frames = sprite.frames;
        this.frameDelay = sprite.frameDelay;
    }

    public Sprite(HarmonicMoon harmonicMoon, int frameDelay, BufferedImage... frames) {
        this.harmonicMoon = harmonicMoon;
        this.frameDelay = frameDelay;
        this.frames = frames;
    }

    public void onTick() {
        if (isFinished()) {
            SpriteAnimationCompleteEvent event = new SpriteAnimationCompleteEvent(this);
            harmonicMoon.getEventManager().dispatchEvent(event);
        }
        frameTicks = frameTicks + 1 < frameDelay ? frameTicks + 1 : 0;
        if (frameTicks == 0) {
            nextFrame();
        }
    }

    public void nextFrame() {
        currentFrame = currentFrame + 1 < frames.length - 1 ? currentFrame + 1 : 0;
    }

    public BufferedImage getImage() {
        return frames[currentFrame];
    }

    public boolean isFinished() {
        return currentFrame + 1 == frames.length - 1;
    }

    public void reset() {
        currentFrame = 0;
    }

}
