package com.seventh_root.harmonicmoon.character;

import com.seventh_root.harmonicmoon.fight.party.CharacterFightParty;
import com.seventh_root.harmonicmoon.inventory.PartyInventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Party {

    private List<Character> members;
    private PartyInventory inventory;

    public Party(Character... members) {
        this.members = Arrays.asList(members);
        this.inventory = new PartyInventory();
    }

    public void addMember(Character character) {
        members.add(character);
    }

    public void removeMember(Character character) {
        members.remove(character);
    }

    public List<? extends Character> getMembers() {
        return members;
    }

    public PartyInventory getInventory() {
        return inventory;
    }

    public CharacterFightParty asFightParty() {
        List<Character.Fight> fightCharacters = new ArrayList<>();
        for (Character character : getMembers()) {
            fightCharacters.add(character.fight());
        }
        return new CharacterFightParty(fightCharacters.toArray(new Character.Fight[fightCharacters.size()]));
    }

}
