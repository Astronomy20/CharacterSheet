package net.astronomy.dnd.ui;

import net.astronomy.dnd.model.Character;
import net.astronomy.dnd.util.Session;
import org.jline.reader.LineReader;
import org.jline.terminal.Attributes;
import org.jline.terminal.Terminal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * CLI selector for saved character sessions.
 */
public class CliSessionSelector extends CliSelector {

    private static final String EXIT_SENTINEL = "__EXIT__";
    private static final String EXIT_DISPLAY_NAME = "-- Back to Main Menu --";

    public CliSessionSelector() throws IOException {
        super();
    }

    public CliSessionSelector(Terminal terminal, Attributes cookedAttributes, LineReader reader) {
        super(terminal, cookedAttributes, reader);
    }

    public Character selectSavedCharacter() throws IOException {
        List<String> savedNames = getSavedCharacterNames();

        if (savedNames.isEmpty()) {
            disableRawMode();
            System.out.println("\nNo saved characters found.");
            promptLine("Press Enter to return to main menu...");
            return null;
        }

        List<Option<String>> options = toOptions(savedNames);
        options.add(new Option<>(EXIT_DISPLAY_NAME, EXIT_SENTINEL));

        String selected = selectOption("Select a character to load", options);

        if (selected == null || selected.equals(EXIT_SENTINEL)) return null;

        return Session.loadCharacter(selected);
    }

    public String selectCharacterToDelete() throws IOException {
        List<String> savedNames = getSavedCharacterNames();

        if (savedNames.isEmpty()) {
            disableRawMode();
            System.out.println("\nNo saved characters found.");
            promptLine("Press Enter to return to main menu...");
            return null;
        }

        // Loop so a failed confirmation brings the user back to the list
        while (true) {
            List<Option<String>> options = toOptions(savedNames);
            options.add(new Option<>(EXIT_DISPLAY_NAME, EXIT_SENTINEL));

            String selected = selectOption("Select a character to delete", options);

            if (selected == null || selected.equals(EXIT_SENTINEL)) return null;

            String required = "delete " + selected;
            String input = promptLine(
                    "Type \"delete " + selected + "\" to confirm deletion: "
            ).trim();

            if (!input.equalsIgnoreCase(required)) {
                System.out.println("Confirmation did not match. Deletion cancelled.");
                promptLine("Press Enter to go back to the list...");
                continue;
            }

            boolean deleted = Session.deleteCharacter(selected);
            if (deleted) {
                System.out.println("Character \"" + selected + "\" deleted successfully.");
                return selected;
            } else {
                System.out.println("Failed to delete: " + selected);
                return null;
            }
        }
    }

    private List<String> getSavedCharacterNames() {
        File savesDir = new File("saves");
        if (!savesDir.exists() || !savesDir.isDirectory()) return List.of();

        return Arrays.stream(Objects.requireNonNull(
                        savesDir.listFiles((dir, name) -> name.endsWith(".json"))))
                .map(file -> file.getName().replaceFirst("\\.json$", ""))
                .collect(Collectors.toList());
    }

    /** Returns a mutable list so callers can append extra options (e.g. Exit). */
    private List<Option<String>> toOptions(List<String> names) {
        return names.stream()
                .map(name -> new Option<>(name, name))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}