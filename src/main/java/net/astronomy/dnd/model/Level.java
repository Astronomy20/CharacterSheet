package net.astronomy.dnd.model;

/**
 * Character's level and experience progression.
 * Handles experience gain, level-ups, and proficiency bonus calculation.
 */
public class Level {

    private int level;
    private int experiencePoints;

    /**
     * Cumulative experience required to reach each level (1–20).
     * Index corresponds to the level number - 1.
     */
    private static final int[] CUMULATIVE_XP_TABLE = {
            0,          // Level 1
            300,        // Level 2
            900,        // Level 3
            2700,       // Level 4
            6500,       // Level 5
            14000,      // Level 6
            23000,      // Level 7
            34000,      // Level 8
            48000,      // Level 9
            64000,      // Level 10
            85000,      // Level 11
            100000,     // Level 12
            120000,     // Level 13
            140000,     // Level 14
            165000,     // Level 15
            195000,     // Level 16
            225000,     // Level 17
            265000,     // Level 18
            305000,     // Level 19
            355000      // Level 20
    };

    /**
     * Creates a Level instance with the given level and experience points.
     *
     * @param level the character's current level (minimum 1)
     * @param experiencePoints the current experience points
     */
    public Level(int level, int experiencePoints) {
        if (level < 1) {
            throw new IllegalArgumentException("Level must be greater than or equal to 1.");
        }
        if (experiencePoints < 0) {
            throw new IllegalArgumentException("Experience points cannot be negative.");
        }

        this.level = level;
        this.experiencePoints = experiencePoints;
    }

    /**
     * Adds experience points and levels up the character if enough XP is gained.
     *
     * @param experiencePoints the amount of XP to add
     */
    public void addExperiencePoints(int experiencePoints) {
        this.experiencePoints += experiencePoints;

        if (this.experiencePoints >= CUMULATIVE_XP_TABLE[this.level]) {
            levelUp();
        }
    }

    /**
     * Increases the character's level by one and adjusts experience points.
     */
    public void levelUp() {
        this.level += 1;
        this.experiencePoints -= CUMULATIVE_XP_TABLE[this.level - 1];
    }

    /**
     * Returns the experience points required to reach a specific level.
     *
     * @param level the target level (1–20)
     * @return required cumulative XP for the given level
     */
    public int getXPRequiredForLevel(int level) {
        if (level < 1 || level > 20) {
            throw new IllegalArgumentException("Level must be between 1 and 20.");
        }

        return CUMULATIVE_XP_TABLE[level];
    }

    /**
     * Calculates the proficiency bonus for a given level.
     *
     * @param level the character level (1–20)
     * @return the proficiency bonus
     */
    public int getProficiencyBonus(int level) {
        if (level < 1 || level > 20) {
            throw new IllegalArgumentException("Level must be between 1 and 20.");
        }

        return 2 + (int) Math.floor((level - 1) / 4.0);
    }

    /**
     * Returns the current character level.
     *
     * @return current level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Returns the current experience points.
     *
     * @return current XP
     */
    public int getExperiencePoints() {
        return experiencePoints;
    }
}