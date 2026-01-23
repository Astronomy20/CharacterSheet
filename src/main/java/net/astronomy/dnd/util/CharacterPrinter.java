package net.astronomy.dnd.util;

import net.astronomy.dnd.model.Character;

public class CharacterPrinter {

    public static void print(Character character) {
        System.out.println("=== DnD Character Sheet ===");
        System.out.println("--- Personal Traits ---");
        System.out.println("Name: " + character.getName());
        System.out.println("Race: " + character.getRace());
        System.out.println("Class: " + character.getCharacterClass());
        System.out.println("Level: " + character.getLevel());
        System.out.println();
        System.out.println("--- Abilities ---");
        System.out.println("Strength: " + character.getAbilityScores().getStrength());
        System.out.println("Dexterity: " + character.getAbilityScores().getDexterity());
        System.out.println("Constitution: " + character.getAbilityScores().getConstitution());
        System.out.println("Intelligence: " + character.getAbilityScores().getIntelligence());
        System.out.println("Wisdom: " + character.getAbilityScores().getWisdom());
        System.out.println("Charisma: " + character.getAbilityScores().getCharisma());
    }
}
