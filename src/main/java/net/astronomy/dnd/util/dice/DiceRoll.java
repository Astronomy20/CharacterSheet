package net.astronomy.dnd.util.dice;

import java.util.Arrays;
import java.util.Random;

/**
 * Represents a series of dice rolls and provides utility methods
 * to roll, access, total, and sort the results.
 */
public class DiceRoll {
    /** Array storing the results of the dice rolls */
    private int[] roll;

    /**
     * Constructs a DiceRoll object with the specified roll results.
     *
     * @param roll an array of integers representing dice roll results
     */
    public DiceRoll(int[] roll) {
        this.roll = Arrays.copyOf(roll, roll.length);
    }

    /**
     * Rolls a specified dice a certain number of times.
     *
     * @param dice the type of dice to roll
     * @param times the number of times to roll the dice
     * @return a new DiceRoll containing the results
     */
    public static DiceRoll roll(Dices.Dice dice, int times) {
        int[] roll = new int[times];
        Random random = new Random();

        for (int i = 0; i < times; i++) {
            roll[i] = random.nextInt(dice.getSides()) + 1;
        }

        return new DiceRoll(roll);
    }

    /**
     * Returns the result of a specific roll.
     *
     * @param i the index of the roll to retrieve
     * @return the value of the roll at the given index
     */
    public int getRoll(int i) {
        return roll[i];
    }

    /**
     * Returns the number of rolls in this DiceRoll.
     *
     * @return the number of dice rolled
     */
    public int size() {
        return roll.length;
    }

    /**
     * Returns the total sum of all dice rolls.
     *
     * @return the sum of all roll results
     */
    public int total() {
        int t = 0;
        for (int i = 0; i < size(); i++) {
            t += roll[i];
        }

        return t;
    }

    /**
     * Returns a new DiceRoll object with the rolls sorted in ascending order.
     *
     * @return a new DiceRoll containing the sorted rolls
     */
    public DiceRoll sort() {
        int[] sortedRolls = Arrays.copyOf(this.roll, size());

        Arrays.sort(sortedRolls);

        return new DiceRoll(sortedRolls);
    }
}