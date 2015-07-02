package com.seventh_root.harmonicmoon.npc;

import com.seventh_root.harmonicmoon.HarmonicMoon;
import com.seventh_root.harmonicmoon.npc.path.UnmovingPath;
import com.seventh_root.harmonicmoon.sprite.SpriteSheet;

public class MerchantNPC extends NPC {

    public MerchantNPC(HarmonicMoon harmonicMoon, SpriteSheet spriteSheet, NPCMetadata metadata) {
        super(harmonicMoon, spriteSheet, metadata);
        setPath(new UnmovingPath(this));
    }

}
