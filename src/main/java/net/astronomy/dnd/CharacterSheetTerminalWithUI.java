package net.astronomy.dnd;

import net.astronomy.dnd.model.Character;
import net.astronomy.dnd.model.Ability;
import net.astronomy.dnd.model.Level;
import net.astronomy.dnd.model.enums.attributes.Alignment;
import net.astronomy.dnd.model.enums.attributes.Background;
import net.astronomy.dnd.model.enums.attributes.CharacterClass;
import net.astronomy.dnd.model.enums.attributes.Race;
import net.astronomy.dnd.ui.CliAttributesSelector;
import net.astronomy.dnd.ui.CliSelector;
import net.astronomy.dnd.ui.CliSessionSelector;
import net.astronomy.dnd.util.CharacterPrinter;
import net.astronomy.dnd.util.Session;
import net.astronomy.dnd.util.dice.Dices;

import java.io.IOException;
import java.util.Scanner;

public class CharacterSheetTerminalWithUI {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        // --- MAIN MENU ---
        CliSelector menuSelector = new CliSelector();
        String choice = menuSelector.selectMainMenu();
        menuSelector.close();

        if ("LOAD".equals(choice)) {
            // --- LOAD SAVED CHARACTER ---
            CliSessionSelector sessionSelector = new CliSessionSelector();
            Character loaded = sessionSelector.selectSavedCharacter();
            sessionSelector.close();

            if (loaded != null) {
                System.out.println("\nLoaded character:\n");
                CharacterPrinter.print(loaded);
            } else {
                System.out.println("No character loaded.");
            }

        } else {
            // -- CREATE NEW CHARACTER --
            String name;
            while (true) {
                System.out.print("Enter character name: ");
                name = scanner.nextLine().trim();

                if (name.isEmpty()) {
                    System.out.println("Name cannot be empty. Try again.");
                    continue;
                }

                if (Session.characterExists(name)) {
                    System.out.println("A character with this name already exists. Choose another name.");
                    continue;
                }
                break;
            }

            CliAttributesSelector characterAttributes = new CliAttributesSelector();

            Race race = characterAttributes.selectEnum("Select Race", Race.values());
            CharacterClass characterClass = characterAttributes.selectEnum("Select Class", CharacterClass.values());
            Background background = characterAttributes.selectEnum("Select Background", Background.values());
            Alignment alignment = characterAttributes.selectEnum("Select Alignment", Alignment.values());

            characterAttributes.close();

            int[] rolls = Dices.getAbilitiesValueRolls();
            Ability ability = new Ability(
                    rolls[0], rolls[1], rolls[2], rolls[3], rolls[4], rolls[5]
            );
            Level level = new Level(1, 0);

            Character character = new Character(
                    name, level, race, characterClass, background, alignment, ability
            );

            try {
                Session.saveCharacter(character);
                System.out.println("\nCharacter saved successfully.");

                System.out.println("Press Enter to show character stats...");
                System.in.read();

                CharacterPrinter.print(character);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}