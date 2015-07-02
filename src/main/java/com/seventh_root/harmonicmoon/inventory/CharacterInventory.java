package com.seventh_root.harmonicmoon.inventory;

import com.seventh_root.harmonicmoon.inventory.item.armour.feet.FeetArmour;
import com.seventh_root.harmonicmoon.inventory.item.armour.legs.LegsArmour;
import com.seventh_root.harmonicmoon.inventory.item.armour.torso.TorsoArmour;
import com.seventh_root.harmonicmoon.inventory.item.weapon.Weapon;
import com.seventh_root.harmonicmoon.inventory.item.armour.head.HeadArmour;

public class CharacterInventory {

    // Weapons
    private Weapon weapon;
    private Weapon offHandWeapon;

    // Armour, clothes, or whatever
    private HeadArmour headArmour;
    private TorsoArmour torsoArmour;
    private LegsArmour legsArmour;
    private FeetArmour feetArmour;

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Weapon getOffHandWeapon() {
        return offHandWeapon;
    }

    public void setOffHandWeapon(Weapon offHandWeapon) {
        this.offHandWeapon = offHandWeapon;
    }

    public HeadArmour getHeadArmour() {
        return headArmour;
    }

    public void setHeadArmour(HeadArmour headArmour) {
        this.headArmour = headArmour;
    }

    public TorsoArmour getTorsoArmour() {
        return torsoArmour;
    }

    public void setTorsoArmour(TorsoArmour torsoArmour) {
        this.torsoArmour = torsoArmour;
    }

    public LegsArmour getLegsArmour() {
        return legsArmour;
    }

    public void setLegsArmour(LegsArmour legsArmour) {
        this.legsArmour = legsArmour;
    }

    public FeetArmour getFeetArmour() {
        return feetArmour;
    }

    public void setFeetArmour(FeetArmour feetArmour) {
        this.feetArmour = feetArmour;
    }
}
