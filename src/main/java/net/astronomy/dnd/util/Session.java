package net.astronomy.dnd.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.astronomy.dnd.model.Character;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;

/**
 * Utility class for saving and loading D&D characters to/from JSON files.
 */
public class Session {

    private static final String SAVE_DIRECTORY = "saves";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    static {
        File savesDir = new File(SAVE_DIRECTORY);
        if (!savesDir.exists()) {
            savesDir.mkdirs();
        }
    }

    public static void saveCharacter(Character character) throws IOException {
        String filePath = SAVE_DIRECTORY + File.separator + sanitizeFileName(character.getName()) + ".json";
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(character, writer);
        }
    }

    /**
     * Loads a character and restores transient fields that Gson skips.
     * Without this, Life.modifiers / characterClass / inventory remain null
     * and any level or XP change will throw NullPointerException.
     */
    public static Character loadCharacter(String characterName) throws IOException {
        String filePath = SAVE_DIRECTORY + File.separator + sanitizeFileName(characterName) + ".json";

        File file = new File(filePath);
        if (!file.exists()) return null;

        Character character;
        try (FileReader reader = new FileReader(filePath)) {
            character = gson.fromJson(reader, Character.class);
        }

        character.getLife().restoreTransients(
                character.getCharacterClass(),
                character.getModifiers(),
                character.getInventory()
        );

        return character;
    }

    public static boolean deleteCharacter(String characterName) {
        String filePath = SAVE_DIRECTORY + File.separator + sanitizeFileName(characterName) + ".json";
        return new File(filePath).delete();
    }

    public static boolean characterExists(String characterName) {
        String filePath = SAVE_DIRECTORY + File.separator + sanitizeFileName(characterName) + ".json";
        return new File(filePath).exists();
    }

    private static String sanitizeFileName(String name) {
        String normalized = Normalizer.normalize(name, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
        return normalized.replaceAll("[^a-zA-Z0-9._-]", "_");
    }
}