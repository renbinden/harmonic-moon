package io.github.lucariatias.harmonicmoon.fight;

import io.github.lucariatias.harmonicmoon.character.FightCharacter;
import io.github.lucariatias.harmonicmoon.enemy.Enemy;
import io.github.lucariatias.harmonicmoon.party.CharacterParty;
import io.github.lucariatias.harmonicmoon.party.EnemyParty;

public class Fight {

    private FightArea area;
    private CharacterParty characterParty;
    private EnemyParty enemyParty;

    public Fight(FightArea area, CharacterParty characterParty, EnemyParty enemyParty) {
        this.area = area;
        this.characterParty = characterParty;
        this.enemyParty = enemyParty;
    }

    public FightArea getArea() {
        return area;
    }

    public void setArea(FightArea area) {
        this.area = area;
    }

    public CharacterParty getCharacterParty() {
        return characterParty;
    }

    public void setCharacterParty(CharacterParty characterParty) {
        this.characterParty = characterParty;
    }

    public EnemyParty getEnemyParty() {
        return enemyParty;
    }

    public void setEnemyParty(EnemyParty enemyParty) {
        this.enemyParty = enemyParty;
    }

    public void onTick() {
        for (FightCharacter character : characterParty.getMembers()) {
            character.onTick();
        }
        for (Enemy enemy : enemyParty.getMembers()) {
            enemy.onTick();
        }
    }

}
