package net.astronomy.dnd.model;

import net.astronomy.dnd.enums.Alignment;
import net.astronomy.dnd.enums.CharacterClass;
import net.astronomy.dnd.enums.Race;
import net.agronomy.dnd.enums.Background;

public class Character {
    private String name;
    private Race race;
    private CharacterClass characterClass;
    private int level;
    private Background background;
    private Alignment alignment;
    private int experiencePoints;

    private AbilityScores abilityScores;
    private SavingThrows savingThrows;
    private Skills skills;
    private CombatStats combatStats;
    private Equipment equipment;
    private Spellcasting spellcasting;

    public Character(String name, Race race, CharacterClass characterClass,
                     int level, String background, Alignment alignment,
                     AbilityScores abilityScores) {
        this.name = name;
        this.race = race;
        this.characterClass = characterClass;
        this.level = level;
        this.background = background;
        this.alignment = alignment;
        this.abilityScores = abilityScores;
    }
}
