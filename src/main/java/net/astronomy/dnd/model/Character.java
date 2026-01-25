package net.astronomy.dnd.model;

import net.astronomy.dnd.enums.attributes.*;

import java.util.Set;

public class Character {
    private final String name;
    private int level;
    private int experiencePoints;
    private Race race;
    private CharacterClass characterClass;
    private Background background;
    private Alignment alignment;
    private Abilities abilities;
    private SavingThrows savingThrows;
    private Skills skills;
    private Set<Language> languages;

    public Character(String name, int level, Race race, CharacterClass characterClass, Background background, Alignment alignment, Abilities abilities) {
        this.name = name;
        this.level = level;
        this.race = race;
        this.characterClass = characterClass;
        this.background = background;
        this.alignment = alignment;
        this.abilities = new Abilities(abilities.getStrength(), abilities.getDexterity(), abilities.getConstitution(),
                                        abilities.getIntelligence(), abilities.getWisdom(), abilities.getCharisma());
        this.abilities.applyRaceBonuses(race);
        this.savingThrows = new SavingThrows(abilities);
        this.skills = new Skills(abilities);
        this.languages = race.getLanguages();
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public Race getRace() {
        return race;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public Background getBackground() {
        return background;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public Abilities getAbilities() {
        return abilities;
    }

    public SavingThrows getSavingThrows() {
        return savingThrows;
    }

    public Skills getSkills() {
        return skills;
    }
}