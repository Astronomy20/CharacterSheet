package net.astronomy.dnd.enums.attributes;

import net.astronomy.dnd.model.Abilities.Ability;

import java.util.Map;
import java.util.Set;

public enum Race {

    DRAGONBORN(
            "Dragonborn",
            Map.of(
                    Ability.STRENGTH, 2,
                    Ability.CHARISMA, 1
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
                    Ability.CONSTITUTION, 2
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
                    Ability.DEXTERITY, 2,
                    Ability.INTELLIGENCE, 1,
                    Ability.WISDOM, 1
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
                    Ability.DEXTERITY, 1,
                    Ability.CONSTITUTION, 1,
                    Ability.INTELLIGENCE, 2
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
                    Ability.CHARISMA, 2
                    // Half-Elf gets +1 to two other abilities;
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
                    Ability.STRENGTH, 2,
                    Ability.CONSTITUTION, 1
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
                    Ability.DEXTERITY, 2,
                    Ability.CONSTITUTION, 1
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
                    Ability.STRENGTH, 1,
                    Ability.DEXTERITY, 1,
                    Ability.CONSTITUTION, 1,
                    Ability.INTELLIGENCE, 1,
                    Ability.WISDOM, 1,
                    Ability.CHARISMA, 1
            ),
            Set.of(
                    Language.COMMON
            ),
            1
    ),

    ORC(
            "Orc",
            Map.of(
                    Ability.STRENGTH, 2,
                    Ability.CONSTITUTION, 1
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
                    Ability.CHARISMA, 2,
                    Ability.INTELLIGENCE, 1
            ),
            Set.of(
                    Language.COMMON,
                    Language.INFERNAL
            ),
            0
    );

    private final String displayName;
    private final Map<Ability, Integer> abilityBonuses;
    private final Set<Language> languages;
    private final int extraLanguageChoices;

    Race(String displayName, Map<Ability, Integer> abilityBonuses, Set<Language> languages, int extraLanguageChoices) {
        this.displayName = displayName;
        this.abilityBonuses = abilityBonuses;
        this.languages = languages;
        this.extraLanguageChoices = extraLanguageChoices;
    }

    public int getBonus(Ability abilityName) {
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