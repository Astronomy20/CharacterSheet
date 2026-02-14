package net.astronomy.dnd;

import net.astronomy.dnd.model.Ability;
import net.astronomy.dnd.model.Character;
import net.astronomy.dnd.model.enums.attributes.Alignment;
import net.astronomy.dnd.model.enums.attributes.Background;
import net.astronomy.dnd.model.enums.attributes.CharacterClass;
import net.astronomy.dnd.model.enums.attributes.Race;
import net.astronomy.dnd.model.Level;
import net.astronomy.dnd.util.CharacterPrinter;
import net.astronomy.dnd.util.Session;
import net.astronomy.dnd.util.dice.Dices;

import java.io.IOException;


public class ExampleSheetTerminalNoUI {

    public static void main(String[] args) {
        Character padre_mateo = new Character(
                "Padre Matéo",
                new Level(1, 0),
                Race.DWARF,
                CharacterClass.MONK,
                Background.ACOLYTE,
                Alignment.LAWFUL_GOOD,
                new Ability(
                        Dices.getAbilitiesValueRolls()[0],
                        Dices.getAbilitiesValueRolls()[1],
                        Dices.getAbilitiesValueRolls()[2],
                        Dices.getAbilitiesValueRolls()[3],
                        Dices.getAbilitiesValueRolls()[4],
                        Dices.getAbilitiesValueRolls()[5])
        );

        CharacterPrinter.print(padre_mateo);

        try {
            Session.saveCharacter(padre_mateo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}