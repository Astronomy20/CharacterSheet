package net.astronomy.dnd.model;

import net.astronomy.dnd.model.enums.attributes.CharacterClass;
import net.astronomy.dnd.model.enums.attributes.Race;
import net.astronomy.dnd.util.LevelUpListener;
import net.astronomy.dnd.util.dice.DiceRoll;
import net.astronomy.dnd.util.dice.Dices.Dice;

public class Life implements LevelUpListener {
    private Dice hitDice;
    private int lifePoints;
    private int armorClass;
    private int initiative;
    private double speed;

    private CharacterClass characterClass;
    private Modifier modifiers;

    public Life(Level level, Race race, CharacterClass characterClass, Modifier modifiers) {
        this.hitDice = characterClass.getHitDice();
        this.lifePoints =  characterClass.getHitDice().getSides() + modifiers.getConstitution();
        this.armorClass = 10 + modifiers.getDexterity();   // TODO: Add armor defence
        this.initiative = modifiers.getDexterity();
        this.speed = race.getSpeed();

        this.characterClass = characterClass;
        this.modifiers = modifiers;

        level.addLevelUpListener(this);
    }

    @Override
    public void onLevelUp() {
        addHitPoints(
                DiceRoll.roll(characterClass.getHitDice(), 1).total()
                        + modifiers.getConstitution()
        );
    }

    public void addHitPoints(int points) {
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
