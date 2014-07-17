package io.github.lucariatias.harmonicmoon.fight;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.enemy.Slime;
import io.github.lucariatias.harmonicmoon.event.character.CharacterMoveEvent;
import io.github.lucariatias.harmonicmoon.event.character.CharacterMoveListener;
import io.github.lucariatias.harmonicmoon.party.CharacterParty;
import io.github.lucariatias.harmonicmoon.party.EnemyParty;

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
            Fight fight = new Fight(harmonicMoon, FightArea.GRASS, new CharacterParty(event.getCharacter()), new EnemyParty(new Slime(harmonicMoon)));
            harmonicMoon.getFightPanel().prepareFight(fight);
            harmonicMoon.getFightPanel().startFight();
            harmonicMoon.setPanel("fight");
        }
    }

}
