package net.astronomy.dnd.util;

import net.astronomy.dnd.model.enums.attributes.Language;
import net.astronomy.dnd.model.Character;
import net.astronomy.dnd.model.SavingThrow;

public class CharacterPrinter {

    public static void print(Character character) {
        System.out.println("=== DnD Character Sheet ===");

        System.out.println("--- Personal Traits ---");
        System.out.println("Name: " + character.getName());
        System.out.println("Race: " + character.getRace());
        System.out.println("Class: " + character.getCharacterClass());
        System.out.println("Background: " + character.getBackground());
        System.out.println("Alignment: " + character.getAlignment());
        System.out.println("Level: " + character.getLevel());
        System.out.println("Experience Points: " + character.getExperiencePoints());
        System.out.println();

        System.out.println("--- Life ---");
        System.out.println("Life points: " + character.getLife().getHitDice());
        System.out.println("Armor class: " + character.getLife().getArmorClass());
        System.out.println("Initiative : " + character.getLife().getInitiative());
        System.out.println("Speed : " + character.getLife().getSpeed());
        System.out.println();

        System.out.println("--- Abilities ---");
        System.out.println("Strength: " + character.getAbilities().getStrength());
        System.out.println("Dexterity: " + character.getAbilities().getDexterity());
        System.out.println("Constitution: " + character.getAbilities().getConstitution());
        System.out.println("Intelligence: " + character.getAbilities().getIntelligence());
        System.out.println("Wisdom: " + character.getAbilities().getWisdom());
        System.out.println("Charisma: " + character.getAbilities().getCharisma());
        System.out.println();

        System.out.println("--- Modifiers ---");
        System.out.println("Strength: " + character.getModifiers().getStrength());
        System.out.println("Dexterity: " + character.getModifiers().getDexterity());
        System.out.println("Constitution: " + character.getModifiers().getConstitution());
        System.out.println("Intelligence: " + character.getModifiers().getIntelligence());
        System.out.println("Wisdom: " + character.getModifiers().getWisdom());
        System.out.println("Charisma: " + character.getModifiers().getCharisma());
        System.out.println();

        System.out.println("--- Saving Throws ---");
        for (SavingThrow.SavingThrows savingThrow : SavingThrow.SavingThrows.values()) {
            System.out.println(savingThrow + ": " + character.getModifiers().getModifierValues()[savingThrow.ordinal()]);
        }
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
        System.out.println("Sleight of Hand: " + character.getSkills().getSleightOfHand());
        System.out.println("Stealth: " + character.getSkills().getStealth());
        System.out.println("Survival: " + character.getSkills().getSurvival());
        System.out.println();

        System.out.println("--- Languages ---");
        for (Language language : character.getRace().getRaceLanguages()) {
            System.out.println(language);
        }
        System.out.println();

        System.out.println("--- Inventory ---");
        System.out.println("Armors:");
        character.getInventory().getArmors().forEach((item, quantity) ->
                System.out.println("  " + item + " x" + quantity)
        );
        System.out.println("Weapons:");
        character.getInventory().getWeapons().forEach((item, quantity) ->
                System.out.println("  " + item + " x" + quantity)
        );
        System.out.println("Adventure Gear:");
        character.getInventory().getAdventureGear().forEach((item, quantity) ->
                System.out.println("  " + item + " x" + quantity)
        );
        System.out.println("Instruments:");
        character.getInventory().getInstruments().forEach((item, quantity) ->
                System.out.println("  " + item + " x" + quantity)
        );
        System.out.println();

        System.out.println("--- Currency ---");
        character.getCurrency().getAllCoins().forEach((coin, quantity) ->
                System.out.println("  " + coin.getDisplayName() + ": " + quantity)
        );
    }
}
