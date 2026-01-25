package net.astronomy.dnd.enums.attributes;

import net.astronomy.dnd.model.Abilities.Ability;

import java.util.Map;

public enum Race {
    DRAGONBORN(Map.of(
            Ability.STRENGTH, 2,
            Ability.CHARISMA, 1
    )),

    DWARF(Map.of(
            Ability.CONSTITUTION, 2
    )),

    ELF(Map.of(
            Ability.DEXTERITY, 2,
            Ability.INTELLIGENCE, 1,
            Ability.WISDOM, 1
    )),

    GNOME(Map.of(
            Ability.DEXTERITY, 1,
            Ability.CONSTITUTION, 1,
            Ability.INTELLIGENCE, 2
    )),

    HALF_ELF(Map.of(
            Ability.CHARISMA, 2
            // Half-Elf gets +1 to two other abilities; can handle later
    )),

    HALF_ORC(Map.of(
            Ability.STRENGTH, 2,
            Ability.CONSTITUTION, 1
    )),

    HALFLING(Map.of(
            Ability.DEXTERITY, 2,
            Ability.CONSTITUTION, 1
    )),

    HUMAN(Map.of(
            Ability.STRENGTH, 1,
            Ability.DEXTERITY, 1,
            Ability.CONSTITUTION, 1,
            Ability.INTELLIGENCE, 1,
            Ability.WISDOM, 1,
            Ability.CHARISMA, 1
    )),
    ORC(Map.of(
            Ability.STRENGTH, 2,
            Ability.CONSTITUTION, 1
    )),

    TIEFLING(Map.of(
            Ability.CHARISMA, 2,
            Ability.INTELLIGENCE, 1
    ));

    private final Map<Ability, Integer> abilityBonuses;

    Race(Map<Ability, Integer> abilityBonuses) {
        this.abilityBonuses = abilityBonuses;
    }

    public int getBonus(Ability abilityName) {
        return abilityBonuses.getOrDefault(abilityName, 0);
    }
}