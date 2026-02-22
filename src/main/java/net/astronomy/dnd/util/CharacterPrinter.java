package net.astronomy.dnd.util;

import net.astronomy.dnd.model.Character;
import net.astronomy.dnd.model.attributes.SavingThrow;
import net.astronomy.dnd.model.enums.attributes.Language;

public class CharacterPrinter {

    /* Box drawing characters */
    private static final String H  = "═";
    private static final String V  = "║";
    private static final String TL = "╔";
    private static final String TR = "╗";
    private static final String BL = "╚";
    private static final String BR = "╝";
    private static final String ML = "╠";
    private static final String MR = "╣";
    private static final String LT = "╟";
    private static final String RT = "╢";
    private static final String SH = "─";

    private static final int WIDTH = 60;

    public static void print(Character character) {

        /* --- Header --- */
        printTop();
        printCentered("D&D 5E CHARACTER SHEET");
        printDividerThick();

        /* --- Personal Traits --- */
        printSectionHeader("PERSONAL TRAITS");
        printDividerThin();
        printRow("Name",       character.getName());
        printRow("Race",       character.getRace().getDisplayName());
        printRow("Class",      character.getCharacterClass().getDisplayName());
        printRow("Background", character.getBackground().getDisplayName());
        printRow("Alignment",  character.getAlignment().getDisplayName());
        printRow("Level",      String.valueOf(character.getLevel().getLevel()));
        printRow("Experience", character.getLevel().getExperiencePoints()
                + " / " + character.getLevel().getXPRequiredForLevel(
                Math.min(character.getLevel().getLevel() + 1, 20)) + " XP");
        printRow("Proficiency Bonus", "+" + character.getLevel()
                .getProficiencyBonus(character.getLevel().getLevel()));

        //* --- Life --- */
        printDividerThick();
        printSectionHeader("LIFE");
        printDividerThin();
        printRow("HP",           character.getLife().getCurrentLifePoints()
                + " / " + character.getLife().getMaxLifePoints());
        printRow("Hit Dice",     character.getLife().getHitDice().toString());
        printRow("Armor Class",  String.valueOf(character.getLife().getArmorClass()));
        printRow("Initiative",   formatModifier(character.getLife().getInitiative()));
        printRow("Speed",        character.getLife().getSpeed() + " m");

        /* --- Abilities & Modifiers --- */
        printDividerThick();
        printSectionHeader("ABILITIES & MODIFIERS");
        printDividerThin();
        printAbilityRow("Strength",     character.getAbilities().getStrength(),     character.getModifiers().getStrength());
        printAbilityRow("Dexterity",    character.getAbilities().getDexterity(),     character.getModifiers().getDexterity());
        printAbilityRow("Constitution", character.getAbilities().getConstitution(),  character.getModifiers().getConstitution());
        printAbilityRow("Intelligence", character.getAbilities().getIntelligence(),  character.getModifiers().getIntelligence());
        printAbilityRow("Wisdom",       character.getAbilities().getWisdom(),        character.getModifiers().getWisdom());
        printAbilityRow("Charisma",     character.getAbilities().getCharisma(),      character.getModifiers().getCharisma());

        /* --- Saving Throws --- */
        printDividerThick();
        printSectionHeader("SAVING THROWS");
        printDividerThin();
        for (SavingThrow.SavingThrows st : SavingThrow.SavingThrows.values()) {
            int value = character.getModifiers().getModifierValues()[st.ordinal()];
            boolean proficient = character.getCharacterClass()
                    .getProficiencyModifiers()
                    .stream()
                    .anyMatch(m -> m.getDisplayName().equals(st.getDisplayName()));
            printRow((proficient ? "★ " : "  ") + st.getDisplayName(), formatModifier(value));
        }

        /* --- Skills --- */
        printDividerThick();
        printSectionHeader("SKILLS");
        printDividerThin();
        printRow("Acrobatics      (DEX)", formatModifier(character.getSkills().getAcrobatics()));
        printRow("Animal Handling (WIS)", formatModifier(character.getSkills().getAnimal_handling()));
        printRow("Arcana          (INT)", formatModifier(character.getSkills().getArcana()));
        printRow("Athletics       (STR)", formatModifier(character.getSkills().getAthletics()));
        printRow("Deception       (CHA)", formatModifier(character.getSkills().getDeception()));
        printRow("History         (INT)", formatModifier(character.getSkills().getHistory()));
        printRow("Insight         (WIS)", formatModifier(character.getSkills().getInsight()));
        printRow("Intimidation    (CHA)", formatModifier(character.getSkills().getIntimidation()));
        printRow("Investigation   (INT)", formatModifier(character.getSkills().getInvestigation()));
        printRow("Medicine        (WIS)", formatModifier(character.getSkills().getMedicine()));
        printRow("Nature          (INT)", formatModifier(character.getSkills().getNature()));
        printRow("Perception      (WIS)", formatModifier(character.getSkills().getPerception()));
        printRow("Performance     (CHA)", formatModifier(character.getSkills().getPerformance()));
        printRow("Persuasion      (CHA)", formatModifier(character.getSkills().getPersuasion()));
        printRow("Religion        (INT)", formatModifier(character.getSkills().getReligion()));
        printRow("Sleight of Hand (DEX)", formatModifier(character.getSkills().getSleightOfHand()));
        printRow("Stealth         (DEX)", formatModifier(character.getSkills().getStealth()));
        printRow("Survival        (WIS)", formatModifier(character.getSkills().getSurvival()));

        /* --- Languages --- */
        printDividerThick();
        printSectionHeader("LANGUAGES");
        printDividerThin();
        StringBuilder langs = new StringBuilder();
        for (Language lang : character.getRace().getRaceLanguages()) {
            if (!langs.isEmpty()) langs.append(", ");
            langs.append(lang.getDisplayName());
        }
        printCentered(langs.toString());

        /* --- Inventory --- */
        printDividerThick();
        printSectionHeader("INVENTORY");

        printInventorySection("Armors",         character.getInventory().getArmors().isEmpty(),
                () -> character.getInventory().getArmors().forEach((item, qty) ->
                        printRow("  " + item.getDisplayName(), "x" + qty)));

        printInventorySection("Weapons",        character.getInventory().getWeapons().isEmpty(),
                () -> character.getInventory().getWeapons().forEach((item, qty) ->
                        printRow("  " + item.getDisplayName(), "x" + qty)));

        printInventorySection("Adventure Gear", character.getInventory().getAdventureGear().isEmpty(),
                () -> character.getInventory().getAdventureGear().forEach((item, qty) ->
                        printRow("  " + item.getDisplayName(), "x" + qty)));

        printInventorySection("Instruments",    character.getInventory().getInstruments().isEmpty(),
                () -> character.getInventory().getInstruments().forEach((item, qty) ->
                        printRow("  " + item.getDisplayName(), "x" + qty)));

        printInventorySection("Miscellaneous",  character.getInventory().getMiscellaneous().isEmpty(),
                () -> character.getInventory().getMiscellaneous().forEach((item, qty) ->
                        printRow("  " + item.getDisplayName(), "x" + qty)));

        /* --- Currency --- */
        printDividerThick();
        printSectionHeader("CURRENCY");
        printDividerThin();
        character.getCurrency().getAllCoins().forEach((coin, qty) ->
                printRow(coin.getDisplayName() + " (" + coin.getAbbreviation() + ")", String.valueOf(qty)));

        printBottom();
        System.out.println();
    }

    /* --- Rendering Helpers --- */

    private static void printTop() {
        System.out.println(TL + H.repeat(WIDTH - 2) + TR);
    }

    private static void printBottom() {
        System.out.println(BL + H.repeat(WIDTH - 2) + BR);
    }

    private static void printDividerThick() {
        System.out.println(ML + H.repeat(WIDTH - 2) + MR);
    }

    private static void printDividerThin() {
        System.out.println(LT + SH.repeat(WIDTH - 2) + RT);
    }

    private static void printCentered(String text) {
        int innerWidth = WIDTH - 2;
        int padding    = Math.max(0, (innerWidth - text.length()) / 2);
        int rightPad   = innerWidth - text.length() - padding;
        System.out.println(V + " ".repeat(padding) + text + " ".repeat(rightPad) + V);
    }

    private static void printSectionHeader(String title) {
        printCentered(title);
    }

    /** Left label, right value, padded to fill the box width. */
    private static void printRow(String label, String value) {
        int innerWidth = WIDTH - 4; // 2 borders + 2 spaces
        String entry   = label + ": " + value;
        int pad        = Math.max(0, innerWidth - entry.length());
        System.out.println(V + " " + entry + " ".repeat(pad) + " " + V);
    }

    /** Ability score row: "Strength: 18    Modifier: +4" */
    private static void printAbilityRow(String name, int score, int modifier) {
        String left  = String.format("%-14s %2d", name + ":", score);
        String right = "Modifier: " + formatModifier(modifier);
        int innerWidth = WIDTH - 4;
        int gap = Math.max(1, innerWidth - left.length() - right.length());
        String line = left + " ".repeat(gap) + right;
        // Trim if somehow too long
        if (line.length() > innerWidth) line = line.substring(0, innerWidth);
        int pad = Math.max(0, innerWidth - line.length());
        System.out.println(V + " " + line + " ".repeat(pad) + " " + V);
    }

    private static void printInventorySection(String title, boolean empty, Runnable printer) {
        printDividerThin();
        printCentered(title);
        if (empty) {
            printRow("  (none)", "");
        } else {
            printer.run();
        }
    }

    private static String formatModifier(int mod) {
        return (mod >= 0 ? "+" : "") + mod;
    }
}