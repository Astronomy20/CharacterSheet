package net.astronomy.dnd.ui;

import net.astronomy.dnd.model.Character;
import net.astronomy.dnd.model.enums.attributes.Alignment;
import net.astronomy.dnd.model.enums.attributes.Background;
import net.astronomy.dnd.model.enums.attributes.CharacterClass;
import net.astronomy.dnd.model.enums.attributes.Race;
import net.astronomy.dnd.util.CharacterPrinter;
import net.astronomy.dnd.util.Session;

import java.io.IOException;
import java.util.List;

public class CliCharacterInteraction extends CliSelector {

    private final Character character;

    public CliCharacterInteraction(Character character) throws IOException {
        super();
        this.character = character;
    }

    public void start() throws IOException {

        while (true) {

            CharacterPrinter.print(character);

            List<Option<String>> options = List.of(
                    new Option<>("Add Life", "ADD_LIFE"),
                    new Option<>("Subtract Life", "SUB_LIFE"),
                    new Option<>("Add XP", "ADD_XP"),
                    new Option<>("Remove XP", "REMOVE_XP"),
                    new Option<>("Change Race", "CHANGE_RACE"),
                    new Option<>("Change Class", "CHANGE_CLASS"),
                    new Option<>("Change Background", "CHANGE_BACKGROUND"),
                    new Option<>("Change Alignment", "CHANGE_ALIGNMENT"),
                    new Option<>("Set Level", "SET_LEVEL"),
                    new Option<>("Exit", "EXIT")
            );

            String action = selectOption("Character Interaction", options);

            switch (action) {

                case "ADD_LIFE" -> modifyLife(true);
                case "SUB_LIFE" -> modifyLife(false);

                case "ADD_XP" -> modifyXp(true);
                case "REMOVE_XP" -> modifyXp(false);

                case "CHANGE_RACE" -> changeBaseAttribute(
                        "Race",
                        Race.values(),
                        character::setRace
                );

                case "CHANGE_CLASS" -> changeBaseAttribute(
                        "Class",
                        CharacterClass.values(),
                        character::setCharacterClass
                );

                case "CHANGE_BACKGROUND" -> changeBaseAttribute(
                        "Background",
                        Background.values(),
                        character::setBackground
                );

                case "CHANGE_ALIGNMENT" -> changeBaseAttribute(
                        "Alignment",
                        Alignment.values(),
                        character::setAlignment
                );

                case "SET_LEVEL" -> setLevel();

                case "EXIT" -> {
                    Session.saveCharacter(character);
                    close();
                    return;
                }
            }
        }
    }

    // =========================
    // LIFE
    // =========================

    private void modifyLife(boolean add) {
        int amount = promptForNumber("Enter life amount: ");
        if (add) character.getLife().addLifePoints(amount);
        else character.getLife().removeLifePoints(amount);
    }

    // =========================
    // XP
    // =========================

    private void modifyXp(boolean add) {
        int amount = promptForNumber("Enter XP amount: ");
        if (add) character.getLevel().addExperiencePoints(amount, character.getLife());
        else character.getLevel().removeExperiencePoints(amount, character.getLife());
    }

    // =========================
    // BASE ATTRIBUTE CHANGE
    // =========================

    private <E extends Enum<E>> void changeBaseAttribute(
            String attributeName,
            E[] values,
            java.util.function.Consumer<E> setter) throws IOException {

        if (confirmManual("""
                WARNING:
                You are modifying a BASE CHARACTER ATTRIBUTE.
                This can affect derived stats, proficiencies and progression.
                Type 'confirm' to proceed:
                """, "confirm")) {
            return;
        }

        CliAttributesSelector selector = new CliAttributesSelector();
        E selected = selector.selectEnum("Select new " + attributeName, values);
        selector.close();

        setter.accept(selected);
    }

    // =========================
    // LEVEL
    // =========================

    private void setLevel() {

        if (confirmManual("""
                WARNING:
                You are manually setting the character level.
                This alters progression and balance.
                Type 'yes' to confirm:
                """, "yes")) {
            return;
        }

        int level = promptForNumber("Enter new level: ");
        character.getLevel().setLevel(level, character.getLife());
    }

    // =========================
    // UTILITIES
    // =========================

    private int promptForNumber(String message) {
        System.out.print(message);
        String input = new java.util.Scanner(System.in).nextLine();
        return Integer.parseInt(input);
    }

    private boolean confirmManual(String warningText, String requiredWord) {
        System.out.println(warningText);
        String input = new java.util.Scanner(System.in).nextLine();
        return !input.equalsIgnoreCase(requiredWord);
    }
}