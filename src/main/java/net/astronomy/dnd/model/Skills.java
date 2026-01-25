package net.astronomy.dnd.model;

public class Skills {
    private int acrobatics;
    private int animal_handling;
    private int arcana;
    private int athletics;
    private int deception;
    private int history;
    private int insight;
    private int intimidation;
    private int investigation;
    private int medicine;
    private int nature;
    private int perception;
    private int performance;
    private int persuasion;
    private int religion;
    private int sleight_of_hand;
    private int stealth;
    private int survival;

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

    public int getAcrobatics() {
        return acrobatics;
    }

    public int getAnimal_handling() {
        return animal_handling;
    }

    public int getArcana() {
        return arcana;
    }

    public int getAthletics() {
        return athletics;
    }

    public int getDeception() {
        return deception;
    }

    public int getHistory() {
        return history;
    }

    public int getInsight() {
        return insight;
    }

    public int getIntimidation() {
        return intimidation;
    }

    public int getInvestigation() {
        return investigation;
    }

    public int getMedicine() {
        return medicine;
    }

    public int getNature() {
        return nature;
    }

    public int getPerception() {
        return perception;
    }

    public int getPerformance() {
        return performance;
    }

    public int getPersuasion() {
        return persuasion;
    }

    public int getReligion() {
        return religion;
    }

    public int getSleight_of_hand() {
        return sleight_of_hand;
    }

    public int getStealth() {
        return stealth;
    }

    public int getSurvival() {
        return survival;
    }

}