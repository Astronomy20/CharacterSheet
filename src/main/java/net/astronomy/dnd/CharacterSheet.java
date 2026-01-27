package net.astronomy.dnd;

import net.astronomy.dnd.model.Abilities;
import net.astronomy.dnd.model.Character;
import net.astronomy.dnd.enums.attributes.Alignment;
import net.astronomy.dnd.enums.attributes.Background;
import net.astronomy.dnd.enums.attributes.CharacterClass;
import net.astronomy.dnd.enums.attributes.Race;
import net.astronomy.dnd.util.CharacterPrinter;
import net.astronomy.dnd.util.SaveSession;
import net.astronomy.dnd.util.dice.Dices;

import java.io.IOException;

public class CharacterSheet {

    public static void main(String[] args) throws IOException {
        Character padre_mateo = new Character(
                "Padre Matéo",
                1,
                Race.DWARF,
                CharacterClass.MONK,
                Background.ACOLYTE,
                Alignment.LAWFUL_GOOD,
                new Abilities(
                        Dices.getAbilitiesValueRolls()[0],
                        Dices.getAbilitiesValueRolls()[1],
                        Dices.getAbilitiesValueRolls()[2],
                        Dices.getAbilitiesValueRolls()[3],
                        Dices.getAbilitiesValueRolls()[4],
                        Dices.getAbilitiesValueRolls()[5])
//                new Abilities(15, 15, 15, 15, 15, 15)
        );

        CharacterPrinter.print(padre_mateo);

        SaveSession.saveCharacter(padre_mateo);
    }
}