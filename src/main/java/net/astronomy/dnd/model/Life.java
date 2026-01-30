package net.astronomy.dnd.model;

import net.astronomy.dnd.model.enums.attributes.CharacterClass;
import net.astronomy.dnd.model.enums.attributes.Race;
import net.astronomy.dnd.util.dice.DiceRoll;
import net.astronomy.dnd.util.dice.Dices.Dice;

public class Life{
    private Dice hitDice;
    private int lifePoints;
    private int armorClass;
    private int initiative;
    private double speed;

    public Life(Level level, Race race, CharacterClass characterClass, Modifier modifiers) {
        this.hitDice = characterClass.getHitDice();
        this.lifePoints =  characterClass.getHitDice().getSides() + modifiers.getConstitution();
        this.armorClass = 10 + modifiers.getDexterity();   // TODO: Add armor defence
        this.initiative = modifiers.getDexterity();
        this.speed = race.getSpeed();
    }

    public void onLevelUp(CharacterClass characterClass, Modifier modifiers) {
        addLifePoints(
                DiceRoll.roll(characterClass.getHitDice(), 1).total()
                        + modifiers.getConstitution()
        );
    }

    public void addLifePoints(int points) {
        this.lifePoints += points;
    }

    public Dice getHitDice() {
        return hitDice;
    }

    public int getLifePoints() {
        return lifePoints;
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