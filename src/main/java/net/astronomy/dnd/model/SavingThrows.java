package net.astronomy.dnd.model;

/**
 * Character's saving throws.
 * Each saving throw value is the corresponding ability modifier value.
 */
public class SavingThrows {
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

    /**
     * Enum representing the six types of saving throws.
     * Each enum value has a display name for readability.
     */
    public enum SavingThrow {
        STRENGTH("Strength"),
        DEXTERITY("Dexterity"),
        CONSTITUTION("Constitution"),
        INTELLIGENCE("Intelligence"),
        WISDOM("Wisdom"),
        CHARISMA("Charisma");

        /** Human-readable name of the saving throw */
        private final String displayName;

        /**
         * Constructor for the SavingThrow enum.
         *
         * @param displayName the human-readable name of the saving throw
         */
        SavingThrow(String displayName) {
            this.displayName = displayName;
        }

        /**
         * Returns the display name of the saving throw.
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

    /**
     * Constructs a SavingThrows object from an Abilities object.
     * Each saving throw is calculated using the corresponding ability modifier.
     *
     * @param abilities the Abilities object containing ability scores
     */
    public SavingThrows(Abilities abilities) {
        this.strength = Abilities.getModifier(abilities.getStrength());
        this.dexterity = Abilities.getModifier(abilities.getDexterity());
        this.constitution = Abilities.getModifier(abilities.getConstitution());
        this.intelligence = Abilities.getModifier(abilities.getIntelligence());
        this.wisdom = Abilities.getModifier(abilities.getWisdom());
        this.charisma = Abilities.getModifier(abilities.getCharisma());
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