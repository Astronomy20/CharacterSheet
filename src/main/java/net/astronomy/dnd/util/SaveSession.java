package net.astronomy.dnd.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.astronomy.dnd.model.Character;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;

/**
 * Utility class for saving and loading D&D characters to/from JSON files.
 * Each character is stored in its own JSON file named after the character.
 */
public class SaveSession {

    private static final String SAVE_DIRECTORY = "saves";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    static {
        File savesDir = new File(SAVE_DIRECTORY);
        if (!savesDir.exists()) {
            savesDir.mkdirs();
        }
    }

    /**
     * Saves a character to a JSON file.
     * The file is named after the character's name (with safe characters only).
     *
     * @param character the character to save
     * @throws IOException if an I/O error occurs
     */
    public static void saveCharacter(Character character) throws IOException {
        String fileName = sanitizeFileName(character.getName());
        String filePath = SAVE_DIRECTORY + File.separator + fileName + ".json";

        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(character, writer);
        }
    }

    /**
     * Loads a character from a JSON file.
     *
     * @param characterName the name of the character to load
     * @return the loaded Character object, or null if not found
     * @throws IOException if an I/O error occurs
     */
    public static Character loadCharacter(String characterName) throws IOException {
        String fileName = sanitizeFileName(characterName);
        String filePath = SAVE_DIRECTORY + File.separator + fileName + ".json";

        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }

        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, Character.class);
        }
    }

    /**
     * Deletes a character's save file.
     *
     * @param characterName the name of the character to delete
     * @return true if the file was deleted, false otherwise
     */
    public static boolean deleteCharacter(String characterName) {
        String fileName = sanitizeFileName(characterName);
        String filePath = SAVE_DIRECTORY + File.separator + fileName + ".json";

        return new File(filePath).delete();
    }

    /**
     * Checks if a character save exists.
     *
     * @param characterName the name of the character to check
     * @return true if the save file exists, false otherwise
     */
    public static boolean characterExists(String characterName) {
        String fileName = sanitizeFileName(characterName);
        String filePath = SAVE_DIRECTORY + File.separator + fileName + ".json";

        return new File(filePath).exists();
    }

    /**
     * Sanitizes a character name to be used as a file name.
     * Removes or replaces invalid file name characters.
     *
     * @param name the character name
     * @return a sanitized file name
     */
    private static String sanitizeFileName(String name) {
        String normalized = Normalizer.normalize(name, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");

        return normalized.replaceAll("[^a-zA-Z0-9._-]", "_");
    }
}