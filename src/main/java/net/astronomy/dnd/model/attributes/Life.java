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

    private Dice hitDice;
    private int maxLifePoints;
    private int currentLifePoints;
    private int armorClass;
    private int initiative;
    private double speed;
    private List<Integer> hpPerLevel = new ArrayList<>();

    // Transient — not serialized, must be restored after loading from JSON
    private transient CharacterClass characterClass;
    private transient Modifier modifiers;
    private transient Inventory inventory;

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
     * Restores transient fields after deserialization.
     * Must be called by Session.loadCharacter() before any gameplay operations.
     *
     * @param characterClass the character's class
     * @param modifiers      the character's computed modifiers
     * @param inventory      the character's inventory
     */
    public void restoreTransients(CharacterClass characterClass, Modifier modifiers, Inventory inventory) {
        this.characterClass = characterClass;
        this.modifiers = modifiers;
        this.inventory = inventory;
    }

    /**
     * Returns true if transient fields have been restored.
     * Useful for defensive checks.
     */
    public boolean isTransientsRestored() {
        return modifiers != null && characterClass != null && inventory != null;
    }

    public void onLevelUp() {
        if (modifiers == null) {
            throw new IllegalStateException(
                    "Life.modifiers is null — call restoreTransients() after loading from JSON before modifying level.");
        }
        int gainedHp = DiceRoll.roll(hitDice, 1).total() + modifiers.getConstitution();
        if (gainedHp < 1) gainedHp = 1;

        hpPerLevel.add(gainedHp);
        maxLifePoints += gainedHp;
        currentLifePoints += gainedHp;
    }

    public void onLevelDown() {
        if (hpPerLevel.isEmpty()) return;

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

    public void setMaxLifePoints(int newMax) {
        if (newMax < 1) throw new IllegalArgumentException("Max life points must be at least 1.");
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