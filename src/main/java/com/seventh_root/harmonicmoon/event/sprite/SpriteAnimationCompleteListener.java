package com.seventh_root.harmonicmoon.event.sprite;

import com.seventh_root.harmonicmoon.event.Listener;

public abstract class SpriteAnimationCompleteListener extends Listener<SpriteAnimationCompleteEvent> {

    public SpriteAnimationCompleteListener() {
        super(SpriteAnimationCompleteEvent.class);
    }

    public abstract void onSpriteAnimationComplete(SpriteAnimationCompleteEvent event);

    @Override
    public void onEvent(SpriteAnimationCompleteEvent event) {
        onSpriteAnimationComplete(event);
    }

}
