package net.astronomy.dnd.model;

import net.astronomy.dnd.enums.attributes.*;

import java.util.Set;

/**
 * Character class with all its core attributes.
 */
public class Character {
    /** The name of the character. */
    private final String name;

    /** The current level of the character. */
    private int level;

    /** The total experience points the character has accumulated. */
    private int experiencePoints;

    /** The race of the character (e.g., Elf, Human). */
    private Race race;

    /** The class of the character (e.g., Fighter, Wizard). */
    private CharacterClass characterClass;

    /** The character's background (e.g., Soldier, Sage). */
    private Background background;

    /** The alignment of the character (e.g., Chaotic Good). */
    private Alignment alignment;

    /** The character's ability scores (Strength, Dexterity, etc.). */
    private Abilities abilities;

    /** The character's saving throw proficiencies. */
    private SavingThrows savingThrows;

    /** The character's skill proficiencies. */
    private Skills skills;

    /** Languages known by the character. */
    private Set<Language> languages;

    /** The character's inventory of items. */
    private Inventory inventory;

    /** The character's currency (gold, silver, copper, etc.). */
    private Currency currency;

    /**
     * Constructs a new Character with the specified parameters.
     *
     * @param name The character's name
     * @param level The starting level of the character
     * @param race The character's race
     * @param characterClass The character's class
     * @param background The character's background
     * @param alignment The character's alignment
     * @param abilities The base ability scores
     */
    public Character(String name, int level, Race race, CharacterClass characterClass, Background background, Alignment alignment, Abilities abilities) {
        this.name = name;
        this.level = level;
        this.race = race;
        this.characterClass = characterClass;
        this.background = background;
        this.alignment = alignment;
        this.abilities = new Abilities(abilities.getStrength(), abilities.getDexterity(), abilities.getConstitution(),
                                        abilities.getIntelligence(), abilities.getWisdom(), abilities.getCharisma(),
                                        race);
        this.savingThrows = new SavingThrows(abilities);
        this.skills = new Skills(abilities);
        this.languages = race.getRaceLanguages();
        this.inventory = new Inventory();
        this.currency = new Currency();
    }

    /** @return The character's name. */
    public String getName() {
        return name;
    }

    /** @return The character's level. */
    public int getLevel() {
        return level;
    }

    /** @return The character's total experience points. */
    public int getExperiencePoints() {
        return experiencePoints;
    }

    /** @return The character's race. */
    public Race getRace() {
        return race;
    }

    /** @return The character's class. */
    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    /** @return The character's background. */
    public Background getBackground() {
        return background;
    }

    /** @return The character's alignment. */
    public Alignment getAlignment() {
        return alignment;
    }

    /** @return The character's abilities. */
    public Abilities getAbilities() {
        return abilities;
    }

    /** @return The character's saving throws. */
    public SavingThrows getSavingThrows() {
        return savingThrows;
    }

    /** @return The character's skills. */
    public Skills getSkills() {
        return skills;
    }

    /** @return The set of languages known by the character. */
    public Set<Language> getLanguages() {
        return languages;
    }

    /** @return The character's inventory. */
    public Inventory getInventory() {
        return inventory;
    }

    /** @return The character's currency. */
    public Currency getCurrency() {
        return currency;
    }
}