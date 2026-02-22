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

    /**
     * Saves a character atomically — writes to a .tmp file first,
     * then renames it over the real .json only after a successful write.
     * This prevents the save file from being wiped if anything goes wrong mid-write.
     */
    public static void saveCharacter(Character character) throws IOException {
        String fileName  = sanitizeFileName(character.getName());
        File   finalFile = new File(SAVE_DIRECTORY + File.separator + fileName + ".json");
        File   tempFile  = new File(SAVE_DIRECTORY + File.separator + fileName + ".tmp");

        try (FileWriter writer = new FileWriter(tempFile)) {
            gson.toJson(character, writer);
        }

        if (finalFile.exists()) finalFile.delete();
        if (!tempFile.renameTo(finalFile)) {
            throw new IOException("Failed to finalize save file for: " + character.getName());
        }
    }

    /**
     * Loads a character and restores transient fields that Gson skips.
     * Without this, Life.modifiers / characterClass / inventory remain null
     * and any level or XP change will throw NullPointerException.
     */
    public static Character loadCharacter(String characterName) throws IOException {
        String fileName  = sanitizeFileName(characterName);
        File   finalFile = new File(SAVE_DIRECTORY + File.separator + fileName + ".json");
        File   tempFile  = new File(SAVE_DIRECTORY + File.separator + fileName + ".tmp");

        if (tempFile.exists()) tempFile.delete();

        if (!finalFile.exists()) return null;

        Character character;
        try (FileReader reader = new FileReader(finalFile)) {
            character = gson.fromJson(reader, Character.class);
        }

        character.restoreTransients(
                character.getName(),
                character.getLevel(),
                character.getRace(),
                character.getCharacterClass(),
                character.getBackground(),
                character.getAlignment(),
                character.getAbilities(),
                character.getModifiers(),
                character.getSavingThrows(),
                character.getSkills(),
                character.getInventory(),
                character.getCurrency(),
                character.getLanguages(),
                character.getLife()
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