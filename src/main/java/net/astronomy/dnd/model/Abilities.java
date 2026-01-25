package net.astronomy.dnd.model;

import net.astronomy.dnd.enums.attributes.Race;

public class Abilities {
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    public enum Ability {
        STRENGTH,
        DEXTERITY,
        CONSTITUTION,
        INTELLIGENCE,
        WISDOM,
        CHARISMA
    }

    public Abilities(int strength, int dexterity, int constitution,
                     int intelligence, int wisdom, int charisma) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
    }

    public Abilities(int strength, int dexterity, int constitution,
                     int intelligence, int wisdom, int charisma, Race race) {
        this.strength = strength + race.getBonus(Ability.STRENGTH);
        this.dexterity = dexterity + race.getBonus(Ability.STRENGTH);
        this.constitution = constitution + race.getBonus(Ability.STRENGTH);
        this.intelligence = intelligence + race.getBonus(Ability.STRENGTH);
        this.wisdom = wisdom + race.getBonus(Ability.STRENGTH);
        this.charisma = charisma + race.getBonus(Ability.STRENGTH);
    }

    public static int getModifier(int ability_score) {
        return (int) Math.floor((ability_score - 10) / 2.0);
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