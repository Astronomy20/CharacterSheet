package net.astronomy.dnd;

import net.astronomy.dnd.model.AbilityScores;
import net.astronomy.dnd.model.Character;
import net.astronomy.dnd.enums.Alignment;
import net.astronomy.dnd.enums.Background;
import net.astronomy.dnd.enums.CharacterClass;
import net.astronomy.dnd.enums.Race;
import net.astronomy.dnd.util.CharacterPrinter;
import net.astronomy.dnd.util.Dices;

public class CharacterSheet {
    public static void main(String[] args) {
        Character padre_mateo = new Character(
                "Padre Matéo",
                1,
                Race.DWARF,
                CharacterClass.MONK,
                Background.ACOLYTE,
                Alignment.LAWFUL_GOOD,
                new AbilityScores(Dices.roll(Dices.Dice.D20), Dices.roll(Dices.Dice.D20), Dices.roll(Dices.Dice.D20), Dices.roll(Dices.Dice.D20), Dices.roll(Dices.Dice.D20), Dices.roll(Dices.Dice.D20))
        );

        CharacterPrinter.print(padre_mateo);
    }
}