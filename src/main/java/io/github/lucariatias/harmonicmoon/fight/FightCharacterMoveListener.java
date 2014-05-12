package io.github.lucariatias.harmonicmoon.fight;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.enemy.EnemyManager;
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
            EnemyManager enemyManager = harmonicMoon.getEnemyManager();
            Fight fight = new Fight(FightArea.GRASS, new CharacterParty(event.getCharacter().fight()), new EnemyParty(new Slime(harmonicMoon, enemyManager.getSpriteSheet(Slime.class), enemyManager.getWaitSprite(Slime.class), enemyManager.getAttackSprite(Slime.class), enemyManager.getInjuredSprite(Slime.class))));
            harmonicMoon.getFightPanel().prepareFight(fight);
            harmonicMoon.getFightPanel().startFight();
            harmonicMoon.setPanel("fight");
        }
    }

}
