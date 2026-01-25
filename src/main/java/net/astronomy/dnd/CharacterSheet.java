package net.astronomy.dnd;

import net.astronomy.dnd.model.Abilities;
import net.astronomy.dnd.model.Character;
import net.astronomy.dnd.enums.attributes.Alignment;
import net.astronomy.dnd.enums.attributes.Background;
import net.astronomy.dnd.enums.attributes.CharacterClass;
import net.astronomy.dnd.enums.attributes.Race;
import net.astronomy.dnd.model.SavingThrows;
import net.astronomy.dnd.model.Skills;
import net.astronomy.dnd.util.CharacterPrinter;
import net.astronomy.dnd.util.dice.Dices;

public class CharacterSheet {

    public static void main(String[] args) {

        int[] rolls = Dices.getAbilitiesValueRolls();

        Abilities abilities = new Abilities(
                rolls[0],
                rolls[1],
                rolls[2],
                rolls[3],
                rolls[4],
                rolls[5]
        );

        SavingThrows savingThrows = new SavingThrows(abilities);
        Skills skills = new Skills(abilities);

        Character padre_mateo = new Character(
                "Padre Matéo",
                1,
                Race.DWARF,
                CharacterClass.MONK,
                Background.ACOLYTE,
                Alignment.LAWFUL_GOOD,
                abilities,
                savingThrows,
                skills
        );

        CharacterPrinter.print(padre_mateo);
    }
}