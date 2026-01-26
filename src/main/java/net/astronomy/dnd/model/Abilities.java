package net.astronomy.dnd.model;

import net.astronomy.dnd.enums.attributes.Race;

/**
 * Character's ability scores: Strength, Dexterity, Constitution, Intelligence, Wisdom, and Charisma.
 */
public class Abilities {
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    /**
     * Enum representing each type of ability.
     */
    public enum Ability {
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
    public Abilities(int strength, int dexterity, int constitution,
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
    public Abilities(int strength, int dexterity, int constitution,
                     int intelligence, int wisdom, int charisma, Race race) {
        this.strength = strength + race.getBonus(Ability.STRENGTH);
        this.dexterity = dexterity + race.getBonus(Ability.DEXTERITY);
        this.constitution = constitution + race.getBonus(Ability.CONSTITUTION);
        this.intelligence = intelligence + race.getBonus(Ability.INTELLIGENCE);
        this.wisdom = wisdom + race.getBonus(Ability.WISDOM);
        this.charisma = charisma + race.getBonus(Ability.CHARISMA);
    }

    /**
     * Calculates the D&D modifier for a given ability score.
     *
     * @param ability_score The ability score to calculate the modifier for
     * @return The ability modifier as an integer
     */
    public static int getModifier(int ability_score) {
        return (int) Math.floor((ability_score - 10) / 2.0);
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