package net.astronomy.dnd.enums.attributes;

import net.astronomy.dnd.model.SavingThrows.SavingThrow;

import java.util.Set;


/**
 * Character classes.
 * Each class has human-readable display name and the set of saving throws.
 */
public enum CharacterClass {

    BARBARIAN(
            "Barbarian",
            Set.of(
                    SavingThrow.STRENGTH,
                    SavingThrow.CONSTITUTION
            )
    ),

    BARD(
            "Bard",
            Set.of(
                    SavingThrow.DEXTERITY,
                    SavingThrow.CHARISMA
            )
    ),

    CLERIC(
            "Cleric",
            Set.of(
                    SavingThrow.WISDOM,
                    SavingThrow.CHARISMA
            )
    ),

    DRUID(
            "Druid",
            Set.of(
                    SavingThrow.INTELLIGENCE,
                    SavingThrow.WISDOM
            )
    ),

    FIGHTER(
            "Fighter",
            Set.of(
                    SavingThrow.STRENGTH,
                    SavingThrow.CONSTITUTION
            )
    ),

    MONK(
            "Monk",
            Set.of(
                    SavingThrow.STRENGTH,
                    SavingThrow.DEXTERITY
            )
    ),

    PALADIN(
            "Paladin",
            Set.of(
                    SavingThrow.WISDOM,
                    SavingThrow.CHARISMA
            )
    ),

    RANGER(
            "Ranger",
            Set.of(
                    SavingThrow.STRENGTH,
                    SavingThrow.DEXTERITY
            )
    ),

    ROGUE(
            "Rogue",
            Set.of(
                    SavingThrow.DEXTERITY,
                    SavingThrow.INTELLIGENCE
            )
    ),

    SORCERER(
            "Sorcerer",
            Set.of(
                    SavingThrow.CONSTITUTION,
                    SavingThrow.CHARISMA
            )
    ),

    WARLOCK(
            "Warlock",
            Set.of(
                    SavingThrow.WISDOM,
                    SavingThrow.CHARISMA
            )
    ),

    WIZARD(
            "Wizard",
            Set.of(
                    SavingThrow.INTELLIGENCE,
                    SavingThrow.WISDOM
            )
    );

    private final String displayName;
    private final Set<SavingThrow> proficientSavingThrows;

    CharacterClass(String displayName, Set<SavingThrow> proficientSavingThrows) {
        this.displayName = displayName;
        this.proficientSavingThrows = proficientSavingThrows;
    }

    public boolean isProficientIn(SavingThrow savingThrow) {
        return proficientSavingThrows.contains(savingThrow);
    }

    public Set<SavingThrow> getProficientSavingThrows() {
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