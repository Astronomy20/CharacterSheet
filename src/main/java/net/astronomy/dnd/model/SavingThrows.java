package net.astronomy.dnd.model;

public class SavingThrows {
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    public enum SavingThrow {
        STRENGTH("Strength"),
        DEXTERITY("Dexterity"),
        CONSTITUTION("Constitution"),
        INTELLIGENCE("Intelligence"),
        WISDOM("Wisdom"),
        CHARISMA("Charisma");

        private final String displayName;

        SavingThrow(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }

    }

    public SavingThrows(Abilities abilities) {
        this.strength = Abilities.getModifier(abilities.getStrength());
        this.dexterity = Abilities.getModifier(abilities.getDexterity());
        this.constitution = Abilities.getModifier(abilities.getConstitution());
        this.intelligence = Abilities.getModifier(abilities.getIntelligence());
        this.wisdom = Abilities.getModifier(abilities.getWisdom());
        this.charisma = Abilities.getModifier(abilities.getCharisma());
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public int getCharisma() {
        return charisma;
    }
}