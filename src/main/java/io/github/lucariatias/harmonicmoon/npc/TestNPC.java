package io.github.lucariatias.harmonicmoon.npc;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.npc.path.BoringPath;
import io.github.lucariatias.harmonicmoon.sprite.SpriteSheet;

public class TestNPC extends NPC {

    private HarmonicMoon harmonicMoon;

    public TestNPC(HarmonicMoon harmonicMoon) {
        super(harmonicMoon, new SpriteSheet(harmonicMoon, "/npcs/template.png", 32, 16));
        this.harmonicMoon = harmonicMoon;
        setPath(new BoringPath(this));
    }

    @Override
    public void interact() {
        say("Hello. I am a test NPC.");
    }

    public void setNeutralPosition() {
        if (getPath() instanceof BoringPath) {
            ((BoringPath) getPath()).setNeutralPosition();
        }
    }

}
