package com.seventh_root.harmonicmoon.npc;

import com.seventh_root.harmonicmoon.HarmonicMoon;
import com.seventh_root.harmonicmoon.npc.path.BoringPath;
import com.seventh_root.harmonicmoon.sprite.SpriteSheet;

public class TestNPC extends NPC {

    private HarmonicMoon harmonicMoon;

    public TestNPC(HarmonicMoon harmonicMoon, NPCMetadata metadata) {
        super(harmonicMoon, new SpriteSheet(harmonicMoon, "/npcs/template.png", 32, 16), metadata);
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
