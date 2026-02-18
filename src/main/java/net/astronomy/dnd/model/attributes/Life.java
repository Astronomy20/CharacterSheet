package net.astronomy.dnd.model.attributes;

import net.astronomy.dnd.model.enums.attributes.CharacterClass;
import net.astronomy.dnd.model.enums.attributes.Race;
import net.astronomy.dnd.util.dice.DiceRoll;
import net.astronomy.dnd.util.dice.Dices.Dice;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a character's life stats.
 * Tracks hit points, armor class, initiative, and speed.
 */
public class Life {

    /** Character's hit dice */
    private Dice hitDice;

    /** Max life points */
    private int maxLifePoints;

    /** Current life points */
    private int currentLifePoints;

    /** Current armor class */
    private int armorClass;

    /** Initiative modifier */
    private int initiative;

    /** Movement speed */
    private double speed;

    /** HP gained per level (excluding level 1 base HP) */
    private List<Integer> hpPerLevel = new ArrayList<>();

    /** Character class (transient) */
    private transient CharacterClass characterClass;

    /** Attribute modifiers (transient) */
    private transient Modifier modifiers;

    /** Inventory (transient) */
    private transient Inventory inventory;

    /**
     * Creates a Life instance based on level, race, class, modifiers, and inventory.
     */
    public Life(Level level,
                Race race,
                CharacterClass characterClass,
                Modifier modifiers,
                Inventory inventory) {

        this.hitDice = characterClass.getHitDice();
        this.characterClass = characterClass;
        this.modifiers = modifiers;
        this.inventory = inventory;

        int baseHp = hitDice.getSides() + modifiers.getConstitution();

        this.maxLifePoints = baseHp;
        this.currentLifePoints = baseHp;

        this.armorClass = calculateArmorClass();
        this.initiative = modifiers.getDexterity();
        this.speed = race.getSpeed();

        for (int i = 1; i < level.getLevel(); i++) {
            onLevelUp();
        }
    }

    /**
     * Called on level-up.
     * Rolls HP and stores the gained amount.
     */
    public void onLevelUp() {
        int gainedHp = DiceRoll.roll(hitDice, 1).total() + modifiers.getConstitution();
        if (gainedHp < 1) gainedHp = 1;

        hpPerLevel.add(gainedHp);
        maxLifePoints += gainedHp;
        currentLifePoints += gainedHp;
    }

    /**
     * Called on level-down.
     * Removes the last gained HP deterministically.
     */
    public void onLevelDown() {
        if (hpPerLevel.isEmpty()) {
            return;
        }

        int removedHp = hpPerLevel.removeLast();
        maxLifePoints -= removedHp;

        if (currentLifePoints > maxLifePoints) {
            currentLifePoints = maxLifePoints;
        }
    }

    private int calculateArmorClass() {
        return 10
                + modifiers.getDexterity()
                + inventory.getWearingArmorDefence(inventory.getWornArmor(), modifiers)
                + inventory.getEquippedShieldDefence(inventory.getEquippedShield(), modifiers);
    }

    public void updateArmorClass() {
        this.armorClass = calculateArmorClass();
    }

    /**
     * Sets the maximum life points.
     * Adjusts current life points if they exceed the new maximum.
     *
     * @param newMax the new maximum HP
     */
    public void setMaxLifePoints(int newMax) {
        if (newMax < 1) {
            throw new IllegalArgumentException("Max life points must be at least 1.");
        }

        this.maxLifePoints = newMax;

        if (this.currentLifePoints > this.maxLifePoints) {
            this.currentLifePoints = this.maxLifePoints;
        }
    }


    public void setCurrentLifePoints(int value) {
        this.currentLifePoints = Math.max(0, Math.min(value, maxLifePoints));
    }

    public void addLifePoints(int points) {
        setCurrentLifePoints(this.currentLifePoints + points);
    }

    public void removeLifePoints(int points) {
        setCurrentLifePoints(this.currentLifePoints - points);
    }

    public void updateSpeed(double newSpeed) {
        this.speed = newSpeed;
    }

    /**
     * Returns the list of HP gained per level (excluding level 1 base HP).
     * The list is immutable to prevent external modification.
     *
     * @return list of integers representing HP gained at each level-up
     */
    public List<Integer> getHpPerLevel() {
        return List.copyOf(hpPerLevel);
    }

    public Dice getHitDice() {
        return hitDice;
    }

    public int getMaxLifePoints() {
        return maxLifePoints;
    }

    public int getCurrentLifePoints() {
        return currentLifePoints;
    }

    public int getArmorClass() {
        return armorClass;
    }

    public int getInitiative() {
        return initiative;
    }

    public double getSpeed() {
        return speed;
    }
}