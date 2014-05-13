package io.github.lucariatias.harmonicmoon.npc;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.sprite.SpriteSheet;

public class TestNPC extends NPC {

    public TestNPC(HarmonicMoon harmonicMoon) {
        super(harmonicMoon, new SpriteSheet("/npcs/template.png", 32, 16));
        setPath(new BoringPath(getLocation()));
    }

    public void setNeutralPosition() {
        setPath(new BoringPath(getLocation()));
    }

}
