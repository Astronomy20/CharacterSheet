package net.astronomy.dnd.model;

import net.astronomy.dnd.enums.attributes.CharacterClass;

public class Modifier {
    /** Strength saving throw value */
    private int strength;
    /** Dexterity saving throw value */
    private int dexterity;
    /** Constitution saving throw value */
    private int constitution;
    /** Intelligence saving throw value */
    private int intelligence;
    /** Wisdom saving throw value */
    private int wisdom;
    /** Charisma saving throw value */
    private int charisma;
    
    public enum Modifiers {
        STRENGTH("Strength"),
        DEXTERITY("Dexterity"),
        CONSTITUTION("Constitution"),
        INTELLIGENCE("Intelligence"),
        WISDOM("Wisdom"),
        CHARISMA("Charisma");

        /** Human-readable name of the saving throw */
        private final String displayName;

        /**
         * Constructor for the Modifiers enum.
         *
         * @param displayName the human-readable name of the modifier
         */
        Modifiers(String displayName) {
            this.displayName = displayName;
        }

        /**
         * Returns the display name of the modifier.
         *
         * @return display name
         */
        public String getDisplayName() {
            return displayName;
        }

        /**
         * Returns the display name when converting the enum to a string.
         *
         * @return display name as string
         */
        @Override
        public String toString() {
            return displayName;
        }
    }

    Modifier(Ability abilities, CharacterClass characterClass) {
        this.strength = getModifier(abilities.getStrength()) + getProficientModifierValues(characterClass)[0];
        this.dexterity = getModifier(abilities.getDexterity()) + getProficientModifierValues(characterClass)[1];
        this.constitution = getModifier(abilities.getConstitution()) + getProficientModifierValues(characterClass)[2];
        this.intelligence = getModifier(abilities.getIntelligence()) + getProficientModifierValues(characterClass)[3];
        this.wisdom = getModifier(abilities.getWisdom()) + getProficientModifierValues(characterClass)[4];
        this.charisma = getModifier(abilities.getCharisma()) + getProficientModifierValues(characterClass)[5];
    }

    /**
     * Returns a map of **proficient saving throws and their modifier values**
     * based on a CharacterClass.
     *
     * @param characterClass the character's class, which defines proficiencies
     * @return int[] of proficient modifiers and their values
     */
    public int[] getProficientModifierValues(CharacterClass characterClass) {
        int[] values = new int[Modifiers.values().length];

        for (Modifiers modifier : Modifiers.values()) {

            int baseModifier = switch (modifier) {
                case STRENGTH -> strength;
                case DEXTERITY -> dexterity;
                case CONSTITUTION -> constitution;
                case INTELLIGENCE -> intelligence;
                case WISDOM -> wisdom;
                case CHARISMA -> charisma;
            };

            // TODO: Add dynamic proficiencyBonus based on character level
            int proficiencyBonus =
                    characterClass.getProficiencyModifiers().contains(modifier) ? 2 : 0;

            values[modifier.ordinal()] = baseModifier + proficiencyBonus;
        }

        return values;
    }

    /**
     * Calculates the modifier for a given ability score.
     *
     * @param abilityScore The ability score to calculate the modifier for
     * @return The ability modifier as an integer
     */
    public static int getModifier(int abilityScore) {
        return (int) Math.floor((abilityScore - 10) / 2.0);
    }

    public int[] getModifierValues() {
        return new int[] {
                strength,
                dexterity,
                constitution,
                intelligence,
                wisdom,
                charisma
        };
    }

    /** @return the Strength saving throw value */
    public int getStrength() {
        return strength;
    }

    /** @return the Dexterity saving throw value */
    public int getDexterity() {
        return dexterity;
    }

    /** @return the Constitution saving throw value */
    public int getConstitution() {
        return constitution;
    }

    /** @return the Intelligence saving throw value */
    public int getIntelligence() {
        return intelligence;
    }

    /** @return the Wisdom saving throw value */
    public int getWisdom() {
        return wisdom;
    }

    /** @return the Charisma saving throw value */
    public int getCharisma() {
        return charisma;
    }
}