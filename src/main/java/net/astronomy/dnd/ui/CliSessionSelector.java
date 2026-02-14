package net.astronomy.dnd.ui;

import net.astronomy.dnd.model.Character;
import net.astronomy.dnd.util.Session;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * CLI selector for saved character sessions.
 * Allows user to select a character to load or delete.
 */
public class CliSessionSelector extends CliSelector {

    public CliSessionSelector() throws IOException {
        super();
    }

    /**
     * Let the user select a saved character to load.
     *
     * @return the selected Character object, or null if none exist
     * @throws IOException on terminal read error
     */
    public Character selectSavedCharacter() throws IOException {
        List<String> savedNames = getSavedCharacterNames();
        if (savedNames.isEmpty()) {
            System.out.println("No saved characters found.");
            return null;
        }

        String selectedName = selectOption("Select a character to load", wrapNamesAsOptions(savedNames));
        return Session.loadCharacter(selectedName);
    }

    /**
     * Let the user select a saved character to delete.
     *
     * @return the name of the deleted character, or null if canceled
     * @throws IOException on terminal read error
     */
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

    /**
     * Returns saved character names without the .json extension.
     */
    private List<String> getSavedCharacterNames() {
        File savesDir = new File("saves");
        if (!savesDir.exists() || !savesDir.isDirectory()) return List.of();

        return Arrays.stream(Objects.requireNonNull(savesDir.listFiles((dir, name) -> name.endsWith(".json"))))
                .map(file -> file.getName().replaceFirst("\\.json$", ""))
                .collect(Collectors.toList());
    }

    /**
     * Names to options wrapper
     */
    private List<Option<String>> wrapNamesAsOptions(List<String> savedNames) {
        return savedNames.stream()
                .map(name -> new Option<>(name, name))
                .collect(Collectors.toList());
    }
}