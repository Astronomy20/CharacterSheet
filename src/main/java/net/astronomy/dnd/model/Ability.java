package net.astronomy.dnd.model;

import net.astronomy.dnd.model.enums.attributes.Race;

/**
 * Character's ability scores: Strength, Dexterity, Constitution, Intelligence, Wisdom, and Charisma.
 */
public class Ability {
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    /**
     * Enum representing each type of ability.
     */
    public enum Abilities {
        STRENGTH,
        DEXTERITY,
        CONSTITUTION,
        INTELLIGENCE,
        WISDOM,
        CHARISMA
    }

    /**
     * Constructs an Abilities object with the specified base scores.
     *
     * @param strength The Strength score
     * @param dexterity The Dexterity score
     * @param constitution The Constitution score
     * @param intelligence The Intelligence score
     * @param wisdom The Wisdom score
     * @param charisma The Charisma score
     */
    public Ability(int strength, int dexterity, int constitution,
                   int intelligence, int wisdom, int charisma) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
    }

    /**
     * Constructs an Abilities object and applies racial bonuses to each ability.
     *
     * @param strength The base Strength score
     * @param dexterity The base Dexterity score
     * @param constitution The base Constitution score
     * @param intelligence The base Intelligence score
     * @param wisdom The base Wisdom score
     * @param charisma The base Charisma score
     * @param race The character's race, which may modify ability scores
     */
    public Ability(int strength, int dexterity, int constitution,
                   int intelligence, int wisdom, int charisma, Race race) {
        this.strength = strength + race.getBonus(Abilities.STRENGTH);
        this.dexterity = dexterity + race.getBonus(Abilities.DEXTERITY);
        this.constitution = constitution + race.getBonus(Abilities.CONSTITUTION);
        this.intelligence = intelligence + race.getBonus(Abilities.INTELLIGENCE);
        this.wisdom = wisdom + race.getBonus(Abilities.WISDOM);
        this.charisma = charisma + race.getBonus(Abilities.CHARISMA);
    }

    /** @return The Strength score. */
    public int getStrength() {
        return strength;
    }

    /** @return The Dexterity score. */
    public int getDexterity() {
        return dexterity;
    }

    /** @return The Constitution score. */
    public int getConstitution() {
        return constitution;
    }

    /** @return The Intelligence score. */
    public int getIntelligence() {
        return intelligence;
    }

    /** @return The Wisdom score. */
    public int getWisdom() {
        return wisdom;
    }

    /** @return The Charisma score. */
    public int getCharisma() {
        return charisma;
    }
}