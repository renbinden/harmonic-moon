package io.github.lucariatias.harmonicmoon.skill;

import io.github.lucariatias.harmonicmoon.fight.Combatant;
import io.github.lucariatias.harmonicmoon.fight.Fight;
import io.github.lucariatias.harmonicmoon.inventory.item.weapon.Weapon;

public interface Skill {

    public String getName();

    public boolean doSkill(Fight fight, Combatant attacking, Combatant defending, Weapon weapon);

    public boolean canUse(Combatant combatant);

}
