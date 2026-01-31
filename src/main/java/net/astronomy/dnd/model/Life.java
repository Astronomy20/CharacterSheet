package net.astronomy.dnd.model;

import net.astronomy.dnd.model.enums.attributes.CharacterClass;
import net.astronomy.dnd.model.enums.attributes.Race;
import net.astronomy.dnd.util.dice.DiceRoll;
import net.astronomy.dnd.util.dice.Dices.Dice;

/**
 * Represents a character's life stats.
 * Tracks hit points, armor class, initiative, and speed.
 */
public class Life {
    /** Character's hit dice */
    private Dice hitDice;

    /** Current life points */
    private int lifePoints;

    /** Current armor class */
    private int armorClass;

    /** Initiative modifier */
    private int initiative;

    /** Movement speed */
    private double speed;

    /** Character class (transient) */
    private transient CharacterClass characterClass;

    /** Attribute modifiers (transient) */
    private transient Modifier modifiers;

    /** Inventory (transient) */
    private transient Inventory inventory;

    /**
     * Creates a Life instance based on level, race, class, modifiers, and inventory.
     */
    public Life(Level level, Race race, CharacterClass characterClass, Modifier modifiers, Inventory inventory) {
        this.hitDice = characterClass.getHitDice();
        this.lifePoints = characterClass.getHitDice().getSides() + modifiers.getConstitution();
        this.armorClass = 10 + modifiers.getDexterity()
                + inventory.getWearingArmorDefence(inventory.getWornArmor(), modifiers)
                + inventory.getEquippedShieldDefence(inventory.getEquippedShield(), modifiers);
        this.initiative = modifiers.getDexterity();
        this.speed = race.getSpeed();

        this.characterClass = characterClass;
        this.modifiers = modifiers;
        this.inventory = inventory;
    }

    /** Called on level-up to add life points based on hit dice and Constitution */
    public void onLevelUp() {
        addLifePoints(
                DiceRoll.roll(characterClass.getHitDice(), 1).total()
                        + modifiers.getConstitution()
        );
    }

    /** Adds life points */
    public void addLifePoints(int points) {
        this.lifePoints += points;
    }

    /** Updates armor class based on Dexterity, armor, and shield */
    public void updateArmorClass() {
        this.armorClass = 10 + this.modifiers.getDexterity()
                + this.inventory.getWearingArmorDefence(this.inventory.getWornArmor(), this.modifiers)
                + this.inventory.getEquippedShieldDefence(this.inventory.getEquippedShield(), this.modifiers);
    }

    /** Returns hit dice */
    public Dice getHitDice() {
        return hitDice;
    }

    /** Returns current life points */
    public int getLifePoints() {
        return lifePoints;
    }

    /** Returns current armor class */
    public int getArmorClass() {
        return armorClass;
    }

    /** Returns initiative */
    public int getInitiative() {
        return initiative;
    }

    /** Returns movement speed */
    public double getSpeed() {
        return speed;
    }
}