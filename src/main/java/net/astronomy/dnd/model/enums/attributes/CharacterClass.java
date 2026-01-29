package net.astronomy.dnd.model.enums.attributes;

import net.astronomy.dnd.model.Modifier.Modifiers;

import java.util.Set;


/**
 * Character classes.
 * Each class has human-readable display name and the set of saving throws.
 */
public enum CharacterClass {

    BARBARIAN(
            "Barbarian",
            Set.of(
                    Modifiers.STRENGTH,
                    Modifiers.CONSTITUTION
            )
    ),

    BARD(
            "Bard",
            Set.of(
                    Modifiers.DEXTERITY,
                    Modifiers.CHARISMA
            )
    ),

    CLERIC(
            "Cleric",
            Set.of(
                    Modifiers.WISDOM,
                    Modifiers.CHARISMA
            )
    ),

    DRUID(
            "Druid",
            Set.of(
                    Modifiers.INTELLIGENCE,
                    Modifiers.WISDOM
            )
    ),

    FIGHTER(
            "Fighter",
            Set.of(
                    Modifiers.STRENGTH,
                    Modifiers.CONSTITUTION
            )
    ),

    MONK(
            "Monk",
            Set.of(
                    Modifiers.STRENGTH,
                    Modifiers.DEXTERITY
            )
    ),

    PALADIN(
            "Paladin",
            Set.of(
                    Modifiers.WISDOM,
                    Modifiers.CHARISMA
            )
    ),

    RANGER(
            "Ranger",
            Set.of(
                    Modifiers.STRENGTH,
                    Modifiers.DEXTERITY
            )
    ),

    ROGUE(
            "Rogue",
            Set.of(
                    Modifiers.DEXTERITY,
                    Modifiers.INTELLIGENCE
            )
    ),

    SORCERER(
            "Sorcerer",
            Set.of(
                    Modifiers.CONSTITUTION,
                    Modifiers.CHARISMA
            )
    ),

    WARLOCK(
            "Warlock",
            Set.of(
                    Modifiers.WISDOM,
                    Modifiers.CHARISMA
            )
    ),

    WIZARD(
            "Wizard",
            Set.of(
                    Modifiers.INTELLIGENCE,
                    Modifiers.WISDOM
            )
    );

    private final String displayName;
    private final Set<Modifiers> proficientSavingThrows;

    CharacterClass(String displayName, Set<Modifiers> proficientSavingThrows) {
        this.displayName = displayName;
        this.proficientSavingThrows = proficientSavingThrows;
    }

    public boolean isProficientIn(Modifiers modifiers) {
        return proficientSavingThrows.contains(modifiers);
    }

    public Set<Modifiers> getProficiencyModifiers() {
        return proficientSavingThrows;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}