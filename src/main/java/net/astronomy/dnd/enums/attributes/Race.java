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
            Set.of(Language.COMMON, Language.DRACONIC)
    ),

    DWARF(
            "Dwarf",
            Map.of(
                    Ability.CONSTITUTION, 2
            ),
            Set.of(Language.COMMON, Language.DWARVISH)
    ),

    ELF(
            "Elf",
            Map.of(
                    Ability.DEXTERITY, 2,
                    Ability.INTELLIGENCE, 1,
                    Ability.WISDOM, 1
            ),
            Set.of(Language.COMMON, Language.ELVISH)
    ),

    GNOME(
            "Gnome",
            Map.of(
                    Ability.DEXTERITY, 1,
                    Ability.CONSTITUTION, 1,
                    Ability.INTELLIGENCE, 2
            ),
            Set.of(Language.COMMON, Language.GNOMISH)
    ),

    HALF_ELF(
            "Half-Elf",
            Map.of(
                    Ability.CHARISMA, 2
            ),
            Set.of(Language.COMMON, Language.ELVISH)
    ),

    HALF_ORC(
            "Half-Orc",
            Map.of(
                    Ability.STRENGTH, 2,
                    Ability.CONSTITUTION, 1
            ),
            Set.of(Language.COMMON, Language.ORC)
    ),

    HALFLING(
            "Halfling",
            Map.of(
                    Ability.DEXTERITY, 2,
                    Ability.CONSTITUTION, 1
            ),
            Set.of(Language.COMMON, Language.HALFLING)
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
            Set.of(Language.COMMON)
    ),

    ORC(
            "Orc",
            Map.of(
                    Ability.STRENGTH, 2,
                    Ability.CONSTITUTION, 1
            ),
            Set.of(Language.ORC, Language.COMMON)
    ),

    TIEFLING(
            "Tiefling",
            Map.of(
                    Ability.CHARISMA, 2,
                    Ability.INTELLIGENCE, 1
            ),
            Set.of(Language.COMMON, Language.INFERNAL)
    );

    private final String displayName;
    private final Map<Ability, Integer> abilityBonuses;
    private final Set<Language> languages;

    Race(String displayName, Map<Ability, Integer> abilityBonuses, Set<Language> languages) {
        this.displayName = displayName;
        this.abilityBonuses = abilityBonuses;
        this.languages = languages;
    }

    public int getBonus(Ability abilityName) {
        return abilityBonuses.getOrDefault(abilityName, 0);
    }

    public Set<Language> getLanguages() {
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