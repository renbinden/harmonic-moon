package com.seventh_root.harmonicmoon.fight;

import com.seventh_root.harmonicmoon.event.character.CharacterMoveEvent;
import com.seventh_root.harmonicmoon.event.character.CharacterMoveListener;
import com.seventh_root.harmonicmoon.HarmonicMoon;
import com.seventh_root.harmonicmoon.enemy.Slime;
import com.seventh_root.harmonicmoon.fight.party.EnemyFightParty;

import java.util.Random;

public class FightCharacterMoveListener extends CharacterMoveListener {

    private HarmonicMoon harmonicMoon;

    public FightCharacterMoveListener(HarmonicMoon harmonicMoon) {
        this.harmonicMoon = harmonicMoon;
    }

    @Override
    public void onPlayerMove(CharacterMoveEvent event) {
        Random random = new Random();
        if (random.nextInt(100) < 2) {
            Fight fight = new Fight(harmonicMoon, FightArea.GRASS, harmonicMoon.getCharacterManager().getParty(event.getCharacter()).asFightParty(), new EnemyFightParty(new Slime(harmonicMoon)));
            harmonicMoon.getFightPanel().prepareFight(fight);
            harmonicMoon.getFightPanel().startFight();
            harmonicMoon.setPanel("fight");
        }
    }

}
