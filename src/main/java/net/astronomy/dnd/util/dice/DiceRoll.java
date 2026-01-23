package net.astronomy.dnd.util.dice;


import java.util.Arrays;
import java.util.Random;

public class DiceRoll {
    private int[] roll;

    public DiceRoll(int[] roll) {
        this.roll = Arrays.copyOf(roll, size());
    }

    public static DiceRoll roll(Dices.Dice dice, int times) {
        int[] roll = new int[times];
        Random random = new Random();

        for (int i = 0; i < times; i++) {
            roll[i] = random.nextInt(dice.getSides()) + 1;
        }

        return new DiceRoll(roll);
    }

    public int getRoll(int i) {
        return roll[i];
    }

    public int size() {
        return roll.length;
    }

    public int total() {
        int t = 0;
        for (int i = 0; i < size(); i++) {
            t += roll[i];
        }

        return t;
    }

    public DiceRoll sort() {
        int[] sortedRolls = Arrays.copyOf(this.roll, size());

        Arrays.sort(sortedRolls);

        return new DiceRoll(sortedRolls);
    }
}
