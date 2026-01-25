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
        System.out.println("Strength: " + character.getAbilities().getStrength());
        System.out.println("Dexterity: " + character.getAbilities().getDexterity());
        System.out.println("Constitution: " + character.getAbilities().getConstitution());
        System.out.println("Intelligence: " + character.getAbilities().getIntelligence());
        System.out.println("Wisdom: " + character.getAbilities().getWisdom());
        System.out.println("Charisma: " + character.getAbilities().getCharisma());
        System.out.println();

        System.out.println("--- Saving Throws ---");
        System.out.println("Strength: " + character.getSavingThrows().getStrength());
        System.out.println("Dexterity: " + character.getSavingThrows().getDexterity());
        System.out.println("Constitution: " + character.getSavingThrows().getConstitution());
        System.out.println("Intelligence: " + character.getSavingThrows().getIntelligence());
        System.out.println("Wisdom: " + character.getSavingThrows().getWisdom());
        System.out.println("Charisma: " + character.getSavingThrows().getCharisma());
        System.out.println();

        System.out.println("--- Skills ---");
        System.out.println("Acrobatics: " + character.getSkills().getAcrobatics());
        System.out.println("Animal Handling: " + character.getSkills().getAnimal_handling());
        System.out.println("Arcana: " + character.getSkills().getArcana());
        System.out.println("Athletics: " + character.getSkills().getAthletics());
        System.out.println("Deception: " + character.getSkills().getDeception());
        System.out.println("History: " + character.getSkills().getHistory());
        System.out.println("Insight: " + character.getSkills().getInsight());
        System.out.println("Intimidation: " + character.getSkills().getIntimidation());
        System.out.println("Investigation: " + character.getSkills().getInvestigation());
        System.out.println("Medicine: " + character.getSkills().getMedicine());
        System.out.println("Nature: " + character.getSkills().getNature());
        System.out.println("Perception: " + character.getSkills().getPerception());
        System.out.println("Performance: " + character.getSkills().getPerformance());
        System.out.println("Persuasion: " + character.getSkills().getPersuasion());
        System.out.println("Religion: " + character.getSkills().getReligion());
        System.out.println("Sleight of Hand: " + character.getSkills().getSleight_of_hand());
        System.out.println("Stealth: " + character.getSkills().getStealth());
        System.out.println("Survival: " + character.getSkills().getSurvival());
    }
}
