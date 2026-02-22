package net.astronomy.dnd.model.attributes;

/**
 * Represents a character's level and experience.
 * Handles XP gain, leveling up, and proficiency bonus calculation.
 */
public class Level {

    private int level;
    private int experiencePoints;

    private static final int[] CUMULATIVE_XP_TABLE = {
            0,       // Level 1
            300,     // Level 2
            900,     // Level 3
            2700,    // Level 4
            6500,    // Level 5
            14000,   // Level 6
            23000,   // Level 7
            34000,   // Level 8
            48000,   // Level 9
            64000,   // Level 10
            85000,   // Level 11
            100000,  // Level 12
            120000,  // Level 13
            140000,  // Level 14
            165000,  // Level 15
            195000,  // Level 16
            225000,  // Level 17
            265000,  // Level 18
            305000,  // Level 19
            355000   // Level 20
    };

    public Level(int level, int experiencePoints) {
        if (level < 1)          throw new IllegalArgumentException("Level must be >= 1.");
        if (experiencePoints < 0) throw new IllegalArgumentException("XP cannot be negative.");
        this.level = level;
        this.experiencePoints = experiencePoints;
    }

    public void addExperiencePoints(int experiencePoints, Life life) {
        this.experiencePoints += experiencePoints;
        while (this.level < 20 && this.experiencePoints >= CUMULATIVE_XP_TABLE[this.level]) {
            levelUp(life);
        }
    }

    public void removeExperiencePoints(int experiencePoints, Life life) {
        this.experiencePoints -= experiencePoints;

        while (this.level > 1 && this.experiencePoints < CUMULATIVE_XP_TABLE[this.level - 1]) {
            levelDown(life);
        }
    }

    public void levelUp(Life life) {
        if (this.level >= 20) return;
        this.level += 1;
        this.experiencePoints -= CUMULATIVE_XP_TABLE[this.level - 1];

        if (this.experiencePoints < 0) this.experiencePoints = 0;

        life.onLevelUp();
    }

    public void levelDown(Life life) {
        if (this.level <= 1) return;
        this.level -= 1;
        this.experiencePoints = CUMULATIVE_XP_TABLE[this.level] + this.experiencePoints;

        if (this.experiencePoints < 0) this.experiencePoints = 0;

        life.onLevelDown();
    }

    /**
     * Directly sets the character level, calling onLevelUp/onLevelDown
     * the correct number of times and incrementing this.level each step.
     */
    public void setLevel(int newLevel, Life life) {
        if (newLevel < 1 || newLevel > 20)
            throw new IllegalArgumentException("Level must be between 1 and 20.");

        while (this.level < newLevel) {
            this.level++;
            life.onLevelUp();
        }

        while (this.level > newLevel) {
            this.level--;
            life.onLevelDown();
        }

        this.experiencePoints = 0;
    }

    public int getXPRequiredForLevel(int level) {
        if (level < 1 || level > 20) throw new IllegalArgumentException("Level must be 1-20.");
        return CUMULATIVE_XP_TABLE[level - 1];
    }

    public int getProficiencyBonus(int level) {
        if (level < 1 || level > 20) throw new IllegalArgumentException("Level must be 1-20.");
        return 2 + (int) Math.floor((level - 1) / 4.0);
    }

    public int getLevel()            { return level; }
    public int getExperiencePoints() { return experiencePoints; }
}