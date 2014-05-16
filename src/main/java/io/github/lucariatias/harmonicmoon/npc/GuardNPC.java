package io.github.lucariatias.harmonicmoon.npc;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.enemy.Guard;
import io.github.lucariatias.harmonicmoon.fight.Fight;
import io.github.lucariatias.harmonicmoon.fight.FightArea;
import io.github.lucariatias.harmonicmoon.npc.path.FollowingPath;
import io.github.lucariatias.harmonicmoon.party.CharacterParty;
import io.github.lucariatias.harmonicmoon.party.EnemyParty;
import io.github.lucariatias.harmonicmoon.sprite.SpriteSheet;

public class GuardNPC extends NPC {

    private HarmonicMoon harmonicMoon;

    public GuardNPC(HarmonicMoon harmonicMoon) {
        super(harmonicMoon, new SpriteSheet("/npcs/guard.png", 32, 16));
        this.harmonicMoon = harmonicMoon;
        setPath(new FollowingPath(this, harmonicMoon.getPlayer(), 320));
    }

    @Override
    public void interact() {
        say("Hey! You there! Stop!", "This castle has been taken over by His Mightiness, Yirnor, no prisoners are to escape.");
        Fight fight = new Fight(FightArea.PALACE, new CharacterParty(harmonicMoon.getPlayer().getCharacter().fight()), new EnemyParty(new Guard(harmonicMoon)));
        harmonicMoon.getFightPanel().prepareFight(fight);
        harmonicMoon.getFightPanel().startFight();
    }

}
