package net.astronomy.dnd;

import net.astronomy.dnd.model.Character;
import net.astronomy.dnd.model.attributes.Ability;
import net.astronomy.dnd.model.attributes.Level;
import net.astronomy.dnd.model.enums.attributes.Alignment;
import net.astronomy.dnd.model.enums.attributes.Background;
import net.astronomy.dnd.model.enums.attributes.CharacterClass;
import net.astronomy.dnd.model.enums.attributes.Race;
import net.astronomy.dnd.ui.CliAttributesSelector;
import net.astronomy.dnd.ui.CliCharacterInteraction;
import net.astronomy.dnd.ui.CliSelector;
import net.astronomy.dnd.ui.CliSessionSelector;
import net.astronomy.dnd.util.CharacterPrinter;
import net.astronomy.dnd.util.Session;
import net.astronomy.dnd.util.dice.Dices;

import java.io.IOException;

public class CharacterSheetTerminalWithUI {

    public static void main(String[] args) throws IOException {

        CliSelector root = new CliSelector();

        while (true) {

            String choice = root.selectMainMenu();

            switch (choice) {
                case "LOAD": {
                    CliSessionSelector sessionSelector = new CliSessionSelector(
                            root.getTerminal(), root.getCookedAttributes(), root.getReader()
                    );
                    Character loaded = sessionSelector.selectSavedCharacter();

                    if (loaded == null) {
                        continue;
                    }

                    System.out.println("\nLoaded character:\n");
                    CharacterPrinter.print(loaded);

                    String input = root.promptLine("Do you want to edit this character? (y/n): ").trim();
                    if (input.equalsIgnoreCase("y")) {
                        CliCharacterInteraction interaction = new CliCharacterInteraction(
                                root.getTerminal(), root.getCookedAttributes(), root.getReader(), loaded
                        );
                        interaction.start();
                    } else {
                        continue;
                    }

                    break;
                }
                case "CREATE":
                    String name;
                    while (true) {
                        name = root.promptLine("Enter character name: ").trim();

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

                    CliAttributesSelector attrs = new CliAttributesSelector(
                            root.getTerminal(), root.getCookedAttributes(), root.getReader()
                    );

                    Race race = attrs.selectEnum("Select Race", Race.values());
                    CharacterClass characterClass = attrs.selectEnum("Select Class", CharacterClass.values());
                    Background background = attrs.selectEnum("Select Background", Background.values());
                    Alignment alignment = attrs.selectEnum("Select Alignment", Alignment.values());

                    int[] rolls = Dices.getAbilitiesValueRolls();
                    Ability ability = new Ability(rolls[0], rolls[1], rolls[2], rolls[3], rolls[4], rolls[5]);
                    Level level = new Level(1, 0);

                    Character character = new Character(name, level, race, characterClass, background, alignment, ability);

                    Session.saveCharacter(character);
                    System.out.println("\nCharacter saved successfully.");

                    root.promptLine("Press Enter to show character stats...");
                    CharacterPrinter.print(character);

                    root.promptLine("Press Enter to return to main menu...");
                    continue;

                case "DELETE": {
                    CliSessionSelector sessionSelector = new CliSessionSelector(
                            root.getTerminal(), root.getCookedAttributes(), root.getReader()
                    );
                    String deleted = sessionSelector.selectCharacterToDelete();

                    if (deleted == null) {
                        continue;
                    }

                    root.promptLine("Press Enter to return to main menu...");
                    continue;

                }
                case "EXIT":
                    break;
            }

            return;
        }
    }
}