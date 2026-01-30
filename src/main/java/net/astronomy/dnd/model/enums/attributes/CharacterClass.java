package net.astronomy.dnd.model.enums.attributes;

import net.astronomy.dnd.model.Modifier.Modifiers;
import net.astronomy.dnd.util.dice.Dices.Dice;

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
            ),
            Dice.D12
    ),

    BARD(
            "Bard",
            Set.of(
                    Modifiers.DEXTERITY,
                    Modifiers.CHARISMA
            ),
            Dice.D8
    ),

    CLERIC(
            "Cleric",
            Set.of(
                    Modifiers.WISDOM,
                    Modifiers.CHARISMA
            ),
            Dice.D8
    ),

    DRUID(
            "Druid",
            Set.of(
                    Modifiers.INTELLIGENCE,
                    Modifiers.WISDOM
            ),
            Dice.D8
    ),

    FIGHTER(
            "Fighter",
            Set.of(
                    Modifiers.STRENGTH,
                    Modifiers.CONSTITUTION
            ),
            Dice.D10
    ),

    MONK(
            "Monk",
            Set.of(
                    Modifiers.STRENGTH,
                    Modifiers.DEXTERITY
            ),
            Dice.D8
    ),

    PALADIN(
            "Paladin",
            Set.of(
                    Modifiers.WISDOM,
                    Modifiers.CHARISMA
            ),
            Dice.D10
    ),

    RANGER(
            "Ranger",
            Set.of(
                    Modifiers.STRENGTH,
                    Modifiers.DEXTERITY
            ),
            Dice.D10
    ),

    ROGUE(
            "Rogue",
            Set.of(
                    Modifiers.DEXTERITY,
                    Modifiers.INTELLIGENCE
            ),
            Dice.D8
    ),

    SORCERER(
            "Sorcerer",
            Set.of(
                    Modifiers.CONSTITUTION,
                    Modifiers.CHARISMA
            ),
            Dice.D6
    ),

    WARLOCK(
            "Warlock",
            Set.of(
                    Modifiers.WISDOM,
                    Modifiers.CHARISMA
            ),
            Dice.D8
    ),

    WIZARD(
            "Wizard",
            Set.of(
                    Modifiers.INTELLIGENCE,
                    Modifiers.WISDOM
            ),
            Dice.D6
    );

    private final String displayName;
    private final Set<Modifiers> proficientSavingThrows;
    private final Dice hitDice;

    CharacterClass(String displayName, Set<Modifiers> proficientSavingThrows, Dice hitDice) {
        this.displayName = displayName;
        this.proficientSavingThrows = proficientSavingThrows;
        this.hitDice = hitDice;
    }

    public boolean isProficientIn(Modifiers modifiers) {
        return proficientSavingThrows.contains(modifiers);
    }

    public Set<Modifiers> getProficiencyModifiers() {
        return proficientSavingThrows;
    }

    public Dice getHitDice() {
        return hitDice;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}