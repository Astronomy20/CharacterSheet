package net.astronomy.dnd.model;

import net.astronomy.dnd.model.enums.attributes.*;

import java.util.Set;

/**
 * Character class with all its core attributes.
 */
public class Character {
    /** The name of the character. */
    private final String name;

    /** The current level of the character. */
    private Level level;

    /** The race of the character. */
    private Race race;

    /** The class of the character. */
    private CharacterClass characterClass;

    /** The character's background. */
    private Background background;

    /** The alignment of the character. */
    private Alignment alignment;

    /** The character's ability scores. */
    private Ability abilities;

    private Modifier modifiers;

    /** The character's saving throws. */
    private SavingThrow savingThrows;

    /** The character's skills. */
    private Skills skills;

    /** The character's life statistics. */
    private Life life;

    /** Languages known by the character. */
    private Set<Language> languages;

    /** The character's inventory of items. */
    private Inventory inventory;

    /** The character's currency. */
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
    public Character(String name, Level level, Race race, CharacterClass characterClass, Background background, Alignment alignment, Ability abilities) {
        this.name = name;
        this.level = level;
        this.race = race;
        this.characterClass = characterClass;
        this.background = background;
        this.alignment = alignment;
        this.abilities = new Ability(abilities.getStrength(), abilities.getDexterity(), abilities.getConstitution(),
                                        abilities.getIntelligence(), abilities.getWisdom(), abilities.getCharisma(),
                                        race);
        this.modifiers = new Modifier(this.level, abilities, this.characterClass);
        this.savingThrows = new SavingThrow(this.modifiers);
        this.skills = new Skills(this.modifiers);
        this.life = new Life(this.level, this.race, this.characterClass, this.modifiers);
        this.languages = race.getRaceLanguages();
        this.inventory = new Inventory();
        this.currency = new Currency();
    }

    /** @return The character's name. */
    public String getName() {
        return name;
    }

    /** @return The character's level and experience points. */
    public Level getLevel() {
        return level;
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
    public Ability getAbilities() {
        return abilities;
    }

    /** @return The character's modifiers. */
    public Modifier getModifiers() {
        return modifiers;
    }

    /** @return The character's saving throws. */
    public SavingThrow getSavingThrows() {
        return savingThrows;
    }

    /** @return The character's skills. */
    public Skills getSkills() {
        return skills;
    }

    /** @return The character's life points. */
    public Life getLife() {
        return life;
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