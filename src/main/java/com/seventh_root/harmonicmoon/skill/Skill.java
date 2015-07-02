package com.seventh_root.harmonicmoon.skill;

import com.seventh_root.harmonicmoon.fight.Combatant;
import com.seventh_root.harmonicmoon.fight.Fight;
import com.seventh_root.harmonicmoon.fight.TurnAction;
import com.seventh_root.harmonicmoon.inventory.item.weapon.Weapon;

public interface Skill {

    public String getName();

    public TurnAction doSkill(Fight fight, Combatant attacking, Combatant defending, Weapon weapon);

    public boolean canUse(Combatant combatant);

}
