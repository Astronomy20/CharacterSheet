package net.astronomy.dnd;

import net.astronomy.dnd.model.attributes.Ability;
import net.astronomy.dnd.model.Character;
import net.astronomy.dnd.model.enums.attributes.Alignment;
import net.astronomy.dnd.model.enums.attributes.Background;
import net.astronomy.dnd.model.enums.attributes.CharacterClass;
import net.astronomy.dnd.model.enums.attributes.Race;
import net.astronomy.dnd.model.attributes.Level;
import net.astronomy.dnd.util.CharacterPrinter;
import net.astronomy.dnd.util.Session;

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
                new Ability(15, 15, 15, 15, 15, 15));

        CharacterPrinter.print(padre_mateo);

        try {
            Session.saveCharacter(padre_mateo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}