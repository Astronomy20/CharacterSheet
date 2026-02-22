package net.astronomy.dnd.ui;

import net.astronomy.dnd.model.Character;
import net.astronomy.dnd.util.Session;
import org.jline.reader.LineReader;
import org.jline.terminal.Attributes;
import org.jline.terminal.Terminal;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * CLI selector for saved character sessions.
 */
public class CliSessionSelector extends CliSelector {

    public CliSessionSelector() throws IOException {
        super();
    }

    /** Reuse an existing terminal. */
    public CliSessionSelector(Terminal terminal, Attributes cookedAttributes, LineReader reader) {
        super(terminal, cookedAttributes, reader);
    }

    public Character selectSavedCharacter() throws IOException {
        List<String> savedNames = getSavedCharacterNames();
        if (savedNames.isEmpty()) {
            System.out.println("No saved characters found.");
            return null;
        }

        String selectedName = selectOption("Select a character to load", wrapNamesAsOptions(savedNames));
        return Session.loadCharacter(selectedName);
    }

    public String selectCharacterToDelete() throws IOException {
        List<String> savedNames = getSavedCharacterNames();
        if (savedNames.isEmpty()) {
            System.out.println("No saved characters found.");
            return null;
        }

        String selectedName = selectOption("Select a character to delete", wrapNamesAsOptions(savedNames));

        boolean deleted = Session.deleteCharacter(selectedName);
        if (deleted) {
            System.out.println("Deleted character: " + selectedName);
            return selectedName;
        } else {
            System.out.println("Failed to delete: " + selectedName);
            return null;
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

    private List<Option<String>> wrapNamesAsOptions(List<String> names) {
        return names.stream()
                .map(name -> new Option<>(name, name))
                .collect(Collectors.toList());
    }
}