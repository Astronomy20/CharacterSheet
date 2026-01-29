package net.astronomy.dnd.enums.attributes;

import net.astronomy.dnd.model.Ability.Abilities;

import java.util.Map;
import java.util.Set;

/**
 * Character races.
 * Each race has a human-readable display name, set of ability bonuses and set of spoken languages.
 */
public enum Race {

    DRAGONBORN(
            "Dragonborn",
            Map.of(
                    Abilities.STRENGTH, 2,
                    Abilities.CHARISMA, 1
            ),
            Set.of(
                    Language.COMMON,
                    Language.DRACONIC
            ),
            0
    ),

    DWARF(
            "Dwarf",
            Map.of(
                    Abilities.CONSTITUTION, 2
                    // Dwarf can choose between STRENGTH +2 or WISDOM +1
            ),
            Set.of(
                    Language.COMMON,
                    Language.DWARVISH
            ),
            0
    ),

    ELF(
            "Elf",
            Map.of(
                    Abilities.DEXTERITY, 2,
                    Abilities.INTELLIGENCE, 1,
                    Abilities.WISDOM, 1
            ),
            Set.of(
                    Language.COMMON,
                    Language.ELVISH
            ),
            0
    ),

    GNOME(
            "Gnome",
            Map.of(
                    Abilities.DEXTERITY, 1,
                    Abilities.CONSTITUTION, 1,
                    Abilities.INTELLIGENCE, 2
            ),
            Set.of(
                    Language.COMMON,
                    Language.GNOMISH
            ),
            0
    ),

    HALF_ELF(
            "Half-Elf",
            Map.of(
                    Abilities.CHARISMA, 2
                    // Half-Elf gets +1 to two other abilities
            ),
            Set.of(
                    Language.COMMON,
                    Language.ELVISH
            ),
            1
    ),

    HALF_ORC(
            "Half-Orc",
            Map.of(
                    Abilities.STRENGTH, 2,
                    Abilities.CONSTITUTION, 1
            ),
            Set.of(
                    Language.COMMON,
                    Language.ORC
            ),
            0
    ),

    HALFLING(
            "Halfling",
            Map.of(
                    Abilities.DEXTERITY, 2,
                    Abilities.CONSTITUTION, 1
            ),
            Set.of(
                    Language.COMMON,
                    Language.HALFLING
            ),
            0
    ),

    HUMAN(
            "Human",
            Map.of(
                    Abilities.STRENGTH, 1,
                    Abilities.DEXTERITY, 1,
                    Abilities.CONSTITUTION, 1,
                    Abilities.INTELLIGENCE, 1,
                    Abilities.WISDOM, 1,
                    Abilities.CHARISMA, 1
            ),
            Set.of(
                    Language.COMMON
            ),
            1
    ),

    ORC(
            "Orc",
            Map.of(
                    Abilities.STRENGTH, 2,
                    Abilities.CONSTITUTION, 1
            ),
            Set.of(
                    Language.ORC,
                    Language.COMMON
            ),
            0
    ),

    TIEFLING(
            "Tiefling",
            Map.of(
                    Abilities.CHARISMA, 2,
                    Abilities.INTELLIGENCE, 1
            ),
            Set.of(
                    Language.COMMON,
                    Language.INFERNAL
            ),
            0
    );

    private final String displayName;
    private final Map<Abilities, Integer> abilityBonuses;
    private final Set<Language> languages;
    private final int extraLanguageChoices;

    Race(String displayName, Map<Abilities, Integer> abilityBonuses, Set<Language> languages, int extraLanguageChoices) {
        this.displayName = displayName;
        this.abilityBonuses = abilityBonuses;
        this.languages = languages;
        this.extraLanguageChoices = extraLanguageChoices;
    }

    public int getBonus(Abilities abilityName) {
        return abilityBonuses.getOrDefault(abilityName, 0);
    }

    public int getExtraLanguageChoices() {
        return extraLanguageChoices;
    }

    public Set<Language> getRaceLanguages() {
        return languages;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}