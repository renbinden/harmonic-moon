package io.github.lucariatias.harmonicmoon.event.sprite;

import io.github.lucariatias.harmonicmoon.event.Event;
import io.github.lucariatias.harmonicmoon.sprite.Sprite;

public class SpriteAnimationCompleteEvent extends Event {

    private final Sprite sprite;

    public SpriteAnimationCompleteEvent(Sprite sprite) {
        this.sprite = sprite;
    }

    public Sprite getSprite() {
        return sprite;
    }

}
