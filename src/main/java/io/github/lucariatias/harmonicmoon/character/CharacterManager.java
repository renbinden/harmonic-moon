package io.github.lucariatias.harmonicmoon.character;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.currency.Currency;

import java.util.*;

public class CharacterManager {

    private Set<Party> parties = new HashSet<>();

    private Map<String, Character> characters;

    public CharacterManager(HarmonicMoon harmonicMoon) {
        characters = new HashMap<>();
        //addCharacter(new Anaria(harmonicMoon));
        //addCharacter(new Idain(harmonicMoon));
        //addCharacter(new Kesowa(harmonicMoon));
        addCharacter(new Lonyre(harmonicMoon));
        //addCharacter(new Namapo(harmonicMoon));
        //addCharacter(new Seuri(harmonicMoon));
        //addCharacter(new Syalae(harmonicMoon));
        //addCharacter(new Tivor(harmonicMoon));
    }

    public void addCharacter(Character character) {
        characters.put(character.getName().toLowerCase(), character);
    }

    public void removeCharacter(Character character) {
        characters.remove(character.getName().toLowerCase());
    }

    public Character getCharacter(String name) {
        return characters.get(name.toLowerCase());
    }

    public Party getParty(Character character) {
        for (Party party : parties) {
            if (party.getMembers().contains(character)) return party;
        }
        Party party = new Party(character);
        parties.add(party);
        return party;
    }

    public void joinParties(Party party1, Party party2) {
        parties.remove(party1);
        parties.remove(party2);
        List<Character> members = new ArrayList<>();
        members.addAll(party1.getMembers());
        members.addAll(party2.getMembers());
        Party joinedParty = new Party(members.toArray(new Character[members.size()]));
        joinedParty.getInventory().addItems(party1.getInventory().getItems());
        joinedParty.getInventory().addItems(party2.getInventory().getItems());
        for (Currency currency : Currency.values()) {
            joinedParty.getInventory().setMoney(currency, party1.getInventory().getMoney(currency) + party2.getInventory().getMoney(currency));
        }
        parties.add(joinedParty);
    }

}
