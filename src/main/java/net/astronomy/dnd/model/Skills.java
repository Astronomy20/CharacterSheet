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
     * @param abilities the Abilities object containing ability scores
     */
    public Skills(Abilities abilities) {
        this.acrobatics = Abilities.getModifier(abilities.getDexterity());
        this.animal_handling = Abilities.getModifier(abilities.getWisdom());
        this.arcana = Abilities.getModifier(abilities.getIntelligence());
        this.athletics = Abilities.getModifier(abilities.getStrength());
        this.deception = Abilities.getModifier(abilities.getCharisma());
        this.history = Abilities.getModifier(abilities.getIntelligence());
        this.insight = Abilities.getModifier(abilities.getWisdom());
        this.intimidation = Abilities.getModifier(abilities.getCharisma());
        this.investigation = Abilities.getModifier(abilities.getIntelligence());
        this.medicine = Abilities.getModifier(abilities.getWisdom());
        this.nature = Abilities.getModifier(abilities.getIntelligence());
        this.perception = Abilities.getModifier(abilities.getWisdom());
        this.performance = Abilities.getModifier(abilities.getCharisma());
        this.persuasion = Abilities.getModifier(abilities.getCharisma());
        this.religion = Abilities.getModifier(abilities.getIntelligence());
        this.sleight_of_hand = Abilities.getModifier(abilities.getDexterity());
        this.stealth = Abilities.getModifier(abilities.getDexterity());
        this.survival = Abilities.getModifier(abilities.getWisdom());
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