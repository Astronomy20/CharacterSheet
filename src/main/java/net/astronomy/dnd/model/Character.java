package net.astronomy.dnd.model;

import net.astronomy.dnd.model.attributes.*;
import net.astronomy.dnd.model.enums.attributes.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Character class with all its core attributes.
 */
public class Character {
    /** The name of the character. */
    private String name;

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
    public Character(String name, Level level, Race race, CharacterClass characterClass, Background background,
                     Alignment alignment, Ability abilities) {
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
        this.inventory = new Inventory();
        this.currency = new Currency();
        this.languages = race.getRaceLanguages();
        this.life = new Life(this.level, this.race, this.characterClass, this.modifiers, this.inventory);
    }

    /**
     * Restores transient fields after deserialization.
     * Must be called by Session.loadCharacter() before any gameplay operations.
     *
     * @param characterClass the character's class
     * @param modifiers      the character's computed modifiers
     * @param inventory      the character's inventory
     */
    public void restoreTransients(String name, Level level, Race race, CharacterClass characterClass, Background background,
                                  Alignment alignment, Ability abilities, Modifier modifiers, SavingThrow savingThrows,
                                  Skills skills, Inventory inventory, Currency currency, Set<Language> languages, Life life) {
        this.name = name;
        this.level = level;
        this.race = race;
        this.characterClass = characterClass;
        this.background = background;
        this.alignment = alignment;
        this.abilities = abilities;
        this.modifiers = modifiers;
        this.savingThrows = savingThrows;
        this.skills = skills;
        this.inventory = inventory;
        this.currency = currency;
        this.languages = languages;
        this.life = life;
    }

    public void setRace(Race newRace) {
        if (newRace == null) {
            throw new IllegalArgumentException("Race cannot be null.");
        }

        if (this.race != null) {
            this.abilities = new Ability(
                    this.abilities.getStrength() - this.race.getBonus(Ability.Abilities.STRENGTH),
                    this.abilities.getDexterity() - this.race.getBonus(Ability.Abilities.DEXTERITY),
                    this.abilities.getConstitution() - this.race.getBonus(Ability.Abilities.CONSTITUTION),
                    this.abilities.getIntelligence() - this.race.getBonus(Ability.Abilities.INTELLIGENCE),
                    this.abilities.getWisdom() - this.race.getBonus(Ability.Abilities.WISDOM),
                    this.abilities.getCharisma() - this.race.getBonus(Ability.Abilities.CHARISMA)
            );

            int oldConBonus = this.race.getBonus(Ability.Abilities.CONSTITUTION);
            if (this.life != null) {
                int totalLevels = this.life.getHpPerLevel().size() + 1;
                this.life.setMaxLifePoints(this.life.getMaxLifePoints() - (oldConBonus * totalLevels));
                this.life.setCurrentLifePoints(Math.min(this.life.getCurrentLifePoints(), this.life.getMaxLifePoints()));
            }
        }

        this.race = newRace;

        this.abilities = new Ability(
                this.abilities.getStrength() + newRace.getBonus(Ability.Abilities.STRENGTH),
                this.abilities.getDexterity() + newRace.getBonus(Ability.Abilities.DEXTERITY),
                this.abilities.getConstitution() + newRace.getBonus(Ability.Abilities.CONSTITUTION),
                this.abilities.getIntelligence() + newRace.getBonus(Ability.Abilities.INTELLIGENCE),
                this.abilities.getWisdom() + newRace.getBonus(Ability.Abilities.WISDOM),
                this.abilities.getCharisma() + newRace.getBonus(Ability.Abilities.CHARISMA)
        );

        this.life.updateSpeed(newRace.getSpeed());

        this.languages = new HashSet<>(newRace.getRaceLanguages());
        this.languages.clear();
        this.languages.addAll(newRace.getRaceLanguages());
    }


    public void setCharacterClass(CharacterClass characterClass) {
        this.characterClass = characterClass;

        this.modifiers = new Modifier(this.level, this.abilities, this.characterClass);
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public void setBackground(Background background) {
        this.background = background;
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