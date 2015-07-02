package com.seventh_root.harmonicmoon.event.sprite;

import com.seventh_root.harmonicmoon.event.Event;
import com.seventh_root.harmonicmoon.sprite.Sprite;

public class SpriteAnimationCompleteEvent extends Event {

    private final Sprite sprite;

    public SpriteAnimationCompleteEvent(Sprite sprite) {
        this.sprite = sprite;
    }

    public Sprite getSprite() {
        return sprite;
    }

}
