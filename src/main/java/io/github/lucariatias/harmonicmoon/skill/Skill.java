package io.github.lucariatias.harmonicmoon.skill;

import io.github.lucariatias.harmonicmoon.character.Character;
import io.github.lucariatias.harmonicmoon.fight.Fight;
import io.github.lucariatias.harmonicmoon.item.Weapon;

public interface Skill {

    public boolean doSkill(Fight fight, Character attacking, Character defending, Weapon weapon);

}
