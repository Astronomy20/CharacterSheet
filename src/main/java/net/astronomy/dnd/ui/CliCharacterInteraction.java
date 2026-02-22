package net.astronomy.dnd.ui;

import net.astronomy.dnd.model.Character;
import net.astronomy.dnd.model.enums.attributes.Alignment;
import net.astronomy.dnd.model.enums.attributes.Background;
import net.astronomy.dnd.model.enums.attributes.CharacterClass;
import net.astronomy.dnd.model.enums.attributes.Race;
import net.astronomy.dnd.util.CharacterPrinter;
import net.astronomy.dnd.util.Session;
import org.jline.reader.LineReader;
import org.jline.terminal.Attributes;
import org.jline.terminal.Terminal;

import java.io.IOException;
import java.util.List;

public class CliCharacterInteraction extends CliSelector {

    private final Character character;

    public CliCharacterInteraction(Character character) throws IOException {
        super();
        this.character = character;
    }

    /** Reuse an existing terminal. */
    public CliCharacterInteraction(Terminal terminal, Attributes cookedAttributes, LineReader reader, Character character) {
        super(terminal, cookedAttributes, reader);
        this.character = character;
    }

    public void start() throws IOException {

        while (true) {
            CharacterPrinter.print(character);

            List<Option<String>> options = List.of(
                    new Option<>("Add Life",          "ADD_LIFE"),
                    new Option<>("Subtract Life",     "SUB_LIFE"),
                    new Option<>("Add XP",            "ADD_XP"),
                    new Option<>("Remove XP",         "REMOVE_XP"),
                    new Option<>("Change Race",       "CHANGE_RACE"),
                    new Option<>("Change Class",      "CHANGE_CLASS"),
                    new Option<>("Change Background", "CHANGE_BACKGROUND"),
                    new Option<>("Change Alignment",  "CHANGE_ALIGNMENT"),
                    new Option<>("Set Level",         "SET_LEVEL"),
                    new Option<>("Exit",              "EXIT")
            );

            String action = selectOption("Character Interaction", options);

            switch (action) {
                case "ADD_LIFE"          -> modifyLife(true);
                case "SUB_LIFE"          -> modifyLife(false);
                case "ADD_XP"            -> modifyXp(true);
                case "REMOVE_XP"         -> modifyXp(false);
                case "CHANGE_RACE"       -> changeBaseAttribute("Race",       Race.values(),           character::setRace);
                case "CHANGE_CLASS"      -> changeBaseAttribute("Class",      CharacterClass.values(), character::setCharacterClass);
                case "CHANGE_BACKGROUND" -> changeBaseAttribute("Background", Background.values(),     character::setBackground);
                case "CHANGE_ALIGNMENT"  -> changeBaseAttribute("Alignment",  Alignment.values(),      character::setAlignment);
                case "SET_LEVEL"         -> setLevel();
                case "EXIT" -> {
                    Session.saveCharacter(character);
                    // Don't close — caller owns the terminal
                    return;
                }
            }
        }
    }

    // =========================
    // LIFE
    // =========================

    private void modifyLife(boolean add) {
        int amount = promptInt("Enter life amount: ");
        if (add) character.getLife().addLifePoints(amount);
        else     character.getLife().removeLifePoints(amount);
    }

    // =========================
    // XP
    // =========================

    private void modifyXp(boolean add) {
        int amount = promptInt("Enter XP amount: ");
        if (add) character.getLevel().addExperiencePoints(amount, character.getLife());
        else     character.getLevel().removeExperiencePoints(amount, character.getLife());
    }

    // =========================
    // BASE ATTRIBUTE CHANGE
    // =========================

    private <E extends Enum<E>> void changeBaseAttribute(
            String attributeName,
            E[] values,
            java.util.function.Consumer<E> setter) throws IOException {

        if (confirmManual(
                "WARNING: Modifying a base attribute affects derived stats.\nType 'confirm' to proceed: ",
                "confirm")) {
            return;
        }

        CliAttributesSelector selector = new CliAttributesSelector(
                getTerminal(), getCookedAttributes(), getReader()
        );
        E selected = selector.selectEnum("Select new " + attributeName, values);
        setter.accept(selected);
    }

    // =========================
    // LEVEL
    // =========================

    private void setLevel() {
        if (confirmManual(
                "WARNING: Manually setting level alters progression.\nType 'yes' to confirm: ",
                "yes")) {
            return;
        }

        int level = promptInt("Enter new level (1-20): ");
        character.getLevel().setLevel(level, character.getLife());
    }

    // =========================
    // UTILITIES
    // =========================

    private boolean confirmManual(String warningText, String requiredWord) {
        String input = promptLine(warningText);
        return !input.trim().equalsIgnoreCase(requiredWord);
    }
}