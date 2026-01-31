package net.astronomy.dnd.model;

/**
 * Represents a character's level and experience.
 * Handles XP gain, leveling up, and proficiency bonus calculation.
 */
public class Level {
    /** Current character level */
    private int level;

    /** Current experience points */
    private int experiencePoints;

    /**
     * Cumulative XP required for each level (1–20).
     * Index = level - 1
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
     * Creates a Level instance.
     *
     * @param level starting level (≥1)
     * @param experiencePoints starting XP (≥0)
     */
    public Level(int level, int experiencePoints) {
        if (level < 1) throw new IllegalArgumentException("Level must be ≥ 1.");
        if (experiencePoints < 0) throw new IllegalArgumentException("XP cannot be negative.");

        this.level = level;
        this.experiencePoints = experiencePoints;
    }

    /**
     * Adds XP and levels up if threshold is reached.
     *
     * @param life character life instance
     * @param experiencePoints XP to add
     */
    public void addExperiencePoints(Life life, int experiencePoints) {
        this.experiencePoints += experiencePoints;

        while (this.level < 20 && this.experiencePoints >= CUMULATIVE_XP_TABLE[this.level]) {
            levelUp(life);
        }
    }

    /**
     * Increases level by one and adjusts XP.
     *
     * @param life character life instance
     */
    public void levelUp(Life life) {
        this.level += 1;
        this.experiencePoints -= CUMULATIVE_XP_TABLE[this.level - 1];

        if (this.experiencePoints < 0) this.experiencePoints = 0;

        life.onLevelUp();
    }

    /**
     * Returns XP required for a specific level.
     *
     * @param level target level (1–20)
     * @return required XP
     */
    public int getXPRequiredForLevel(int level) {
        if (level < 1 || level > 20) throw new IllegalArgumentException("Level must be 1–20.");
        return CUMULATIVE_XP_TABLE[level];
    }

    /**
     * Returns proficiency bonus for a given level.
     *
     * @param level character level (1–20)
     * @return proficiency bonus
     */
    public int getProficiencyBonus(int level) {
        if (level < 1 || level > 20) throw new IllegalArgumentException("Level must be 1–20.");
        return 2 + (int) Math.floor((level - 1) / 4.0);
    }

    /** Returns current level */
    public int getLevel() {
        return level;
    }

    /** Returns current XP */
    public int getExperiencePoints() {
        return experiencePoints;
    }
}