package net.astronomy.dnd.model;

/**
 * Character's skills.
 * Each skill's value is determined by the corresponding ability modifier.
 */
public class Skills {
    /** Acrobatics skill (Dexterity-based) */
    private int acrobatics;
    /** Animal Handling skill (Wisdom-based) */
    private int animal_handling;
    /** Arcana skill (Intelligence-based) */
    private int arcana;
    /** Athletics skill (Strength-based) */
    private int athletics;
    /** Deception skill (Charisma-based) */
    private int deception;
    /** History skill (Intelligence-based) */
    private int history;
    /** Insight skill (Wisdom-based) */
    private int insight;
    /** Intimidation skill (Charisma-based) */
    private int intimidation;
    /** Investigation skill (Intelligence-based) */
    private int investigation;
    /** Medicine skill (Wisdom-based) */
    private int medicine;
    /** Nature skill (Intelligence-based) */
    private int nature;
    /** Perception skill (Wisdom-based) */
    private int perception;
    /** Performance skill (Charisma-based) */
    private int performance;
    /** Persuasion skill (Charisma-based) */
    private int persuasion;
    /** Religion skill (Intelligence-based) */
    private int religion;
    /** Sleight of Hand skill (Dexterity-based) */
    private int sleight_of_hand;
    /** Stealth skill (Dexterity-based) */
    private int stealth;
    /** Survival skill (Wisdom-based) */
    private int survival;

    /**
     * Constructs a Skills object, calculating each skill's value
     * from the corresponding ability modifier.
     *
     * @param modifier the Abilities object containing ability scores
     */
    public Skills(Modifier modifier) {
        this.acrobatics = modifier.getDexterity();
        this.animal_handling = modifier.getWisdom();
        this.arcana = modifier.getIntelligence();
        this.athletics = modifier.getStrength();
        this.deception = modifier.getCharisma();
        this.history = modifier.getIntelligence();
        this.insight = modifier.getWisdom();
        this.intimidation = modifier.getCharisma();
        this.investigation = modifier.getIntelligence();
        this.medicine = modifier.getWisdom();
        this.nature = modifier.getIntelligence();
        this.perception = modifier.getWisdom();
        this.performance = modifier.getCharisma();
        this.persuasion = modifier.getCharisma();
        this.religion = modifier.getIntelligence();
        this.sleight_of_hand = modifier.getDexterity();
        this.stealth = modifier.getDexterity();
        this.survival = modifier.getWisdom();
    }

    /** @return the Acrobatics skill value */
    public int getAcrobatics() {
        return acrobatics;
    }

    /** @return the Animal Handling skill value */
    public int getAnimal_handling() {
        return animal_handling;
    }

    /** @return the Arcana skill value */
    public int getArcana() {
        return arcana;
    }

    /** @return the Athletics skill value */
    public int getAthletics() {
        return athletics;
    }

    /** @return the Deception skill value */
    public int getDeception() {
        return deception;
    }

    /** @return the History skill value */
    public int getHistory() {
        return history;
    }

    /** @return the Insight skill value */
    public int getInsight() {
        return insight;
    }

    /** @return the Intimidation skill value */
    public int getIntimidation() {
        return intimidation;
    }

    /** @return the Investigation skill value */
    public int getInvestigation() {
        return investigation;
    }

    /** @return the Medicine skill value */
    public int getMedicine() {
        return medicine;
    }

    /** @return the Nature skill value */
    public int getNature() {
        return nature;
    }

    /** @return the Perception skill value */
    public int getPerception() {
        return perception;
    }

    /** @return the Performance skill value */
    public int getPerformance() {
        return performance;
    }

    /** @return the Persuasion skill value */
    public int getPersuasion() {
        return persuasion;
    }

    /** @return the Religion skill value */
    public int getReligion() {
        return religion;
    }

    /** @return the Sleight of Hand skill value */
    public int getSleightOfHand() {
        return sleight_of_hand;
    }

    /** @return the Stealth skill value */
    public int getStealth() {
        return stealth;
    }

    /** @return the Survival skill value */
    public int getSurvival() {
        return survival;
    }
}